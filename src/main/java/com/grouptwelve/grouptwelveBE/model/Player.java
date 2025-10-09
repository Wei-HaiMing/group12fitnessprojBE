package com.grouptwelve.grouptwelveBE.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "players")
public class Player {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String team;

    @Column(nullable = false)
    private String position;

    @Column(name = "jersey_number")
    private int jerseyNumber;

    @Column(name = "touchdowns")
    private int touchdowns;

    @Column(name = "total_yards")
    private int totalYards;

    @Column(name = "games_played")
    private int gamesPlayed;

    @Column(name = "field_goals")
    private int fieldGoals;

    @Column(name = "completion_rate")
    private double completionRate;

    // Default constructor
    public Player() {
        this.touchdowns = 0;
        this.totalYards = 0;
        this.gamesPlayed = 0;
        this.fieldGoals = 0;
        this.completionRate = 0.0;
    }

    // Constructor with fields
    public Player(String name, String team, String position, int jerseyNumber) {
        this.name = name;
        this.team = team;
        this.position = position;
        this.jerseyNumber = jerseyNumber;
        this.touchdowns = 0;
        this.totalYards = 0;
        this.gamesPlayed = 0;
        this.fieldGoals = 0;
        this.completionRate = 0.0;
    }

    // Getters and Setters
    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public int getTouchdowns() {
        return touchdowns;
    }

    public void setTouchdowns(int touchdowns) {
        this.touchdowns = touchdowns;
    }

    public int getTotalYards() {
        return totalYards;
    }

    public void setTotalYards(int totalYards) {
        this.totalYards = totalYards;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getFieldGoals() {
        return fieldGoals;
    }

    public void setFieldGoals(int fieldGoals) {
        this.fieldGoals = fieldGoals;
    }

    public double getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(double completionRate) {
        this.completionRate = completionRate;
    }
}