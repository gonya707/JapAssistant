package com.grsynth.japaneseassistant.activity;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.grsynth.japaneseassistant.R;
import com.grsynth.japaneseassistant.Type.Kanji;
import com.grsynth.japaneseassistant.Type.ScoreKanji;

public class KanjiEntryActivity extends Activity{
	
	private static final String TAG = "KanjiEntryActivity"; 
	int pos;
	ArrayList<Kanji> kanjiList;
	ArrayList<ScoreKanji> scoreList = new ArrayList<ScoreKanji>();
	Intent in;
	TextView tv0, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8;
	
	@SuppressWarnings("unchecked")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kanji_entry);
		
		Log.d(TAG, "Entering onCreate");
		
		tv0 = (TextView) findViewById(R.id.kanji_container);
		tv1 = (TextView) findViewById(R.id.onyomi_container);
		tv2 = (TextView) findViewById(R.id.kunyomi_container);
		tv3 = (TextView) findViewById(R.id.jouyou_container);
		tv4 = (TextView) findViewById(R.id.jlpt_container);
		tv5 = (TextView) findViewById(R.id.strokes_container);
		tv6 = (TextView) findViewById(R.id.radical_container);
		tv7 = (TextView) findViewById(R.id.meaning);
		tv8 = (TextView) findViewById(R.id.score1);
		
		try{
			FileInputStream finS = openFileInput("scoreKanji");
			ObjectInputStream oisS = new ObjectInputStream(finS);

			for(int i = 0; i < getResources().getInteger(R.integer.number_of_kanji); i++){ 
				ScoreKanji bu = (ScoreKanji) oisS.readObject(); 
				scoreList.add(bu);
			}
			oisS.close();

		}catch(Exception ex){
			ex.printStackTrace();
		} 


		pos = getIntent().getExtras().getInt("position");
		kanjiList = (ArrayList<Kanji>) getIntent().getExtras().getSerializable("list");
		Kanji info = (Kanji) getIntent().getExtras().getSerializable("info");
		ScoreKanji score = scoreList.get(info.getIndex());
		refreshScreen(info, score);
		
		final Button b1 = (Button) findViewById(R.id.next);
		final Button b2 = (Button) findViewById(R.id.previous);
		b1.setOnClickListener(buttonHandler1);
		b2.setOnClickListener(buttonHandler2);
		
	}
	
	private void refreshScreen(Kanji k, ScoreKanji sk){
		tv0.setText(k.getKanji());
		tv1.setText(k.getOnyomi());
		tv2.setText(k.getKunyomi());
		tv3.setText(k.getJouyou());
		tv4.setText(k.getJlpt());
		tv5.setText(k.getStrokes());
		tv6.setText(k.getRadical());
		tv7.setText(k.getMeaning());
		tv8.setText(""+sk.meaning);
	}
	
	View.OnClickListener buttonHandler1 = new View.OnClickListener() {
		public void onClick(View v) {
			if (pos + 1 < kanjiList.size()){
				pos++;
				refreshScreen(kanjiList.get(pos), scoreList.get(kanjiList.get(pos).getIndex()));
			}
			else{
				Toast.makeText(getApplicationContext(), "Last entry", Toast.LENGTH_SHORT).show();
			}
			
		}
	};
	
	View.OnClickListener buttonHandler2 = new View.OnClickListener() {
		public void onClick(View v) {
			if (pos > 0){
				pos--;
				refreshScreen(kanjiList.get(pos), scoreList.get(kanjiList.get(pos).getIndex()));
			}
			else{
				Toast.makeText(getApplicationContext(), "First entry", Toast.LENGTH_SHORT).show();
			}
		}
	};

}