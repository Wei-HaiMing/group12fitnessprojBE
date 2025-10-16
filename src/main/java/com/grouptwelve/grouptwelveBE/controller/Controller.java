package com.grouptwelve.grouptwelveBE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grouptwelve.grouptwelveBE.model.User;
import com.grouptwelve.grouptwelveBE.repository.UserRepository;
import com.grouptwelve.grouptwelveBE.model.FavoriteTeam;
import com.grouptwelve.grouptwelveBE.repository.FavoriteTeamRepository;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FavoriteTeamRepository favoriteTeamRepository;

    @GetMapping("/")
    public String root() {
        return "API up. Try /api/greeting";
    }

    @GetMapping("/greeting")
    public String hello(@RequestParam(defaultValue = "world") String name) {
        return "Greetings, " + name + "!";
    }

    // User CRUD operations BEGIN
    @GetMapping("/users") // GET 1
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}") // GET 2
    public User getUserById(@PathVariable("id") Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping("/users") // POST 1
    public User createUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @PatchMapping("/users/{id}/name") // PATCH 1
    public User updateName(@PathVariable("id") Long id, @RequestBody String newName) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setName(newName);
            return userRepository.save(user);
        }
        return null;
    }

    @PatchMapping("/users/{id}/password") // PATCH 2
    public User updatePassword(@PathVariable("id") Long id, @RequestBody String newPassword) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setPassword(newPassword);
            return userRepository.save(user);
        }
        return null;
    }

    @DeleteMapping("/users/{id}") // DELETE 1
    public String deleteUser(@PathVariable("id") Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User with id " + id + " deleted.";
        } else {
            return "User with id " + id + " not found.";
        }
    }

    @DeleteMapping("/users/email/{email}") // DELETE 2
    public String deleteUserByEmail(@PathVariable("email") String email) {
        if (userRepository.existsByEmail(email)) {
            userRepository.deleteByEmail(email);
            return "User with email " + email + " deleted.";
        } else {
            return "User with email " + email + " not found.";
        }
    }
    // User CRUD operations END

    // FavoriteTeam CRUD operations BEGIN
    @GetMapping("/favoriteteams") // GET 1
    public List<FavoriteTeam> getAllFavoriteTeams() {
        return favoriteTeamRepository.findAll();
    }

    @GetMapping("/favoriteteams/user/{userId}") // GET 2
    public List<FavoriteTeam> getFavoriteTeamsByUserId(@PathVariable("userId") Long userId) {
        return favoriteTeamRepository.findByUserId(userId);
    }

    @GetMapping("/favoriteteams/user/{userId}/team/{teamId}") // GET 3
    public FavoriteTeam getFavoriteTeamByUserIdAndTeamId(@PathVariable("userId") Long userId, @PathVariable("teamId") Long teamId) {
        return favoriteTeamRepository.findByUserIdAndTeamId(userId, teamId).orElse(null);
    }

    @PostMapping("/favoriteteams/user/{userId}/team/{teamId}") // POST 1
    public FavoriteTeam addFavoriteTeamByUserIdAndTeamId(@PathVariable("userId") Long userId, @PathVariable("teamId") Long teamId) {
        FavoriteTeam favoriteTeam = new FavoriteTeam();
        favoriteTeam.setUserId(userId);
        favoriteTeam.setTeamId(teamId);
        return favoriteTeamRepository.save(favoriteTeam);
    }

    @PutMapping("/favoriteteams/user/{userId}/team/{teamId}") // PUT 1
    public FavoriteTeam updateFavoriteTeam(@PathVariable("userId") Long userId, @PathVariable("teamId") Long teamId, @RequestBody FavoriteTeam updatedFavoriteTeam) {
        FavoriteTeam existing = favoriteTeamRepository.findByUserIdAndTeamId(userId, teamId).orElse(null);
        if (existing != null) {
            // update fields we allow to change
            existing.setUserId(updatedFavoriteTeam.getUserId());
            existing.setTeamId(updatedFavoriteTeam.getTeamId());
            return favoriteTeamRepository.save(existing);
        }
        return null;
    }

    @DeleteMapping("/favoriteteams/user/{userId}/team/{teamId}") // DELETE 1
    public String deleteFavoriteTeamByUserIdAndTeamId(@PathVariable("userId") Long userId, @PathVariable("teamId") Long teamId) {
        if (favoriteTeamRepository.existsByUserIdAndTeamId(userId, teamId)) {
            favoriteTeamRepository.deleteByUserIdAndTeamId(userId, teamId);
            return "FavoriteTeam with userId " + userId + " and teamId " + teamId + " deleted.";
        } else {
            return "FavoriteTeam with userId " + userId + " and teamId " + teamId + " not found.";
        }
    }

    @DeleteMapping("/favoriteteams/{id}") // DELETE 2
    public String deleteFavoriteTeamById(@PathVariable("id") Long id) {
        if (favoriteTeamRepository.existsById(id)) {
            favoriteTeamRepository.deleteById(id);
            return "FavoriteTeam with id " + id + " deleted.";
        } else {
            return "FavoriteTeam with id " + id + " not found.";
        }
    }
    // FavoriteTeam CRUD operations END
}
