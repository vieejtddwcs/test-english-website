package com.testenglish.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class MyIOUtils {
	
	private MyIOUtils() {
		throw new AssertionError();
	}

	public static String readToString(Reader reader) throws IOException {
		StringBuilder fileContent = new StringBuilder();
		BufferedReader br = new BufferedReader(reader);
		String line = null;
		while ((line = br.readLine()) != null) fileContent.append(line).append(System.lineSeparator());
		br.close();
		return fileContent.toString();
	}
	
}