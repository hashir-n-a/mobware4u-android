package com.mobware4u.nfccounter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		((Button) findViewById(R.id.resetB)).setOnClickListener(this);
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(getIntent().getAction())) {
	    	((TextView) findViewById(R.id.counterTV)).setText(getCount() + "");
	    }
	}
	
	private int getCount() {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		setCount(pref.getInt("COUNT", 0) + 1);
		return pref.getInt("COUNT", 0);
	}
	
	private void setCount(int count) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt("COUNT", count);
		editor.commit();
	}

	@Override
	public void onClick(View v) {
		setCount(0);
		((TextView) findViewById(R.id.counterTV)).setText("" + getCount());
	}

		
}
