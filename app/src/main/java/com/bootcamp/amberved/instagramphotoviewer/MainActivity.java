package com.bootcamp.amberved.instagramphotoviewer;

import android.app.Activity;
import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends Activity {

    static final String ACCESS_TOKEN = "25699d4557ac4bd8b79935597d7d7254";

    private ArrayList<InstagramPhoto> photos;
    private InstagramPhotosAdapter photoAdapter;
    private SwipeRefreshLayout swipeContainer;
    private ListView lvPhotos ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photos = new ArrayList<>();
        photoAdapter = new InstagramPhotosAdapter(this, photos);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        lvPhotos = (ListView) findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(photoAdapter);
        fetchInstagramPhotos();
        setupListViewListener();

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchInstagramPhotos();
            }
        });

    }

    private void setupListViewListener() {
        lvPhotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this, "OnClick", Toast.LENGTH_SHORT).show();
                InstagramPhoto photo = photos.get(position);

                //Intent i = new Intent(MainActivity.this, PhotoDetailsActivity.class);
                //i.putExtra("photo", photo);
                //startActivity(i);
            }
        });
    }

    private void fetchInstagramPhotos()
    {
        String url = "https://api.instagram.com/v1/media/popular?client_id=" + ACCESS_TOKEN;
        //String url = "https://www.google.com";

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                swipeContainer.setRefreshing(false);
                Log.i("DEBUG",response.toString());
                JSONArray photosJson = null;
                try {
                    photosJson = response.getJSONArray("data");
                    photos.clear();
                    for(int i = 0 ; i < photosJson.length(); i++){
                    //for(int i = 0 ; i < 2; i++) {
                        JSONObject photoJson = photosJson.getJSONObject(i);
                        InstagramPhoto photo = new InstagramPhoto();
                        photo.username = photoJson.getJSONObject("user").getString("username");
                        //photo.userImageUrl = photoJson.getJSONObject("user").getString("profile_picture");
                        photo.caption = photoJson.getJSONObject("caption").getString("text");
                        photo.imageUrl = photoJson.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
                        photo.imageHeight = photoJson.getJSONObject("images").getJSONObject("standard_resolution").getInt("height");
                        photo.likes = photoJson.getJSONObject("likes").getInt("count");

                        photos.add(photo);
                    }

                } catch (JSONException ex){
                    ex.printStackTrace();
                }

                photoAdapter.notifyDataSetChanged();

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
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
