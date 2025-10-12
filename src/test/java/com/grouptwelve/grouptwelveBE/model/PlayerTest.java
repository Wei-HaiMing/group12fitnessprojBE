package com.grouptwelve.grouptwelveBE.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayerCreation() {
        Player player = new Player();
        player.setName("Tom Brady");
        player.setTeam("Tampa Bay Buccaneers");
        player.setPosition("QB");
        player.setJerseyNumber(12);

        assertEquals("Tom Brady", player.getName());
        assertEquals("Tampa Bay Buccaneers", player.getTeam());
        assertEquals("QB", player.getPosition());
        assertEquals(12, player.getJerseyNumber());
    }

    @Test
    void testPlayerConstructor() {
        Player player = new Player("Patrick Mahomes", "Kansas City Chiefs", "QB", 15);
        
        assertEquals("Patrick Mahomes", player.getName());
        assertEquals("Kansas City Chiefs", player.getTeam());
        assertEquals("QB", player.getPosition());
        assertEquals(15, player.getJerseyNumber());
        assertEquals(0, player.getTouchdowns());
        assertEquals(0, player.getTotalYards());
        assertEquals(0, player.getGamesPlayed());
        assertEquals(0, player.getFieldGoals());
        assertEquals(0.0, player.getCompletionRate());
    }

    @Test
    void testPlayerStats() {
        Player player = new Player();
        player.setTouchdowns(25);
        player.setTotalYards(4500);
        player.setGamesPlayed(16);
        player.setCompletionRate(65.5);

        assertEquals(25, player.getTouchdowns());
        assertEquals(4500, player.getTotalYards());
        assertEquals(16, player.getGamesPlayed());
        assertEquals(65.5, player.getCompletionRate());
    }

    @Test
    void testDefaultConstructor() {
        Player player = new Player();
        
        assertEquals(0, player.getTouchdowns());
        assertEquals(0, player.getTotalYards());
        assertEquals(0, player.getGamesPlayed());
        assertEquals(0, player.getFieldGoals());
        assertEquals(0.0, player.getCompletionRate());
    }
}