package com.sakila;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Staff {
    @Id
    @Column(name = "staff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int staffId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address_id")
    private int adressId;

    @Column(name = "picture")
    private String picture;

    @Column(name = "email")
    private String email;

    @Column(name = "store_id")
    private int storeId;

    @Column(name = "active")
    private int active;

    @Column(name = "username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name = "last_update")
    private int lastUpdate;

    public Staff(){

    }

    public Staff(int staffId, String firstName, String lastName, int adressId, String picture, String email, int storeId, int active, String username, String password, int lastUpdate) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adressId = adressId;
        this.picture = picture;
        this.email = email;
        this.storeId = storeId;
        this.active = active;
        this.username = username;
        this.password = password;
        this.lastUpdate = lastUpdate;
    }
}
