package com.grsynth.japaneseassistant.activity;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
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
import android.widget.Toast;

import com.grsynth.japaneseassistant.R;
import com.grsynth.japaneseassistant.Type.Kanji;
public class KanjiGridActivity extends Activity {

	private static final String TAG = "KanjiGridActivity"; 
	Spinner spinner1;
	Spinner spinner2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kanji_grid);

		Log.d(TAG, "Entering onCreate");

		// FILE READING AND DUMPING
		FileInputStream fin;
		final List<Kanji> kanjiList = new ArrayList<Kanji>();
		Kanji k;
		String ka[] = new String[2217];

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

		GridView gridView = (GridView) findViewById(R.id.gridView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ka);

		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Intent toAnotherActivity = new Intent(v.getContext(), KanjiEntryActivity.class);
				toAnotherActivity.putExtra("info", kanjiList.get(position));
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
			Toast.makeText(getApplicationContext(), "Aplicar filtro: " + spinner1.getSelectedItemPosition() +", " + spinner2.getSelectedItemPosition() ,Toast.LENGTH_SHORT).show();
		}
	};

}

