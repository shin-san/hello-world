package au.com.naythanc.controller;

import au.com.naythanc.config.WebClientConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Objects;

@SpringBootTest
public class ExternalApiTest {

    @Autowired
    WebClientConfig webClientConfig;

    @Test
    @Tag("integrationtest")
    public void helloWorldFromExternalUrl() throws Exception {
        String helloWorldUrl = "https://naythanc.dev/hello/api/v1/hello";
        Mono<String> response = webClientConfig.getWebClient()
                                .get()
                                .uri(helloWorldUrl)
                                .retrieve()
                                .bodyToMono(String.class);

        Assertions.assertTrue(Objects.
                requireNonNull(response.block())
                .contains("{ \"message\": \"Hello World\" }"));
    }

    @Test
    @Tag("integrationtest")
    public void errorResponseFromExternalUrl() throws Exception {

        String helloWorldUrl = "https://naythanc.dev/hello/";
        Mono<String> response = webClientConfig.getWebClient()
                .get()
                .uri(helloWorldUrl)
                .retrieve()
                .bodyToMono(String.class);

        Exception exception = Assertions.assertThrows(WebClientResponseException.class, response::block);

        Assertions.assertTrue(exception.getMessage().contains("404"));
    }
}
