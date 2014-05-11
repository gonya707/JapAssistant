package com.grsynth.japaneseassistant.activity;

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
import com.grsynth.japaneseassistant.utils.FileListReader;
import com.grsynth.japaneseassistant.utils.JapTextParser;
public class KanjiGridActivity extends Activity {

	private static final String TAG = "ListActivity"; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kanji_grid);
		
		Log.d(TAG, "Entering onCreate");
		
		FileListReader flr;
		String dump; 
		final JapTextParser jtp;

		flr = new FileListReader("lists/kanjiN5");
		dump = flr.readTxt(getApplicationContext());
		jtp = new JapTextParser(dump);

		String values[] = jtp.getShowList();
		
		GridView gridView = (GridView) findViewById(R.id.gridView1);
		 
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
 
		gridView.setAdapter(adapter);
 
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Intent toAnotherActivity = new Intent(v.getContext(), KanjiEntryActivity.class);
				toAnotherActivity.putExtra("info", jtp.getElement(position));
				startActivityForResult(toAnotherActivity, 0);
			}
		});
	}

}

