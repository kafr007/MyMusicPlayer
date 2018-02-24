package com.chimpee.com.mymusicplayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Tracks extends AppCompatActivity {
    TextView titleTextView;
    TextView artistTextView;
    int pos;
    ArrayList<Song> songs;
    ListView listView;
    ArrayList<Album> albums;
    ImageView playPauseButton;
    ImageView backwardButton;
    ImageView forwardButton;
    Drawable drawable;
    DataBase db = new DataBase();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks);

        //set the label of the activity
        setTitle("Tracks");

        titleTextView = (TextView)findViewById(R.id.title_textview);
        artistTextView = (TextView)findViewById(R.id.artist_textview);

        //Find the play and pause button view
        playPauseButton = (ImageView)findViewById(R.id.play_button);

        //Find the backward button
        backwardButton = (ImageView)findViewById(R.id.backward_button);

        //Find the forward button
        forwardButton = (ImageView)findViewById(R.id.forward_button);

        listView = (ListView)findViewById(R.id.list);
        //get the position of the song from the albums activity
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            pos = extras.getInt("position");
            }
        //get the albums from the database
        albums = db.getAlbums();
        //get the songs from the database
        songs = db.getSongs();
        //set the title and the artist in the textview
        titleTextView.setText(songs.get(pos).getTitle());
        artistTextView.setText(songs.get(pos).getArtist());
        //create a songadapter to show the songs in a listview
        SongAdapter songAdapter = new SongAdapter(this, songs);

        listView.setAdapter(songAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                titleTextView.setText(songs.get(position).getTitle());
                artistTextView.setText(songs.get(position).getArtist());
            }
        });

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

        //Set a listener to the backward button, if it is the first song it changes to the last song, anyway it plays the song before it.
        backwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos == 0){
                    pos = songs.size()-1;
                    titleTextView.setText(songs.get(pos).getTitle());
                    artistTextView.setText(songs.get(pos).getArtist());
                }
                else
                    pos-= 1;
                titleTextView.setText(songs.get(pos).getTitle());
                artistTextView.setText(songs.get(pos).getArtist());
            }
        });

        //Set a listener to the forward button, if it is the last song it changes to the first song, anyway it plays the song after it.
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos == songs.size()-1){
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",pos);
        setResult(this.RESULT_OK,returnIntent);
        finish();
    }
}
