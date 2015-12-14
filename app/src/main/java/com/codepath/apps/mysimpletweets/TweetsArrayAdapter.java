package com.codepath.apps.mysimpletweets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by yaldahakki on 2015-12-13.
 */
//Taking the Tweet objects and turning them into views displayed in the list
public class TweetsArrayAdapter extends ArrayAdapter<Tweet>{


    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context,android.R.layout.simple_list_item_1, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1.get the tweets
        Tweet tweet = getItem(position);
        // 2.find or inflate the template
        if (convertView == null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_tweet,parent,false);
        }
        //3.Find the subwies to fill with data in the temolate
        ImageView ivProfileImage = (ImageView) convertView.findViewById(R.id.ivProfileImage);
        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        TextView tvBody = (TextView) convertView.findViewById(R.id.tvBody);
        //4.Populate data into the subviews
        tvUserName.setText(tweet.getUser().getName());
        tvBody.setText(tweet.getBody());
        //clear out an old image for a recycled view
        ivProfileImage.setImageResource(android.R.color.transparent);
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivProfileImage);
        //5.Return the view to be inserted into the list
        return convertView;
    }
}
