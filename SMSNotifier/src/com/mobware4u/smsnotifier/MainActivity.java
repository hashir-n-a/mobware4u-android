package com.mobware4u.smsnotifier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobware4u.smsnotifier.db.SmsLetterBoxDB;
import com.mobware4u.smsnotifier.intentservices.SmsPostManIntentService;

public class MainActivity extends Activity implements OnClickListener{

	// edit text containing the address url
	private EditText mAddressET = null;
	
	// the save button
	private Button mSaveB = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mAddressET = (EditText) findViewById(R.id.enter_url_et);
		mAddressET.setText(getAddress());
		
		mSaveB = (Button) findViewById(R.id.save_b);
		mSaveB.setOnClickListener(this);
		
		// send unsend message is any
		startPostManIntentServiceToSendUnsendMessages();
	}


	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(R.id.save_b == id) {
			saveAddress();
		}
	}
	
	
	/**
	 * Validate and Save the address to DB
	 */
	private void saveAddress() {
		String address = mAddressET.getText().toString();
		if(null != address && !address.equals("")) {
			SmsLetterBoxDB.getInstance().saveAddress(address);
			Toast.makeText(
						this, 
						getString(R.string.saved), 
						Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(
					this, 
					getString(R.string.error_msg), 
					Toast.LENGTH_SHORT).show();
		}
	}
	
	
	/**
	 * Get the last saved address from DB
	 * @return String address
	 */
	private String getAddress() {
		return SmsLetterBoxDB.getInstance().getAddress();
	}
	
	private void startPostManIntentServiceToSendUnsendMessages() {
		Intent postman = new Intent(this, SmsPostManIntentService.class);
		startService(postman);
	}
}
