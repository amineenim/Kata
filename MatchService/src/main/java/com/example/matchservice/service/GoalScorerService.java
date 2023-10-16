package com.example.matchservice.service;

import com.example.matchservice.exception.GoalScorerNotFoundException;
import com.example.matchservice.model.GoalScorer;
import com.example.matchservice.repository.GoalScorerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GoalScorerService {
    private final GoalScorerRepository goalScorerRepository;

    @Autowired
    public GoalScorerService(GoalScorerRepository goalScorerRepository) {
        this.goalScorerRepository = goalScorerRepository;
    }

    public List<GoalScorer> getAllGoalScorers() {
        return goalScorerRepository.findAll();
    }

    public GoalScorer createGoalScorer(GoalScorer goalScorer) {
        return goalScorerRepository.save(goalScorer);
    }

    public Map<String, Object> getGoalsAmountForPlayer(Long playerId) {
        Map<String, Object> result = new HashMap<>();
        result.put("playerId", playerId);
        result.put("goals", goalScorerRepository.getGoalsSumForPlayer(playerId));
        return result;
    }

    public void deleteGoalScorer(Long id) {
        if (goalScorerRepository.existsById(id)) {
            goalScorerRepository.deleteById(id);
        } else {
            throw new GoalScorerNotFoundException(id);
        }
    }
}
