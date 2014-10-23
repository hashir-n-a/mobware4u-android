package com.mobware4u.smsnotifier.network;

import org.apache.http.HttpResponse;


/**
 * The Network Layer for sending the message
 */
public class SmsPosterNetworkAPI {
	
	/**
	 * Execute the post webservive API and return whether its success or not
	 * @param address - address of webservice API
	 * @param message - message to post
	 * @return boolean , true if success, false otherwise
	 */
	public static boolean deliverLetters(String address, String message) {
		HttpResponse response = NetworkUtils.doHttpPost(address, message);
		if(null != response) {
			if(response.getStatusLine().getStatusCode() == 200) {
				return true;
			} 
		} 
		return false;
	}
}