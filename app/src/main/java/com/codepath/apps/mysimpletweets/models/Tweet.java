package com.codepath.apps.mysimpletweets.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by yaldahakki on 2015-12-13.
 */

//Parse the JSON and Store the data
public class Tweet {

    private String body;
    private long uid; //unique id for the tweet
    private User user;
    private String createdAt;

    public String getBody() {
        return body;
    }

    public User getUser() {
        return user;
    }

    public long getUid() {

        return uid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    //deserialize the JSON
    public static Tweet fromJSON (JSONObject jsonObject){
        Tweet tweet = new Tweet();
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid=jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user=User.fromJson(jsonObject.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tweet;
    }
    public static ArrayList<Tweet> fromJSONArray (JSONArray jsonArray){
        ArrayList<Tweet> tweets = new ArrayList<>();
        for (int i=0;i<jsonArray.length();i++){
            try {
                JSONObject tweetJson = jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(tweetJson);
                if ( tweet != null ){
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }


        }

        return tweets;
    }
}
