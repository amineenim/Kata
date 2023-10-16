package com.example.matchservice.exception;

public class AssistNotFoundException extends  RuntimeException{
    public AssistNotFoundException(Long id) {
        super("Le passeur décisif du match non trouvé avec l'ID : " + id);
    }
}
