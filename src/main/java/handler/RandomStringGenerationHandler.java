package handler;

import java.util.Random;

public class RandomStringGenerationHandler {

	public static String randomStringFactory(int length) {

		Random rnd = new Random();

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < length; i++) {
			if (rnd.nextBoolean()) {
				buf.append((char) ((int) (rnd.nextInt(26)) + 97));
			} else {
				buf.append((rnd.nextInt(10)));
			}
		}

		return buf.toString();

	}

}
