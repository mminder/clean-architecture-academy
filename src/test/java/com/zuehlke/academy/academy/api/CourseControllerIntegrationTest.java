package com.zuehlke.academy.academy.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CourseControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void shouldReturnCoursesFromApi() {
        // When
        String url = "http://localhost:" + port + "/api/courses";
        String response = restTemplate.getForObject(url, String.class);

        // Then
        assertNotNull(response, "Response should not be null");
        assertTrue(response.startsWith("["), "Response should be a JSON array starting with [");
        assertTrue(response.endsWith("]"), "Response should be a JSON array ending with ]");
    }
}
