package it.unipr.soi23.game_web_server.config;

import it.unipr.soi23.game_web_server.props.Soi23GameWebServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Component
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

    private final String stompBrokerRelayHost;

    public WebSocketConfig(Soi23GameWebServerProperties properties) {
        this.stompBrokerRelayHost = properties.getStompBrokerRelayHost();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setPathMatcher(new AntPathMatcher("."));
        registry.setApplicationDestinationPrefixes("/app");
        if (StringUtils.hasLength(stompBrokerRelayHost)) {
            logger.info("Stomp Broker Relay enabled with host: {}", stompBrokerRelayHost);
            registry.enableStompBrokerRelay("/topic") //
                    .setRelayHost(stompBrokerRelayHost);
        } else {
            logger.info("Stomp Broker Relay not enabled");
            registry.enableSimpleBroker("/topic");
        }
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket") //
                .withSockJS();
    }
}
