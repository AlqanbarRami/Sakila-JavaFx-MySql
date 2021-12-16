package com.sakila;

import javax.persistence.Column;

public class FilmCategory {

    @Column(name = "film_id")
    private int filmId;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "last_update")
    private int lastUpdate;

    public FilmCategory(){

    }

    public FilmCategory(int filmId, int categoryId, int lastUpdate) {
        this.filmId = filmId;
        this.categoryId = categoryId;
        this.lastUpdate = lastUpdate;
    }
}
