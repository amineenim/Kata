package com.example.service.stats.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //authorizes accees to enpoint Shutdown only for users with  "ACTUATOR_ADMIN".
                .requestMatchers(EndpointRequest.to(ShutdownEndpoint.class))
                .hasRole("ACTUATOR_ADMIN")
                // allows access to all other endpoints (health, metrics, etc.) without authentification.
                .requestMatchers(EndpointRequest.toAnyEndpoint())
                .permitAll()
                //Authorizes aceess to static ressources
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll()
                //Authorizes access to base URLS ("/" et "/slowApi") without AUTH
                .antMatchers("/", "/slowApi")
                .permitAll()
                // requires authentication for other URLS
                .antMatchers("/**")
                .authenticated()
                .and()
                .httpBasic(); // ACTIVATE BASE AUTH.
    }
}
