package com.sakila;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Payment {

    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "staff_id")
    private int staffId;

    @Column(name = "rental_id")
    private int rentalId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "payment_date")
    private String paymentDate;

    @Column(name = "last_update")
    private int lastUpdate;

    public Payment(){

    }

    public Payment(int paymentId, int customerId, int staffId, int rentalId, double amount, String paymentDate, int lastUpdate) {
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.staffId = staffId;
        this.rentalId = rentalId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.lastUpdate = lastUpdate;
    }
}
