package com.example.service.player.error;

public class PlayerNotFoundError extends RuntimeException {
    public PlayerNotFoundError(Long playerId) {
        super("Joueur non trouvé avec l'ID : " + playerId);
    }
}

