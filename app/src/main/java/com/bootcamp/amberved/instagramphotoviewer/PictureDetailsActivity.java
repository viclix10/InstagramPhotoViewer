package com.bootcamp.amberved.instagramphotoviewer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.makeramen.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class PictureDetailsActivity extends ActionBarActivity {

    ImageView image;
    ImageView userImg;

    TextView username;
    TextView likes;
    TextView caption;
    TextView comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_details);

        InstagramPhoto photo = (InstagramPhoto) getIntent().getSerializableExtra("photo");

        ImageView ivPhoto = (ImageView) findViewById(R.id.ivImage);
        image = ivPhoto;

        ImageView ivUserImg = (ImageView) findViewById(R.id.ivUserImg);
        userImg = ivUserImg;

        TextView tvUsername = (TextView) findViewById(R.id.tvUsername);
        username = tvUsername;

        TextView tvLikes = (TextView) findViewById(R.id.tvLikes);
        likes = tvLikes;

        TextView tvCaption = (TextView) findViewById(R.id.tvCaption);
        caption = tvCaption;

        TextView tvComment = (TextView) findViewById(R.id.tvComment);
        comments = tvComment;
        tvComment.setMovementMethod(new ScrollingMovementMethod());

        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(30)
                .oval(false)
                .build();

        userImg.setImageResource(0);
        Picasso.with(this)
                .load(photo.userImageUrl)
                .fit()
                .transform(transformation)
                .into(userImg);
        username.setText(photo.username);

        likes.setText(Integer.toString((photo.likes)));

        image.setImageResource(0);
        Picasso.with(this).load(photo.imageUrl)
                .fit()
                .centerInside()
                .into(image);

        caption.setText(photo.caption);

        getComments(photo.id);

    }

    String commentStr = "Comments -> \n";
    static final String ACCESS_TOKEN = "25699d4557ac4bd8b79935597d7d7254";

    private void getComments(String id)
    {
        String url = "https://api.instagram.com/v1/media/"+id+"/comments?client_id="+ACCESS_TOKEN;
        //String url = "https://www.google.com";
        Log.i("PictureDetailsActivity","Comment URL= " + url);

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //Log.i("PictureDetailsActivity","response = " + response.toString());
                JSONArray commentsJson = null;
                try {
                    commentsJson = response.getJSONArray("data");
                    for (int i = 0; i < commentsJson.length(); i++) {
                        //for(int i = 0 ; i < 2; i++) {
                        JSONObject commentJson = commentsJson.getJSONObject(i);
                        String s = commentJson.getString("text");
                        Log.i("PictureDetailsActivity","comment"+i+"="+s);

                        commentStr += (s+"\n");
                    }

                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
                comments.setText(commentStr);//TODO


            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i("ERROR", responseString);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_picture_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
