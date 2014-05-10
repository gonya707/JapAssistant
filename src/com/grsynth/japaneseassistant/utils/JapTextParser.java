package com.grsynth.japaneseassistant.utils;

import android.util.Log;

public class JapTextParser {
	
	/*TODO clase que lea cada linea de la string resultante de leer los archivos de assets
	 tiene que discernir si eran: 
	         kanji	onyomi	kunyomi	meaning	jlpt	jouyou	params
			o bien
	         word  kana  meaning  jlpt  params 
	 y estructurarlos en un array de Kanji o Vocab segun proceda
	         
	         
	         */
	private static final String TAG = "JapTextParser"; 
	
	private String sourceListed[];
	boolean isKanji = false;

	public JapTextParser(String source) {
		super();
		
		sourceListed = source.split("\\r?\\n");
		isKanjiList(sourceListed[0]);
	}
	
	private void isKanjiList(String sl){
		String line[];
		line = sl.split("\\t");
		
		if(line.length == 7){
			isKanji = true;
		}
	}

	public String[] getShowList() {
		// TODO crea un string con los elementos para mostrar en lista"
		//    kanji - kun - on - meaning
		//    word - kana - meaning
		
		 Log.d(TAG, "Entering getShowList");
		 Log.d(TAG, "sourceListed.length = " + sourceListed.length);
		
		String res[];
		res = new String[sourceListed.length];
		
		String line[];
		String buff;
		
		// FIXME untested.
		if (this.isKanji){
			for (int i = 0; i < sourceListed.length; i++){
				buff = sourceListed[i];
				line = buff.split("\\t");
				res[i] = line[0]+ "  " + line[1]+ "  " + line[2]+ "  " + line[3];
			}
		}
		else{
			for (int i = 0; i < sourceListed.length; i++){
				buff = sourceListed[i];
				line = buff.split("\\t");
				res[i] = line[0]+ "  " + line[1] + "  "  + line[2];
			}
		}
		
		return res;
	}

	public String[] getElement(int position) {
		String l = sourceListed[position];		
		String res[] = l.split("\\t");
		return res;
	}
	
	
	
	
}