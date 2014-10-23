package com.mobware4u.smsnotifier.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.util.Log;

import com.mobware4u.smsnotifier.db.SmsLetterBoxDB;
import com.mobware4u.smsnotifier.intentservices.SmsPostManIntentService;
import com.mobware4u.smsnotifier.model.SmsLetter;

/**
 * Broadcast receiver for receiving SMS
 * NOTE : Using an old deprecated API for now.
 * 
 * Saves the sms letter message to letter box db
 * Informs the Post Man service to send the letter
 */
@SuppressWarnings("deprecation")
public class SMSLetterReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		saveSmsLetterInBox(intent);
        informPostManAboutTheLetter(context);   
	}
	
	
	private SmsLetter saveSmsLetterInBox(Intent intent) {
		final Bundle bundle = intent.getExtras();
		try {
		    if (bundle != null) {
		        final Object[] pdusObj = (Object[]) bundle.get("pdus");
		        if(pdusObj != null && pdusObj.length > 0) {
					SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[0]);
		            String message = currentMessage.getDisplayMessageBody();
		            
		            // put the sms letter in the Letter Box DB
		            return SmsLetterBoxDB.getInstance().putSMSLetter(message);
		            
		        } // end for loop
		      } // bundle is null
		 
		} catch (Exception e) {
		    Log.e("SmsReceiver", "Exception smsReceiver" +e);
		}
		return null;
	}
	
	
	private void informPostManAboutTheLetter(Context context) {
		Intent postManIntent = new Intent(context, SmsPostManIntentService.class);
		context.startService(postManIntent);
	}
}
