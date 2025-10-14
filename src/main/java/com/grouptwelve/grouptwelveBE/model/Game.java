package com.grouptwelve.grouptwelveBE.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "games")
public class Game {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String league = "NFL";
  private String homeTeam;
  private String awayTeam;

  private LocalDateTime startTime;
  private String status = "scheduled"; // scheduled | in_progress | completed | cancelled

  @Column(precision = 10, scale = 2)
  private BigDecimal oddsHome;

  @Column(precision = 10, scale = 2)
  private BigDecimal oddsAway;

  public Game() {}

  // getters/setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getLeague() { return league; }
  public void setLeague(String league) { this.league = league; }
  public String getHomeTeam() { return homeTeam; }
  public void setHomeTeam(String homeTeam) { this.homeTeam = homeTeam; }
  public String getAwayTeam() { return awayTeam; }
  public void setAwayTeam(String awayTeam) { this.awayTeam = awayTeam; }
  public LocalDateTime getStartTime() { return startTime; }
  public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
  public BigDecimal getOddsHome() { return oddsHome; }
  public void setOddsHome(BigDecimal oddsHome) { this.oddsHome = oddsHome; }
  public BigDecimal getOddsAway() { return oddsAway; }
  public void setOddsAway(BigDecimal oddsAway) { this.oddsAway = oddsAway; }
}
