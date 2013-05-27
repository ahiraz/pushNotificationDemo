package com.iakremera.pushnotificationdemo;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.PushService;

public class MainActivity extends Activity implements OnClickListener {

	Button push;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Parse.initialize(this, Application key,client key);
		PushService.setDefaultPushCallback(this, MainActivity.class);

		ParseInstallation.getCurrentInstallation().saveInBackground();
		push = (Button)findViewById(R.id.senPushB);
		push.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		JSONObject obj;
		try {
			obj =new JSONObject();
			obj.put("alert","erwerwe");
			obj.put("action","com.iakremera.pushnotificationdemo.UPDATE_STATUS");
			obj.put("customdata","My string");
			
			ParsePush push = new ParsePush();
			ParseQuery query = ParseInstallation.getQuery();
			
			 
			// Notification for Android users
			query.whereEqualTo("deviceType", "android");
			push.setQuery(query);
			push.setData(obj);
			push.sendInBackground(); 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
