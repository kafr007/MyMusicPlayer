package com.chimpee.com.mymusicplayer;

import java.util.ArrayList;

/**
 * Created by Csimpi on 2018.02.22..
 */

public class DataBase {
    private ArrayList<Album> albums = new ArrayList<Album>();
    private Album enyaBlue;
    private Album enyaGreen;
    private Album dmWhite;
    private ArrayList<Song> songs = new ArrayList<Song>();

    public DataBase(){
        songs.add(new Song("Blue", "Enya", "Blue", "3:24"));
        songs.add(new Song("White", "Enya", "Blue", "3:24"));
        songs.add(new Song("Green", "Enya", "Blue", "3:24"));
        songs.add(new Song("Gray", "Enya", "Blue", "3:24"));
        songs.add(new Song("Lightblue", "Enya", "Blue", "3:24"));
        songs.add(new Song("Blach", "Enya", "Blue", "3:24"));
        songs.add(new Song("Yellow", "Enya", "Blue", "3:24"));
        songs.add(new Song("Orange", "Enya", "Blue", "3:24"));
        songs.add(new Song("Blue", "Enya", "Green", "3:24"));
        songs.add(new Song("Blue", "Enya", "Green", "3:24"));
        songs.add(new Song("Yellow", "Depeche Mode", "White", "3:24"));
        songs.add(new Song("Orange", "Depeche Mode", "White", "3:24"));
        songs.add(new Song("Blue", "Depeche Mode", "White", "3:24"));
        songs.add(new Song("Blue", "Depeche Mode", "White", "3:24"));

        ArrayList<Song> enyaBlueSongs = new ArrayList<>();
        ArrayList<Song> enyaGreenSongs = new ArrayList<>();
        ArrayList<Song> dmWhiteSongs = new ArrayList<>();
        for (int i = 0; i<songs.size(); i++){
            if (songs.get(i).getAlbumName().equals("Blue")){
                enyaBlueSongs.add(songs.get(i));
            }
            if (songs.get(i).getAlbumName().equals("Green")){
                enyaGreenSongs.add(songs.get(i));
            }
            if (songs.get(i).getAlbumName().equals("White")){
                dmWhiteSongs.add(songs.get(i));
            }

        }
        enyaBlue = new Album("Blue", "Enya", enyaBlueSongs);
        enyaGreen = new Album("Green", "Enya", enyaGreenSongs);
        dmWhite = new Album("White", "Depeche Mode", dmWhiteSongs);


        albums.add(enyaBlue);
        albums.add(enyaGreen);
        albums.add(dmWhite);
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public Album getEnyaBlue() {
        return enyaBlue;
    }

    public Album getEnyaGreen() {
        return enyaGreen;
    }

    public Album getDmWhite() {
        return dmWhite;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
}
