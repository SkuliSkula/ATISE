package org.camunda.bpm.fotof;

import java.util.Random;

public class Url {
	public static String generateURL() {
		Random rnd = new Random();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("http://www.dropbox.com/");
		stringBuilder.append("/"+ rnd.nextInt());
		return stringBuilder.toString();
	}
}