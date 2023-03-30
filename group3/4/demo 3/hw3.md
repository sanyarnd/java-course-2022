# Домашнее задание 3

ДЗ посвящено работе с HTTP сервером, HTTP клиентами, OpenAPI и настройке фоновых задач.

## Задача 1

В прошлом задании мы подключили Spring Boot Web Starter, который создаёт и запускает веб-сервер вместе с приложением,
но сервер не обрабатывает информацию ни по каким маршрутам.

1. Реализуйте контроллеры приложений по OpenAPI-спецификации: [bot](#bot-api), [scrapper](#scrapper-api).
   Так как реальных данных пока нет, то достаточно возвращать заглушки.
2. Опишите DTO-объекты в отдельном пакете в формате `*Request`/`*Response`.
3. Для обработки ошибок используйте `@RestControllerAdvice`.

_Подсказка: для более комфортного чтения спецификаций можно использовать https://editor.swagger.io/_

## Задача 2

Для облегчения тестирования добавим автоматическое создание OpenAPI из вашего кода.
После этого вы сможете тестировать ваши endpoint'ы через Swagger UI.

1. Добавьте зависимость `org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2` в модули `bot` и `scrapper`.
2. Сконфигурируйте модуль (через `application.properties/yaml`) таким образом, чтобы по
   адресу `http://localhost:8080/swagger-ui` был доступен Swagger UI.
   Описание конфигурации можно найти на сайте https://springdoc.org/v2/.

## Задача 3

Для получения информации об обновлениях нам потребуются HTTP-клиенты для GitHub и StackOverflow в модуле `scrapper`.
Создание клиентов в Spring Boot возможно 2 способами:
[WebClient](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#io.rest-client.webclient) и
[HttpExchange](https://docs.spring.io/spring-framework/docs/6.0.0/reference/html/integration.html#rest-http-interface).

После создания зарегистрируйте клиентов как бины в отдельном файле `ClientConfiguration` при помощи аннотации `@Bean`.

Ограничения:

1. Запрещается использовать `RestTemplate` или готовые SDK для доступа к API.
2. При создании клиента обязательно должна быть возможность указать базовый URL.
   При этом если он не указывается, то должен использоваться URL по умолчанию.
3. В `*Response`-классах для хранения времени должен использоваться класс `OffsetDateTime`.

_Подсказка: часто API возвращает слишком много данных.
Можно пропустить их объявление в DTO-классе, тогда лишние поля будут проигнорированы._

### GitHub

Создайте клиент `GitHubClient` любым удобным для вас способом.
Вам потребуется изучить [API-документацию](https://docs.github.com/en/rest) и найти нужный метод для получения
информации из репозитория, фактически у клиента будет 1 метод:

```java
RepoResponse fetchRepo(String user,String repo)
```

### StackOverflow

Создайте клиент `StackOverflowClient` любым удобным для вас способом.
Вам потребуется изучить [API-документацию](https://api.stackexchange.com/docs) и найти нужный метод для получения
информации о вопросе, фактически у клиента будет 1 метод:

```java
QuestionResponse fetchQuestion(long id)
```

## Задача 4

В работе web-сервисов приходится прибегать к механизму фоновых задач.
Почти всегда это какие-то простые сценарии, не требующие сложной логики, например, контроль количества запусков,
таймауты или машина состояний (см. BPM).
Так и в нашей задаче -- требуется в какой-то интервал времени ходить по ссылкам и проверять не появилось ли обновлений.

1. Создайте класс `LinkUpdaterScheduler` с единственным методом `update` и добавьте простое логирование в тело метода.
2. Включите поддержку `@Schedule`-аннотаций при помощи `@EnableScheduling`.
3. Сделайте метод `update` запланированным (`@Scheduled`).
4. Добавьте в `ApplicationConfig` поле `scheduler` типа `Scheduler` с единственным полем `Duration interval`.
5. В `application.properties/yaml` создайте ключ `app.scheduler.interval`.
6. Сделайте так, чтобы аннотация `@Scheduled` инициализировала значение `delay` из конфигурации.

_Подсказка: `@Scheduled`-аннотация поддерживает `SpEL` -- возможность обращения к контексту Spring через специальный
синтаксис, например: `@Scheduled(fixedDelayString = "#{@beanName}")`._

### Scrapper API

```json
{
    "openapi": "3.0.1",
    "info": {
        "title": "Scrapper API",
        "version": "1.0.0",
        "contact": {
            "name": "Alexander Biryukov",
            "url": "https://github.com"
        }
    },
    "paths": {
        "/tg-chat/{id}": {
            "post": {
                "summary": "Зарегистрировать чат",
                "parameters": [
                    {
                        "name": "id",
                        "in": "path",
                        "required": true,
                        "schema": {
                            "type": "integer",
                            "format": "int64"
                        }
                    }
                ],
                "responses": {
                    "200": {
                        "description": "Чат зарегистрирован",
                        "content": {
                            "application/json": {
                                "schema": {
                                    "$ref": "#/components/schemas/LinkResponse"
                                }
                            }
                        }
                    },
                    "400": {
                        "description": "Некорректные параметры запроса",
                        "content": {
                            "application/json": {
                                "schema": {
                                    "$ref": "#/components/schemas/ApiErrorResponse"
                                }
                            }
                        }
                    }
                }
            },
            "delete": {
                "summary": "Удалить чат",
                "parameters": [
                    {
                        "name": "id",
                        "in": "path",
                        "required": true,
                        "schema": {
                            "type": "integer",
                            "format": "int64"
                        }
                    }
                ],
                "responses": {
                    "200": {
                        "description": "Чат успешно удалён",
                        "content": {
                            "application/json": {
                                "schema": {
                                    "$ref": "#/components/schemas/LinkResponse"
                                }
                            }
                        }
                    },
                    "400": {
                        "description": "Некорректные параметры запроса",
                        "content": {
                            "application/json": {
                                "schema": {
                                    "$ref": "#/components/schemas/ApiErrorResponse"
                                }
                            }
                        }
                    },
                    "404": {
                        "description": "Чат не существует",
                        "content": {
                            "application/json": {
                                "schema": {
                                    "$ref": "#/components/schemas/ApiErrorResponse"
                                }
                            }
                        }
                    }
                }
            }
        },
        "/links": {
            "get": {
                "summary": "Получить все отслеживание ссылки",
                "parameters": [
                    {
                        "name": "Tg-Chat-Id",
                        "in": "header",
                        "required": true,
                        "schema": {
                            "type": "integer",
                            "format": "int64"
                        }
                    }
                ],
                "responses": {
                    "200": {
                        "description": "Ссылка успешно убрана",
                        "content": {
                            "application/json": {
                                "schema": {
                                    "$ref": "#/components/schemas/ListLinksResponse"
                                }
                            }
                        }
                    },
                    "400": {
                        "description": "Некорректные параметры запроса",
                        "content": {
                            "application/json": {
                                "schema": {
                                    "$ref": "#/components/schemas/ApiErrorResponse"
                                }
                            }
                        }
                    }
                }
            },
            "post": {
                "summary": "Добавить отслеживание ссылки",
                "parameters": [
                    {
                        "name": "Tg-Chat-Id",
                        "in": "header",
                        "required": true,
                        "schema": {
                            "type": "integer",
                            "format": "int64"
                        }
                    }
                ],
                "requestBody": {
                    "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/AddLinkRequest"
                            }
                        }
                    },
                    "required": true
                },
                "responses": {
                    "200": {
                        "description": "Ссылка успешно добавлена",
                        "content": {
                            "application/json": {
                                "schema": {
                                    "$ref": "#/components/schemas/LinkResponse"
                                }
                            }
                        }
                    },
                    "400": {
                        "description": "Некорректные параметры запроса",
                        "content": {
                            "application/json": {
                                "schema": {
                                    "$ref": "#/components/schemas/ApiErrorResponse"
                                }
                            }
                        }
                    }
                }
            },
            "delete": {
                "summary": "Убрать отслеживание ссылки",
                "parameters": [
                    {
                        "name": "Tg-Chat-Id",
                        "in": "header",
                        "required": true,
                        "schema": {
                            "type": "integer",
                            "format": "int64"
                        }
                    }
                ],
                "requestBody": {
                    "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/RemoveLinkRequest"
                            }
                        }
                    },
                    "required": true
                },
                "responses": {
                    "200": {
                        "description": "Ссылка успешно убрана",
                        "content": {
                            "application/json": {
                                "schema": {
                                    "$ref": "#/components/schemas/LinkResponse"
                                }
                            }
                        }
                    },
                    "400": {
                        "description": "Некорректные параметры запроса",
                        "content": {
                            "application/json": {
                                "schema": {
                                    "$ref": "#/components/schemas/ApiErrorResponse"
                                }
                            }
                        }
                    },
                    "404": {
                        "description": "Ссылка не найдена",
                        "content": {
                            "application/json": {
                                "schema": {
                                    "$ref": "#/components/schemas/ApiErrorResponse"
                                }
                            }
                        }
                    }
                }
            }
        }
    },
    "components": {
        "schemas": {
            "LinkResponse": {
                "type": "object",
                "properties": {
                    "id": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "url": {
                        "type": "string",
                        "format": "uri"
                    }
                }
            },
            "ApiErrorResponse": {
                "type": "object",
                "properties": {
                    "description": {
                        "type": "string"
                    },
                    "code": {
                        "type": "string"
                    },
                    "exceptionName": {
                        "type": "string"
                    },
                    "exceptionMessage": {
                        "type": "string"
                    },
                    "stacktrace": {
                        "type": "array",
                        "items": {
                            "type": "string"
                        }
                    }
                }
            },
            "AddLinkRequest": {
                "type": "object",
                "properties": {
                    "link": {
                        "type": "string",
                        "format": "uri"
                    }
                }
            },
            "ListLinksResponse": {
                "type": "object",
                "properties": {
                    "links": {
                        "type": "array",
                        "items": {
                            "$ref": "#/components/schemas/LinkResponse"
                        }
                    },
                    "size": {
                        "type": "integer",
                        "format": "int32"
                    }
                }
            },
            "RemoveLinkRequest": {
                "type": "object",
                "properties": {
                    "link": {
                        "type": "string",
                        "format": "uri"
                    }
                }
            }
        }
    }
}
```

### Bot API

```json
{
    "openapi": "3.0.1",
    "info": {
        "title": "Bot API",
        "version": "1.0.0",
        "contact": {
            "name": "Alexander Biryukov",
            "url": "https://github.com"
        }
    },
    "paths": {
        "/updates": {
            "post": {
                "summary": "Отправить обновление",
                "requestBody": {
                    "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/LinkUpdate"
                            }
                        }
                    },
                    "required": true
                },
                "responses": {
                    "200": {
                        "description": "Обновление обработано"
                    },
                    "400": {
                        "description": "Некорректные параметры запроса",
                        "content": {
                            "application/json": {
                                "schema": {
                                    "$ref": "#/components/schemas/ApiErrorResponse"
                                }
                            }
                        }
                    }
                }
            }
        }
    },
    "components": {
        "schemas": {
            "ApiErrorResponse": {
                "type": "object",
                "properties": {
                    "description": {
                        "type": "string"
                    },
                    "code": {
                        "type": "string"
                    },
                    "exceptionName": {
                        "type": "string"
                    },
                    "exceptionMessage": {
                        "type": "string"
                    },
                    "stacktrace": {
                        "type": "array",
                        "items": {
                            "type": "string"
                        }
                    }
                }
            },
            "LinkUpdate": {
                "type": "object",
                "properties": {
                    "id": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "url": {
                        "type": "string",
                        "format": "uri"
                    },
                    "description": {
                        "type": "string"
                    },
                    "tgChatIds": {
                        "type": "array",
                        "items": {
                            "type": "integer",
                            "format": "int64"
                        }
                    }
                }
            }
        }
    }
}
```
