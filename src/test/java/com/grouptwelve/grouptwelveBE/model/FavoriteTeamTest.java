package com.grouptwelve.grouptwelveBE.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class FavoriteTeamTest {

    @Test
    public void testFavoriteTeamCreation(){
        FavoriteTeam favoriteTeam = new FavoriteTeam(2L, 3L);

        assertEquals(2L, favoriteTeam.getUserId());
        assertEquals(3L, favoriteTeam.getTeamId());
    }

    @Test
    public void testGettersAndSetters() {
        FavoriteTeam favoriteTeam = new FavoriteTeam();

        favoriteTeam.setFavoriteTeamId(1L);
        favoriteTeam.setUserId(2L);
        favoriteTeam.setTeamId(3L);

        assertEquals(1L, favoriteTeam.getFavoriteTeamId());
        assertEquals(2L, favoriteTeam.getUserId());
        assertEquals(3L, favoriteTeam.getTeamId());
    }

    @Test
    public void testFavoriteTeamConstructor(){
        FavoriteTeam favoriteTeam = new FavoriteTeam(5L, 10L);

        assertEquals(5L, favoriteTeam.getUserId());
        assertEquals(10L, favoriteTeam.getTeamId());
    }

    @Test
    public void testDefaultValues() {
        FavoriteTeam favoriteTeam = new FavoriteTeam();

        assertNull(favoriteTeam.getFavoriteTeamId());
        assertNull(favoriteTeam.getUserId());
        assertNull(favoriteTeam.getTeamId());
    }
}