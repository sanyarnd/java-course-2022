# Домашнее задание 2

## Общая информация

Начиная с этого занятия все домашние задания будут посвящены одному проекту, т.е. связаны между собой.

В ходе заданий вы напишете 2 web-сервиса для отслеживания обновлений контента по ссылкам. В сервисе поддерживаются:

* Вопросы со StackOverflow
* Репозитории GitHub

Управление подписками (ссылками) происходит через чат с ботом в Telegram.
При новых изменениях в чат отправляется уведомление.

Сервисы будут общаться между собой как напрямую (по HTTP), так и асинхронно (очередь сообщений).
Для хранения данных будет использоваться СУБД PostgreSQL.

Примитивная схема выглядит следующим образом:

```
+-------------+                        
|  PostgreSQL |                        
+----------|--+                        
           |                           
           |                           
 +---------|--+                        
 |            |                        
 |  Scrapper  --\                      
 |            |  ---\   +-------------+
 +---------|--+      ---|             |
           |            |   RabbitMQ  |
           |HTTP      --|             |
           |        -/  +-------------+
 +---------|--+   -/                   
 |            | -/                     
 |    Bot     -/                       
 |            |                        
 +------------+                        
```

Важно!

1. Все задания делаются в одном репозитории.
2. Каждая домашняя работа должна быть оформлена как отдельный pull request.

## Задача 1

Первое задание посвящено настройке maven-проекта, добавлению зависимостей Spring Boot и загрузке ДЗ на `GitHub`.

1. Создайте пустой git-репозиторий
2. Создайте ветку `hw1` и переключитесь на неё
3. В репозитории создайте мультимодульный maven-проект со следующей структурой:
    ```
    /<git root>
    ├─ bot/
    │ ├─ pom.xml
    ├─ link-parser/
    │ ├─ pom.xml
    ├─ scrapper/
    │ ├─ pom.xml
    ├─ pom.xml
    ```

Проверьте, что команда `mvn package` успешно отрабатывает в терминале.

## Задача 2

1. Добавьте зависимости `spring-cloud=2022.0.0`, `spring-boot=3.0.1` в секцию `<dependencyManagement>` в
   корневом `pom.xml`:
    ```xml
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-dependencies</artifactId>
      <version>${spring-cloud.version}</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
    
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-dependencies</artifactId>
      <version>${spring-boot.version}</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
    ```
2. В модулях bot и scrapper сконфигурируйте запуск плагинов в секции `<build>`.:
   _Подсказка: для наследования конфигурации можно использовать `<pluginManagement>` в корневом pom.xml_
    ```xml
    <plugin>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-maven-plugin</artifactId>
      <version>${spring-boot.version}</version>
      <configuration>
          <layers>
              <enabled>true</enabled>
          </layers>
          <excludes>
              <exclude>
                  <groupId>org.projectlombok</groupId>
                  <artifactId>lombok</artifactId>
              </exclude>
          </excludes>
      </configuration>
      <executions>
          <execution>
              <goals>
                  <goal>repackage</goal>
                  <goal>build-info</goal>
              </goals>
          </execution>
      </executions>
    </plugin>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-compiler-plugin</artifactId>
      <version>${maven-compiler-plugin.version}</version>
      <configuration>
          <release>17</release>
          <parameters>true</parameters>
      </configuration>
    </plugin>
    ```
3. Подключите в модуле `bot` и `scrapper` зависимость `spring-boot-starter-web`, `spring-boot-starter-validation` и
   перечисленные технические зависимости:
    ```xml
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-devtools</artifactId>
      <scope>runtime</scope>
      <optional>true</optional>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-configuration-processor</artifactId>
      <optional>true</optional>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context-indexer</artifactId>
      <optional>true</optional>
    </dependency>
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <optional>true</optional>
    </dependency>
    ```
4. Добавьте зависимость `org.jetbrains:annotations:23.1.0` в корневой pom.xml со `scope=provided`:

Проверьте, что команда `mvn package` успешно отрабатывает в терминале.

## Задача 3

1. Создайте record с конфигурацией в
   пакетах `ru.tinkoff.edu.java.scrapper.configuration`/`ru.tinkoff.edu.java.bot.configuration`:
   _Подсказка: не забудьте проинициализировать конфигурацию в `application.properties/yaml`_
    ```java
    import jakarta.validation.constraints.NotNull;
    import org.springframework.validation.annotation.Validated;
    
    @Validated
    @ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
    public record ApplicationConfig(@NotNull String test) {}
    ```
2. Создайте класс `ScrapperApplication`/`BotApplication` с методом main в модуле scrapper в
   пакете `ru.tinkoff.edu.java.scrapper`/`ru.tinkoff.edu.java.bot` и проверьте, что он запускается:
    ```java
    @SpringBootApplication
    @EnableConfigurationProperties(ApplicationConfig.class)
    public class *Application {
      public static void main(String[] args) {
          var ctx = SpringApplication.run(ScrapperApplication.class, args);
          ApplicationConfig config = ctx.getBean(ApplicationConfig.class);
    
          System.out.println(config);
      }
    }
    ```

Проверьте, что команда `mvn package` успешно отрабатывает в терминале.

## Задание 4

1. Загрузите результаты в удаленный репозиторий на `GitHub` в ветку hw1 и создайте pull request (не забудьте настроить
   ssh-ключи).
