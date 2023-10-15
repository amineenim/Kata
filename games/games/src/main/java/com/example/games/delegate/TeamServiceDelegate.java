package com.example.games.delegate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TeamServiceDelegate {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "callTeamServiceAndCheckTeamExists_fallBack")
    public String callTeamServiceAndCheckTeamExists(Long teamId){
        System.out.println("checking for the existence of team with id " + teamId + " in TeamService");
        String response = this.restTemplate.exchange(
                "http://localhost:8012/teams/" + teamId
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<String>() {}
        ).getBody();
        System.out.println("response received as " + response);
        return response;
    }

    @SuppressWarnings("unused")
    private String callTeamServiceAndCheckTeamExists_fallBack(Long teamId){
        System.out.println("Teams Service is down !!");
        return "Circuit Breaker, No response from Team Service at the moment !";
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
