package com.example.matchservice.service;

import com.example.matchservice.model.AppearancePlayer;
import com.example.matchservice.repository.AppearancePlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AppearancePlayerService {
    private final AppearancePlayerRepository appearancePlayerRepository;

    @Autowired
    public AppearancePlayerService(AppearancePlayerRepository appearancePlayerRepository) {
        this.appearancePlayerRepository = appearancePlayerRepository;
    }

    public Map<String, Object> getMatchPlayedPlayersForPlayer(Long playerId) {
        Map<String, Object> result = new HashMap<>();
        result.put("playerId", playerId);
        result.put("appearances", appearancePlayerRepository.countByPlayerId(playerId));
        return result;
    }

    public AppearancePlayer addMatchPlayedPlayer(AppearancePlayer appearancePlayer) {
        return appearancePlayerRepository.save(appearancePlayer);
    }

    public void deleteMatchPlayedPlayer(Long matchPlayedPlayerId) {
        appearancePlayerRepository.deleteById(matchPlayedPlayerId);
    }
}
