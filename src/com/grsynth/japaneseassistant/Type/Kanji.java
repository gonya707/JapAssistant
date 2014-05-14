package com.grsynth.japaneseassistant.Type;

import java.io.Serializable;

import android.os.Parcel;

@SuppressWarnings("serial")
public class Kanji implements Serializable{
	private String kanji;
	private String onyomi;
	private String kunyomi;
	private String meaning;
	private String jlpt;
	private String jouyou;
	private String strokes;
	private String radical;
	private int index;


	public Kanji(String kanji, String onyomi, String kunyomi, String meaning, String jouyou, String jlpt, int index, String strokes, String radical) {
		super();
		this.kanji = kanji;
		this.onyomi = onyomi;
		this.kunyomi = kunyomi;
		this.meaning = meaning;
		this.jlpt = jlpt;
		this.jouyou = jouyou;
		this.index = index;
		this.strokes = strokes;
		this.radical = radical;

	}
	

	public Kanji(String kanjiString, int index) {
		super();
		
		String a[] = kanjiString.split("\\t");
		
		this.kanji = a[0];
		this.onyomi = a[1];
		this.kunyomi = a[2];
		this.meaning = a[3];
		this.strokes = a[4];
		this.radical = a[5];
		this.jouyou = a[6];
		this.jlpt = a[7];
		
		this.index = index;

	}
	
	public String toString(){
		return kanji + "\t" + onyomi + "\t" + kunyomi + "\t" + meaning + "\t" +  strokes + "\t" + radical + "\t" + jouyou + "\t" + jlpt;
	}
	
	public String getStrokes() {
		return strokes;
	}

	public void setStrokes(String strokes) {
		this.strokes = strokes;
	}

	public String getRadical() {
		return radical;
	}

	public void setRadical(String radical) {
		this.radical = radical;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getKanji() {
		return kanji;
	}

	public void setKanji(String kanji) {
		this.kanji = kanji;
	}

	public String getOnyomi() {
		return onyomi;
	}

	public void setOnyomi(String onyomi) {
		this.onyomi = onyomi;
	}

	public String getKunyomi() {
		return kunyomi;
	}

	public void setKunyomi(String kunyomi) {
		this.kunyomi = kunyomi;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getJlpt() {
		return jlpt;
	}

	public void setJlpt(String jlpt) {
		this.jlpt = jlpt;
	}

	public String getJouyou() {
		return jouyou;
	}

	public void setJouyou(String jouyou) {
		this.jouyou = jouyou;
	}


	public int getIntStrokes() {
		return Integer.parseInt(this.strokes);
	}
	
	public int getIntJlpt() {
		return Integer.parseInt(this.jlpt);
	}
	
	public int getIntJouyou() {
		return Integer.parseInt(this.jouyou);
	}

}
