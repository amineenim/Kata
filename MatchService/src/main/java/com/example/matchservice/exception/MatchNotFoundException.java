package com.example.matchservice.exception;

public class MatchNotFoundException extends RuntimeException{
    public MatchNotFoundException(Long matchId) {
        super("Match non trouvé avec l'ID : " + matchId);
    }

}

