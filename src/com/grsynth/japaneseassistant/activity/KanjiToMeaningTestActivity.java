package com.grsynth.japaneseassistant.activity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.grsynth.japaneseassistant.R;
import com.grsynth.japaneseassistant.Type.Kanji;
import com.grsynth.japaneseassistant.Type.ScoreKanji;

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

	ArrayList<Kanji> kanjiList;
	short addPoint[];

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
		jump =  1 + ran.nextInt(kanjiList.size() - 1);

		tvNQuestion = (TextView) findViewById(R.id.message);
		tvRight = (TextView) findViewById(R.id.right);
		tvWrong = (TextView) findViewById(R.id.wrong);

		tvRight.setText("" + r);
		tvWrong.setText("" + w);

		tvNQuestion.setText(questionIndex + "/" + nQuestion);
		askedQuestionKanjiIndex = (first + ((questionIndex - 1) * jump)) % kanjiList.size();
		getQuestion(askedQuestionKanjiIndex);

		addPoint = new short[getResources().getInteger(R.integer.number_of_kanji)];

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
					if (list.get(i).meaning == -1){
						list.get(i).meaning += addPoint[i] + 1;
					}
					else{
						list.get(i).meaning += addPoint[i];
					}
				}
				else if (addPoint[i] == -1){
					list.get(i).meaning = 0;
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

	View.OnClickListener buttonHandler1 = new View.OnClickListener() {
		public void onClick(View v) {
			String answer = (String) tvOpA.getText();
			questionIndex++;

			if(answer == kanjiList.get(askedQuestionKanjiIndex).getMeaning()){ //right
				r++;
				addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] ++;
				if (addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] == 0){
					addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] ++;
				}
			}
			else{	//wrong
				w++;
				addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] = -1;
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
				addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] ++;
				if (addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] == 0){
					addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] ++;
				}
			}
			else{	//wrong
				w++;
				addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] = -1;
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
				addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] ++;
				if (addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] == 0){
					addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] ++;
				}
			}
			else{	//wrong
				w++;
				addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] = -1;
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
				addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] ++;
				if (addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] == 0){
					addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] ++;
				}
			}
			else{	//wrong
				w++;
				addPoint[kanjiList.get(askedQuestionKanjiIndex).getIndex()] = -1;
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
		int jump =  1 + ran.nextInt(kanjiList.size() - 1);

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
