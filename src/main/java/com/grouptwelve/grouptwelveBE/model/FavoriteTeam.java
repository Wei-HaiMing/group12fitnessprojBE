package com.grouptwelve.grouptwelveBE.model;

import jakarta.persistence.*;

@Entity
@Table(name="favoriteTeam")
public class FavoriteTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteTeamId;

    private Long userId;
    private Long teamId;

    public FavoriteTeam(){}

    public FavoriteTeam(Long userId, Long teamId){
        this.userId = userId;
        this.teamId = teamId;
    }

    public Long getFavoriteTeamId() {
        return favoriteTeamId;
    }

    public void setFavoriteTeamId(Long favoriteTeamId) {
        this.favoriteTeamId = favoriteTeamId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
