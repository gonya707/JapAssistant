package com.grsynth.japaneseassistant.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.grsynth.japaneseassistant.R;

public class MainMenuActivity extends Activity {

	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		final Button b1 = (Button) findViewById(R.id.button1);
		final Button b2 = (Button) findViewById(R.id.button2);
		final Button b3 = (Button) findViewById(R.id.button3);
		final Button b4 = (Button) findViewById(R.id.button4);

		b1.setOnClickListener(buttonHandler1);
		b2.setOnClickListener(buttonHandler2);
		b3.setOnClickListener(buttonHandler3);
		b4.setOnClickListener(buttonHandler4);

		b1.setText("Vocabulary");
		b2.setText("Kanji");
		b3.setText("Test");
		b4.setText("Settings menu");
		
	}

	View.OnClickListener buttonHandler1 = new View.OnClickListener() {
		public void onClick(View v) {
			Intent toAnotherActivity = new Intent(v.getContext(), ListActivity.class);
			startActivityForResult(toAnotherActivity, 0);
		}
	};

	View.OnClickListener buttonHandler2 = new View.OnClickListener() {
		public void onClick(View v) {
			Intent toAnotherActivity = new Intent(v.getContext(), KanjiGridActivity.class);
			startActivityForResult(toAnotherActivity, 0);
		}
	};
	
	View.OnClickListener buttonHandler3 = new View.OnClickListener() {
		public void onClick(View v) {
			Intent toAnotherActivity = new Intent(v.getContext(), KanjiTestSettingsActivity.class);
			startActivityForResult(toAnotherActivity, 0);
		}
	};

	View.OnClickListener buttonHandler4 = new View.OnClickListener() {
		public void onClick(View v) {
			Intent toAnotherActivity = new Intent(v.getContext(), SettingsActivity.class);
			startActivityForResult(toAnotherActivity, 0);
		}
	};


}


