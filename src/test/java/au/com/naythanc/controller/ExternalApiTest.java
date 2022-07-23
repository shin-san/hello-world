package au.com.naythanc.controller;

import au.com.naythanc.config.WebClientConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

@SpringBootTest
public class ExternalApiTest {

    @Autowired
    WebClientConfig webClientConfig;

    private final String HELLO_WORLD_URL = "https://naythanc.dev/hello/api/v1/hello";

    @Test
    @Tag("integrationtest")
    public void helloWorldFromExternalUrl() throws Exception {
        Mono<String> response = webClientConfig.getWebClient()
                                .get()
                                .uri(HELLO_WORLD_URL)
                                .retrieve()
                                .bodyToMono(String.class);

        Assertions.assertThat(response.block())
                .contains("{ \"message\": \"Hello World\" }");
    }
}
