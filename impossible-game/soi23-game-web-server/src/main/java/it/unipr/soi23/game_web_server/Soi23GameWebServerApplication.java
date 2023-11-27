package it.unipr.soi23.game_web_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Soi23GameWebServerApplication {

	private static ConfigurableApplicationContext appCtx;

	public static void main(String[] args) {
		start(args);
	}

	public static ApplicationContext start(String[] args) {
		appCtx = SpringApplication.run(Soi23GameWebServerApplication.class, args);
		return appCtx;
	}

	public static void close() {
		appCtx.close();
	}
}
