package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TableCardsTest {

	@Test
	void test() {
		TableCards a = new TableCards(3);
		System.out.println(a.getAllCards());
	}

}
