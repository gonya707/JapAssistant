package com.grsynth.japaneseassistant.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.grsynth.japaneseassistant.R;

public class KanjiEntryActivity extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kanji_entry);
		
		final TextView tv1 = (TextView) findViewById(R.id.kanji);
		final TextView tv2 = (TextView) findViewById(R.id.kunyomi);
		final TextView tv3 = (TextView) findViewById(R.id.onyomi);
		final TextView tv4 = (TextView) findViewById(R.id.jlpt);
		final TextView tv5 = (TextView) findViewById(R.id.jouyou);
		
		//recibir bundle del intent "info". info es un String[] y ahi estan todos los campos por el orden de siempre
		
		//cambiar textviews

	}

}