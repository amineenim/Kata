package com.example.matchservice.exception;

public class GoalScorerNotFoundException extends  RuntimeException {
    public GoalScorerNotFoundException(Long id) {
        super("Le buteur du match non trouvé avec l'ID : " + id);
    }
}
