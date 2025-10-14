package com.grouptwelve.grouptwelveBE.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grouptwelve.grouptwelveBE.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByStatus(String status);
    long deleteByStatus(String status);
}
