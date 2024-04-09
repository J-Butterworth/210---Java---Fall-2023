package model;

import java.math.RoundingMode;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Author: Jason Butterworth.

public class Game {

	ArrayList<ArrayList<Card>> allCards;
	int playerCount;
	Map<Integer, Double> moneyMap;

	public Game() {
		// Get user input for number of players.
		Scanner keyboard = new Scanner(System.in);
		System.out.print("How many players? ");
		playerCount = keyboard.nextInt();
		moneyMap = createMoneyMap(playerCount);

	}

	public void start() {
		// Shuffle cards and create maps.
		TableCards myCards = new TableCards(playerCount);
		allCards = myCards.getAllCards();

		Map<Integer, PokerHand> fullPlayerMap = getFullPlayerMap(allCards);

		Map<Integer, PokerHand> potentialWinnersMap = getWinnersMap(fullPlayerMap);
		Map<Integer, PokerHand> championMap = checkWinners(potentialWinnersMap);

		System.out.println();
		System.out.println("Community Cards: " + myCards.toString(0));
		System.out.println();

		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.FLOOR);
		
		displayMoney();

		for (Map.Entry<Integer, PokerHand> entry : fullPlayerMap.entrySet()) {
			int playerNumber = entry.getKey();
			PokerHand playerPokerHand = entry.getValue();
			System.out.println("Player " + Integer.toString(playerNumber) + ": $"
					+ df.format(moneyMap.get(playerNumber)) + " - " + myCards.toString(playerNumber));
			System.out.println(
					"   Best Hand:  " + playerPokerHand.toString() + " - " + getString(playerPokerHand.getHand()));
			System.out.println();

		}

		distributeMoney(fullPlayerMap, championMap);

		// If there is one clear winner, we want to display the text a certain way.
		if (championMap.size() == 1) {
			for (Map.Entry<Integer, PokerHand> entry : championMap.entrySet()) {
				int playerNumber = entry.getKey();
				PokerHand playerPokerHand = entry.getValue();
				System.out.println("Winner: Player " + Integer.toString(playerNumber) + " $"
						+ df.format(moneyMap.get(playerNumber)));
				System.out.println(playerPokerHand.toString() + "    " + getString(playerPokerHand.getHand()));
			}
		}

