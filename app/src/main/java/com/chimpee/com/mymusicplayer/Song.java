package com.chimpee.com.mymusicplayer;

/**
 * Created by Csimpi on 2018.02.21..
 */

public class Song {
    private String title;
    private String artist;
    private String albumName;
    private String duration;

    public Song(String title, String artist, String albumName, String duration) {
        this.title = title;
        this.artist = artist;
        this.albumName = albumName;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getDuration(){
        return duration;
    }

    public void setDuration(String duration){
        this.duration = duration;
    }
}
