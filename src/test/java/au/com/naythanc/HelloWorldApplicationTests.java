package au.com.naythanc;

import au.com.naythanc.config.WebClientConfig;
import au.com.naythanc.controller.HelloWorldController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloWorldApplicationTests {

	@Autowired
	private HelloWorldController controller;

	@Autowired
	private WebClientConfig config;

	@Test
	@Tag("unittest")
	void contextLoads() {
		Assertions.assertThat(controller).isNotNull();
		Assertions.assertThat(config).isNotNull();
	}

}
