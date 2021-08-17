package au.com.naythanc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    private String helloWorld() {
        log.info("GET request helloWorld() invoked!");
        return "Hello World";
    }
}
