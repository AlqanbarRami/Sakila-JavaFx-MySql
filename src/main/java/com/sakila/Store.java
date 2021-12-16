package com.sakila;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Store {
    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeId;

    @Column(name = "manager_staff_id")
    private int managerStaffId;

    @Column(name = "address_id")
    private int addressId;

    @Column(name = "last_update")
    private int lastUpdate;

    public Store(){

    }

    public Store(int storeId, int managerStaffId, int addressId, int lastUpdate) {
        this.storeId = storeId;
        this.managerStaffId = managerStaffId;
        this.addressId = addressId;
        this.lastUpdate = lastUpdate;
    }
}
