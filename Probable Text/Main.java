package view_controller;

import java.io.FileNotFoundException;
import java.util.Scanner;

import model.OurHashMap;
import model.ProbableText;

//Author: Jason Butterworth.

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		// Obtain a file name and number of probable characters to print from the users
		// to do
		// whatever you have to do to have the book be input to print the number of
		// characters.
		// You also need the length of the ngram, The dialog should look something like
		// this:
		//
		// Enter book name: Alice
		// How many letters? 12
		// Enter ngram length 8
		//
		// came rattling in at the Queen's ears--" the Rabbit say,
		// "A barrowful will do, to begin. Alice gave a sudden
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter book name: ");
		String bookName = "books/" + keyboard.next();
		System.out.print("How many letters? ");
		int letterCount = keyboard.nextInt();
		System.out.print("Enter ngram length: ");
		int ngramLength = keyboard.nextInt();
		ProbableText myText = new ProbableText();
		StringBuilder storyString = myText.createString(bookName);
		OurHashMap myMap = myText.createMap(storyString, ngramLength);
		StringBuilder newStory = myText.createStory(storyString, myMap, letterCount, ngramLength);
		myText.printStory(newStory, letterCount);
	}
}