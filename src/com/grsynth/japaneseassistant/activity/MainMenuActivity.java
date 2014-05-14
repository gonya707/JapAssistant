package com.grsynth.japaneseassistant.activity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.grsynth.japaneseassistant.R;
import com.grsynth.japaneseassistant.Type.Kanji;
import com.grsynth.japaneseassistant.Type.ScoreKanji;
import com.grsynth.japaneseassistant.utils.FileListReader;
import com.grsynth.japaneseassistant.utils.JapTextParser;

public class MainMenuActivity extends ActionBarActivity {

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
		
		tv = (TextView) findViewById(R.id.tv);
		tv.setText("");

		b1.setText("Vocabulary");
		b2.setText("Kanji");
		b3.setText("Load everything");
		b4.setText("");
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

			// BLOQUE LECTURA
			FileInputStream fin;
			
			tv.setText("Entrando a onClick");

			try {
				tv.append("\nExiste el archivo settings?");
				fin = openFileInput("settings");
				ObjectInputStream ois = new ObjectInputStream(fin);
				@SuppressWarnings("unused")
				String user = (String) ois.readObject();
				ois.close();
				
				tv.append(" SI");
				tv.append("\nVamos a intentar 10 kanjis");
				
				fin = openFileInput("kanji");
				ois = new ObjectInputStream(fin);
				Kanji k1;
				for (int i = 0; i < 10; i++){
					k1 = (Kanji) ois.readObject();
					tv.append("\nLeido: " + k1.getKanji());
				}
			} catch (FileNotFoundException e) {
				
				tv.append(" NO\nCreando archivos");
				// BLOQUE ESCRITURA
				FileOutputStream fos;
				try {
					
					tv.append("\nCreando settings...");
					fos = openFileOutput("settings", Context.MODE_PRIVATE);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					String alyed = "default";
					oos.writeObject(alyed);
					fos.close();
					tv.append(" hecho");
					
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
					fos2 = openFileOutput("kanji", Context.MODE_PRIVATE);
					ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
					for (int i = 0; i < entries.length; i++){
						Kanji k = new Kanji(entries[i] , i);
						oos2.writeObject(k);
						tv.append("\nInsertado: " + k.getKanji());
					}
					fos.close();
					tv.append("\nArchivo Kanji cerrado.");
					
					tv.append("\nCreando scoreKanji...");
					fos = openFileOutput("scoreKanji", Context.MODE_PRIVATE);
					oos = new ObjectOutputStream(fos);
					ScoreKanji sk = new ScoreKanji(0, 0, 0, 0);
					for(int i = 0; i < entries.length; i++){
						oos.writeObject(sk);
					}
					fos.close();
					tv.append(" hecho");

					// clear entries... kind of
					entries = null;
					dump = null;
					jtp = null;
					System.gc();

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
					tv.append("\nFILE NOT FOUND");
				} catch (IOException e1) {
					e.printStackTrace();
					tv.append("\nIOEXCEPTION");
				}
				
			} catch (StreamCorruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
	};

	View.OnClickListener buttonHandler4 = new View.OnClickListener() {
		public void onClick(View v) {
			// it was the 4nd button
		}
	};
}


