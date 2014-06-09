package com.grsynth.japaneseassistant.Type;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ScoreKanji implements Serializable {
	
	public int meaning;
	public int kanji;
	public int kunyomi;
	public int onyomi;
	public int index;

	public ScoreKanji(int m, int k1, int k2, int o, int index) {
		this.meaning = m;
		this.kanji = k1;
		this.kunyomi = k2;
		this.onyomi = o;
		this.index = index;
	}

}
