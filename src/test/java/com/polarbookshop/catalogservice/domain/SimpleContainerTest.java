package com.polarbookshop.catalogservice.domain;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleContainerTest {


    @Test
    void testContainerStartup() {
        try (PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14.4")) {
            System.setProperty("DOCKER_HOST", "unix:///Users/PARAGUPT/.colima/default/docker.sock");
            System.setProperty("testcontainers.ryuk.container.timeout", "120"); // Increase timeout
            postgres.start();
            assertTrue(postgres.isRunning());
        }
    }
}
