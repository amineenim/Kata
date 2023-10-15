package com.example.games.configuration;

import org.springframework.boot.actuate.endpoint.ShutdownEndpoint;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/actuator/shutdown").hasRole("ACTUATOR_ADMIN")  // Configure access to the shutdown endpoint
                .antMatchers("/", "/slowApi").permitAll() // Permit access to other endpoints
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}
