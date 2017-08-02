package com.wheejuni.spring;

import java.util.ArrayList;

public class Umpire {

	public static int Strike(ArrayList<Integer> userball, ArrayList<Integer> computerball) {

		int strikeCount = 0;

		for (int i = 0; i < userball.size(); i++) {
			if (userball.get(i) == computerball.get(i)) {
				strikeCount += 1;
			}

		}

		return strikeCount;

	}

	public static int Ball(ArrayList<Integer> userball, ArrayList<Integer> computerball) {

		int ballCount = 0;
		for (int i = 0; i < userball.size(); i++) {

			if (computerball.contains(userball.get(i)) && userball.get(i) != computerball.get(i)) {
				ballCount += 1;
			}
		}

		return ballCount;
	}
}
