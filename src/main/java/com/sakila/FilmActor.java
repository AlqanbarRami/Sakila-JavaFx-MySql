package com.sakila;

import javax.persistence.Column;


public class FilmActor {


    @Column(name = "actor_id")
    private int actorId;

    @Column(name = "film_id")
    private int filmId;

    @Column(name = "last_update")
    private int lastUpdate;

    public FilmActor(){

    }

    public FilmActor(int actorId, int filmId, int lastUpdate) {
        this.actorId = actorId;
        this.filmId = filmId;
        this.lastUpdate = lastUpdate;
    }
}
