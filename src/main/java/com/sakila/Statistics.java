package com.sakila;

import javax.persistence.*;

@Entity
@Table
public class Statistics {
    @Id
    @Column(name = "statisticId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statisticId;

    @Column(name = "singerId")
    private int singerId;

    @Column(name = "songId")
    private int songId;

    @Column(name = "weeksOnList")
    private int weeksOnList;

    @Column(name = "highestPlacement")
    private int highestPlacement;

    public Statistics(){

    }

    public Statistics(int statisticId, int singerId, int songId, int weeksOnList, int highestPlacement) {
        this.statisticId = statisticId;
        this.singerId = singerId;
        this.songId = songId;
        this.weeksOnList = weeksOnList;
        this.highestPlacement = highestPlacement;
    }

    public int getStatisticId() {
        return statisticId;
    }

    public void setStatisticId(int statisticId) {
        this.statisticId = statisticId;
    }

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getWeeksOnList() {
        return weeksOnList;
    }

    public void setWeeksOnList(int weeksOnList) {
        this.weeksOnList = weeksOnList;
    }

    public int getHighestPlacement() {
        return highestPlacement;
    }

    public void setHighestPlacement(int highestPlacement) {
        this.highestPlacement = highestPlacement;
    }
}
