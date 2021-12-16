package com.sakila;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Language {

    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int languageId;

    @Column(name = "name")
    private String name;

    @Column(name = "last_update")
    private int lastUpdate;

    public Language(){

    }

    public Language(int languageId, String name, int lastUpdate) {
        this.languageId = languageId;
        this.name = name;
        this.lastUpdate = lastUpdate;
    }
}
