package com.mobware4u.smsnotifier.intentservices;

import java.util.List;

import android.app.IntentService;
import android.content.Intent;

import com.mobware4u.smsnotifier.db.SmsLetterBoxDB;
import com.mobware4u.smsnotifier.model.SmsLetter;
import com.mobware4u.smsnotifier.network.NetworkUtils;
import com.mobware4u.smsnotifier.network.SmsPosterNetworkAPI;

/**
 * Intent Service to invoke the webservice informing about the sms letters
 * Intent Service operates in its own thread
 * Intent Service maintains an internal queue with
 * onHandleIntent called sequentially for multiple invocations
 */
public class SmsPostManIntentService extends IntentService {

	public SmsPostManIntentService() {
		super("SMSPostMan");
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		
		// if there is no internet connection then 
		// do not proceed
		if(!NetworkUtils.isNetworkAvailable(this)) {
			return;
		}
		
		// we have internet connection
		// get all unsend letters
		List<SmsLetter> letters = SmsLetterBoxDB.getInstance().getAllUnSendLetters();
		
		if( null != letters && letters.size() > 0 ) {
			// get the address
			String address = SmsLetterBoxDB.getInstance().getAddress();
			
			if(null != address && !address.equals("")) {

				// send the letters
				for(SmsLetter letter : letters) {
					boolean status = SmsPosterNetworkAPI.deliverLetters(address, letter.getMessage());
					
					// save the status of network operation
					SmsLetterBoxDB.getInstance().updateStatus(letter, status);
				}
				
			}
		}
	}
}
