package com.sakila;

import javax.persistence.*;

@Entity
@Table
public class Song {
    @Id
    @Column(name = "songId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int songId;

    @Column(name = "singerId")
    private String singerId;

    @Column(name = "songName")
    private String songName;

    public Song(){

    }

    public Song(int songId, String singerId, String songName) {
        this.songId = songId;
        this.singerId = singerId;
        this.songName = songName;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSingerId() {
        return singerId;
    }

    public void setSingerId(String singerId) {
        this.singerId = singerId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
}
