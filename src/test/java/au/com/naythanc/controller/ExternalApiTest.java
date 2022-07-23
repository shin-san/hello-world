package au.com.naythanc.controller;

import au.com.naythanc.config.WebClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@SpringBootTest
public class ExternalApiTest {

    @Autowired
    WebClientConfig webClientConfig;

    @Value("${hello.world.url}")
    private String helloWorldUrl;

    @Test
    @Tag("integrationtest")
    public void helloWorldFromExternalUrl() throws Exception {
        log.info("Running helloWorldFromExternalUrl...");
        String url = helloWorldUrl + "/api/v1/hello";
        Mono<String> response = webClientConfig.getWebClient()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);

        log.info("Asserting response: {}", response.block());
        Assertions.assertTrue(Objects.
                requireNonNull(response.block())
                .contains("{ \"message\": \"Hello World\" }"));
        log.info("helloWorldFromExternalUrl Test Complete");
    }

    @Test
    @Tag("integrationtest")
    public void errorResponseFromExternalUrl() throws Exception {
        log.info("Running errorResponseFromExternalUrl...");
        String url = helloWorldUrl + "/hello";
        Mono<String> response = webClientConfig.getWebClient()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);

        log.info("Capturing exception...");
        Exception exception = Assertions.assertThrows(WebClientResponseException.class, response::block);

        log.info("Asserting exception message: {}", exception.getMessage());
        Assertions.assertTrue(exception.getMessage().contains("404"));

        log.info("errorResponseFromExternalUrl Test Complete");
    }
}
