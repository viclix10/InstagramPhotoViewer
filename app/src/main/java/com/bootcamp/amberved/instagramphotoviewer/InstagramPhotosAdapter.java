package com.bootcamp.amberved.instagramphotoviewer;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import junit.framework.Test;

import org.w3c.dom.Text;

import java.util.List;

public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {

    private static class ViewHolder {
        ImageView image;
        TextView username;
        TextView likes;
        TextView caption;
    }

    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        InstagramPhoto photo = getItem(position);
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.photos, parent, false);
            ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivImage);
            viewHolder.image = ivPhoto;

            TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
            viewHolder.username = tvUsername;

            TextView tvLikes = (TextView) convertView.findViewById(R.id.tvLikes);
            viewHolder.likes = tvLikes;

            TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
            viewHolder.caption = tvCaption;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.image.setImageResource(0);
        Picasso.with(getContext()).load(photo.imageUrl)
                .fit()
                .centerInside()
                .into(viewHolder.image);

        viewHolder.username.setText(photo.username);
        viewHolder.likes.setText(Integer.toString((photo.likes)));
        viewHolder.caption.setText(photo.caption);

        return convertView;
    }
}

