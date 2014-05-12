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
import android.widget.GridView;

import com.grsynth.japaneseassistant.R;
import com.grsynth.japaneseassistant.Type.Kanji;
public class KanjiGridActivity extends Activity {

	private static final String TAG = "KanjiGridActivity"; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kanji_grid);
		
		Log.d(TAG, "Entering onCreate");
		
		FileInputStream fin;
		final List<Kanji> kanjiList = new ArrayList<Kanji>();
		Kanji k;
		String ka[] = new String[100];
		
		try{
			fin = openFileInput("kanji");
			ObjectInputStream ois = new ObjectInputStream(fin);
			
			
			for(int i = 0; i < 100; i++){ //hacerlo hasta EOF, el fichero escrito esta entero
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
	}

}

