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

public class Albums extends AppCompatActivity {
    TextView titleTextView;
    TextView artistTextView;
    int pos = 0;
    ArrayList<Album> albums;
    ListView listView;
    ArrayList<Song> songs;
    ImageView playPauseButton;
    ImageView backwardButton;
    ImageView forwardButton;
    Drawable drawable;
    int listSize;
    DataBase db = new DataBase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

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
        //create an albumadapter to show the albums in a list
        AlbumAdapter albumAdapter = new AlbumAdapter(this, albums);

        listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(albumAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   Intent intent = new Intent(Albums.this, Albums2.class);
                   intent.putExtra("albumPosition", position);
                   intent.putExtra("position", pos);
                    startActivity(intent);

            }
        });

       //Set a listener, so if the song now plays and we touch the button it pause, or if it pauses, it starts to play
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
    }
}
