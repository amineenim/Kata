package com.example.matchservice.repository;

import com.example.matchservice.model.FootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FootballMatchRepository extends JpaRepository<FootballMatch, Long> {
    @Query("SELECT " +
            "COUNT(fm) AS matchesPlayed, " +
            "SUM(CASE WHEN (fm.homeTeam = ?1 OR fm.awayTeam = ?1) THEN " +
            "(CASE WHEN fm.homeTeam = ?1 THEN fm.homeTeamScore ELSE 0 END + " +
            "CASE WHEN fm.awayTeam = ?1 THEN fm.awayTeamScore ELSE 0 END) ELSE 0 END) AS goalsScored, " +
            "SUM(CASE WHEN (fm.homeTeam = ?1 OR fm.awayTeam = ?1) THEN " +
            "(CASE WHEN fm.homeTeam = ?1 THEN fm.awayTeamScore ELSE 0 END + " +
            "CASE WHEN fm.awayTeam = ?1 THEN fm.homeTeamScore ELSE 0 END) ELSE 0 END) AS goalsConceded, " +
            "SUM(CASE WHEN (fm.homeTeam = ?1 OR fm.awayTeam = ?1) THEN " +
            "(CASE WHEN (fm.homeTeam = ?1 AND fm.homeTeamScore > fm.awayTeamScore) OR " +
            "(fm.awayTeam = ?1 AND fm.awayTeamScore > fm.homeTeamScore) THEN 1 ELSE 0 END) ELSE 0 END) AS wins, " +
            "SUM(CASE WHEN (fm.homeTeam = ?1 OR fm.awayTeam = ?1) THEN " +
            "(CASE WHEN (fm.homeTeam = ?1 AND fm.homeTeamScore < fm.awayTeamScore) OR " +
            "(fm.awayTeam = ?1 AND fm.awayTeamScore < fm.homeTeamScore) THEN 1 ELSE 0 END) ELSE 0 END) AS losses, " +
            "SUM(CASE WHEN (fm.homeTeam = ?1 OR fm.awayTeam = ?1) THEN " +
            "(CASE WHEN fm.homeTeamScore = fm.awayTeamScore THEN 1 ELSE 0 END) ELSE 0 END) AS draws, " +
            "SUM(CASE WHEN (fm.homeTeam = ?1 OR fm.awayTeam = ?1) THEN " +
            "(CASE WHEN fm.homeTeam = ?1 THEN (fm.homeTeamScore - fm.awayTeamScore) ELSE (fm.awayTeamScore - fm.homeTeamScore) END) ELSE 0 END) AS goalDifference " +
            "FROM FootballMatch fm WHERE (fm.homeTeam = ?1 OR fm.awayTeam = ?1)")
    List<Object[]> getTeamStatistics(Long team);



}
