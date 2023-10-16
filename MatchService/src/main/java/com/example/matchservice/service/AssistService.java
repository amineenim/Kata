package com.example.matchservice.service;

import com.example.matchservice.exception.AssistNotFoundException;
import com.example.matchservice.model.Assist;
import com.example.matchservice.repository.AssistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssistService {
    private final AssistRepository assistRepository;

    @Autowired
    public AssistService(AssistRepository assistRepository) {
        this.assistRepository = assistRepository;
    }

    public List<Assist> getAllAssists() {
        return assistRepository.findAll();
    }

    public Assist createAssist(Assist assist) {
        return assistRepository.save(assist);
    }

    public Map<String, Object> getAssistsAmountForPlayer(Long playerId) {
        Map<String, Object> result = new HashMap<>();
        result.put("playerId", playerId);
        result.put("assists", assistRepository.getAssistsSumForPlayer(playerId));
        return result;
    }

    public void deleteAssist(Long id) {
        if (assistRepository.existsById(id)) {
            assistRepository.deleteById(id);
        } else {
            throw new AssistNotFoundException(id);
        }
    }
}
