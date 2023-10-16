package com.example.matchservice.repository;

import com.example.matchservice.model.MatchTeamStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchTeamStatRepository extends JpaRepository<MatchTeamStat, Long> {

}
