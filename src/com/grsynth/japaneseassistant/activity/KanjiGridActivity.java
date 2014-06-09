package com.grsynth.japaneseassistant.activity;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

import com.grsynth.japaneseassistant.R;
import com.grsynth.japaneseassistant.Type.Kanji;
import com.grsynth.japaneseassistant.Type.ScoreKanji;
public class KanjiGridActivity extends Activity {

	private static final String TAG = "KanjiGridActivity"; 
	Spinner spinner1;
	Spinner spinner2;
	GridView gridView;
	ArrayList<Kanji> kanjiList;
	ArrayList<ScoreKanji> scoreList = new ArrayList<ScoreKanji>();
	ArrayAdapter<String> adapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kanji_grid);

		Log.d(TAG, "Entering onCreate");

		// FILE READING AND DUMPING
		
		Kanji k;
		String ka[] = new String[getResources().getInteger(R.integer.number_of_kanji)];
		kanjiList = new ArrayList<Kanji>();

		try{
			FileInputStream finK = openFileInput("kanji");
			ObjectInputStream oisK = new ObjectInputStream(finK);
			
			FileInputStream finS = openFileInput("scoreKanji");
			ObjectInputStream oisS = new ObjectInputStream(finS);

			for(int i = 0; i < getResources().getInteger(R.integer.number_of_kanji); i++){ 
				k = (Kanji) oisK.readObject();
				kanjiList.add(k);
				ka[i] = k.getKanji();
				
				scoreList.add((ScoreKanji) oisS.readObject());
			}
			

			oisK.close();
			oisS.close();

		}catch(Exception ex){
			ex.printStackTrace();
		} 

		gridView = (GridView) findViewById(R.id.gridView1);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);

		showFirstBoot();

		// SPINNER HANDLES

		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

				String[] smjb;

				switch (spinner1.getSelectedItemPosition()){
				case 1: //strokes
					smjb = getResources().getStringArray(R.array.filter_stroke);
					break;
				case 2: //JLPT
					smjb = getResources().getStringArray(R.array.jlpt_level);
					break;
				case 3:
					smjb = getResources().getStringArray(R.array.jouyou_level);
					break;
				default:
					smjb = getResources().getStringArray(R.array.empty_list);
					break;
				}

				ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, smjb);
				spinner2.setAdapter(spinnerArrayAdapter);

				switch (spinner1.getSelectedItemPosition()){
				case 1: //strokes
					spinner2.setSelection(0, false);
					break;
				case 2: //JLPT
					spinner2.setSelection(5, false);
					break;
				case 3:
					spinner2.setSelection(0, false);
					break;
				}

			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				//Toast.makeText(getApplicationContext(), "nada seleccionado",Toast.LENGTH_SHORT).show();
			}
		});

		// BUTTON HANDLES

		final Button b1 = (Button) findViewById(R.id.button);
		b1.setOnClickListener(buttonHandler1);


	}

	private void showFirstBoot(){ // set the inicial grid adapter to JLPT N5 to avoid overcharge

		spinner1.setSelection(2, false); //jltp
		spinner2.setSelection(5, false);

		String ka[] = new String[getResources().getInteger(R.integer.max_filter_length)]; 
		int filter;
		int compareTo = 5;
		Kanji kanjiBuffer;
		int j = 0;
		final ArrayList<Kanji> kanjiList2 = new ArrayList<Kanji>();

		for (int i = 0; i < getResources().getInteger(R.integer.number_of_kanji); i++){
			kanjiBuffer = kanjiList.get(i);
			filter = kanjiBuffer.getIntJlpt();
			if (filter == compareTo){
				ka[j] = kanjiBuffer.getKanji();
				kanjiList2.add(kanjiBuffer);
				j++;
			}
		}

		//adapter.clear();
		try {
			final String[] grp = Arrays.copyOfRange(ka, 0, j);
			adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, grp);
			gridView.setAdapter(adapter);

			gridView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
					Intent toAnotherActivity = new Intent(v.getContext(), KanjiEntryActivity.class);
					toAnotherActivity.putExtra("info", kanjiList2.get(position));
					toAnotherActivity.putExtra("position", position);
					toAnotherActivity.putExtra("list", kanjiList2);
					startActivityForResult(toAnotherActivity, 0);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	View.OnClickListener buttonHandler1 = new View.OnClickListener() {
		public void onClick(View v) {
			//Toast.makeText(getApplicationContext(), "Aplicar filtro: " + spinner1.getSelectedItemPosition() +", " + spinner2.getSelectedItemPosition() ,Toast.LENGTH_SHORT).show();

			String ka[] = new String[getResources().getInteger(R.integer.max_filter_length)]; 
			int filter;
			int compareTo;
			Kanji kanjiBuffer;
			int j = 0;
			final ArrayList<Kanji> kanjiList2 = new ArrayList<Kanji>();
			//if (spinner2.getSelectedItemPosition() !=0){
			//rellenar ka con los elementos filtrados de kanjiList

			switch (spinner1.getSelectedItemPosition()){
			case 1: // number of strokes

				compareTo = spinner2.getSelectedItemPosition() + 1;

				for (int i = 0; i < getResources().getInteger(R.integer.number_of_kanji); i++){
					kanjiBuffer = kanjiList.get(i);
					filter = kanjiBuffer.getIntStrokes();
					if (filter == compareTo){
						ka[j] = kanjiBuffer.getKanji();
						kanjiList2.add(kanjiBuffer);
						j++;
					}
				}
				break;

			case 2: // jlpt

				compareTo = spinner2.getSelectedItemPosition();
				for (int i = 0; i < getResources().getInteger(R.integer.number_of_kanji); i++){
					kanjiBuffer = kanjiList.get(i);
					filter = kanjiBuffer.getIntJlpt();
					if (filter == compareTo){
						ka[j] = kanjiBuffer.getKanji();
						kanjiList2.add(kanjiBuffer);
						j++;
					}
				}
				break;

			case 3: // jouyou 

				String filterS;

				compareTo = spinner2.getSelectedItemPosition() + 1;

				for (int i = 0; i < getResources().getInteger(R.integer.number_of_kanji); i++){
					kanjiBuffer = kanjiList.get(i);
					filterS = kanjiBuffer.getJouyou();
					if(filterS.startsWith("名")){
						filterS = filterS.substring(1);
					} 
					if (Integer.parseInt(filterS) == compareTo){
						ka[j] = kanjiBuffer.getKanji();
						kanjiList2.add(kanjiBuffer);
						j++;
					}
				}
				break;
			}
			//}else 
			if(spinner1.getSelectedItemPosition() == 0){ // FIXME despues de hacer esto no se puede hacer click en un kanji
				for (int i = 0; i < getResources().getInteger(R.integer.number_of_kanji); i++){
					kanjiBuffer = kanjiList.get(i);
					ka[j] = kanjiBuffer.getKanji();
					kanjiList2.add(kanjiBuffer);
					j++;
				}
			}

			//adapter.clear();
			try {
				final String[] grp = Arrays.copyOfRange(ka, 0, j);
				adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, grp);
				gridView.setAdapter(adapter);

				gridView.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
						Intent toAnotherActivity = new Intent(v.getContext(), KanjiEntryActivity.class);
						toAnotherActivity.putExtra("info", kanjiList2.get(position));
						toAnotherActivity.putExtra("position", position);
						toAnotherActivity.putExtra("list", kanjiList2);
						startActivityForResult(toAnotherActivity, 0);
					}
				});


			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	};
}


