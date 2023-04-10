package com.example.testingworkshop;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import java.util.concurrent.atomic.AtomicBoolean;

public class BlackboxExtension implements BeforeAllCallback {

    private AtomicBoolean initialized = new AtomicBoolean(false);

    private static final Network network = Network.newNetwork();
    public static final AppContainer appContainer = new AppContainer()
        .withNetwork(network);
    public static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:14"))
        .withNetwork(network)
        .withExposedPorts(5432)
        .withUsername("postgres")
        .withPassword("secret");

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if (initialized.compareAndSet(false, true)) {
            Startables.deepStart(postgres).join();
            appContainer.start();
        }
    }

}
