package com.sakila;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Rental {
    @Id
    @Column(name = "rental_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rentalId;

    @Column(name = "rental_date")
    private String rentalDate;

    @Column(name = "inventory_id")
    private int inventoryId;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "return_date")
    private String returnDate;

    @Column(name = "staff_id")
    private int staffId;

    @Column(name = "last_update")
    private int lastUpdate;

    public Rental(){

    }

    public Rental(int rentalId, String rentalDate, int inventoryId, int customerId, String returnDate, int staffId, int lastUpdate) {
        this.rentalId = rentalId;
        this.rentalDate = rentalDate;
        this.inventoryId = inventoryId;
        this.customerId = customerId;
        this.returnDate = returnDate;
        this.staffId = staffId;
        this.lastUpdate = lastUpdate;
    }
}
