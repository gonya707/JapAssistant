package com.grsynth.japaneseassistant.utils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;

public class FileListReader {
	
	static String path;
	
	public FileListReader(String givenPath){
		path = givenPath;
		
		//DEBUG
		//path = "/JapaneseAssistant/assets/lists/verbsN5";
	}

	public String readTxt(Context c){

		AssetManager am = c.getAssets();
		InputStream inputStream = null;
		try {
			inputStream = am.open(path);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		int i;
		try{
			i = inputStream.read();
			while (i != -1){
				byteArrayOutputStream.write(i);
				i = inputStream.read();
			}
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return byteArrayOutputStream.toString();
	}

}
