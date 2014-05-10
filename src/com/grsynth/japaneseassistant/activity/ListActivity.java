package com.grsynth.japaneseassistant.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.grsynth.japaneseassistant.R;
import com.grsynth.japaneseassistant.utils.FileListReader;
import com.grsynth.japaneseassistant.utils.JapTextParser;

public class ListActivity extends ActionBarActivity {

	String values[] = {"N5 verbs", "N5 vocab", "N5 kanji"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		String display = getIntent().getExtras().getString("display");
		StableArrayAdapter adapter;
		FileListReader flr;
		String dump; 

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
					//Toast toast = Toast.makeText(getApplicationContext(), item.toString(), Toast.LENGTH_SHORT);
					//toast.show();
					Intent toAnotherActivity = new Intent(view.getContext(), ListActivity.class);
					toAnotherActivity.putExtra("display", item);
					startActivityForResult(toAnotherActivity, 0);
				}
			});
			break;

		case "N5 verbs":
			flr = new FileListReader("lists/verbsN5");
			dump = flr.readTxt(getApplicationContext());
			//JapTextParser jtp = new JapTextParser(dump);
			
			values = dump.split("\\r?\\n");

			for (int i = 0; i < values.length; ++i) {
				list.add(values[i]);
			}

			adapter = new StableArrayAdapter(this,	android.R.layout.simple_list_item_1, list);
			listview.setAdapter(adapter);

			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
					final String item = (String) parent.getItemAtPosition(position);
					Toast toast = Toast.makeText(getApplicationContext(), item.toString(), Toast.LENGTH_SHORT);
					toast.show();
				}
			});
			break;
			
		case "N5 kanji":
			flr = new FileListReader("lists/kanjisN5");
			dump = flr.readTxt(getApplicationContext());
			values = dump.split("\\r?\\n");

			for (int i = 0; i < values.length; ++i) {
				list.add(values[i]);
			}

			adapter = new StableArrayAdapter(this,	android.R.layout.simple_list_item_1, list);
			listview.setAdapter(adapter);

			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
					final String item = (String) parent.getItemAtPosition(position);
					Toast toast = Toast.makeText(getApplicationContext(), item.toString(), Toast.LENGTH_SHORT);
					toast.show();
				}
			});
			break;
			
		case "N5 vocab":
			flr = new FileListReader("lists/vocabN5");
			dump = flr.readTxt(getApplicationContext());
			values = dump.split("\\r?\\n");

			for (int i = 0; i < values.length; ++i) {
				list.add(values[i]);
			}

			adapter = new StableArrayAdapter(this,	android.R.layout.simple_list_item_1, list);
			listview.setAdapter(adapter);

			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
					final String item = (String) parent.getItemAtPosition(position);
					Toast toast = Toast.makeText(getApplicationContext(), item.toString(), Toast.LENGTH_SHORT);
					toast.show();
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


