package com.grsynth.japaneseassistant.Type;

public class Kanji {
	private String kanji;
	private String onyomi;
	private String kunyomi;
	private String meaning;
	private int jlpt;
	private int jouyou;
	private String params;

	public Kanji(String kanji, String onyomi, String kunyomi, String meaning, int jouyou, String params, int jlpt) {
		super();
		this.kanji = kanji;
		this.onyomi = onyomi;
		this.kunyomi = kunyomi;
		this.meaning = meaning;
		this.jlpt = jlpt;
		this.jouyou = jouyou;
		this.params = params;
	}
	
	// cosas >_<

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

	public int getJlpt() {
		return jlpt;
	}

	public void setJlpt(int jlpt) {
		this.jlpt = jlpt;
	}

	public int getJouyou() {
		return jouyou;
	}

	public void setJouyou(int jouyou) {
		this.jouyou = jouyou;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	

}
