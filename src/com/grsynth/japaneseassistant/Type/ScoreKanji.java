package com.grsynth.japaneseassistant.Type;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ScoreKanji implements Serializable {
	
	int meaning;
	int kanji;
	int kunyomi;
	int onyomi;

	public ScoreKanji(int m, int k1, int k2, int o) {
		this.meaning = m;
		this.kanji = k1;
		this.kunyomi = k2;
		this.onyomi = o;
	}

}
