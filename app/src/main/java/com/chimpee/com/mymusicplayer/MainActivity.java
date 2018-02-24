package com.chimpee.com.mymusicplayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    DataBase db = new DataBase();
    TextView titleTextView;
    TextView artistTextView;
    TextView tracks;
    TextView albums;
    String playingText = "";
    ArrayList<Song> songs;
    ImageView playPauseButton;
    ImageView backwardButton;
    ImageView forwardButton;
    ListView listView;
    SongAdapter songAdapter;
    Drawable drawable;
    int pos =0;
    int listSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the songs arraylist from the database
        songs = db.getSongs();

        //Find views, which are shows the title and the artist
        titleTextView = (TextView)findViewById(R.id.title_textview);
        artistTextView = (TextView)findViewById(R.id.artist_textview);

        //Set the title and the artist, when you first enter the program
        titleTextView.setText(songs.get(pos).getTitle());
        artistTextView.setText(songs.get(pos).getArtist());

        //Find the play and pause button view
        playPauseButton = (ImageView)findViewById(R.id.play_button);

        //Set a listener, so if the song now play and we touch the button it pause, or if it is pause, it starts to play
        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawable = playPauseButton.getDrawable();
                if (drawable.getConstantState().equals(getResources().getDrawable(R.drawable.play).getConstantState())){
                    playPauseButton.setImageResource(R.drawable.pause);
                }
                else {
                    playPauseButton.setImageResource(R.drawable.play);
                }
            }
        });
        //Size of the songs list
        listSize = songs.size();

        //Find the backward button
        backwardButton = (ImageView)findViewById(R.id.backward_button);

        //Set a listener to the backward button, if it is the first song it changes to the last song, anyway it plays the song before it.
        backwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos == 0){
                    pos = listSize-1;
                    titleTextView.setText(songs.get(pos).getTitle());
                    artistTextView.setText(songs.get(pos).getArtist());
                }
                else
                    pos-= 1;
                    titleTextView.setText(songs.get(pos).getTitle());
                    artistTextView.setText(songs.get(pos).getArtist());
            }
        });

        //Find the forward button
        forwardButton = (ImageView)findViewById(R.id.forward_button);

        //Set a listener to the forward button, if it is the last song it changes to the first song, anyway it plays the song after it.
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos == listSize-1){
                    pos = 0;
                    titleTextView.setText(songs.get(pos).getTitle());
                    artistTextView.setText(songs.get(pos).getArtist());
                }
                else{
                    pos+= 1;
                    titleTextView.setText(songs.get(pos).getTitle());
                    artistTextView.setText(songs.get(pos).getArtist());
                }
            }
        });
        //create a songadapter to show the songs in a listview
        songAdapter = new SongAdapter(this, songs);

        listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(songAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    titleTextView.setText(songs.get(position).getTitle());
                    artistTextView.setText(songs.get(position).getArtist());
                    pos = position;
                    playPauseButton.setImageResource(R.drawable.pause);
            }
        });

        // Find the View that shows the tracks category
        tracks = (TextView)findViewById(R.id.tracks_textview);

        // Set a click listener on the tracks View
        tracks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tracksIntent = new Intent(MainActivity.this, Tracks.class);
                tracksIntent.putExtra("position", pos);
                startActivityForResult(tracksIntent,1);
            }
        });

        // Find the View that shows the albums category
        albums = (TextView)findViewById(R.id.albums_textview);

        // Set a click listener on the albums View
        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent albumsIntent = new Intent(MainActivity.this, Albums.class);
                albumsIntent.putExtra("position", pos);
                startActivityForResult(albumsIntent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == this.RESULT_OK){
                pos =data.getIntExtra("result", 0);
                titleTextView.setText(songs.get(pos).getTitle());
                artistTextView.setText(songs.get(pos).getArtist());
            }
            if (resultCode == this.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}


