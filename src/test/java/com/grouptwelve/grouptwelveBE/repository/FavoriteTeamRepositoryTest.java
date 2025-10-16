package com.grouptwelve.grouptwelveBE.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.grouptwelve.grouptwelveBE.model.FavoriteTeam;
import java.util.List;
import java.util.ArrayList;

@DataJpaTest
@ActiveProfiles("test")
public class FavoriteTeamRepositoryTest {
    @Autowired
    private FavoriteTeamRepository favoriteTeamRepository;

    @Test
    void testSaveAndFindFavoriteTeam() {
        FavoriteTeam favoriteTeam = new FavoriteTeam(1L, 2L);
        FavoriteTeam savedFavoriteTeam = favoriteTeamRepository.save(favoriteTeam);

        assertNotNull(savedFavoriteTeam.getFavoriteTeamId());
        assertEquals(1L, savedFavoriteTeam.getUserId());
        assertEquals(2L, savedFavoriteTeam.getTeamId());
    }

    @Test
    void testFindByUserIdAndTeamId(){
        FavoriteTeam favoriteTeam = new FavoriteTeam(3L, 4L);
        favoriteTeamRepository.save(favoriteTeam);

        FavoriteTeam foundFavoriteTeam = favoriteTeamRepository.findByUserIdAndTeamId(3L, 4L).orElse(null);

        assertNotNull(foundFavoriteTeam);
        assertEquals(3L, foundFavoriteTeam.getUserId());
        assertEquals(4L, foundFavoriteTeam.getTeamId());
    }

    @Test
    void testFindByUserId() {
        FavoriteTeam favoriteTeam1 = new FavoriteTeam(5L, 6L);
        FavoriteTeam favoriteTeam2 = new FavoriteTeam(5L, 7L);
        List<FavoriteTeam> foundFavoriteTeams = new ArrayList<>();
        foundFavoriteTeams.add(favoriteTeam1);
        foundFavoriteTeams.add(favoriteTeam2);

        favoriteTeamRepository.save(favoriteTeam1);
        favoriteTeamRepository.save(favoriteTeam2);

        assertEquals(2, favoriteTeamRepository.findByUserId(5L).size());
        assertEquals(foundFavoriteTeams.size(), favoriteTeamRepository.findByUserId(5L).size());
        assertEquals(foundFavoriteTeams.get(0).getTeamId(), favoriteTeamRepository.findByUserId(5L).get(0).getTeamId());
        assertEquals(foundFavoriteTeams.get(1).getTeamId(), favoriteTeamRepository.findByUserId(5L).get(1).getTeamId());
    }

    @Test
    void testFindByTeamId() {
        FavoriteTeam favoriteTeam1 = new FavoriteTeam(8L, 9L);
        FavoriteTeam favoriteTeam2 = new FavoriteTeam(10L, 9L);
        List<FavoriteTeam> foundFavoriteTeams = new ArrayList<>();
        foundFavoriteTeams.add(favoriteTeam1);
        foundFavoriteTeams.add(favoriteTeam2);

        favoriteTeamRepository.save(favoriteTeam1);
        favoriteTeamRepository.save(favoriteTeam2);

        assertEquals(2, favoriteTeamRepository.findByTeamId(9L).size());
        assertEquals(foundFavoriteTeams.size(), favoriteTeamRepository.findByTeamId(9L).size());
        assertEquals(foundFavoriteTeams.get(0).getUserId(), favoriteTeamRepository.findByTeamId(9L).get(0).getUserId());
        assertEquals(foundFavoriteTeams.get(1).getUserId(), favoriteTeamRepository.findByTeamId(9L).get(1).getUserId());
    }

    @Test
    void testExistsByUserIdAndTeamId() {
        FavoriteTeam favoriteTeam = new FavoriteTeam(11L, 12L);
        favoriteTeamRepository.save(favoriteTeam);

        assertEquals(true, favoriteTeamRepository.existsByUserIdAndTeamId(11L, 12L));
        assertEquals(false, favoriteTeamRepository.existsByUserIdAndTeamId(11L, 13L));
    }

    @Test
    void testDeleteByUserIdAndTeamId() {
        FavoriteTeam favoriteTeam = new FavoriteTeam(14L, 15L);
        favoriteTeamRepository.save(favoriteTeam);

        assertEquals(true, favoriteTeamRepository.existsByUserIdAndTeamId(14L, 15L));

        favoriteTeamRepository.deleteByUserIdAndTeamId(14L, 15L);

        assertFalse(favoriteTeamRepository.existsByUserIdAndTeamId(14L, 15L));
    }
}
