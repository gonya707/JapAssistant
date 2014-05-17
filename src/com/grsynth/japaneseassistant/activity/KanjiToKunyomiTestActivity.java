package com.grsynth.japaneseassistant.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.grsynth.japaneseassistant.R;
import com.grsynth.japaneseassistant.Type.Kanji;

public class KanjiToKunyomiTestActivity extends Activity {

	TextView tvKanji;
	TextView tvNQuestion;
	TextView tvRight;
	TextView tvWrong;

	int w = 0;
	int r = 0;

	int questionIndex;
	int nQuestion;

	ArrayList<Kanji> kanjiList;

	int askedQuestionKanjiIndex;

	Random ran;
	int first;
	int jump;

	EditText edittext;
	Button button;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kanji_to_kunyomi_test);

		questionIndex = 1;
		nQuestion = getIntent().getExtras().getInt("nQuestion");
		kanjiList = (ArrayList<Kanji>) getIntent().getExtras().getSerializable("list");

		tvKanji = (TextView) findViewById(R.id.kanji_container);

		ran = new Random();
		first = ran.nextInt(kanjiList.size());
		jump =  1 + ran.nextInt(kanjiList.size() - 1);

		tvNQuestion = (TextView) findViewById(R.id.message);
		tvRight = (TextView) findViewById(R.id.right);
		tvWrong = (TextView) findViewById(R.id.wrong);

		tvRight.setText("0");
		tvWrong.setText("0");

		tvNQuestion.setText(questionIndex + "/" + nQuestion);
		askedQuestionKanjiIndex = (first + ((questionIndex - 1) * jump)) % kanjiList.size();
		getQuestion(askedQuestionKanjiIndex);

		edittext = (EditText) findViewById(R.id.editText);
		edittext.setOnKeyListener(editTextHandler);
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(buttonHandler);

	}

	View.OnKeyListener editTextHandler = new View.OnKeyListener() {
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			if ((event.getAction() == KeyEvent.ACTION_DOWN)	&& (keyCode == KeyEvent.KEYCODE_ENTER) && "" != edittext.getText().toString()) {
				checkAnswer();
				return true;
			}
			else{
				return false;
			}
		}
	};

	View.OnClickListener buttonHandler = new View.OnClickListener() {
		public void onClick(View v) {
			if("" != edittext.getText().toString()){
				checkAnswer();
			}
		}
	};

	private void checkAnswer() {
		String s = edittext.getText().toString();
		s.replaceAll(",", "");
		String goodAnswers[]; 
		questionIndex++;
		
		
		Kanji k = kanjiList.get(askedQuestionKanjiIndex);
		goodAnswers = k.getKunyomi().split(" ");
		
		if(Arrays.asList(goodAnswers).contains(edittext.getText().toString())){
			r++;
			// TODO add scores
		}
		else{
			w++;
			
		}
		
		refreshScreen();
		edittext.setText(null);
	
		if (questionIndex < nQuestion + 1){
			askedQuestionKanjiIndex = (first + ((questionIndex - 1) * jump)) % kanjiList.size();
			getQuestion(askedQuestionKanjiIndex);
		}
		else{
			Intent in = new Intent(getApplicationContext(), TestResultActivity.class);
			in.putExtra("right", r);
			in.putExtra("wrong", w);
			in.putExtra("category", "kanji2kun"); 
			in.putExtra("nQuestion", nQuestion);
			finish(); 
			startActivityForResult(in, 0);
		}
		
	}
	
	void getQuestion(int q){
		Kanji k = kanjiList.get(q);
		tvKanji.setText(k.getKanji());
	}


	void refreshScreen(){
		tvRight.setText("" + r);
		tvWrong.setText("" + w);
		tvNQuestion.setText(questionIndex + "/" + nQuestion);
	}



}
