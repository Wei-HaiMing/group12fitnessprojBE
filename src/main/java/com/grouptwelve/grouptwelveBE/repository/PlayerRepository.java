package com.grouptwelve.grouptwelveBE.repository;

import com.grouptwelve.grouptwelveBE.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeam(String team);
    List<Player> findByPosition(String position);
    Player findByNameAndTeam(String name, String team);
}