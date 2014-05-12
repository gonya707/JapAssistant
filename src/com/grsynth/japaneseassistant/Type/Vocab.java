package com.grsynth.japaneseassistant.Type;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Vocab  implements Serializable {
	private String word;
	private String kana;
	private String meaning;
	private String jlpt;
	private String params;
	
	public Vocab(String word, String kana, String meaning, String jlpt, String params) {
		super();
		this.word = word;
		this.kana = kana;
		this.meaning = meaning;
		this.jlpt = jlpt;
		this.params = params;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getKana() {
		return kana;
	}

	public void setKana(String kana) {
		this.kana = kana;
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

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
	
	

}
