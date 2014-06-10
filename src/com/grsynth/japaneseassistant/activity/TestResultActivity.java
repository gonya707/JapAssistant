package com.grsynth.japaneseassistant.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.grsynth.japaneseassistant.R;

public class TestResultActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_result);

		TextView tv1 = (TextView) findViewById(R.id.right);
		TextView tv2 = (TextView) findViewById(R.id.wrong);
		TextView tv3 = (TextView) findViewById(R.id.questions);
		TextView tv4 = (TextView) findViewById(R.id.mode);
		Button b = (Button) findViewById(R.id.b);
		
		tv1.setText("Right: " + getIntent().getExtras().getInt("right"));
		tv2.setText("Wrong: " + getIntent().getExtras().getInt("wrong"));
		tv3.setText("Category: " + getIntent().getExtras().getString("category"));
		tv4.setText("Number of questions: " + getIntent().getExtras().getInt("nQuestion"));
		
		b.setOnClickListener(buttonHandler1);

	}
	
	View.OnClickListener buttonHandler1 = new View.OnClickListener() {
		public void onClick(View v) {
			finish();
			Intent in = new Intent(getApplicationContext(), MainMenuActivity.class);
			startActivityForResult(in, 0);
		}
	};
	
	
}