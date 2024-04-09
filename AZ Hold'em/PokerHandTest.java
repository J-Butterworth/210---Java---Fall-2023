package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.Card;
import model.PokerHand;
import model.Rank;
import model.Suit;

/**
 * Tests the CardHand class, last modified Sep 2015, June 2017, July 23, August
 * 23
 * 
 * I think this a pretty good unit test, if you add any other test cases please
 * send them to me!
 * 
 * I am providing all 52 possible Cars to save you time writing @Tests
 * 
 * @author Rick Mercer and Jason Butterworth.
 */
public class PokerHandTest {

	// This file contains all 52 cards to save you time with names like
	// C2 for the two of clubs
	// AS for the ace of spades

	// Set up 52 cards so we can use C2 instead of new Card(Rank.Deuce, Suit.Clubs)
	private final static Card C2 = new Card(Rank.DEUCE, Suit.CLUBS);
	private final static Card C3 = new Card(Rank.THREE, Suit.CLUBS);
	private final static Card C4 = new Card(Rank.FOUR, Suit.CLUBS);
	private final static Card C5 = new Card(Rank.FIVE, Suit.CLUBS);
	private final static Card C6 = new Card(Rank.SIX, Suit.CLUBS);
	private final static Card C7 = new Card(Rank.SEVEN, Suit.CLUBS);
	private final static Card C8 = new Card(Rank.EIGHT, Suit.CLUBS);
	private final static Card C9 = new Card(Rank.NINE, Suit.CLUBS);
	private final static Card C10 = new Card(Rank.TEN, Suit.CLUBS);
	private final static Card CJ = new Card(Rank.JACK, Suit.CLUBS);
	private final static Card CQ = new Card(Rank.QUEEN, Suit.CLUBS);
	private final static Card CK = new Card(Rank.KING, Suit.CLUBS);
	private final static Card CA = new Card(Rank.ACE, Suit.CLUBS);

	private final static Card D2 = new Card(Rank.DEUCE, Suit.DIAMONDS);
	private final static Card D3 = new Card(Rank.THREE, Suit.DIAMONDS);
	private final static Card D4 = new Card(Rank.FOUR, Suit.DIAMONDS);
	private final static Card D5 = new Card(Rank.FIVE, Suit.DIAMONDS);
	private final static Card D6 = new Card(Rank.SIX, Suit.DIAMONDS);
	private final static Card D7 = new Card(Rank.SEVEN, Suit.DIAMONDS);
	private final static Card D8 = new Card(Rank.EIGHT, Suit.DIAMONDS);
	private final static Card D9 = new Card(Rank.NINE, Suit.DIAMONDS);
	private final static Card D10 = new Card(Rank.TEN, Suit.DIAMONDS);
	private final static Card DJ = new Card(Rank.JACK, Suit.DIAMONDS);
	private final static Card DQ = new Card(Rank.QUEEN, Suit.DIAMONDS);
	private final static Card DK = new Card(Rank.KING, Suit.DIAMONDS);
	private final static Card DA = new Card(Rank.ACE, Suit.DIAMONDS);

	private final static Card H2 = new Card(Rank.DEUCE, Suit.HEARTS);
	private final static Card H3 = new Card(Rank.THREE, Suit.HEARTS);
	private final static Card H4 = new Card(Rank.FOUR, Suit.HEARTS);
	private final static Card H5 = new Card(Rank.FIVE, Suit.HEARTS);
	private final static Card H6 = new Card(Rank.SIX, Suit.HEARTS);
	private final static Card H7 = new Card(Rank.SEVEN, Suit.HEARTS);
	private final static Card H8 = new Card(Rank.EIGHT, Suit.HEARTS);
	private final static Card H9 = new Card(Rank.NINE, Suit.HEARTS);
	private final static Card H10 = new Card(Rank.TEN, Suit.HEARTS);
	private final static Card HJ = new Card(Rank.JACK, Suit.HEARTS);
	private final static Card HQ = new Card(Rank.QUEEN, Suit.HEARTS);
	private final static Card HK = new Card(Rank.KING, Suit.HEARTS);
	private final static Card HA = new Card(Rank.ACE, Suit.HEARTS);

	private final static Card S2 = new Card(Rank.DEUCE, Suit.SPADES);
	private final static Card S3 = new Card(Rank.THREE, Suit.SPADES);
	private final static Card S4 = new Card(Rank.FOUR, Suit.SPADES);
	private final static Card S5 = new Card(Rank.FIVE, Suit.SPADES);
	private final static Card S6 = new Card(Rank.SIX, Suit.SPADES);
	private final static Card S7 = new Card(Rank.SEVEN, Suit.SPADES);
	private final static Card S8 = new Card(Rank.EIGHT, Suit.SPADES);
	private final static Card S9 = new Card(Rank.NINE, Suit.SPADES);
	private final static Card S10 = new Card(Rank.TEN, Suit.SPADES);
	private final static Card SJ = new Card(Rank.JACK, Suit.SPADES);
	private final static Card SQ = new Card(Rank.QUEEN, Suit.SPADES);
	private final static Card SK = new Card(Rank.KING, Suit.SPADES);
	private final static Card SA = new Card(Rank.ACE, Suit.SPADES);

