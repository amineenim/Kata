package com.example.matchservice.repository;

import com.example.matchservice.model.GoalScorer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalScorerRepository extends JpaRepository<GoalScorer, Long> {
    @Query("SELECT SUM(g.goals) FROM GoalScorer g WHERE g.player = :playerId")
    Integer getGoalsSumForPlayer(@Param("playerId") Long playerId);
}
