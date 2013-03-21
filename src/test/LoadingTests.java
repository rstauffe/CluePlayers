package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

import cluePlayers.Card;
import cluePlayers.ClueGame;
import cluePlayers.ComputerPlayer;
import cluePlayers.Player;

public class LoadingTests {
	ClueGame game;
	public static final int NUM_COLUMNS = 18;
	
	@BeforeClass
	public void setup() {
		game = new ClueGame();
	}
	
	@Test
	public void testHuman() {
		Player human = game.getPlayer();
		assertEquals(human.getName(), "Professor Plum");
		assertEquals(human.getColor(), "Purple");
		assertEquals(human.getIndex(), 0*NUM_COLUMNS + 4);
	}

	@Test
	public void testComputer1() {
		Player ai1 = game.getComps().get(0);
		assertEquals(ai1.getName(), "Reverend Green");
		assertEquals(ai1.getColor(), "Green");
		assertEquals(ai1.getIndex(), 0*NUM_COLUMNS + 13);
	}
	
	@Test
	public void testComputer5() {
		Player ai5 = game.getComps().get(4);
		assertEquals(ai5.getName(), "Miss Scarlett");
		assertEquals(ai5.getColor(), "Red");
		assertEquals(ai5.getIndex(), 13*NUM_COLUMNS + 17);
	}
	
	@Test
	public void testCards() {
		LinkedList<Card> cards = game.getCards();
		assertEquals(21, cards.size());
		int rooms = 0;
		int weapons = 0;
		int people = 0;
		for (Card c : cards) {
			switch (c.getType()) {
			case ROOM:
				rooms++;
				continue;
			case WEAPON:
				weapons++;
				continue;
			case PERSON:
				people++;
				continue;
			}
		}
		assertEquals(people, 6);
		assertEquals(weapons, 6);
		assertEquals(rooms, 9);
		Card card = new Card("Professor Plum", "Person");
		assertTrue(cards.contains(card));
		card = new Card("Miss Scarlett", "Person");
		assertTrue(cards.contains(card));
		card = new Card("Great Hall", "Room");
		assertTrue(cards.contains(card));
		card = new Card("Knife", "Weapon");
		assertTrue(cards.contains(card));
	}
	
	@Test
	public void testDeal() {
		LinkedList<Card> toDeal = game.getToDeal();
		assertTrue(toDeal.isEmpty());
		Player human = game.getPlayer();
		int numCards = human.getCards().size();
		LinkedList<ComputerPlayer> ais = game.getComps();
		for (ComputerPlayer c : ais) {
			assertFalse((Math.abs(c.getCards().size()-numCards) > 1));
		}
		HashSet<Card> cardsDealt = new HashSet<Card>();
		for (Card c : human.getCards()) {
			cardsDealt.add(c);
		}
		for (ComputerPlayer ai : ais) {
			for (Card c : ai.getCards()) {
				cardsDealt.add(c);
			}
		}
		assertEquals(21, cardsDealt.size());
	}
}
