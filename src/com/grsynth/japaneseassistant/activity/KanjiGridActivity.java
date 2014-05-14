package com.grsynth.japaneseassistant.activity;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

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
public class KanjiGridActivity extends Activity {

	private static final String TAG = "KanjiGridActivity"; 
	Spinner spinner1;
	Spinner spinner2;
	GridView gridView;
	ArrayList<Kanji> kanjiList;
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kanji_grid);

		Log.d(TAG, "Entering onCreate");

		// FILE READING AND DUMPING
		FileInputStream fin;

		Kanji k;
		String ka[] = new String[2217];
		kanjiList = new ArrayList<Kanji>();

		try{
			fin = openFileInput("kanji");
			ObjectInputStream ois = new ObjectInputStream(fin);
			for(int i = 0; i < 2217; i++){ //hacerlo hasta EOF, el fichero escrito esta entero
				k = (Kanji) ois.readObject();
				kanjiList.add(k);
				ka[i] = k.getKanji();
			}

			ois.close();
		}catch(Exception ex){
			ex.printStackTrace();
		} 

		gridView = (GridView) findViewById(R.id.gridView1);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ka);
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Intent toAnotherActivity = new Intent(v.getContext(), KanjiEntryActivity.class);
				toAnotherActivity.putExtra("info", kanjiList.get(position));
				toAnotherActivity.putExtra("position", position);
				toAnotherActivity.putExtra("list", kanjiList);
				startActivityForResult(toAnotherActivity, 0);
			}
		});

		// SPINNER HANDLES

		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);

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

	View.OnClickListener buttonHandler1 = new View.OnClickListener() {
		public void onClick(View v) {
			//Toast.makeText(getApplicationContext(), "Aplicar filtro: " + spinner1.getSelectedItemPosition() +", " + spinner2.getSelectedItemPosition() ,Toast.LENGTH_SHORT).show();

			String ka[] = new String[2217]; 
			int filter;
			int compareTo;
			Kanji kanjiBuffer;
			int j = 0;
			final ArrayList<Kanji> kanjiList2 = new ArrayList<Kanji>();

			if (spinner1.getSelectedItemPosition() != 0 && spinner2.getSelectedItemPosition() !=0){
				//rellenar ka con los elementos filtrados de kanjiList

				switch (spinner1.getSelectedItemPosition()){
				case 1: // number of strokes

					compareTo = spinner2.getSelectedItemPosition();

					for (int i = 0; i < 2217; i++){
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

					for (int i = 0; i < 2217; i++){
						kanjiBuffer = kanjiList.get(i);
						filter = kanjiBuffer.getIntJlpt();
						if (filter == compareTo){
							ka[j] = kanjiBuffer.getKanji();
							kanjiList2.add(kanjiBuffer);
							j++;
						}
					}
					break;
					
				case 3: // jouyou FIXME no funciono

					compareTo = spinner2.getSelectedItemPosition();

					for (int i = 0; i < 2217; i++){
						kanjiBuffer = kanjiList.get(i);
						filter = kanjiBuffer.getIntJouyou();
						if (filter == compareTo){
							ka[j] = kanjiBuffer.getKanji();
							kanjiList2.add(kanjiBuffer);
							j++;
						}
					}
					break;
				}
			}

			//adapter.clear();
			try {
				final String[] grp = Arrays.copyOfRange(ka, 0, j-1);
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
	};

}

