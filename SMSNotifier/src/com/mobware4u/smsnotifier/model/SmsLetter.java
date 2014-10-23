package com.mobware4u.smsnotifier.model;

import com.orm.SugarRecord;

public class SmsLetter extends SugarRecord<SmsLetter> {

	// when the message was received
	private long receivedTime;

	// the sms message
	private String message;

	// whether the message was notified to server
	private boolean notifiedStatus = false;
	
	public SmsLetter() {
		
	}

	public SmsLetter(long time, String message) {
		this.receivedTime = time;
		this.message = message;
	}

	public long getReceivedTime() {
		return receivedTime;
	}

	public void setReceivedTime(long receivedTime) {
		this.receivedTime = receivedTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isNotifiedStatus() {
		return notifiedStatus;
	}

	public void setNotifiedStatus(boolean notifiedStatus) {
		this.notifiedStatus = notifiedStatus;
	}
}
