package com.mobware4u.smsnotifier.model;

import com.orm.SugarRecord;

/**
 * The webservice address used for posting the
 * the sms messages
 */
public class InformerAddress extends SugarRecord<InformerAddress> {

	private String address;
	
	public InformerAddress() {
		
	}
	
	public InformerAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
