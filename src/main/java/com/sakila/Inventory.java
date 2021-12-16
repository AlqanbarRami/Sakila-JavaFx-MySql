package com.sakila;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Inventory {
    @Id
    @Column(name = "inventory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int inventoryId;

    @Column(name = "film_id")
    private int filmId;

    @Column(name = "store_id")
    private int storeId;

    @Column(name = "last_update")
    private int lastUpdate;

    public Inventory(){

    }

    public Inventory(int inventoryId, int filmId, int storeId, int lastUpdate) {
        this.inventoryId = inventoryId;
        this.filmId = filmId;
        this.storeId = storeId;
        this.lastUpdate = lastUpdate;
    }
}
