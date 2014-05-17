package com.grsynth.japaneseassistant.activity;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.grsynth.japaneseassistant.R;
import com.grsynth.japaneseassistant.Type.Kanji;

public class KanjiTestSettingsActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kanji_test_settings);

		final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
		final Button btnDisplay = (Button) findViewById(R.id.button);
		final CheckBox chkIos1 = (CheckBox) findViewById(R.id.checkBox1);
		final CheckBox chkIos2 = (CheckBox) findViewById(R.id.checkBox2);
		final CheckBox chkIos3 = (CheckBox) findViewById(R.id.checkBox3);
		final CheckBox chkIos4 = (CheckBox) findViewById(R.id.checkBox4);
		final CheckBox chkIos5 = (CheckBox) findViewById(R.id.checkBox5);
		final Spinner spinner = (Spinner) findViewById(R.id.spinner);

		btnDisplay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Kanji k;
				ArrayList<Kanji> kanjiList= new ArrayList<Kanji>();
				RadioButton rb = (RadioButton) findViewById(rg.getCheckedRadioButtonId());

				if (chkIos1.isChecked() || chkIos2.isChecked() || chkIos3.isChecked() || chkIos4.isChecked() || chkIos5.isChecked()){

					//List
					try{
						FileInputStream fin = openFileInput("kanji");
						ObjectInputStream ois = new ObjectInputStream(fin);
						for(int i = 0; i < getResources().getInteger(R.integer.number_of_kanji); i++){
							k = (Kanji) ois.readObject();
							if (chkIos1.isChecked() && k.getIntJlpt() == 1 ||
									chkIos2.isChecked() && k.getIntJlpt() == 2 ||
									chkIos3.isChecked() && k.getIntJlpt() == 3 ||
									chkIos4.isChecked() && k.getIntJlpt() == 4 ||
									chkIos5.isChecked() && k.getIntJlpt() == 5){
								kanjiList.add(k);
							}
						}
						ois.close();
					}catch(Exception ex){
						ex.printStackTrace();
					} 

					int questions = 0;
					//N of questions
					switch(spinner.getSelectedItemPosition()){
					case 0:
						questions = 10;
						break;
					case 1:
						questions = 25;
						break;
					case 2:
						questions = 50;
						break;
					case 3:
						questions = 100;
						break;
					case 4:
						questions = 0;
						break;
					}

					Intent toAnotherActivity = null;
					switch (rg.indexOfChild(rb)){
					case 0:
						toAnotherActivity = new Intent(v.getContext(), KanjiToMeaningTestActivity.class);
						break;
					case 1:
						toAnotherActivity = new Intent(v.getContext(), MeaningToKanjiTestActivity.class); 
						break;
					default:
		
						break;
					}

					toAnotherActivity.putExtra("nQuestion", questions);
					toAnotherActivity.putExtra("list", kanjiList);
					startActivityForResult(toAnotherActivity, 0);
				}
				else{
					Toast.makeText(getApplicationContext(), "Please select at least one level", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

}
