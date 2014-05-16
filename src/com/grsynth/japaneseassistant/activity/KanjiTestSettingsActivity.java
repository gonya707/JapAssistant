package com.grsynth.japaneseassistant.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.grsynth.japaneseassistant.R;

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

		btnDisplay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				RadioButton rb;
				rb = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
//
//				if (chkIos1.isChecked()) {
//
//				}

				//rb.getText()

				if (chkIos1.isChecked() || chkIos2.isChecked() || chkIos3.isChecked() || chkIos4.isChecked() || chkIos5.isChecked()){
					Intent toAnotherActivity = new Intent(v.getContext(), KanjiTestActivity.class);
					startActivityForResult(toAnotherActivity, 0);
				}
			}
		});

	}

}
