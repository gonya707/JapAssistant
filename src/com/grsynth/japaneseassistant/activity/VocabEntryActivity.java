package com.grsynth.japaneseassistant.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.grsynth.japaneseassistant.R;

public class VocabEntryActivity extends Activity{
	
	private static final String TAG = "VocabEntryActivity";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vocab_entry);
		
		Log.d(TAG, "Entering onCreate");

		final TextView tv0 = (TextView) findViewById(R.id.word);
		final TextView tv1 = (TextView) findViewById(R.id.kana);
		final TextView tv2 = (TextView) findViewById(R.id.meaning);
		
		String info = getIntent().getExtras().getString("info");
		//"word  kana  meaning  jlpt  params " // separados con tabs
		
		String fields[] = info.split("\\t");
		
		tv0.setText(fields[0]);
		tv1.setText(fields[1]);
		tv2.setText(fields[2]);
		
		//TODO Parse params
	}

}
