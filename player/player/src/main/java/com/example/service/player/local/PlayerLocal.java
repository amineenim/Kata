package com.example.service.player.local;


import com.example.service.player.bean.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerLocal extends JpaRepository<Player, Long> {
    List<Player> findAllInTeam(Long teamId);
}
