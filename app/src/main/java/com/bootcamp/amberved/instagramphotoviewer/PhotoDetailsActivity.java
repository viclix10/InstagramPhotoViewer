package com.bootcamp.amberved.instagramphotoviewer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.makeramen.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by amber_ved on 2/7/15.
 */

public class PhotoDetailsActivity extends ActionBarActivity {
/*
    private static class ViewHolder {
        ImageView userImage;
        TextView userName;
        TextView time;
        ImageView image;
        TextView likes;
        TextView caption;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InstagramPhoto photo = (InstagramPhoto) getIntent().getSerializableExtra("photo");

        ViewHolder viewHolder;

        setContentView(R.layout.photo_details);

        viewHolder = new ViewHolder();

        TextView tvCaption = (TextView) findViewById(R.id.tvCaption);
        ImageView ivPhoto = (ImageView) findViewById(R.id.ivImage);
        TextView tvUserName = (TextView) findViewById(R.id.tvUserName);
        ImageView ivUserPhoto = (ImageView) findViewById(R.id.ivUserImage);
        TextView tvTime = (TextView) findViewById(R.id.tvTime);
        TextView tvLike = (TextView) findViewById(R.id.tvLike);
        TextView tvComments = (TextView) findViewById(R.id.tvComments);

        viewHolder.userImage = ivUserPhoto;
        viewHolder.userName = tvUserName;
        viewHolder.time = tvTime;
        viewHolder.image = ivPhoto;
        viewHolder.likes = tvLike;
        viewHolder.caption = tvCaption;


        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(30)
                .oval(false)
                .build();

        viewHolder.userImage.setImageResource(0);
        Picasso.with(this)
                .load(photo.userImageUrl)
                .fit()
                .transform(transformation)
                .into(viewHolder.userImage);

        viewHolder.userName.setText(photo.username);

        viewHolder.image.setImageResource(0);
        Picasso.with(this).load(photo.imageUrl)
                .fit()
                .centerInside()
                .into(viewHolder.image);

        viewHolder.likes.setText(Integer.toString(photo.likes) +
                this.getResources().getString(R.string.space) +
                this.getResources().getString(R.string.likes));

        viewHolder.caption.setText(photo.caption);


        return;
    }
    */
}
