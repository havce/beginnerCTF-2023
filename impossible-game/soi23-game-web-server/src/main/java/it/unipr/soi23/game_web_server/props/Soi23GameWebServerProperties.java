package it.unipr.soi23.game_web_server.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("it.unipr.soi23.game-web-server")
public class Soi23GameWebServerProperties {

    private String adminName;
    private String adminPassword;
    private String stompBrokerRelayHost;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getStompBrokerRelayHost() {
        return stompBrokerRelayHost;
    }

    public void setStompBrokerRelayHost(String stompBrokerRelayHost) {
        this.stompBrokerRelayHost = stompBrokerRelayHost;
    }
}
