package ru.tinkoff.lecture.testing.ext;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class LoggingExtension implements BeforeEachCallback, AfterEachCallback {

    private final Map<String, Long> executionStartByUniqueId = new ConcurrentHashMap<>();

    @Override
    public void beforeEach(ExtensionContext context) {
        executionStartByUniqueId.put(context.getUniqueId(), System.nanoTime());
        System.out.printf("\t\tВыполняем тест %s\n", context.getDisplayName());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        var execTime = System.nanoTime() - executionStartByUniqueId.getOrDefault(context.getUniqueId(), 0L);
        System.out.printf("\t\tВыполнили тест %s, время выполнения - %d нс\n", context.getDisplayName(), execTime);
    }

}
