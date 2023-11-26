package it.unipr.soi23.game_web_server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

public class Soi23GameWebServerEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(Soi23GameWebServerEnvironmentPostProcessor.class);

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        final String afterName = "configurationProperties";
        final String name = Soi23GameWebServerEnvironmentPostProcessor.class.getPackage().getName() //
                + ".default";
        final String location = "classpath:/it/unipr/soi23/game_web_server/application.properties";
        try {
            final ResourcePropertySource resource = new ResourcePropertySource(name, location);
            final MutablePropertySources mps = environment.getPropertySources();
            mps.addAfter(afterName, resource);
        } catch (IOException e) {
            logger.warn("Application config not added: {}; {}", location, e.getMessage());
        }
    }
}
