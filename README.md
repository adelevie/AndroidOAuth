AndroidOAuth
============

Work in progess.

Installation
------------

Clone this repository into your Eclipse workspace.

Create `Constants.java` inside `src/com/example/android_oauth`. Here's what it should look like:

	package com.example.android_oauth;

	public class Constants {
	  public static final String CONSUMER_KEY = ""; // get key and secret from dev.twitter.com
	  public static final String CONSUMER_SECRET = "";
	  public static final String REQUEST_URL = "https://api.twitter.com/oauth/request_token";
	  public static final String AUTHORIZE_URL = "https://api.twitter.com/oauth/authorize";
	  public static final String ACCESS_URL = "https://api.twitter.com/oauth/access_token";
	  public static final String CALLBACK_URL = "appName://callback";
	  public static final String OAUTH_CALLBACK_SCHEME = "appName";
	}

Create a redirecting website

	rails new RedirectSite
	cd RedirectSite
	rails g controller welcome index

add `match "callback" => "welcome#index"` to `config/routes.rb`

add `redirect_to "appName://callback"` to `WelcomeController#index`

	git init
	heroku create
	git add .
	git commit -m "initial commit"
	git push heroku master

Copy the url of the app, and go to dev.twitter.com and register your application (if you haven't done so already). Set the callback url to `[heroku app url]/callback`.

Open or create a new Android project. Follow [these](http://developer.android.com/guide/developing/projects/projects-eclipse.html) instructions for referencing a library project in Eclipse.

In your main application's `AndroidManifest.xml`, add the following:

		<activity android:name="com.example.oauth_example.MainOAuthActivity"
				android:label="@string/app_name" />

		<activity android:name="com.example.oauth_example.PrepareRequestTokenActivity" android:launchMode="singleTask">>
			<intent-filter>
				<action android:name="android.intent.action.VIEW" />
				<category android:name="android.intent.category.DEFAULT" />
				<category android:name="android.intent.category.BROWSABLE" />
				<data android:scheme="appName" android:host="callback" />
			</intent-filter>
		</activity>

Anywhere you'd like to launch the OAuth dance, start the `MainOAuthActivity` intent:

		Intent myIntent = new Intent(MainActivity.this, MainOAuthActivity.class);
		MainActivity.this.startActivity(myIntent);

I built a simple Twitter api wrapper to help get you started (`src/com/example/android_oauth/Twitter.java`). Feel free to add to it, or build your own.

Once a user is authenticated, you can start making authenticated HTTP requests. Sample usage:

		Twitter twitter = new Twitter(MainOAuthActivity.this);
		twitter.updateStatus("updating twitter from a droid app #xcool");


