package com.grsynth.japaneseassistant.activity;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.grsynth.japaneseassistant.R;
import com.grsynth.japaneseassistant.Type.Kanji;

public class KanjiToMeaningTestActivity extends Activity {
	
	Button tvOpA;
	Button tvOpB;
	Button tvOpC;
	Button tvOpD;
	
	TextView tvKanji;
	TextView tvNQuestion;
	TextView tvRight;
	TextView tvWrong;
	
	int w = 0;
	int r = 0;
	
	int questionIndex;
	int nQuestion;
	int category;
	ArrayList<Kanji> kanjiList;
	
	int askedQuestionKanjiIndex;
	
	Random ran;
	int first;
	int jump;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kanji_to_meaning_test);

		questionIndex = 1;
		nQuestion = getIntent().getExtras().getInt("nQuestion");
		category = getIntent().getExtras().getInt("category");
		kanjiList = (ArrayList<Kanji>) getIntent().getExtras().getSerializable("list");
		
		tvKanji = (TextView) findViewById(R.id.kanji_container);
		
		tvOpA = (Button) findViewById(R.id.b1);
		tvOpB = (Button) findViewById(R.id.b2);
		tvOpC = (Button) findViewById(R.id.b3);
		tvOpD = (Button) findViewById(R.id.b4);
		
		tvOpA.setOnClickListener(buttonHandler1);
		tvOpB.setOnClickListener(buttonHandler2);
		tvOpC.setOnClickListener(buttonHandler3);
		tvOpD.setOnClickListener(buttonHandler4);
		
		ran = new Random();
		first = ran.nextInt(kanjiList.size());
		jump =  ran.nextInt(kanjiList.size());
		
		tvNQuestion = (TextView) findViewById(R.id.message);
		tvRight = (TextView) findViewById(R.id.right);
		tvWrong = (TextView) findViewById(R.id.wrong);
		
		tvRight.setText("0");
		tvWrong.setText("0");
		
		tvNQuestion.setText(questionIndex + "/" + nQuestion);
		askedQuestionKanjiIndex = (first + ((questionIndex - 1) * jump)) % kanjiList.size();
		getQuestion(askedQuestionKanjiIndex);

	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		// TODO scores and files and shit
	}

	View.OnClickListener buttonHandler1 = new View.OnClickListener() {
		public void onClick(View v) {
			String answer = (String) tvOpA.getText();
			questionIndex++;
			
			if(answer == kanjiList.get(askedQuestionKanjiIndex).getMeaning()){ //right
				r++;
				// TODO add scores
			}
			else{	//wrong
				w++;
			}
			
			refreshScreen();
			
			if (questionIndex < nQuestion + 1){
				askedQuestionKanjiIndex = (first + ((questionIndex - 1) * jump)) % kanjiList.size();
				getQuestion(askedQuestionKanjiIndex);
			}
			else{
				Intent in = new Intent(getApplicationContext(), TestResultActivity.class);
				in.putExtra("right", r);
				in.putExtra("wrong", w);
				in.putExtra("category", "kanji2Meaning"); 
				in.putExtra("nQuestion", nQuestion);
				finish(); // i wonder if this works
				startActivityForResult(in, 0);
			}
		}
	};

	View.OnClickListener buttonHandler2 = new View.OnClickListener() {
		public void onClick(View v) {
			String answer = (String) tvOpB.getText();
			questionIndex++;
			
			if(answer == kanjiList.get(askedQuestionKanjiIndex).getMeaning()){ //right
				r++;
				// TODO add scores
			}
			else{	//wrong
				w++;
			}
			
			refreshScreen();
			
			if (questionIndex < nQuestion + 1){
				askedQuestionKanjiIndex = (first + ((questionIndex - 1) * jump)) % kanjiList.size();
				getQuestion(askedQuestionKanjiIndex);
			}
			else{
				Intent in = new Intent(getApplicationContext(), TestResultActivity.class);
				in.putExtra("right", r);
				in.putExtra("wrong", w);
				in.putExtra("category", "kanji2Meaning"); 
				in.putExtra("nQuestion", nQuestion);
				finish(); // i wonder if this works
				startActivityForResult(in, 0);
			}
		}
	};
	
	View.OnClickListener buttonHandler3 = new View.OnClickListener() {
		public void onClick(View v) {
			String answer = (String) tvOpC.getText();
			questionIndex++;
			
			if(answer == kanjiList.get(askedQuestionKanjiIndex).getMeaning()){ //right
				r++;
				// TODO add scores
			}
			else{	//wrong
				w++;
			}
			
			refreshScreen();
			
			if (questionIndex < nQuestion + 1){
				askedQuestionKanjiIndex = (first + ((questionIndex - 1) * jump)) % kanjiList.size();
				getQuestion(askedQuestionKanjiIndex);
			}
			else{
				Intent in = new Intent(getApplicationContext(), TestResultActivity.class);
				in.putExtra("right", r);
				in.putExtra("wrong", w);
				in.putExtra("category", "kanji2Meaning"); 
				in.putExtra("nQuestion", nQuestion);
				finish(); // i wonder if this works
				startActivityForResult(in, 0);
			}
		}
	};

	View.OnClickListener buttonHandler4 = new View.OnClickListener() {
		public void onClick(View v) {
			String answer = (String) tvOpD.getText();
			questionIndex++;
			
			if(answer == kanjiList.get(askedQuestionKanjiIndex).getMeaning()){ //right
				r++;
				// TODO add scores
			}
			else{	//wrong
				w++;
			}
			
			refreshScreen();
			
			if (questionIndex < nQuestion + 1){
				askedQuestionKanjiIndex = (first + ((questionIndex - 1) * jump)) % kanjiList.size();
				getQuestion(askedQuestionKanjiIndex);
			}
			else{
				Intent in = new Intent(getApplicationContext(), TestResultActivity.class);
				in.putExtra("right", r);
				in.putExtra("wrong", w);
				in.putExtra("category", "kanji2Meaning"); 
				in.putExtra("nQuestion", nQuestion);
				finish(); // i wonder if this works
				startActivityForResult(in, 0);
			}
		}
	};
	
	void getQuestion(int q){
		Kanji k = kanjiList.get(q);
		tvKanji.setText(k.getKanji());
		getAnswers(k, q);
	}
	
	void getAnswers(Kanji kan, int q){
		Random ran = new Random();
		int position = ran.nextInt(4);
		int jump = ran.nextInt(kanjiList.size());
		
		switch (position){
		case 0:
			tvOpA.setText(kan.getMeaning());
			tvOpB.setText(kanjiList.get((q + (jump)) % kanjiList.size()).getMeaning());
			tvOpC.setText(kanjiList.get((q + (2 * jump)) % kanjiList.size()).getMeaning());
			tvOpD.setText(kanjiList.get((q + (3 * jump)) % kanjiList.size()).getMeaning());
			break;
		case 1:
			tvOpB.setText(kan.getMeaning());			
			tvOpA.setText(kanjiList.get((q + (jump)) % kanjiList.size()).getMeaning());
			tvOpC.setText(kanjiList.get((q + (2 * jump)) % kanjiList.size()).getMeaning());
			tvOpD.setText(kanjiList.get((q + (3 * jump)) % kanjiList.size()).getMeaning());
			break;
		case 2:
			tvOpC.setText(kan.getMeaning());			
			tvOpA.setText(kanjiList.get((q + (jump)) % kanjiList.size()).getMeaning());
			tvOpB.setText(kanjiList.get((q + (2 * jump)) % kanjiList.size()).getMeaning());
			tvOpD.setText(kanjiList.get((q + (3 * jump)) % kanjiList.size()).getMeaning());
			break;
		default:
			tvOpD.setText(kan.getMeaning());			
			tvOpA.setText(kanjiList.get((q + (jump)) % kanjiList.size()).getMeaning());
			tvOpB.setText(kanjiList.get((q + (2 * jump)) % kanjiList.size()).getMeaning());
			tvOpC.setText(kanjiList.get((q + (3 * jump)) % kanjiList.size()).getMeaning());
			break;
		}
		
	}
	
	void refreshScreen(){
		tvRight.setText("" + r);
		tvWrong.setText("" + w);
		tvNQuestion.setText(questionIndex + "/" + nQuestion);
	}

}
