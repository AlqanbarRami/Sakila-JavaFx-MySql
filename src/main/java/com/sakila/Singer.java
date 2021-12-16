package com.sakila;

import javax.persistence.*;

@Entity
@Table
public class Singer {
    @Id
    @Column(name = "singerId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int singerId;

    @Column(name = "singerName")
    private String singerName;

    public Singer(){

    }

    public Singer(int singerId, String singerName) {
        this.singerId = singerId;
        this.singerName = singerName;
    }

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }
}
