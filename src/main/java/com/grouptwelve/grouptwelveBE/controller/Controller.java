package com.grouptwelve.grouptwelveBE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grouptwelve.grouptwelveBE.model.Game;
import com.grouptwelve.grouptwelveBE.model.User;
import com.grouptwelve.grouptwelveBE.repository.GameRepository;
import com.grouptwelve.grouptwelveBE.repository.UserRepository;


// keep all controllers in this file for simplicity

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;


    @GetMapping("/")
    public String root() {
        return "API up. Try /api/greeting";
    }

    @GetMapping("/greeting")
    public String hello(@RequestParam(defaultValue = "world") String name) {
        return "Greetings, " + name + "!";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @PostMapping("/users")
    public User createUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }
    
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PatchMapping("/users/{id}/name")
    public User updateName(@PathVariable Long id, @RequestBody String newName) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setName(newName);
            return userRepository.save(user);
        }
        return null;
    }

    @PatchMapping("/users/{id}/password")
    public User updatePassword(@PathVariable Long id, @RequestBody String newPassword) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setPassword(newPassword);
            return userRepository.save(user);
        }
        return null;
    }


    //GAMES (8 roues) 
    // POST (1): create one
    @PostMapping("/games")
    public Game createGame(@RequestBody Game g) {
        if (g.getLeague() == null || g.getLeague().isBlank()) g.setLeague("NFL");
        if (g.getStatus() == null || g.getStatus().isBlank()) g.setStatus("scheduled");
        return gameRepository.save(g);
    }

    // POST (2): bulk create
    @PostMapping("/games/bulk")
    public List<Game> bulkCreateGames(@RequestBody List<Game> games) {
        for (Game g : games) {
            if (g.getLeague() == null || g.getLeague().isBlank()) g.setLeague("NFL");
            if (g.getStatus() == null || g.getStatus().isBlank()) g.setStatus("scheduled");
        }
        return gameRepository.saveAll(games);
    }

    // GET (1): list (optional status)
    @GetMapping("/games")
    public List<Game> listGames(@RequestParam(required = false) String status) {
        return (status == null || status.isBlank())
                ? gameRepository.findAll()
                : gameRepository.findByStatus(status);
    }

    // GET (2): one by id
    @GetMapping("/games/{id}")
    public Game getGame(@PathVariable Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    // PUT (1): update core fields
    @PutMapping("/games/{id}")
    public Game updateGame(@PathVariable Long id, @RequestBody Game u) {
        return gameRepository.findById(id).map(g -> {
            if (u.getLeague() != null) g.setLeague(u.getLeague());
            if (u.getHomeTeam() != null) g.setHomeTeam(u.getHomeTeam());
            if (u.getAwayTeam() != null) g.setAwayTeam(u.getAwayTeam());
            if (u.getStartTime() != null) g.setStartTime(u.getStartTime());
            if (u.getStatus() != null) g.setStatus(u.getStatus());
            if (u.getOddsHome() != null) g.setOddsHome(u.getOddsHome());
            if (u.getOddsAway() != null) g.setOddsAway(u.getOddsAway());
            return gameRepository.save(g);
        }).orElse(null);
    }

    // PUT (2): update odds only
    @PutMapping("/games/{id}/odds")
    public Game updateOdds(@PathVariable Long id, @RequestBody Game u) {
        return gameRepository.findById(id).map(g -> {
            if (u.getOddsHome() != null) g.setOddsHome(u.getOddsHome());
            if (u.getOddsAway() != null) g.setOddsAway(u.getOddsAway());
            return gameRepository.save(g);
        }).orElse(null);
    }

    // DELETE (1): delete one
    @DeleteMapping("/games/{id}")
    public void deleteGame(@PathVariable Long id) {
        gameRepository.deleteById(id);
    }

    // DELETE (2): bulk delete by status
    @DeleteMapping("/games")
    public long deleteGamesByStatus(@RequestParam String status) {
        return gameRepository.deleteByStatus(status);
    }
    




}
