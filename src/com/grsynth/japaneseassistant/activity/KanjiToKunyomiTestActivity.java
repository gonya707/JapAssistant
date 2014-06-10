package com.grsynth.japaneseassistant.activity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.grsynth.japaneseassistant.R;
import com.grsynth.japaneseassistant.Type.Kanji;
import com.grsynth.japaneseassistant.Type.ScoreKanji;

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
	short addPoint[];

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
		
		addPoint = new short[getResources().getInteger(R.integer.number_of_kanji)];

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
			addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] ++;
			if (addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] == 0){
				addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] ++;
			}
		}
		else{
			w++;
			addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] = -1;
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
	
	@Override
	protected void onPause() {
		super.onPause();

		List<ScoreKanji> list = new ArrayList<ScoreKanji>();

		try {
			FileInputStream fin = openFileInput("scoreKanji");
			ObjectInputStream ois = new ObjectInputStream(fin);

			for (int i = 0; i < getResources().getInteger(R.integer.number_of_kanji); i++){
				list.add((ScoreKanji) ois.readObject());

				if (addPoint[i] > 0){
					if (list.get(i).kunyomi == -1){
						list.get(i).kunyomi += addPoint[i] + 1;
					}
					else{
						list.get(i).kunyomi += addPoint[i];
					}
				}
				else if (addPoint[i] == -1){
					list.get(i).kunyomi = 0;
				}
			}
			ois.close();
			
			deleteFile("scoreKanji");
			FileOutputStream fos = openFileOutput("scoreKanji", Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			for(int i = 0; i < getResources().getInteger(R.integer.number_of_kanji); i++){
				ScoreKanji sk = list.get(i);
				oos.writeObject(sk);
			}
			fos.close();

		} catch (FileNotFoundException e) {
			Toast.makeText(getApplicationContext(), "scoreKanji file not created. Unable to save your score.", Toast.LENGTH_SHORT ).show();
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
