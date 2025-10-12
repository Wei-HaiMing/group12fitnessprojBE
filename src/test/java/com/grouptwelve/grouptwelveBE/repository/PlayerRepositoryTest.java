package com.grouptwelve.grouptwelveBE.repository;

import com.grouptwelve.grouptwelveBE.model.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void testSaveAndFindPlayer() {
        Player player = new Player("Tom Brady", "Tampa Bay Buccaneers", "QB", 12);
        Player savedPlayer = playerRepository.save(player);

        assertNotNull(savedPlayer.getPlayerId());
        assertEquals("Tom Brady", savedPlayer.getName());
        assertEquals("Tampa Bay Buccaneers", savedPlayer.getTeam());
        assertEquals("QB", savedPlayer.getPosition());
        assertEquals(12, savedPlayer.getJerseyNumber());
    }

    @Test
    void testFindByTeam() {
        Player player1 = new Player("Player 1", "Team A", "QB", 1);
        Player player2 = new Player("Player 2", "Team A", "RB", 2);
        Player player3 = new Player("Player 3", "Team B", "WR", 3);
        
        playerRepository.save(player1);
        playerRepository.save(player2);
        playerRepository.save(player3);

        List<Player> teamAPlayers = playerRepository.findByTeam("Team A");
        assertEquals(2, teamAPlayers.size());
    }

    @Test
    void testFindByPosition() {
        Player player1 = new Player("QB 1", "Team A", "QB", 1);
        Player player2 = new Player("QB 2", "Team B", "QB", 2);
        Player player3 = new Player("RB 1", "Team A", "RB", 3);
        
        playerRepository.save(player1);
        playerRepository.save(player2);
        playerRepository.save(player3);

        List<Player> qbPlayers = playerRepository.findByPosition("QB");
        assertEquals(2, qbPlayers.size());
    }

    @Test
    void testFindByNameAndTeam() {
        Player player = new Player("Patrick Mahomes", "Kansas City Chiefs", "QB", 15);
        playerRepository.save(player);

        Player foundPlayer = playerRepository.findByNameAndTeam("Patrick Mahomes", "Kansas City Chiefs");
        
        assertNotNull(foundPlayer);
        assertEquals("Patrick Mahomes", foundPlayer.getName());
        assertEquals("Kansas City Chiefs", foundPlayer.getTeam());
        assertEquals(15, foundPlayer.getJerseyNumber());
    }
}