package com.example.matchservice.exception;

public class MatchTeamStatNotFoundException extends RuntimeException {
    public MatchTeamStatNotFoundException(Long id) {
        super("Les stats du Match non trouvé avec l'ID : " + id);
    }
}
