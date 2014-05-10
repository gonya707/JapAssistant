package com.grsynth.japaneseassistant.utils;

public class JapTextParser {
	
	/*TODO clase que lea cada linea de la string resultante de leer los archivos de assets
	 tiene que discernir si eran: 
	         kanji	onyomi	kunyomi	meaning	jlpt	jouyou	params
			o bien
	         word  kana  meaning  jlpt  params 
	 y estructurarlos en un array de Kanji o Vocab segun proceda
	         
	         
	         */
	private String source;

	public JapTextParser(String source) {
		super();
		this.source = source;
	}
	
	
	
	
}