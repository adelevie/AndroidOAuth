package com.example.android_oauth;

import oauth.signpost.OAuth;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

public class RetrieveAccessTokenTask extends AsyncTask<Uri, Void, Void> {
	private CommonsHttpOAuthConsumer consumer;
	private CommonsHttpOAuthProvider provider;
	private SharedPreferences prefs;
	private Context context;

	public RetrieveAccessTokenTask(
			Context context,
			CommonsHttpOAuthConsumer consumer,
			CommonsHttpOAuthProvider provider, SharedPreferences prefs) {
		this.consumer = consumer;
		this.provider = provider;
		this.prefs = prefs;
		this.context = context;
	}

	@Override
	protected Void doInBackground(Uri... params) {
		final Uri uri = params[0];
		final String oauth_verifier = uri.getQueryParameter(OAuth.OAUTH_VERIFIER);
		
		try {
			provider.retrieveAccessToken(consumer, oauth_verifier);
			
			final Editor edit = prefs.edit();
			edit.putString(OAuth.OAUTH_TOKEN, consumer.getToken());
			edit.putString(OAuth.OAUTH_TOKEN_SECRET, consumer.getTokenSecret());
			edit.commit();
			
			String token = prefs.getString(OAuth.OAUTH_TOKEN, "");
			String secret = prefs.getString(OAuth.OAUTH_TOKEN_SECRET, "");
			
			consumer.setTokenWithSecret(token, secret);
			context.startActivity(new Intent(context, MainOAuthActivity.class));
						
		} catch (Exception e) {
			Log.e("foo", "foo", e);
		}
		return null;
	}



}
