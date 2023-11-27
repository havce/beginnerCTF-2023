package it.unipr.soi23.game_web_server;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Soi23GameWebServerApplicationTests {

	@Test
	void contextLoads() {
		final String[] args = new String[] {};
		final ApplicationContext appCtx = Soi23GameWebServerApplication.start(args);
		assertNotNull(appCtx, "appCtx");
		Soi23GameWebServerApplication.close();
	}
}
