package com.codepath.apps.mysimpletweets;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TimelineActivity extends AppCompatActivity {
    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    private ListView lvTweets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        //Find the list view
        lvTweets = (ListView)findViewById(R.id.lvTweets);
        //create the arraylist (data source)
        tweets = new ArrayList<>();
        //Construct the adapter to list view
        aTweets = new TweetsArrayAdapter(this,tweets);
        //Connect adapter to list view
        lvTweets.setAdapter(aTweets);
        //client is a singleton client i.e. we will be using the same client across all our activities
        client =  TwitterApplication.getRestClient();
        populateTimeLine();


    }
    //send an api request to get the timeline json
    //Fill the listview by creating the tweet objects from the json
    private void populateTimeLine(){
        client.getHomeTimeline(new JsonHttpResponseHandler(){
            //SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
            //public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //super.onSuccess(statusCode, headers, response);
                //Log.d("Debug",response.toString());
                //DESERIALIZE JSON
                //CREATE MODELS AND ADD THEM TO THE ADAPTER
                //LOAD THE MODEL DATA INTO LISTVIEW
                aTweets.addAll(Tweet.fromJSONArray(response));

            }

            //FAILURE


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                //super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d("Debug",errorResponse.toString());
            }
        });


    }

}
