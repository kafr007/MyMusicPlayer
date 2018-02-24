package com.chimpee.com.mymusicplayer;

import java.util.ArrayList;

/**
 * Created by Csimpi on 2018.02.21..
 */

public class Album {
    private String albumName;
    private String artist;
    private ArrayList<Song> songs;

    public Album(String albumName, String artist, ArrayList<Song> songs) {
        this.albumName = albumName;
        this.artist = artist;
        this.songs = songs;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
}
