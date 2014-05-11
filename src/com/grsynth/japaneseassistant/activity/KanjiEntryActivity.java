package com.grsynth.japaneseassistant.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.grsynth.japaneseassistant.R;

public class KanjiEntryActivity extends Activity{
	
	private static final String TAG = "KanjibEntryActivity"; 
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kanji_entry);
		
		Log.d(TAG, "Entering onCreate");
		
		final TextView tv0 = (TextView) findViewById(R.id.kanji);
		final TextView tv1 = (TextView) findViewById(R.id.kunyomi);
		final TextView tv2 = (TextView) findViewById(R.id.onyomi);
		final TextView tv3 = (TextView) findViewById(R.id.jlpt);
		final TextView tv4 = (TextView) findViewById(R.id.jouyou);
		final TextView tv5 = (TextView) findViewById(R.id.meaning);
		
		String info = getIntent().getExtras().getString("info");
		//"kanji	onyomi	kunyomi	meaning	jouyou	jlpt	params" // separados con tabs
		
		String fields[] = info.split("\\t");
		
		tv0.setText(fields[0]);
		tv1.setText(fields[1]);
		tv2.setText(fields[2]);
		tv3.setText(fields[5]);
		tv4.setText(fields[4]);
		tv5.setText(fields[3]);

		
		// TODO parse params
	}

}