package com.wheejuni.spring;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class BallShuffler {

	private final static String[] numbers = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };

	static ArrayList<Integer> returnRandint() {
		ArrayList <Integer> randomInteger = new ArrayList<>();
		List numlist = Arrays.asList(numbers);
		Collections.shuffle(numlist);
		
		for (int i = 0; i < 3; i++) {
			String temp = numlist.get(i).toString();
			randomInteger.add(Integer.parseInt(temp));
		}
		
		return randomInteger;
	}

}
