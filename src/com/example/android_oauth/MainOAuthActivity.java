package com.example.android_oauth;

import org.json.JSONException;

import com.example.oauth_example.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainOAuthActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oauth_main);
        
        Button launchOauth = (Button)findViewById(R.id.btn_launch_oauth);
        Button tweet = (Button)findViewById(R.id.btn_tweet);
        
        launchOauth.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent().setClass(v.getContext(), PrepareRequestTokenActivity.class));
			}
		});
        
        tweet.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Twitter twitter = new Twitter(MainOAuthActivity.this);
				try {
					twitter.updateStatus("DROID!");
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
        
    }
    
}