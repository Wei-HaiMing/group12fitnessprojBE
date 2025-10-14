package com.grouptwelve.grouptwelveBE.repository;

import com.grouptwelve.grouptwelveBE.model.FavoriteTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface FavoriteTeamRepository extends JpaRepository<FavoriteTeam, Long> {

    // save() = insert or update,
    // findAll() = select * from
    // findById() = select * from where id=?
    // deleteById() = delete from where id=? 

    Optional<FavoriteTeam> findByUserIdAndTeamId(Long userId, Long teamId);                  // WHERE userId = ? AND teamId = ?
    List<FavoriteTeam> findByUserId(Long userId);                           // WHERE userId = ?
    List<FavoriteTeam> findByTeamId(Long teamId);                            // WHERE teamId = ?
    boolean existsByUserIdAndTeamId(Long userId, Long teamId);             // SELECT COUNT(*) WHERE userId = ? AND teamId = ?
    void deleteByUserIdAndTeamId(Long userId, Long teamId);                // DELETE WHERE userId = ? AND teamId = ?
    void modifyByUserIdAndTeamId(Long userId, Long teamId, FavoriteTeam newFavoriteTeam);                // UPDATE WHERE userId = ? AND teamId = ?
}  
