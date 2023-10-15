package com.example.service.stats.delegate;

import com.example.service.stats.bean.Game;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class GameServiceDelegate {

    @Autowired
    RestTemplate restTemplate;

    public List<Game> parseResponseToJson(String response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response, new TypeReference<List<Game>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions that occur during parsing
            return Collections.emptyList(); // Return an empty list or handle the error appropriately
        }
    }
    @HystrixCommand(fallbackMethod = "callGameServiceAndGetAllGamesWhereTeamPlayed_fallBack")
    public String callGameServiceAndGetAllGamesWhereTeamPlayed(Long teamId){
        System.out.println("getting all games where team with id " + teamId + " is part of");
        String response = this.restTemplate.exchange(
                "http://localhost:8013/matches"
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<String>() {}
        ).getBody();
        System.out.println("response received as " + response);
        // parse response and map it to a list of Game objects
        List<Game> allGames = parseResponseToJson(response);
        System.out.println(allGames);
        System.out.println(allGames.toString());
        return response;
    }

    @SuppressWarnings("unused")
    private String callGameServiceAndGetAllGamesWhereTeamPlayed_fallBack(Long teamId){
        System.out.println("Game Service is down !!");
        return "Circuit Breaker, No response from Game Service at the moment !";
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
