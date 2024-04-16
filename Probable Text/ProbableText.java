package model;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

//Author: Jason Butterworth.

public class ProbableText {

	public StringBuilder createString(String bookName) throws FileNotFoundException {
		StringBuilder storyString = new StringBuilder();
		File storyFile = new File(bookName);
		Scanner input = new Scanner(storyFile);
		while (input.hasNextLine()) {
			storyString.append(input.nextLine());
			storyString.append(" ");
		}
		return storyString;
	}

	public OurHashMap createMap(StringBuilder storyString, int nLength) {

		OurHashMap<String, ArrayList<Character>> myMap = new OurHashMap();

		for (int i = 0; i < storyString.length() - nLength; i++) {
			String ngramKey = storyString.substring(i, i + nLength);
			char ngramValue = storyString.charAt(i + nLength);

			if (!myMap.containsKey(ngramKey)) {
				ArrayList<Character> potentialVals = new ArrayList<Character>();
				potentialVals.add(ngramValue);
				myMap.put(ngramKey, potentialVals);
			} else {
				ArrayList<Character> potentialVals = myMap.get(ngramKey);
				potentialVals.add(ngramValue);
				myMap.put(ngramKey, potentialVals);
			}

		}

		return myMap;
	}

	public StringBuilder createStory(StringBuilder originalStory, OurHashMap<String, ArrayList<Character>> myMap,
			int letterCount, int nLength) {
		StringBuilder newStory = new StringBuilder();
		Random rand = new Random();
		int min = 0;
		int max = originalStory.length() - 20;
		int ngramStart = rand.nextInt((max - min) + 1) + min;
		String ngramKey = originalStory.substring(ngramStart, ngramStart + nLength);
		int i = 0;
		while (i < letterCount) {
			ArrayList<Character> potentialChars = myMap.get(ngramKey);
			int arrayMin = 0;
			int arrayMax = potentialChars.size();
			int randomCharIdx = rand.nextInt(arrayMax - arrayMin) + arrayMin;
			char addedCharacter = potentialChars.get(randomCharIdx);
			newStory.append(addedCharacter);
			ngramKey = ngramKey.substring(1) + String.valueOf(addedCharacter);
			i++;
		}
		return newStory;

	}

	public void printStory(StringBuilder newStory, int letterCount) {
		int count = 0;
		while (count < letterCount) {
			System.out.print(newStory.substring(0, 1));
			newStory = newStory.delete(0, 1);
			count++;
			if (count % 60 == 0) {
				System.out.println();
			}
		}

	}

}
