package com.sakila;

import javax.persistence.*;

@Entity
@Table
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @Column(name = "address")
    private String address;

    @Column(name = "address2")
    private String address2;

    @Column(name = "district")
    private String district;

    @Column(name = "city_id")
    private int cityId;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "phone")
    private String phone;

    @Column(name = "location")
    private String location;

    @Column(name = "last_update")
    private int lastUpdate;

    public Address(){

    }

    public Address(int addressId, String address, String address2, String district, int cityId, String postalCode, String phone, String location, int lastUpdate) {
        this.addressId = addressId;
        this.address = address;
        this.address2 = address2;
        this.district = district;
        this.cityId = cityId;
        this.postalCode = postalCode;
        this.phone = phone;
        this.location = location;
        this.lastUpdate = lastUpdate;
    }
}
