package com.chimpee.com.mymusicplayer;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Csimpi on 2018.02.21..
 */

public class SongAdapter extends ArrayAdapter<Song>  {
    private static final String LOG_TAG = SongAdapter.class.getSimpleName();

    public SongAdapter(Activity context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.song_list_item, parent, false);
        }
        // Get the {@link song} object located at this position in the list
        Song currentSong = getItem(position);
        // Find the TextView in the song_list_item.xml layout with the ID version_name
        TextView tracksNameTextView = (TextView)listItemView.findViewById(R.id.track_name_textview);
        // Get the version name from the current song object and set this text on the name TextView
        tracksNameTextView.setText(currentSong.getTitle());
        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView artistNameTextView = (TextView)listItemView.findViewById(R.id.artist_name_textview);
        // Get the version name from the current song object and set this text on the name TextView
        artistNameTextView.setText(currentSong.getArtist());
        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView durationTextView = (TextView)listItemView.findViewById(R.id.duration_textview);
        // Get the version name from the current song object and set this text on the name TextView
        durationTextView.setText(currentSong.getDuration());

        return listItemView;

    }
}
