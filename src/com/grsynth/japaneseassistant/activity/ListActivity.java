package com.grsynth.japaneseassistant.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.grsynth.japaneseassistant.R;
import com.grsynth.japaneseassistant.utils.FileListReader;
import com.grsynth.japaneseassistant.utils.JapTextParser;

public class ListActivity extends ActionBarActivity {

	String values[] = {"N5 verbs", "N5 kanji"}; //he quitado N5 vocab
	private static final String TAG = "ListActivity"; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		Log.d(TAG, "Entering onCreate");

		String display = getIntent().getExtras().getString("display");
		StableArrayAdapter adapter;
		FileListReader flr;
		String dump; 
		final JapTextParser jtp;

		final ListView listview = (ListView) findViewById(R.id.listView1);
		final ArrayList<String> list = new ArrayList<String>();

		switch (display){

		case "list": 

			for (int i = 0; i < values.length; ++i) {
				list.add(values[i]);
			}

			adapter = new StableArrayAdapter(this,	android.R.layout.simple_list_item_1, list);
			listview.setAdapter(adapter);

			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
					final String item = (String) parent.getItemAtPosition(position);
					Intent toAnotherActivity = new Intent(view.getContext(), ListActivity.class);
					toAnotherActivity.putExtra("display", item);
					startActivityForResult(toAnotherActivity, 0);
				}
			});
			break;

		case "N5 verbs":
			flr = new FileListReader("lists/verbsN5");
			dump = flr.readTxt(getApplicationContext());
			jtp = new JapTextParser(dump);
			
			values = jtp.getShowList();

			for (int i = 0; i < values.length; ++i) {
				list.add(values[i]);
			}

			adapter = new StableArrayAdapter(this,	android.R.layout.simple_list_item_1, list);
			listview.setAdapter(adapter);

			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
					Intent toAnotherActivity = new Intent(view.getContext(), VocabEntryActivity.class);
					toAnotherActivity.putExtra("info", jtp.getElement(position));
					startActivityForResult(toAnotherActivity, 0);
				}
			});
			break;
			
		case "N5 kanji":
			flr = new FileListReader("lists/kanjiN5");
			dump = flr.readTxt(getApplicationContext());
			jtp = new JapTextParser(dump);
			
			values = jtp.getShowList();

			for (int i = 0; i < values.length; ++i) {
				list.add(values[i]);
			}

			adapter = new StableArrayAdapter(this,	android.R.layout.simple_list_item_1, list);
			listview.setAdapter(adapter);

			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
					Intent toAnotherActivity = new Intent(view.getContext(), KanjiEntryActivity.class);
					toAnotherActivity.putExtra("info", jtp.getElement(position));
					startActivityForResult(toAnotherActivity, 0);
				}
			});
			break;
			
		}

	}

}

class StableArrayAdapter extends ArrayAdapter<String> {

	HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

	public StableArrayAdapter(Context context, int textViewResourceId,
			List<String> objects) {
		super(context, textViewResourceId, objects);
		for (int i = 0; i < objects.size(); ++i) {
			mIdMap.put(objects.get(i), i);
		}
	}

	@Override
	public long getItemId(int position) {
		String item = getItem(position);
		return mIdMap.get(item);
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

}


