package com.grouptwelve.grouptwelveBE.repository;

import com.grouptwelve.grouptwelveBE.model.FootballTeam; 
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FootballTeamRepository extends JpaRepository<FootballTeam, Long> {
    Optional<FootballTeam> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
    List<FootballTeam> findByNameContainingIgnoreCase(String partialName);
    List<FootballTeam> findByCityIgnoreCase(String city);
    List<FootballTeam> search(String q);
}