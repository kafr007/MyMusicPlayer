package com.chimpee.com.mymusicplayer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Csimpi on 2018.02.21..
 */

public class AlbumAdapter extends ArrayAdapter<Album>  {
    private static final String LOG_TAG = AlbumAdapter.class.getSimpleName();

    public AlbumAdapter(Activity context, ArrayList<Album> albums) {
        super(context, 0, albums);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.album_list_item, parent, false);
        }
        // Get the {@link album} object located at this position in the list
        Album currentAlbum = getItem(position);
        // Find the TextView in the album_list_item.xml layout with the ID version_name
        TextView albumsNameTextView = (TextView)listItemView.findViewById(R.id.album_name_textview);
        // Get the version name from the current album object and set this text on the name TextView
        albumsNameTextView.setText(currentAlbum.getAlbumName());
        // Find the TextView in the album_item.xml layout with the ID version_name
        TextView artistNameTextView = (TextView)listItemView.findViewById(R.id.artist_name_textview);
        // Get the version name from the current album object and set this text on the name TextView
        artistNameTextView.setText(currentAlbum.getArtist());
        // Find the TextView in the list_item.xml layout with the ID version_name


        return listItemView;

    }
}
