package au.com.naythanc.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Tag("unittest")
    public void helloworldShouldReturnDefaultMessage() {
        String url = "http://localhost:" + port +
                "/api/v1/hello";
        Assertions.assertThat(this.testRestTemplate.getForObject(url, String.class))
                .contains("{ \"message\": \"Hello World\" }");
    }

    @Test
    @Tag("unittest")
    public void wrongContextPathShouldReturnErrorMessage() {
        String url = "http://localhost:" + port +
                "/test";
        Assertions.assertThat(this.testRestTemplate.getForObject(url, String.class))
                .contains("\"path\":\"/test\",\"status\":404,\"error\":\"Not Found\"");
    }
}
