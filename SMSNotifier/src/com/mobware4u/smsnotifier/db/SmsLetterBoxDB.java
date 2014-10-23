package com.mobware4u.smsnotifier.db;

import java.util.Calendar;
import java.util.List;

import com.mobware4u.smsnotifier.model.InformerAddress;
import com.mobware4u.smsnotifier.model.SmsLetter;

/**
 * Singleton class to access the database of saved sms messages
 * Uses SugarORM for database operations
 */
public class SmsLetterBoxDB {

	private static SmsLetterBoxDB mLetterBoxDb = null;
	
	public static SmsLetterBoxDB getInstance() {
		if(null == mLetterBoxDb) {
			return new SmsLetterBoxDB();
		}
		return mLetterBoxDb;
	}
	
	private SmsLetterBoxDB() {
		
	}
	
	/**
	 * Save the address that is used by the webservice
	 * @param address - Url address
	 */
	synchronized public void saveAddress(String address) {
		// only one address url should be saved
		InformerAddress.deleteAll(InformerAddress.class);
		InformerAddress iAddress = new InformerAddress(address);
		iAddress.setAddress(address);
		iAddress.save();
	}
	
	
	/**
	 * Returns the address of the webservice 
	 * @return String url
	 */
	public String getAddress() {
		long size = InformerAddress.count(InformerAddress.class);
		if(size > 0) {
			InformerAddress iAddress = InformerAddress.findById(InformerAddress.class, 1);
			if(null != iAddress) {
				return iAddress.getAddress();
			}
		}
		return "";
	}
	
	
	/**
	 * Save a sms message to DB
	 * @param smsMessage - String sms message
	 * @return SmsLetter 
	 */
	synchronized public SmsLetter putSMSLetter(String smsMessage) {
		SmsLetter letter = new SmsLetter(
									Calendar.getInstance().getTimeInMillis(), 
									smsMessage);
		letter.save();
		return letter;
	}
	
	/**
	 * Return all sms messages which has not been sent yet
	 * @return List<SmsLetter>
	 */
	public List<SmsLetter> getAllUnSendLetters() {
		List<SmsLetter> letters = SmsLetter.find(
												SmsLetter.class, 
												"notified_status = ?", "0");
		return letters;
	}
	
	
	/**
	 * Update the status of the smsLetter
	 * @param smsLetter - the smsLetter
	 * @param status - true/false whether send successfully to server or not
	 */
	synchronized public void updateStatus(SmsLetter smsLetter, boolean status) {
		smsLetter.setNotifiedStatus(status);
		smsLetter.save();
	}
	
}
