package com.example.matchservice.service;

import com.example.matchservice.exception.MatchTeamStatNotFoundException;
import com.example.matchservice.model.MatchTeamStat;
import com.example.matchservice.repository.MatchTeamStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchTeamStatService {
    private final MatchTeamStatRepository matchTeamStatRepository;

    @Autowired
    public MatchTeamStatService(MatchTeamStatRepository matchTeamStatRepository) {
        this.matchTeamStatRepository = matchTeamStatRepository;
    }

    public List<MatchTeamStat> getAllMatchTeamStats() {
        return matchTeamStatRepository.findAll();
    }

    public Optional<MatchTeamStat> getMatchTeamStatById(Long id) {
        return matchTeamStatRepository.findById(id);
    }

    public MatchTeamStat createMatchTeamStat(MatchTeamStat matchTeamStat) {
        return matchTeamStatRepository.save(matchTeamStat);
    }

    public MatchTeamStat updateMatchTeamStat(Long id, MatchTeamStat updatedMatchTeamStat) {
        if (matchTeamStatRepository.existsById(id)) {
            updatedMatchTeamStat.setId(id);
            return matchTeamStatRepository.save(updatedMatchTeamStat);
        } else {
            throw new MatchTeamStatNotFoundException(id);
        }
    }

    public void deleteMatchTeamStat(Long id) {
        matchTeamStatRepository.deleteById(id);
    }
}
