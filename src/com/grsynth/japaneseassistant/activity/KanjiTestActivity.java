package com.grsynth.japaneseassistant.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.grsynth.japaneseassistant.R;
import com.grsynth.japaneseassistant.Type.Kanji;

public class KanjiTestActivity extends Activity {

	@Override
	@SuppressWarnings("unchecked")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kanji_test);
		
		TextView tv = (TextView) findViewById(R.id.tv);

		int nQuestion = getIntent().getExtras().getInt("nQuestion");
		int category = getIntent().getExtras().getInt("category");
		ArrayList<Kanji> kanjiList = (ArrayList<Kanji>) getIntent().getExtras().getSerializable("list");

		tv.append("\n" + kanjiList.get(0).getKanji());
		tv.append("\n" + kanjiList.get(1).getKanji());
		tv.append("\n" + kanjiList.get(2).getKanji());
		tv.append("\n" + nQuestion);
		tv.append("\n" + category);
	}

}
