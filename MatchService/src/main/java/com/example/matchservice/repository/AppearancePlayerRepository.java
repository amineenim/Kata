package com.example.matchservice.repository;

import com.example.matchservice.model.AppearancePlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppearancePlayerRepository extends JpaRepository<AppearancePlayer, Long> {
    Integer countByPlayerId(Long playerId);
}
