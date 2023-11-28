package it.unipr.soi23.game_web_server.config;

import java.util.function.UnaryOperator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import it.unipr.soi23.game_web_server.props.Soi23GameWebServerProperties;

@Configuration
public class SecurityConfig {

    static final String ROLE_ADMIN = "ADMIN";

    private final Soi23GameWebServerProperties props;

    public SecurityConfig(Soi23GameWebServerProperties props) {
        this.props = props;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        final UnaryOperator<String> passwordEncoder = passwordEncoder()::encode;
        return new InMemoryUserDetailsManager( //
                User.withUsername(props.getAdminName()) //
                        .passwordEncoder(passwordEncoder) //
                        .password(props.getAdminPassword()) //
                        .roles(ROLE_ADMIN) //
                        .build() //
        );
    }
}
