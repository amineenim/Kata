package com.example.service.player.configuration;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
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
