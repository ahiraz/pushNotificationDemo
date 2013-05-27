package com.iakremera.pushnotificationdemo;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class MyCustomReceiver extends BroadcastReceiver {
	private static final String TAG = "MyCustomReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			if (intent == null)
			{
				Log.d(TAG, "Receiver intent null");
			}
			else
			{
				String action = intent.getAction();
				Log.d(TAG, "got action " + action );
				if (action.equals("com.iakremera.pushnotificationdemo.UPDATE_STATUS"))
				{
					String channel = intent.getExtras().getString("com.parse.Channel");
					JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));

					Log.d(TAG, "got action " + action + " on channel " + channel + " with:");
					Iterator itr = json.keys();
					while (itr.hasNext()) {
						String key = (String) itr.next();
						if (key.equals("customdata"))
						{
							Intent pupInt = new Intent(context, ShowPopUp.class);
							pupInt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
							context.getApplicationContext().startActivity(pupInt);
						}
						Log.d(TAG, "..." + key + " => " + json.getString(key));
					}
				}
			}

		} catch (JSONException e) {
			Log.d(TAG, "JSONException: " + e.getMessage());
		}
	}
}
