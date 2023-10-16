package com.example.matchservice.service;

import com.example.matchservice.model.FootballMatch;
import com.example.matchservice.model.TeamStatisticsDTO;
import com.example.matchservice.repository.FootballMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FootballMatchService {
    private final FootballMatchRepository footballMatchRepository;

    private final  TeamPlayerService teamPlayerService;

    @Autowired
    public FootballMatchService(FootballMatchRepository footballMatchRepository, TeamPlayerService teamPlayerService) {
        this.footballMatchRepository = footballMatchRepository;
        this.teamPlayerService = teamPlayerService;
    }

    public List<FootballMatch> getAllFootballMatches() {
        return footballMatchRepository.findAll();
    }

    public TeamStatisticsDTO getAllFootballMatchStatByTeam(Long teamId) {
        List<Object[]> teamStatistics = footballMatchRepository.getTeamStatistics(teamId);
        TeamStatisticsDTO result = new TeamStatisticsDTO();
        Object team = teamPlayerService.getTeamById(teamId);

        if (!teamStatistics.isEmpty()) {
            Object[] row = teamStatistics.get(0);
            Long matchesPlayed = (Long) row[0];
            Long goalsScored = (Long) row[1];
            Long goalsConceded = (Long) row[2];
            Long wins = (Long) row[3];
            Long losses = (Long) row[4];
            Long draws = (Long) row[5];
            Long goalDifference = (Long) row[6];

            if(matchesPlayed == null){
                matchesPlayed = 0L;
            }

            if(goalsScored == null){
                goalsScored = 0L;
            }

            if(goalsConceded == null){
                goalsConceded = 0L;
            }

            if(wins == null){
                wins = 0L;
            }

            if(losses == null){
                losses = 0L;
            }

            if(draws == null){
                draws = 0L;
            }

            if(goalDifference == null){
                goalDifference = 0L;
            }

            result = new TeamStatisticsDTO(team, matchesPlayed, goalsScored, goalsConceded, wins, losses, draws, goalDifference);
        }

        return result;
    }

    public Optional<FootballMatch> getFootballMatchById(Long id) {
        return footballMatchRepository.findById(id);
    }

    public FootballMatch createFootballMatch(FootballMatch footballMatch) {
        return footballMatchRepository.save(footballMatch);
    }

    public FootballMatch updateFootballMatch(Long id, FootballMatch updatedFootballMatch) {
        // Obtenez le match existant par son ID
        Optional<FootballMatch> existingFootballMatch = footballMatchRepository.findById(id);

        if (existingFootballMatch.isPresent()) {
            // Mettez à jour les champs du match existant avec les valeurs du match mis à jour.
            // Assurez-vous d'ajouter la logique appropriée ici.

            // Exemple de mise à jour du nom du stade :
            existingFootballMatch.get().setStadium(updatedFootballMatch.getStadium());

            return footballMatchRepository.save(existingFootballMatch.get());
        } else {
            return null; // Le match n'existe pas, vous pouvez gérer cela différemment.
        }
    }

    public void deleteFootballMatch(Long id) {
        footballMatchRepository.deleteById(id);
    }
}
