package com.grouptwelve.grouptwelveBE.model;

import jakarta.persistence.*;

@Entity
@Table(name = "football_teams")
public class FootballTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String homeTeam;

    @Column(nullable = false)
    private String awayTeam;

    @Column(nullable = false)
    private int homeScore;

    @Column(nullable = false)
    private int awayScore;

    @Column(nullable = false)
    private int homeTouchdowns;

    @Column(nullable = false)
    private int awayTouchdowns;

    @Column(nullable = false)
    private int homeFieldGoals;

    @Column(nullable = false)
    private int awayFieldGoals;

    // Some nice Constructors :)
    public FootballTeam() {}

    public FootballTeam(String homeTeam, String awayTeam, int homeScore, int awayScore,int homeTouchdowns, int awayTouchdowns,int homeFieldGoals, int awayFieldGoals) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.homeTouchdowns = homeTouchdowns;
        this.awayTouchdowns = awayTouchdowns;
        this.homeFieldGoals = homeFieldGoals;
        this.awayFieldGoals = awayFieldGoals;
    }

    // Getters and setters
    public Long getId() { return id; }

    public String getHomeTeam() { return homeTeam; }
    public void setHomeTeam(String homeTeam) { this.homeTeam = homeTeam; }

    public String getAwayTeam() { return awayTeam; }
    public void setAwayTeam(String awayTeam) { this.awayTeam = awayTeam; }

    public int getHomeScore() { return homeScore; }
    public void setHomeScore(int homeScore) { this.homeScore = homeScore; }

    public int getAwayScore() { return awayScore; }
    public void setAwayScore(int awayScore) { this.awayScore = awayScore; }

    public int getHomeTouchdowns() { return homeTouchdowns; }
    public void setHomeTouchdowns(int homeTouchdowns) { this.homeTouchdowns = homeTouchdowns; }

    public int getAwayTouchdowns() { return awayTouchdowns; }
    public void setAwayTouchdowns(int awayTouchdowns) { this.awayTouchdowns = awayTouchdowns; }

    public int getHomeFieldGoals() { return homeFieldGoals; }
    public void setHomeFieldGoals(int homeFieldGoals) { this.homeFieldGoals = homeFieldGoals; }

    public int getAwayFieldGoals() { return awayFieldGoals; }
    public void setAwayFieldGoals(int awayFieldGoals) { this.awayFieldGoals = awayFieldGoals; }
}
