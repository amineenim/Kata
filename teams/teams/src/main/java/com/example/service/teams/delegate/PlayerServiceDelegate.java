package com.example.service.teams.delegate;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlayerServiceDelegate {
    @Autowired
    RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "callPlayerServiceAndCheckPlayerExists_fallBack")
    public String callPlayerServiceAndCheckPlayerExists(Long playerId){
        System.out.println("checking existence of player with id " + playerId);
        String response = this.restTemplate.exchange(
                "http://localhost:8011/players/" + playerId
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<String>() {}
        ).getBody();
        System.out.println("response received as " + response);

        return response;
    }

    @SuppressWarnings("unused")
    private String callPlayerServiceAndCheckPlayerExists_fallBack(Long id){
        System.out.println("Player service is down !!");
        return "Circuit Breaker, No response from Player Service at the moment !";
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
