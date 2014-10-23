package com.mobware4u.smsnotifier.receivers;

import com.mobware4u.smsnotifier.intentservices.SmsPostManIntentService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Network change receiver to start the post man service
 * when a connection is obtained
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		 ConnectivityManager connectivityManager = (ConnectivityManager) 
														context.getSystemService(Context.CONNECTIVITY_SERVICE );
		 NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
		 boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();   
		 if (isConnected) {       
			 // a connection was detected
			 // ask post man to send any unsend letters
			 context.startService(new Intent(context, SmsPostManIntentService.class));
		 }
	}

}
