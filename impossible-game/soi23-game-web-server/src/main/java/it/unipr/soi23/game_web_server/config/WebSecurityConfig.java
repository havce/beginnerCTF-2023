package it.unipr.soi23.game_web_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(requests -> requests //
                .requestMatchers("/admin/**") //
                .hasRole(SecurityConfig.ROLE_ADMIN) //
                .anyRequest().permitAll() //
        ) //
                .httpBasic(Customizer.withDefaults()) //
                .build();
    }
}
