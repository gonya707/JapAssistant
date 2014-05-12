package com.grsynth.japaneseassistant.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.grsynth.japaneseassistant.R;
import com.grsynth.japaneseassistant.Type.Kanji;

public class KanjiEntryActivity extends Activity{
	
	private static final String TAG = "KanjiEntryActivity"; 
	
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
		
		Kanji info = (Kanji) getIntent().getExtras().getSerializable("info");
		//"kanji	onyomi	kunyomi	meaning	jouyou	jlpt	params" // separados con tabs
		
		tv0.setText(info.getKanji());
		tv1.setText(info.getKunyomi());
		tv2.setText(info.getOnyomi());
		tv3.setText(info.getJlpt());
		tv4.setText(info.getJouyou());
		tv5.setText(info.getMeaning());

		
		// TODO parse params
	}

}