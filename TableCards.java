package model;

import java.util.ArrayList;

import java.util.Random;
//Author: Jason Butterworth.

public class TableCards {
	ArrayList<ArrayList<Card>> allCards;

	public TableCards(int playerCount) {
		// Create place to store every card on the table.
		allCards = new ArrayList<ArrayList<Card>>();
		// Add the five community cards.
		allCards.add(getCommunity());
		// Add unique pairs of cards for each player at the table.
		int count = 0;
		while (count < playerCount) {
			addPlayerCards();
			++count;
		}
	}

	public String toString(int index) {
		String result = "";
		for (int i = 0; i < allCards.get(index).size(); i++) {
			result += allCards.get(index).get(i).toString() + " ";
		}
		return result;
	}

	public ArrayList<Card> getCommunity() {

		ArrayList<Card> communityCards = new ArrayList<Card>();
		Suit[] allSuits = Suit.values();
		Rank[] allRanks = Rank.values();
		Random randSuit = new Random();
		Random randRank = new Random();

		// Adding to the ArrayList<Card> of community cards.
		while (communityCards.size() < 5) {

			// Get random rank and suit, check if that card is already dealt.
			Suit newSuit = allSuits[randSuit.nextInt(allSuits.length)];
			Rank newRank = allRanks[randRank.nextInt(allRanks.length)];

			Card newCard = new Card(newRank, newSuit);
			if (!communityCards.contains(newCard)) {
				communityCards.add(newCard);
			}

		}
		// Must add ArrayList<Card> of community cards to our allCards ArrayList.
		return communityCards;
	}

	public void addPlayerCards() {
		ArrayList<Card> playerCards = new ArrayList<Card>();
		Suit[] allSuits = Suit.values();
		Rank[] allRanks = Rank.values();
		// Adding to the ArrayList<Card> of new player's cards.
		while (playerCards.size() < 2) {
			Random randSuit = new Random();
			Random randRank = new Random();
			// Get random rank and suit, check if that card is already dealt.
			Suit newSuit = allSuits[randSuit.nextInt(allSuits.length)];
			Rank newRank = allRanks[randRank.nextInt(allRanks.length)];

			boolean validCard = true;
			Card newCard = new Card(newRank, newSuit);
			// Check if new player already has this card.
			if (!playerCards.contains(newCard)) {
				// Check if the community or any other player has this card.
				for (ArrayList<Card> hand : allCards) {
					for (Card card : hand) {
						if (card.equals(newCard)) {
							validCard = false;
						}
					}
				}
				if (validCard) {
					playerCards.add(newCard);
				}
			}
		}
		// We have two new unique cards for the player, need to add them to
		// the allCards ArrayList so they are part of the game.
		allCards.add(playerCards);
	}

	public ArrayList<ArrayList<Card>> getAllCards() {
		return allCards;
	}

}
