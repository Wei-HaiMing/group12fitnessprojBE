package com.grouptwelve.grouptwelveBE.repository;

import com.grouptwelve.grouptwelveBE.model.FavoriteTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteTeamRepository extends JpaRepository<FavoriteTeam, Long> {
    FavoriteTeam findByUserIdAndTeamId(Long userId, Long teamId);
    List<FavoriteTeam> findByFavoriteTeamId(Long favoriteTeamId);
    List<FavoriteTeam> findByUserId(Long userId);
    List<FavoriteTeam> findByTeamId(Long teamId);
    void deleteByUserIdAndTeamId(Long userId, Long teamId);
}
