package com.example.matchservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.HashMap;
import java.util.Map;

@Service
public class TeamPlayerService {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackForGetPlayerById")
    public Object getPlayerById(Long playerId){
        return restTemplate.getForObject("http://player-service/players/" + playerId , Object.class);
    }

    @HystrixCommand(fallbackMethod = "fallbackForGetTeamById")
    public Object getTeamById(Long teamId){
        return restTemplate.getForObject("http://team-service/teams/" + teamId , Object.class);
    }
    public Object fallbackForGetPlayerById(Long playerId){
        Map<String, Object> player = new HashMap<>();
        player.put("id", playerId);

        return player;
    }

    public Object fallbackForGetTeamById(Long teamId){
        Map<String, Object> team = new HashMap<>();
        team.put("id", teamId);

        return team;
    }

}
