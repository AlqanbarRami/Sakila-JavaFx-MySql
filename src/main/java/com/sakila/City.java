package com.sakila;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class City {

    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cityId;

    @Column(name = "city")
    private String city;

    @Column(name = "country_id")
    private int countryId;

    @Column(name = "last_update")
    private int lastUpdate;

    public City(){

    }

    public City(int cityId, String city, int countryId, int lastUpdate) {
        this.cityId = cityId;
        this.city = city;
        this.countryId = countryId;
        this.lastUpdate = lastUpdate;
    }
}
