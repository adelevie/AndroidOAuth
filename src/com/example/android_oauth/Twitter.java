package com.example.android_oauth;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class Twitter extends RestfulConsumer {

	public Twitter(Context context) {
		super(context);
		this.setBaseURI("http://api.twitter.com/1");
	}
	
	public JSONObject updateStatus(String status) throws JSONException, Exception {
		ArrayList<NameValuePair> options = new ArrayList<NameValuePair>();
		options.add(new BasicNameValuePair("status", status));
		return new JSONObject(this.post("/statuses/update.json", options));
	}
	
	public JSONArray userTimeline(String screen_name) throws JSONException, Exception {
		ArrayList<NameValuePair> options = new ArrayList<NameValuePair>();
		options.add(new BasicNameValuePair("screen_name", screen_name));
		return new JSONArray(this.get("/statuses/user_timeline.json", options));
	}

}
