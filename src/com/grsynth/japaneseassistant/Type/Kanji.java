package com.grsynth.japaneseassistant.Type;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Kanji  implements Serializable {
	private int index;
	private String kanji;
	private String onyomi;
	private String kunyomi;
	private String meaning;
	private String jlpt;
	private String jouyou;
	private String params;
	private boolean open;
	private int score;

	public Kanji(String kanji, String onyomi, String kunyomi, String meaning, String jouyou, String jlpt, String params) {
		super();
		this.kanji = kanji;
		this.onyomi = onyomi;
		this.kunyomi = kunyomi;
		this.meaning = meaning;
		this.jlpt = jlpt;
		this.jouyou = jouyou;
		this.params = params;
	}
	
	public Kanji(String kanjiString, int i) {
		super();
		
		String a[] = kanjiString.split("\\t");
		
		this.kanji = a[0];
		this.onyomi = a[1];
		this.kunyomi = a[2];
		this.meaning = a[3];
		this.jlpt = a[5];
		this.jouyou = a[4];
		this.params = a[6];
		
		this.open = false;
		this.score = 0;
		this.index = i;
	}
	
	// cosas >_<

	public String getKanji() {
		return kanji;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
	
	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


}
