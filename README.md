AndroidOAuth
============

Work in progess.

Usage
-----

Anywhere you'd like to launch the OAuth dance, start the `MainOAuthActivity` intent:

``` java
Intent myIntent = new Intent(MainActivity.this, MainOAuthActivity.class);
MainActivity.this.startActivity(myIntent);
```

I built a simple Twitter api wrapper to help get you started (`src/com/example/android_oauth/Twitter.java`). Feel free to add to it, or build your own.

Once a user is authenticated, you can start making authenticated HTTP requests. Sample usage:

``` java
Twitter twitter = new Twitter(MainOAuthActivity.this);
twitter.updateStatus("updating twitter from a droid app #xcool");
```

Installation
------------

### Clone this repository into your Eclipse workspace.

```
cd workspace
git clone git@github.com:adelevie/AndroidOAuth.git
```

### Add OAuth token and secret

Create `Constants.java` inside `src/com/example/android_oauth`. Here's what it should look like:

``` java
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
```

### Create a redirecting website

[Why?](http://stackoverflow.com/questions/2199357/oauth-twitter-on-android-callback-fails/2401135#2401135)

These are instructions for setting up a Rails app and deploying to Heroku, but you can use any language/framework you want. Just be sure that you have a public-facing url that redirects to `appName://callback`.

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

### Declare Activities in Manifest

In your main application's `AndroidManifest.xml`, add the following:

``` xml
<activity android:name="com.example.oauth_example.MainOAuthActivity"
		android:label="@string/app_name" />

<activity android:name="com.example.oauth_example.PrepareRequestTokenActivity" android:launchMode="singleTask">
	<intent-filter>
		<action android:name="android.intent.action.VIEW" />
		<category android:name="android.intent.category.DEFAULT" />
		<category android:name="android.intent.category.BROWSABLE" />
		<data android:scheme="appName" android:host="callback" />
	</intent-filter>
</activity>
```

License
-------

Copyright (c) 2011 Alan deLevie

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.



