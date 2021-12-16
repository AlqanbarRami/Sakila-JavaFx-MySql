package com.sakila;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column(name = "store_id")
    private int storeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "address_id")
    private int addressId;

    @Column(name = "active")
    private int active;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "last_update")
    private int lastUpdate;

    public Customer(){

    }

    public Customer(int customerId, int storeId, String firstName, String lastName, String email, int addressId, int active, String createDate, int lastUpdate) {
        this.customerId = customerId;
        this.storeId = storeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressId = addressId;
        this.active = active;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }
}
