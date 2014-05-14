package com.grsynth.japaneseassistant.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.grsynth.japaneseassistant.R;
import com.grsynth.japaneseassistant.Type.Kanji;

public class KanjiEntryActivity extends Activity{
	
	private static final String TAG = "KanjiEntryActivity"; 
	int pos;
	ArrayList<Kanji> kanjiList;
	Intent in;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kanji_entry);
		
		Log.d(TAG, "Entering onCreate");
		
		final TextView tv0 = (TextView) findViewById(R.id.kanji);
		final TextView tv1 = (TextView) findViewById(R.id.kunyomi);
		final TextView tv2 = (TextView) findViewById(R.id.onyomi);
		final TextView tv3 = (TextView) findViewById(R.id.jouyou);
		final TextView tv4 = (TextView) findViewById(R.id.jlpt);
		final TextView tv5 = (TextView) findViewById(R.id.textView5);
		final TextView tv6 = (TextView) findViewById(R.id.textView6);
		final TextView tv7 = (TextView) findViewById(R.id.meaning);
		
		Kanji info = (Kanji) getIntent().getExtras().getSerializable("info");
		
		tv0.setText(info.getKanji());
		tv1.setText(info.getKunyomi());
		tv2.setText(info.getOnyomi());
		tv3.setText(info.getJouyou());
		tv4.setText(info.getJlpt());
		tv5.setText(info.getStrokes());
		tv6.setText(info.getRadical());
		tv7.setText(info.getMeaning());
		
		
		pos = getIntent().getExtras().getInt("position");
		kanjiList = (ArrayList<Kanji>) getIntent().getExtras().getSerializable("list");
		
		final Button b1 = (Button) findViewById(R.id.next);
		final Button b2 = (Button) findViewById(R.id.previous);
		b1.setOnClickListener(buttonHandler1);
		b2.setOnClickListener(buttonHandler2);
		
	}
	
	View.OnClickListener buttonHandler1 = new View.OnClickListener() {
		public void onClick(View v) {
			//TODO
			if (pos + 1 < kanjiList.size()){
				in = new Intent(v.getContext(), KanjiEntryActivity.class);
				in.putExtra("info", kanjiList.get(pos + 1));
				in.putExtra("position", pos + 1);
				in.putExtra("list", kanjiList);
				finish();
				startActivityForResult(in, 0);
			}
		}
	};
	
	View.OnClickListener buttonHandler2 = new View.OnClickListener() {
		public void onClick(View v) {
			//TODO
			if (pos > 0){
				in = new Intent(v.getContext(), KanjiEntryActivity.class);
				in.putExtra("info", kanjiList.get(pos - 1));
				in.putExtra("position", pos + 1);
				in.putExtra("list", kanjiList);
				finish();
				startActivityForResult(in, 0);
			}
		}
	};

}