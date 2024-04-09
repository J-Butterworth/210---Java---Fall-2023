package model;

import java.util.ArrayList;

import java.util.Collections;

//Author: Jason Butterworth.

public class PokerHand implements Comparable<PokerHand> {

	private final Hand hand;

	ArrayList<Card> cards;

	public PokerHand(Card c1, Card c2, Card c3, Card c4, Card c5) {
		this.hand = null;
		cards = new ArrayList<Card>();
		cards.add(c1);
		cards.add(c2);
		cards.add(c3);
		cards.add(c4);
		cards.add(c5);

		Collections.sort(cards);
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < cards.size(); i++) {
			result += cards.get(i).toString() + " ";
		}
		return result;
	}

	private String flushStraightCheck() {
		// Check for any possible flush or straight hand.
		boolean suitCheck = true;
		boolean straightCheck = true;
		// Checking for all same suit, rank, and number of same card.
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).getSuit() != cards.get(i + 1).getSuit()) {
				suitCheck = false;
			}
			if (cards.get(i).getValue() != cards.get(i + 1).getValue() - 1) {
				straightCheck = false;
			}
		}
		// Check for or eliminate possible hands based on suitCheck, straightCheck.

		// If cards have same suit, straight, and first card is 10: Royal Flush.
		if (suitCheck && straightCheck && cards.get(0).getValue() == 10) {
			return "ROYAL FLUSH";
		}
		// If cards have same suit, straight: Straight Flush.
		else if (suitCheck && straightCheck) {
			return "STRAIGHT FLUSH";
		}
		// If cards have same same suit: Flush.
		else if (suitCheck) {
			return "FLUSH";
		}
		// If cards are in order (straight): Straight.
		else if (straightCheck) {
			return "STRAGIHT";
		}
		return null;
	}

	private boolean fourOfKindCheck() {
		// Check for a four of a kind hand.
		int maxMatchCount = 0;
		for (int i = 0; i < cards.size(); i++) {
			int tempCount = 0;
			for (int j = 0; j < cards.size(); j++) {
				if (cards.get(j).getValue() == cards.get(i).getValue()) {
					tempCount++;
				}
			}
			if (tempCount > maxMatchCount) {
				maxMatchCount = tempCount;
			}
		}
		return maxMatchCount == 4;

	}

	private boolean fullHouseCheck() {
		// Check for a full house hand.
		ArrayList<Rank> ranksInHand = new ArrayList<Rank>();
		for (Card card : cards) {
			if (!ranksInHand.contains(card.getRank())) {
				ranksInHand.add(card.getRank());
			}
		}
		return ranksInHand.size() == 2;
	}

	private boolean threeOfKindCheck() {
		// Check for a three of a kind hand.
		int maxMatchCount = 0;
		for (int i = 0; i < cards.size(); i++) {
			int tempCount = 0;
			for (int j = 0; j < cards.size(); j++) {
				if (cards.get(j).getValue() == cards.get(i).getValue()) {
					tempCount++;
				}
			}
			if (tempCount > maxMatchCount) {
				maxMatchCount = tempCount;
			}
		}
		return maxMatchCount == 3;
	}

	private int pairsCheck() {
		// Check for two pair or one pair hands.
		ArrayList<Rank> ranksInHand = new ArrayList<Rank>();
		for (Card card : cards) {
			if (!ranksInHand.contains(card.getRank())) {
				ranksInHand.add(card.getRank());
			}
		}
		// If there are three different ranks there must be two pairs.
		// (Because we will have already checked all better options in getHand()).
		if (ranksInHand.size() == 3) {
			return 2;
		}
		// If there are four different ranks then there is one pair.
		else if (ranksInHand.size() == 4) {
			return 1;
		}
		// Only time it will return 0 is if there is if the hand has only high card.
		return 0;
	}

	public Hand getHand() {
		// Check for each possible hand.
		if (this.flushStraightCheck() != null) {
			if (this.flushStraightCheck().equals("ROYAL FLUSH")) {
				return Hand.ROYALFLUSH;
			} else if (this.flushStraightCheck().equals("STRAIGHT FLUSH")) {
				return Hand.STRAIGHTFLUSH;
			} else if (this.flushStraightCheck().equals("FLUSH")) {
				return Hand.FLUSH;
			} else {
				return Hand.STRAIGHT;
			}
		}

		if (this.fourOfKindCheck()) {
			return Hand.FOURKIND;
		}

		if (this.fullHouseCheck()) {
			return Hand.FULLHOUSE;
		}

		if (this.threeOfKindCheck()) {
			return Hand.THREEKIND;
		}
		if (this.pairsCheck() != 0) {
			if (this.pairsCheck() == 2) {
				return Hand.TWOPAIR;
			} else {
				return Hand.PAIR;
			}
		}

		// If nothing has returned at this point then it must have nothing.
		return Hand.HIGHCARD;

	}
	
	//JUST MAKING THIS
	private int fourKindTieBreak(PokerHand playerTwo) {
		// Return 1 if player1 has better hand, -1 if player2 has better hand.
		// Cannot be a tie with three of a kind.
		int playerOneFourKindValue = 0;
		int playerOneExtraVal = 0;
		int playerTwoFourKindValue = 0;
		int playerTwoExtraVal = 0;
		for (int i = 0; i < cards.size(); i++) {
			int tempCount = 0;
			for (int j = 0; j < cards.size(); j++) {
				if (cards.get(j).getValue() == cards.get(i).getValue()) {
					tempCount++;
				}
			}
			if (tempCount == 4) {
				playerOneFourKindValue = cards.get(i).getValue();
			} else {
				playerOneExtraVal = cards.get(i).getValue();
			}
		}
		for (int i = 0; i < playerTwo.cards.size(); i++) {
			int tempCount = 0;
			for (int j = 0; j < playerTwo.cards.size(); j++) {
				if (playerTwo.cards.get(j).getValue() == playerTwo.cards.get(i).getValue()) {
					tempCount++;
				}
			}
			if (tempCount == 4) {
				playerTwoFourKindValue = playerTwo.cards.get(i).getValue();
			} else {
				playerTwoExtraVal = playerTwo.cards.get(i).getValue();
			}
		}
		// Compare the three of a kind rank.
		if (playerOneFourKindValue > playerTwoFourKindValue) {
			return 1;
		}
		else if (playerOneFourKindValue < playerTwoFourKindValue) {
			return -1;
		}
		//If three of kind rank is tied, compare the extra cards.
		if (playerOneExtraVal > playerTwoExtraVal) {
			return 1;
		}
		else if (playerOneExtraVal < playerTwoExtraVal) {
			return -1;
		}
		return 0;

	}

	private int straightFlushTieBreak(PokerHand playerTwo) {
		// Return 1 if player one has better hand, -1 if player2 has better hand.

		// Based off highest card. If highest is tied, check next one.
		int i = 4;
		while (i >= 0) {
			Rank playerOneMaxRank = cards.get(i).getRank();
			Rank playerTwoMaxRank = playerTwo.cards.get(i).getRank();

			if (playerOneMaxRank.getValue() != playerTwoMaxRank.getValue()) {
				if (playerOneMaxRank.getValue() > playerTwoMaxRank.getValue()) {
					return 1;
				}
				return -1;
			}
			i--;
		}
		// If highest cards are matching, it is a tie.
		return 0;
	}

	private int fullHouseTieBreak(PokerHand playerTwo) {
		// Return 1 if player1 has better hand, -1 if player2 has better hand.
		int playerOneThreeKindValue = 0;
		int playerOnePairValue = 0;
		int playerTwoThreeKindValue = 0;
		int playerTwoPairValue = 0;
		for (int i = 0; i < cards.size(); i++) {
			int tempCount = 0;
			for (int j = 0; j < cards.size(); j++) {
				if (cards.get(j).getValue() == cards.get(i).getValue()) {
					tempCount++;
				}
			}
			if (tempCount == 3) {
				playerOneThreeKindValue = cards.get(i).getValue();
			}
			else {
				playerOnePairValue = cards.get(i).getValue();
			}
		}
		for (int i = 0; i < playerTwo.cards.size(); i++) {
			int tempCount = 0;
			for (int j = 0; j < playerTwo.cards.size(); j++) {
				if (playerTwo.cards.get(j).getValue() == playerTwo.cards.get(i).getValue()) {
					tempCount++;
				}
			}
			if (tempCount == 3) {
				playerTwoThreeKindValue = playerTwo.cards.get(i).getValue();
			}
			else {
				playerTwoPairValue = playerTwo.cards.get(i).getValue();
			}
		}
		
		if (playerOneThreeKindValue != playerTwoThreeKindValue) {
			if (playerOneThreeKindValue > playerTwoThreeKindValue) {
				return 1;
			}
			else {
				return -1;
			}
		}
		
		else if (playerOnePairValue != playerTwoPairValue) {
			if (playerOnePairValue > playerTwoPairValue) {
				return 1;
			}
			else {
				return -1;
			}
		}
		
		return 0;
	}

	private int threeKindTieBreak(PokerHand playerTwo) {
		// Return 1 if player1 has better hand, -1 if player2 has better hand.
		// Cannot be a tie with three of a kind.
		int playerOneThreeKindValue = 0;
		ArrayList<Integer> playerOneExtraVals = new ArrayList<Integer>();
		int playerTwoThreeKindValue = 0;
		ArrayList<Integer> playerTwoExtraVals = new ArrayList<Integer>();
		for (int i = 0; i < cards.size(); i++) {
			int tempCount = 0;
			for (int j = 0; j < cards.size(); j++) {
				if (cards.get(j).getValue() == cards.get(i).getValue()) {
					tempCount++;
				}
			}
			if (tempCount == 3) {
				playerOneThreeKindValue = cards.get(i).getValue();
			} else {
				playerOneExtraVals.add(cards.get(i).getValue());
			}
		}
		for (int i = 0; i < playerTwo.cards.size(); i++) {
			int tempCount = 0;
			for (int j = 0; j < playerTwo.cards.size(); j++) {
				if (playerTwo.cards.get(j).getValue() == playerTwo.cards.get(i).getValue()) {
					tempCount++;
				}
			}
			if (tempCount == 3) {
				playerTwoThreeKindValue = playerTwo.cards.get(i).getValue();
			} else {
				playerTwoExtraVals.add(playerTwo.cards.get(i).getValue());
			}
		}
		// Compare the three of a kind rank.
		if (playerOneThreeKindValue > playerTwoThreeKindValue) {
			return 1;
		}
		else if (playerOneThreeKindValue < playerTwoThreeKindValue) {
			return -1;
		}
		//If three of kind rank is tied, compare the players' 2 extra cards.
		Collections.sort(playerOneExtraVals);
		Collections.sort(playerTwoExtraVals);
		int i=1;
		while (i>=0) {
			if (playerOneExtraVals.get(i)>playerTwoExtraVals.get(i)) {
				return 1;
			}
			else if (playerOneExtraVals.get(i)<playerTwoExtraVals.get(i)) {
				return -1;
			}
			i--;
		}
		return 0;

	}

	private int twoPairTieBreak(PokerHand playerTwo) {
		// Return 1 if player1 has better hand, -1 if player2 has better hand.
		// Return 0 if there is a tie.
		ArrayList<Rank> playerOnePairRanks = new ArrayList<Rank>();
		int playerOneExtraValue = 0;
		ArrayList<Rank> playerTwoPairRanks = new ArrayList<Rank>();
		int playerTwoExtraValue = 0;
		for (int i = 0; i < cards.size(); i++) {
			int tempCount = 0;
			for (int j = 0; j < cards.size(); j++) {
				if (cards.get(j).getValue() == cards.get(i).getValue()) {
					tempCount++;
				}
			}
			if (tempCount == 2 && !playerOnePairRanks.contains(cards.get(i).getRank())) {
				playerOnePairRanks.add(cards.get(i).getRank());
			} else if (tempCount != 2) {
				playerOneExtraValue = cards.get(i).getValue();
			}
		}
		for (int i = 0; i < playerTwo.cards.size(); i++) {
			int tempCount = 0;
			for (int j = 0; j < playerTwo.cards.size(); j++) {
				if (playerTwo.cards.get(j).getValue() == playerTwo.cards.get(i).getValue()) {
					tempCount++;
				}
			}
			if (tempCount == 2 && !playerTwoPairRanks.contains(playerTwo.cards.get(i).getRank())) {
				playerTwoPairRanks.add(playerTwo.cards.get(i).getRank());
			} else if (tempCount != 2) {
				playerTwoExtraValue = playerTwo.cards.get(i).getValue();
			}
		}

		Collections.sort(playerOnePairRanks);
		Collections.sort(playerTwoPairRanks);
		// Check for the biggest pair between the two hands.
		int i = 1;
		while (i >= 0) {
			if (playerOnePairRanks.get(i).getValue() != playerTwoPairRanks.get(i).getValue()) {
				if (playerOnePairRanks.get(i).getValue() > playerTwoPairRanks.get(i).getValue()) {
					return 1;
				}
				return -1;
			}
			i--;
		}
		// Check the one remaining card of each hand if their two pairs are the same.
		if (playerOneExtraValue != playerTwoExtraValue) {
			if (playerOneExtraValue > playerTwoExtraValue) {
				return 1;
			}
			return -1;
		}
		// If both hands have the same two pairs and kicker is the same rank, it is a
		// tie.
		return 0;
	}

	private int pairTieBreak(PokerHand playerTwo) {
		// Return 1 if player1 has better hand, -1 if player2 has better hand.
		// Return 0 if there is a tie.
		int playerOnePairValue = 0;
		ArrayList<Rank> playerOneExtraRanks = new ArrayList<Rank>();
		int playerTwoPairValue = 0;
		ArrayList<Rank> playerTwoExtraRanks = new ArrayList<Rank>();
		for (int i = 0; i < cards.size(); i++) {
			int tempCount = 0;
			for (int j = 0; j < cards.size(); j++) {
				if (cards.get(j).getValue() == cards.get(i).getValue()) {
					tempCount++;
				}
			}
			if (tempCount == 2) {
				playerOnePairValue = cards.get(i).getValue();
			} else if (tempCount != 2) {
				playerOneExtraRanks.add(cards.get(i).getRank());
			}
		}
		for (int i = 0; i < playerTwo.cards.size(); i++) {
			int tempCount = 0;
			for (int j = 0; j < playerTwo.cards.size(); j++) {
				if (playerTwo.cards.get(j).getValue() == playerTwo.cards.get(i).getValue()) {
					tempCount++;
				}
			}
			if (tempCount == 2) {
				playerTwoPairValue = playerTwo.cards.get(i).getValue();
			} else if (tempCount != 2) {
				playerTwoExtraRanks.add(playerTwo.cards.get(i).getRank());
			}
		}

		// Compare values of the pair in each hand.
		if (playerOnePairValue != playerTwoPairValue) {
			if (playerOnePairValue > playerTwoPairValue) {
				return 1;
			}
			return -1;
		}

		// If they have the same pair, then compare the highest card in each hand.
		Collections.sort(playerOneExtraRanks);
		Collections.sort(playerTwoExtraRanks);
		int i = 2;
		while (i >= 0) {
			if (playerOneExtraRanks.get(i).getValue() != playerTwoExtraRanks.get(i).getValue()) {
				if (playerOneExtraRanks.get(i).getValue() > playerTwoExtraRanks.get(i).getValue()) {
					return 1;
				}
				return -1;
			}
			i--;
		}
		// If both hands have the same pair and all three cards are also the same, it's
		// a tie.
		return 0;
	}

	private int highCardTieBreak(PokerHand playerTwo) {
		// Return 1 if player1 has better hand, -1 if player2 has better hand.
		// Return 0 if there is a tie.

		// Get array lists of each value for each player's hand.
		ArrayList<Integer> playerOneValues = new ArrayList<Integer>();
		ArrayList<Integer> playerTwoValues = new ArrayList<Integer>();
		for (Card card : this.cards) {
			playerOneValues.add(card.getValue());
		}
		for (Card card : playerTwo.cards) {
			playerTwoValues.add(card.getValue());
		}

		// Sort the hands in order and compare the highest card in each.
		Collections.sort(playerOneValues);
		Collections.sort(playerTwoValues);
		int i = 4;
		while (i >= 0) {
			if (playerOneValues.get(i) != playerTwoValues.get(i)) {
				if (playerOneValues.get(i) > playerTwoValues.get(i)) {
					return 1;
				}
				return -1;
			}
			i--;
		}
		// If both players have the same 5 unique cards in their hand, it is a tie.
		return 0;
	}

	@Override
	public int compareTo(PokerHand o) {
		Hand playerOneHand = this.getHand();
		Hand playerTwoHand = o.getHand();
		int playerOneValue = playerOneHand.getValue();
		int playerTwoValue = playerTwoHand.getValue();
		// Need to check for equal hands and adjust accordingly.
		if (playerOneValue == playerTwoValue) {
			// Two royal flush hands will always be a tie.
			if (playerOneHand == Hand.ROYALFLUSH) {
				return 0;
			}
			if (playerOneHand == Hand.STRAIGHTFLUSH) {
				return this.straightFlushTieBreak(o);
			}
			if (playerOneHand == Hand.FOURKIND) {
				return this.fourKindTieBreak(o);
			}
			if (playerOneHand == Hand.FULLHOUSE) {
				return this.fullHouseTieBreak(o);
			}
			if (playerOneHand == Hand.FLUSH || playerOneHand == Hand.STRAIGHT) {
				return this.straightFlushTieBreak(o);
			}
			if (playerOneHand == Hand.THREEKIND) {
				return this.threeKindTieBreak(o);
			}
			if (playerOneHand == Hand.TWOPAIR) {
				return this.twoPairTieBreak(o);
			}
			if (playerOneHand == Hand.PAIR) {
				return this.pairTieBreak(o);
			}
			if (playerOneHand == Hand.HIGHCARD) {
				return this.highCardTieBreak(o);
			}
		}
		// If there was no tie break needed, we just return difference of values.
		return playerOneValue - playerTwoValue;
	}

}
