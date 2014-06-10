package com.grsynth.japaneseassistant.activity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.grsynth.japaneseassistant.R;
import com.grsynth.japaneseassistant.Type.Kanji;
import com.grsynth.japaneseassistant.Type.ScoreKanji;
import com.grsynth.japaneseassistant.utils.FileListReader;
import com.grsynth.japaneseassistant.utils.JapTextParser;

public class SettingsActivity extends Activity {

	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		final Button b1 = (Button) findViewById(R.id.button1);
		final Button b2 = (Button) findViewById(R.id.button2);
		final Button b3 = (Button) findViewById(R.id.button3);
		
		b1.setOnClickListener(buttonHandler1);
		b2.setOnClickListener(buttonHandler2);
		b3.setOnClickListener(buttonHandler3);
		
		tv = (TextView) findViewById(R.id.tv);
		tv.setText("");
		
		b1.setText("Generate settings file");
		b2.setText("Reset kanji file");
		b3.setText("Reset score file");

	}
	
	View.OnClickListener buttonHandler1 = new View.OnClickListener() {
		public void onClick(View v) {
			tv.append("Generating settings file...");
			FileInputStream fin;
			

			try {
				tv.append("\nExiste el archivo settings?");
				fin = openFileInput("settings");
				ObjectInputStream ois = new ObjectInputStream(fin);
				String user = (String) ois.readObject();
				ois.close();
				
				tv.append(" SI");
				tv.append("\nUser is: " + user);

			} catch (IOException e) {
				tv.append(" NO");
				tv.append("\nCreando... ");
				
				try {
					FileOutputStream fos = openFileOutput("settings", Context.MODE_PRIVATE);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					String alyed = "default";
					oos.writeObject(alyed);
					fos.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				tv.append(" hecho\nThis does nothing though ( ͡° ͜ʖ ͡°)");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	};
	
	View.OnClickListener buttonHandler2 = new View.OnClickListener() {
		public void onClick(View v) {
			
			tv.append("\nExiste el archivo kanji?");

			try {
				FileInputStream fin = openFileInput("kanji");
				ObjectInputStream ois = new ObjectInputStream(fin);
				ois.close();
				tv.append(" SI");
				
				tv.append("\nBorrando kanji de la memoria... ");
				deleteFile("kanji");
				tv.append("hecho");
				
			} catch (FileNotFoundException e) {
				tv.append(" NO");
				e.printStackTrace();
			} catch (StreamCorruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
			FileListReader flr;
			String dump; 
			String entries[]; 
			JapTextParser jtp;
			
			tv.append("\nLeyendo lists/kanji... ");
			flr = new FileListReader("lists/kanji");
			dump = flr.readTxt(getApplicationContext());
			jtp = new JapTextParser(dump);
			entries = jtp.getCompleteList();
			tv.append("hecho");
			
			tv.append("\nCreando archivo kanji");
			FileOutputStream fos2;
			try {
				fos2 = openFileOutput("kanji", Context.MODE_PRIVATE);
				ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
				for (int i = 0; i < entries.length; i++){
					Kanji k = new Kanji(entries[i] , i);
					oos2.writeObject(k);
					if (i % 100 == 0){
						tv.append("\nInsertado: (" + i + ") " + k.getKanji());
					}
				}
				fos2.close();
				tv.append("\nArchivo Kanji cerrado.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	};
	
	View.OnClickListener buttonHandler3 = new View.OnClickListener() {
		public void onClick(View v) {

			tv.append("\nExiste el archivo scoreKanji?");

			try {
				FileInputStream fin = openFileInput("scoreKanji");
				ObjectInputStream ois = new ObjectInputStream(fin);
				ois.close();
				tv.append(" SI");
				
				tv.append("\nBorrando scoreKanji de la memoria... ");
				deleteFile("scoreKanji");
				tv.append("hecho");
				
			} catch (FileNotFoundException e) {
				tv.append(" NO");
				e.printStackTrace();
			} catch (StreamCorruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 

			try {
				tv.append("\nCreando scoreKanji...");
				FileOutputStream fos = openFileOutput("scoreKanji", Context.MODE_PRIVATE);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				
				for(int i = 0; i < getResources().getInteger(R.integer.number_of_kanji); i++){
					ScoreKanji sk = new ScoreKanji(-1, -1, -1, -1, i);
					oos.writeObject(sk);
					if (i % 100 == 0){
						tv.append("\nInsertado: (" + i + ") " + sk.meaning);
					}
				}
				fos.close();
				tv.append(" hecho.");
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (NotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	};
	
}