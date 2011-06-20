package com.example.android_oauth;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;

public class OAuthRequestTokenTask extends AsyncTask<Uri, Void, Void> {
	CommonsHttpOAuthProvider provider;
	OAuthConsumer consumer;
	Context context;

	public OAuthRequestTokenTask(Context context, CommonsHttpOAuthConsumer consumer, CommonsHttpOAuthProvider provider) {
		this.provider = provider;
		this.consumer = consumer;
		this.context = context;
	}

	@Override
	protected Void doInBackground(Uri... params) {
		try {
			final String url = provider.retrieveRequestToken(consumer, Constants.CALLBACK_URL);
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url)).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_FROM_BACKGROUND);
			context.startActivity(intent);
		} catch (Exception e) {
			Log.e("", "", e);
		}
		return null;
	}


}
