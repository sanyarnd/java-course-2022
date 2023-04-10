package com.example.testingworkshop;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.Future;

public class AppContainer extends GenericContainer<AppContainer> {

    public AppContainer() {
        super(image());
    }

    @Override
    public void configure() {
        super.configure();
        withExposedPorts(8080);
        waitStrategy.withStartupTimeout(Duration.ofMinutes(5));
    }

    public static Future<String> image() {
        return new ImageFromDockerfile("local-app", true)
            .withDockerfile(Paths.get("../Dockerfile"));

    }

}
