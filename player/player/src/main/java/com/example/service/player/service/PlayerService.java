package com.example.service.player.service;

import com.example.service.player.bean.Player;
import com.example.service.player.error.PlayerNotFoundError;
import com.example.service.player.local.PlayerLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@Service
public class PlayerService {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    private final PlayerLocal playerLocal;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public PlayerService(PlayerLocal playerLocal) {
        this.playerLocal = playerLocal;
    }

    public List<Player> getAllPlayers() {
        return playerLocal.findAll();
    }

    public List<Player> getAllPlayersByTeamId(Long teamId) {
        return playerLocal.findAllInTeam (teamId);
    }

}