	@Test
	public void testRoyalFlush() {
		PokerHand a = new PokerHand(H10, HJ, HQ, HK, HA);
		PokerHand b = new PokerHand(S9, S10, SJ, SQ, SK);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void testRoyalFlushTB() {
		PokerHand a = new PokerHand(H10, HJ, HQ, HK, HA);
		PokerHand b = new PokerHand(S10, SJ, SQ, SK, SA);
		assertTrue(a.compareTo(b) == 0);
	}

	@Test
	public void testStraightFlush() {
		PokerHand a = new PokerHand(S9, S10, SJ, SQ, SK);
		PokerHand b = new PokerHand(SA, HA, CA, DA, S3);
		assertTrue(a.compareTo(b) > 0);
		PokerHand c = new PokerHand(S9, S10, SJ, SQ, SK);
		PokerHand d = new PokerHand(H10, HJ, HQ, HK, HA);
		assertTrue(c.compareTo(d) < 0);
	}

	@Test
	public void testStraightFlushTB() {
		PokerHand a = new PokerHand(H2, H3, H4, H5, H6);
		PokerHand b = new PokerHand(S2, S3, S4, S5, S6);
		assertTrue(a.compareTo(b) == 0);
		PokerHand c = new PokerHand(H10, HJ, HQ, HK, HA);
		PokerHand d = new PokerHand(H9, H10, HJ, HQ, HK);
		assertTrue(c.compareTo(d) > 0);
	}

	@Test
	public void testfourKind() {
		PokerHand a = new PokerHand(SA, HA, CA, DA, S3);
		PokerHand b = new PokerHand(SA, HA, CA, D3, S3);
		assertTrue(a.compareTo(b) > 0);
		PokerHand c = new PokerHand(SA, HA, CA, DA, S3);
		PokerHand d = new PokerHand(S9, S10, SJ, SQ, SK);
		assertTrue(c.compareTo(d) < 0);
	}

	@Test
	public void testFourKindTB() {
		PokerHand a = new PokerHand(H2, C2, D2, H10, S2);
		PokerHand b = new PokerHand(H7, C7, S9, D7, S7);
		assertTrue(a.compareTo(b) < 0);
		PokerHand c = new PokerHand(H9, C9, D9, H10, S9);
		PokerHand d = new PokerHand(H7, C7, S9, D7, S7);
		assertTrue(c.compareTo(d) > 0);
	}

	@Test
	public void testFullHouse() {
		PokerHand a = new PokerHand(SA, HA, CA, D3, S3);
		PokerHand b = new PokerHand(S5, SQ, SA, S6, S3);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void testFullHouseTB() {
		PokerHand a = new PokerHand(H5, C5, D3, H3, S5);
		PokerHand b = new PokerHand(H3, C3, S3, DQ, SQ);
		assertTrue(a.compareTo(b) > 0);

		PokerHand c = new PokerHand(H5, C5, D3, H3, S5);
		PokerHand d = new PokerHand(HK, CQ, SK, DK, SQ);
		assertTrue(c.compareTo(d) < 0);

		PokerHand e = new PokerHand(H5, C5, D3, H3, S5);
		PokerHand f = new PokerHand(H4, CQ, S4, D4, SQ);
		assertTrue(e.compareTo(f) > 0);
	}

	@Test
	public void testFlush() {
		PokerHand a = new PokerHand(S5, SQ, SA, S6, S3);
		PokerHand b = new PokerHand(S5, H6, H7, D8, S9);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void testFlushTB() {
		PokerHand a = new PokerHand(H2, H7, H5, HA, H4);
		PokerHand b = new PokerHand(D5, D8, D3, D7, D9);
		assertTrue(a.compareTo(b) > 0);
		PokerHand c = new PokerHand(H2, H7, H5, H3, H4);
		PokerHand d = new PokerHand(D5, D8, D3, D7, D9);
		assertTrue(c.compareTo(d) < 0);
		PokerHand e = new PokerHand(H5, H8, H3, H7, H9);
		PokerHand f = new PokerHand(D5, D8, D3, D7, D9);
		assertTrue(e.compareTo(f) == 0);
	}

	@Test
	public void testStraight() {
		PokerHand a = new PokerHand(S5, H6, H7, D8, S9);
		PokerHand b = new PokerHand(H3, C3, S3, D2, SQ);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void testStraightTB() {
		PokerHand a = new PokerHand(H2, D3, S4, H5, H6);
		PokerHand b = new PokerHand(D5, D6, H7, C8, C9);
		assertTrue(a.compareTo(b) < 0);
		PokerHand c = new PokerHand(H7, D8, S9, H10, HJ);
		PokerHand d = new PokerHand(D5, D6, H7, C8, C9);
		assertTrue(c.compareTo(d) > 0);
		PokerHand e = new PokerHand(H7, D8, S9, H10, HJ);
		PokerHand f = new PokerHand(D7, D8, H9, C10, CJ);
		assertTrue(e.compareTo(f) == 0);
	}

	@Test
	public void testThreeKind() {
		PokerHand a = new PokerHand(HJ, CJ, S3, D2, SJ);
		PokerHand b = new PokerHand(HA, C3, S3, DA, SQ);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void testThreeKindTB() {
		PokerHand a = new PokerHand(H5, C5, D3, H8, S5);
		PokerHand b = new PokerHand(H3, C3, S3, D2, SQ);
		assertTrue(a.compareTo(b) > 0);
		PokerHand c = new PokerHand(C8, D8, HA, CK, H8);
		PokerHand d = new PokerHand(HK, C3, SK, DK, SQ);
		assertTrue(c.compareTo(d) < 0);
	}

	@Test
	public void testTwoPair() {
		PokerHand a = new PokerHand(HA, C3, S3, DA, SQ);
		PokerHand b = new PokerHand(HA, C3, S4, DA, SQ);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void testTwoPairTB() {
		PokerHand a = new PokerHand(D5, H5, S9, D7, H7);
		PokerHand b = new PokerHand(D3, H3, S9, D6, H6);
		assertTrue(a.compareTo(b) > 0);
		PokerHand c = new PokerHand(D5, H5, S9, D4, H4);
		PokerHand d = new PokerHand(D3, H3, S9, D6, H6);
		assertTrue(c.compareTo(d) < 0);
		PokerHand e = new PokerHand(D3, H3, SA, D6, H6);
		PokerHand f = new PokerHand(D3, H3, S9, D6, H6);
		assertTrue(e.compareTo(f) > 0);
		PokerHand g = new PokerHand(D3, H3, S9, D6, H6);
		PokerHand h = new PokerHand(D3, H3, SA, D6, H6);
		assertTrue(g.compareTo(h) < 0);
		PokerHand i = new PokerHand(D3, H3, SA, D6, H6);
		PokerHand j = new PokerHand(D3, H3, SA, D6, H6);
		assertTrue(i.compareTo(j) == 0);
	}

	@Test
	public void testPair() {
		PokerHand a = new PokerHand(HA, C3, S4, DA, SQ);
		PokerHand b = new PokerHand(HA, C3, S4, DK, SQ);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void testPairTB() {
		PokerHand a = new PokerHand(D5, H5, S9, D6, H7);
		PokerHand b = new PokerHand(D4, H3, S9, D2, H2);
		assertTrue(a.compareTo(b) > 0);
		PokerHand c = new PokerHand(D5, H5, S9, D6, H7);
		PokerHand d = new PokerHand(D4, H3, S9, DJ, HJ);
		assertTrue(c.compareTo(d) < 0);
		PokerHand e = new PokerHand(D3, H3, SA, D7, H6);
		PokerHand f = new PokerHand(D3, H3, S9, D6, H5);
		assertTrue(e.compareTo(f) > 0);
		PokerHand g = new PokerHand(D3, H3, S8, D7, H6);
		PokerHand h = new PokerHand(D3, H3, S9, D6, H5);
		assertTrue(g.compareTo(h) < 0);
		PokerHand i = new PokerHand(D3, H3, SA, D8, H6);
		PokerHand j = new PokerHand(D3, H3, SA, D8, H6);
		assertTrue(i.compareTo(j) == 0);
	}

	@Test
	public void testHighCard() {
		PokerHand a = new PokerHand(HA, C3, S4, DK, SQ);
		PokerHand b = new PokerHand(H2, C2, S4, DK, SQ);
		assertTrue(a.compareTo(b) < 0);
	}

	@Test
	public void testHighCardTB() {
		PokerHand a = new PokerHand(C3, C4, D6, D7, DA);
		PokerHand b = new PokerHand(C2, C5, C7, DQ, DK);
		assertTrue(a.compareTo(b) > 0);
		PokerHand c = new PokerHand(C3, C4, D6, D7, DQ);
		PokerHand d = new PokerHand(C2, C5, C7, DQ, DK);
		assertTrue(c.compareTo(d) < 0);
		PokerHand e = new PokerHand(H2, H5, H7, SQ, DK);
		PokerHand f = new PokerHand(C2, C5, C7, DQ, DK);
		assertTrue(e.compareTo(f) == 0);

	}

}
