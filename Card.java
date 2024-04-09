package model;

/**
 * class Card represents one of the 52 poker cards. There are no comments before
 * methods because the method name says it all.
 * 
 * @author Jason Butterworth and Rick Mercer.
 */

public class Card implements Comparable<Card> {
	private final Rank rank;
	private final Suit suit;

	// Constructor
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	// Getter for suit of individual card.
	public Suit getSuit() {
		return this.suit;
	}
	
	// Getter for rank of a hand.
	public Rank getRank() {
		return this.rank;
	}
	
	// Getter for value of card.
	public int getValue() {
		return rank.getValue();
	}

	public String toString() {
		// Use these four Unicode icons for the solid suit icons.
		char suitIcon = '\u2663';
		if (suit == Suit.DIAMONDS)
			suitIcon = '\u2666';
		if (suit == Suit.HEARTS)
			suitIcon = '\u2665';
		if (suit == Suit.SPADES)
			suitIcon = '\u2660';

		if (rank.getValue() == 11) {
			return "J" + suitIcon;
		}
		if (rank.getValue() == 12) {
			return "Q" + suitIcon;
		}
		if (rank.getValue() == 13) {
			return "K" + suitIcon;
		}
		if (rank.getValue() == 14) {
			return "A" + suitIcon;
		}

		return Integer.toString(rank.getValue()) + suitIcon;
	}

	@Override
	public int compareTo(Card other) {
		return rank.getValue() - other.getValue();
	}
	
	// Check if two cards equal one another.
	public boolean equals(Object other) {
		if (!(other instanceof Card)) {
			return false;
		}
		Card card = (Card) other;
		if (this.getRank() == card.getRank() && this.getSuit() == card.getSuit()) {
			return true;
		}
		return false;
	}

}