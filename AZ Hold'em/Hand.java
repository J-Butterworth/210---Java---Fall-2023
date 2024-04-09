package model;
// Author: Jason Butterworth.

// Class to assist in assigning rankings to a player's hand.
public enum Hand {
	HIGHCARD(1), PAIR(2), TWOPAIR(3), THREEKIND(4), 
	STRAIGHT(5), FLUSH(6), FULLHOUSE(7), 
	FOURKIND(8), STRAIGHTFLUSH(9), ROYALFLUSH(10);

	private int value;

	Hand(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
