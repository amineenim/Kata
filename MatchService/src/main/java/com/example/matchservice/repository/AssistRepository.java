package com.example.matchservice.repository;

import com.example.matchservice.model.Assist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistRepository extends JpaRepository<Assist, Long> {
    @Query("SELECT SUM(a.assists) FROM Assist a WHERE a.player = :playerId")
    Integer getAssistsSumForPlayer(@Param("playerId") Long playerId);
}
