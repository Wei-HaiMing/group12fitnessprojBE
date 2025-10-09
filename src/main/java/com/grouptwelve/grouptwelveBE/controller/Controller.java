package com.grouptwelve.grouptwelveBE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grouptwelve.grouptwelveBE.model.User;
import com.grouptwelve.grouptwelveBE.repository.FitnessUserRepository;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private FitnessUserRepository userRepository;

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
}