		// If there are multiples winner, we want to mention it is a tie.
		else {
			System.out.println("Winning Hands (TIE): ");
			for (Map.Entry<Integer, PokerHand> entry : championMap.entrySet()) {
				int playerNumber = entry.getKey();
				PokerHand playerPokerHand = entry.getValue();
				System.out.println(playerPokerHand.toString() + getString(playerPokerHand.getHand()) + " Player "
						+ Integer.toString(playerNumber) + " $" + df.format(moneyMap.get(playerNumber)));
			}
		}
		System.out.println();
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Play another game? (y or n)");
		String decision = keyboard.next();
		if (decision.equals("y")) {
			start();
		}

	}

	public String toString(ArrayList<Card> cardPair) {
		String result = "";
		for (int i = 0; i < cardPair.size(); i++) {
			result += cardPair.get(i).toString() + " ";
		}
		return result;
	}

	// USED IN getFullPlayerMap.
	public PokerHand getPlayersBestPokerHand(ArrayList<Card> cardPair) {
		ArrayList<Card> communityCards = allCards.get(0);
		PokerHand bestPokerHand = new PokerHand(communityCards.get(0), communityCards.get(1), communityCards.get(2),
				communityCards.get(3), communityCards.get(4));
		Card card1 = cardPair.get(0);
		Card card2 = cardPair.get(1);

		// Check all combinations with first card.
		for (int i = 0; i < communityCards.size(); i++) {
			ArrayList<Card> currentHand = new ArrayList<>(communityCards);
			// Make temporary combo of five cards, so we can reset it after looking at each.
			currentHand.set(i, card1);
			Card c1 = currentHand.get(0);
			Card c2 = currentHand.get(1);
			Card c3 = currentHand.get(2);
			Card c4 = currentHand.get(3);
			Card c5 = currentHand.get(4);

			PokerHand currPokerHand = new PokerHand(c1, c2, c3, c4, c5);
			if (currPokerHand.compareTo(bestPokerHand) > 0) {
				bestPokerHand = currPokerHand;
			}
		}

		// Check all combinations with second card.
		for (int i = 0; i < communityCards.size(); i++) {
			ArrayList<Card> currentHand = new ArrayList<>(communityCards);
			// This is the only change to above for loop, using card2 instead.
			currentHand.set(i, card2);
			Card c1 = currentHand.get(0);
			Card c2 = currentHand.get(1);
			Card c3 = currentHand.get(2);
			Card c4 = currentHand.get(3);
			Card c5 = currentHand.get(4);

			PokerHand currPokerHand = new PokerHand(c1, c2, c3, c4, c5);
			if (currPokerHand.compareTo(bestPokerHand) > 0) {
				bestPokerHand = currPokerHand;
			}
		}

		// Check all combinations with both cards.
		for (int i = 0; i < communityCards.size(); i++) {
			for (int j = 0; j < communityCards.size(); j++) {
				ArrayList<Card> currentHand = new ArrayList<>(communityCards);
				currentHand.set(i, card1);
				currentHand.set(j, card2);
				Card c1 = currentHand.get(0);
				Card c2 = currentHand.get(1);
				Card c3 = currentHand.get(2);
				Card c4 = currentHand.get(3);
				Card c5 = currentHand.get(4);
				PokerHand currPokerHand = new PokerHand(c1, c2, c3, c4, c5);
				if (currPokerHand.compareTo(bestPokerHand) > 0) {
					bestPokerHand = currPokerHand;
				}
			}
		}

		return bestPokerHand;

	}

	// USE.
	public Map<Integer, PokerHand> getFullPlayerMap(ArrayList<ArrayList<Card>> allCards) {
		ArrayList<ArrayList<Card>> allPlayerCards = new ArrayList<>();
		for (ArrayList<Card> cardGroup : allCards) {
			ArrayList<Card> playerHand = new ArrayList<>(cardGroup);
			allPlayerCards.add(playerHand);
		}
		// Do not include community cards array in allPlayerCards.
		allPlayerCards.remove(0);

		// Create a map that stores the player number as the key and their best
		// PokerHand as the value.
		Map<Integer, PokerHand> numHandMap = new HashMap<>();
		for (int i = 0; i < allPlayerCards.size(); i++) {
			PokerHand currentPlayerPokerHand = getPlayersBestPokerHand(allPlayerCards.get(i));
			numHandMap.put(i + 1, currentPlayerPokerHand);
		}

		return numHandMap;
	}

	// USE.
	public Map<Integer, PokerHand> getWinnersMap(Map<Integer, PokerHand> numHandMap) {
		// Find whichever player has the highest hand, and if multiple have the same
		// (e.g fourKind)
		// then add them both to the map.
		Hand maxHand = Hand.HIGHCARD;
		Map<Integer, PokerHand> winnersMap = new HashMap<Integer, PokerHand>();

		// First, determine what the max hand on the table is.
		for (Map.Entry<Integer, PokerHand> entry : numHandMap.entrySet()) {
			int playerNumber = entry.getKey();
			PokerHand playerPokerHand = entry.getValue();
			if (playerPokerHand.getHand().getValue() > maxHand.getValue()) {
				maxHand = playerPokerHand.getHand();
			}
		}
		// We have the maxHand on the table, now add any player with this hand to the
		// winners map.
		for (Map.Entry<Integer, PokerHand> entry : numHandMap.entrySet()) {
			int playerNumber = entry.getKey();
			PokerHand playerPokerHand = entry.getValue();
			if (playerPokerHand.getHand().getValue() == maxHand.getValue()) {
				winnersMap.put(playerNumber, playerPokerHand);
			}
		}
		return winnersMap;
	}

	public Map<Integer, PokerHand> checkWinners(Map<Integer, PokerHand> winnersMap) {
		// If there were no tie breaks to check and we already know we have one winner,
		// return the same map.
		if (winnersMap.size() == 1) {
			return winnersMap;
		}
		// If there are initially multiple players with same winning hand, check for tie
		// breaks.
		Map<Integer, PokerHand> championMap = new HashMap<Integer, PokerHand>();
		ArrayList<PokerHand> winningHands = new ArrayList<PokerHand>();
		if (!winnersMap.isEmpty()) {
			for (Map.Entry<Integer, PokerHand> entry : winnersMap.entrySet()) {
				PokerHand playerPokerHand = entry.getValue();
				winningHands.add(playerPokerHand);
			}

			// We now have an ArrayList of all potential winning PokerHand objects. Must
			// compare each.
			PokerHand highestHand = winningHands.get(0);
			for (int i = 0; i < winningHands.size(); i++) {
				PokerHand currentHand = winningHands.get(i);
				if (currentHand.compareTo(highestHand) > 0) {
					highestHand = currentHand;
				}
			}
			// We now have the highest possible pokerhand on the table, and we will add any
			// player
			// that has the exact same hand to our final champions map.
			for (Map.Entry<Integer, PokerHand> entry : winnersMap.entrySet()) {
				Integer playerNumber = entry.getKey();
				PokerHand playerPokerHand = entry.getValue();
				if (highestHand.compareTo(playerPokerHand) == 0) {
					championMap.put(playerNumber, highestHand);
				}
			}
		}
		return championMap;
	}

	public String getString(Hand playerHand) {
		if (playerHand == Hand.ROYALFLUSH) {
			return "Royal Flush";
		}
		if (playerHand == Hand.STRAIGHTFLUSH) {
			return "Straight Flush";
		}
		if (playerHand == Hand.FOURKIND) {
			return "Four of a kind";
		}
		if (playerHand == Hand.FULLHOUSE) {
			return "Full House";
		}
		if (playerHand == Hand.FLUSH) {
			return "Flush";
		}
		if (playerHand == Hand.STRAIGHT) {
			return "Straight";
		}
		if (playerHand == Hand.THREEKIND) {
			return "Three of a Kind";
		}
		if (playerHand == Hand.TWOPAIR) {
			return "Two Pair";
		}
		if (playerHand == Hand.PAIR) {
			return "One Pair";
		}
		return "High Card";
	}

	public Map<Integer, Double> createMoneyMap(Integer playerCount) {
		Map<Integer, Double> moneyMap = new HashMap<Integer, Double>();
		int i = 0;
		while (i < playerCount) {
			moneyMap.put(i + 1, 100.00);
			i++;
		}
		return moneyMap;
	}
	
	public void displayMoney() {
		//Change moneyMap so it takes two from each player to display their
		//updated holdings.
		

		// Subtract $2.00 from each player at the beginning of each turn.
		for (Map.Entry<Integer, Double> entry : moneyMap.entrySet()) {
			int playerNumber = entry.getKey();
			Double playerMoney = entry.getValue();
			moneyMap.put(playerNumber, playerMoney - 2.00);
		}
	}
	
	public void distributeMoney(Map<Integer, PokerHand> fullPlayerMap, Map<Integer, PokerHand> championMap) {
		// Distribute the money appropriately depending on amount of players with
		// winning hand.
		/*
		 * double pot = 2 * playerCount;
		 * 
		 * // Subtract $2.00 from each player at the beginning of each turn. for
		 * (Map.Entry<Integer, Double> entry : moneyMap.entrySet()) { int playerNumber =
		 * entry.getKey(); Double playerMoney = entry.getValue();
		 * moneyMap.put(playerNumber, playerMoney - 2.00); }
		 */
		double pot = 2 * playerCount;
		int winnerCount = championMap.size();
		double prizeMoney = pot/winnerCount;
		ArrayList<Integer> championNums = new ArrayList<Integer>();
		for (Map.Entry<Integer, PokerHand> numHand : championMap.entrySet()) {
			int playerNum = numHand.getKey();
			championNums.add(playerNum);
		}

		for (Map.Entry<Integer, Double> numMoney : moneyMap.entrySet()) {
			int playerNum = numMoney.getKey();
			double currMoney = numMoney.getValue();
			if (championNums.contains(playerNum)) {
				moneyMap.put(playerNum, currMoney + prizeMoney);
			}
		}
	}
}
