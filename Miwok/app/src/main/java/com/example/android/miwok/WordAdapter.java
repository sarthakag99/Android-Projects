package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    /**Resource ID for the bakground color for this list of words*/
    private int mColorResourceId;



    public WordAdapter(Activity context, ArrayList<Word> words,int colorResourceId){
        super(context,0,words);
        mColorResourceId = colorResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Word currentWord =getItem(position);


        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView miwokTextView = (TextView)listItemView.findViewById(R.id.miwok_text_view);
        // Get the Miwok translation from the currentWord object and set this text on the Miwok TextView
        miwokTextView.setText((currentWord.getMiwokTranslation()));


        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView defaultTextView = (TextView)listItemView.findViewById(R.id.default_text_view);
        // Get the default translation from the currentWord object and set this text on the default TextView
        defaultTextView.setText((currentWord.getDefaultTranslation()));


        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView imageView = (ImageView)listItemView.findViewById(R.id.image);

        if(currentWord.hasImage()) {
            // Set the ImageView to the image resource specified in the current word
            imageView.setImageResource(currentWord.getmImageResourceId());

            //Make sure the View is Visible
            imageView.setVisibility(View.VISIBLE);
        }
        else{
            //Otherwise hide the ImageView (set the visibility to GONE)
            imageView.setVisibility(View.GONE);
        }

        //Set the Theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_continer);
        //Find the color that the resource ID maps to
        int color =ContextCompat.getColor(getContext(),mColorResourceId);
        //Set the background color of the text conntainer View
        textContainer.setBackgroundColor(color);


        // Return the whole list item layout (containing 2 TextViews) so thet it can be shown in the ListView
        return  listItemView;


    }
}
