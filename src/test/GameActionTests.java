package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BoardCell;
import cluePlayers.Card;
import cluePlayers.ClueGame;
import cluePlayers.ComputerPlayer;
import cluePlayers.HumanPlayer;
import cluePlayers.Player;

public class GameActionTests {
	ClueGame game;

	@BeforeClass
	public void setup() {
		game = new ClueGame();
	}

	@Test
	public void testAccusationCorrect() {
		HashSet<Card> answer = new HashSet<Card>();
		answer.add(new Card("Mrs White", "Person"));
		answer.add(new Card("Wrench", "Weapon"));
		answer.add(new Card("Pool", "Room"));
		LinkedList<Card> cards = new LinkedList<Card>();
		Player player = new HumanPlayer("Miss Scarlett", cards, "Red", 0);
		HashSet<Card> accuse = player.makeAccusation(new Card("Mrs White", "Person"), 
				new Card("Wrench", "Weapon"), new Card("Pool", "Room"));
		assertEquals(accuse, answer);
	}

	@Test
	public void testAccusationPerson() {
		HashSet<Card> answer = new HashSet<Card>();
		answer.add(new Card("Mrs White", "Person"));
		answer.add(new Card("Wrench", "Weapon"));
		answer.add(new Card("Pool", "Room"));
		LinkedList<Card> cards = new LinkedList<Card>();
		Player player = new HumanPlayer("Miss Scarlett", cards, "Red", 0);
		HashSet<Card> accuse = player.makeAccusation(new Card("Colonel Mustard", "Person"), 
				new Card("Wrench", "Weapon"), new Card("Pool", "Room"));
		assertFalse(accuse.equals(answer));
	}

	@Test
	public void testAccusationWeapon() {
		HashSet<Card> answer = new HashSet<Card>();
		answer.add(new Card("Mrs White", "Person"));
		answer.add(new Card("Wrench", "Weapon"));
		answer.add(new Card("Pool", "Room"));
		LinkedList<Card> cards = new LinkedList<Card>();
		Player player = new HumanPlayer("Miss Scarlett", cards, "Red", 0);
		HashSet<Card> accuse = player.makeAccusation(new Card("Mrs White", "Person"), 
				new Card("Candlestick", "Weapon"), new Card("Pool", "Room"));
		assertFalse(accuse.equals(answer));
	}

	@Test
	public void testAccusationRoom() {
		HashSet<Card> answer = new HashSet<Card>();
		answer.add(new Card("Mrs White", "Person"));
		answer.add(new Card("Wrench", "Weapon"));
		answer.add(new Card("Pool", "Room"));
		LinkedList<Card> cards = new LinkedList<Card>();
		Player player = new HumanPlayer("Miss Scarlett", cards, "Red", 0);
		HashSet<Card> accuse = player.makeAccusation(new Card("Mrs White", "Person"), 
				new Card("Wrench", "Weapon"), new Card("Bowling Alley", "Room"));
		assertFalse(accuse.equals(answer));
	}

	@Test
	public void testTargetSelect() {
		LinkedList<Card> cards = new LinkedList<Card>();
		ComputerPlayer player = new ComputerPlayer("Miss Scarlett", cards, "Red", 233);
		int location = player.pickLocation(5);
		assertEquals(location, 267); //should only pick door
		player = new ComputerPlayer("Miss Scarlett", cards, "Red", 8);
		location = player.pickLocation(2);
		assertEquals(location, 44); //checks only one location possible
		//test multiple possible locations at index (10,10)
		int loc192 = 0;
		int loc209 = 0;
		int loc226 = 0; //counter variables
		player = new ComputerPlayer("Miss Scarlett", cards, "Red", 190);
		for (int i = 0; i < 100; i++) {
			location = player.pickLocation(2);
			switch (location) {
			case 192:
				loc192++;
				continue;
			case 209:
				loc209++;
				continue;
			case 226:
				loc226++;
				continue;
			default:
				System.out.println("You done goofed");
				continue;
			}
		}
		assertEquals(loc192 + loc209 + loc226, 100);
		assertTrue(loc192 > 0);
		assertTrue(loc209 > 0);
		assertTrue(loc226 > 0);
		//test to not reenter room
		player = new ComputerPlayer("Miss Scarlett", cards, "Red", 297);
		for (int i = 0; i < 100; i++) {
			location = player.pickLocation(3);
			assertFalse(location == 296);
		}
	}

	@Test
	public void testDisproveOneOne() {
		//sorry in advance for the wall of text- couldn't think of a better way to change hands
		LinkedList<Card> cardsHuman = new LinkedList<Card>(); //create hands for each player
		LinkedList<Card> cards1 = new LinkedList<Card>();
		LinkedList<Card> cards2 = new LinkedList<Card>();
		LinkedList<Card> cards3 = new LinkedList<Card>();
		LinkedList<Card> cards4 = new LinkedList<Card>();
		LinkedList<Card> cards5 = new LinkedList<Card>();
		//put cards into each player's hand (changes with every test)
		cardsHuman.add(new Card("Colonel Mustard", "Person"));
		cardsHuman.add(new Card("Reverend Green", "Person"));
		cardsHuman.add(new Card("Bowling Alley", "Room"));
		cards1.add(new Card("Miss Scarlett", "Person"));
		cards1.add(new Card("Study", "Room"));
		cards1.add(new Card("Rope", "Weapon"));
		cards2.add(new Card("Mrs White", "Person")); //this is what will be returned
		cards2.add(new Card("Revolver", "Weapon"));
		cards2.add(new Card("Foyer", "Room"));
		cards3.add(new Card("Mrs Peacock", "Person"));
		cards3.add(new Card("Candlestick", "Weapon"));
		cards3.add(new Card("Knife", "Weapon"));
		cards4.add(new Card("Lounge", "Room"));
		cards4.add(new Card("Great Hall", "Room"));
		cards4.add(new Card("Lead Pipe", "Weapon"));
		cards5.add(new Card("Bedroom", "Room"));
		cards5.add(new Card("Library", "Room"));
		cards5.add(new Card("Kitchen", "Room"));
		//creates the players with these hands
		HumanPlayer human = new HumanPlayer("Professor Plum", cardsHuman, "Purple", 0);
		ComputerPlayer player1 = new ComputerPlayer("Miss Scarlett", cards1, "Red", 297);
		ComputerPlayer player2 = new ComputerPlayer("Mrs White", cards2, "White", 297);
		ComputerPlayer player3 = new ComputerPlayer("Colonel Mustard", cards3, "Yellow", 297);
		ComputerPlayer player4 = new ComputerPlayer("Mrs Peacock", cards4, "Blue", 297);
		ComputerPlayer player5 = new ComputerPlayer("Reverend Green", cards5, "Green", 297);
		LinkedList<ComputerPlayer> players = new LinkedList<ComputerPlayer>();
		//adds the players to a LinkedList so game can iterate
		game.setPlayer(human);
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		game.setComps(players);
		Card shown = game.makeSuggestion(new Card("Mrs White", "Person"), 
				new Card("Wrench", "Weapon"), new Card("Pool", "Room"), 0);
		assertEquals(shown, new Card("Mrs White", "Person"));
	}

	@Test
	public void testDisproveOneTwo() {
		LinkedList<Card> cardsHuman = new LinkedList<Card>();
		LinkedList<Card> cards1 = new LinkedList<Card>();
		LinkedList<Card> cards2 = new LinkedList<Card>();
		LinkedList<Card> cards3 = new LinkedList<Card>();
		LinkedList<Card> cards4 = new LinkedList<Card>();
		LinkedList<Card> cards5 = new LinkedList<Card>();
		cardsHuman.add(new Card("Colonel Mustard", "Person"));
		cardsHuman.add(new Card("Reverend Green", "Person"));
		cardsHuman.add(new Card("Bowling Alley", "Room"));
		cards1.add(new Card("Miss Scarlett", "Person"));
		cards1.add(new Card("Study", "Room"));
		cards1.add(new Card("Rope", "Weapon"));
		cards2.add(new Card("Mrs White", "Person")); //this is what will be returned
		cards2.add(new Card("Wrench", "Weapon")); //second possibility
		cards2.add(new Card("Foyer", "Room"));
		cards3.add(new Card("Mrs Peacock", "Person"));
		cards3.add(new Card("Candlestick", "Weapon"));
		cards3.add(new Card("Knife", "Weapon"));
		cards4.add(new Card("Lounge", "Room"));
		cards4.add(new Card("Great Hall", "Room"));
		cards4.add(new Card("Lead Pipe", "Weapon"));
		cards5.add(new Card("Bedroom", "Room"));
		cards5.add(new Card("Library", "Room"));
		cards5.add(new Card("Kitchen", "Room"));
		//creates the players with these hands
		HumanPlayer human = new HumanPlayer("Professor Plum", cardsHuman, "Purple", 0);
		ComputerPlayer player1 = new ComputerPlayer("Miss Scarlett", cards1, "Red", 297);
		ComputerPlayer player2 = new ComputerPlayer("Mrs White", cards2, "White", 297);
		ComputerPlayer player3 = new ComputerPlayer("Colonel Mustard", cards3, "Yellow", 297);
		ComputerPlayer player4 = new ComputerPlayer("Mrs Peacock", cards4, "Blue", 297);
		ComputerPlayer player5 = new ComputerPlayer("Reverend Green", cards5, "Green", 297);
		LinkedList<ComputerPlayer> players = new LinkedList<ComputerPlayer>();
		//adds the players to a LinkedList so game can iterate
		game.setPlayer(human);
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		game.setComps(players);
		Card shown = game.makeSuggestion(new Card("Mrs White", "Person"), 
				new Card("Wrench", "Weapon"), new Card("Pool", "Room"), 0);
		assertTrue(shown.equals(new Card("Mrs White", "Person")) || (shown.equals(new Card("Wrench", "Weapon"))));
	}

	@Test
	public void testDisproveTwoOne() {
		LinkedList<Card> cardsHuman = new LinkedList<Card>();
		LinkedList<Card> cards1 = new LinkedList<Card>();
		LinkedList<Card> cards2 = new LinkedList<Card>();
		LinkedList<Card> cards3 = new LinkedList<Card>();
		LinkedList<Card> cards4 = new LinkedList<Card>();
		LinkedList<Card> cards5 = new LinkedList<Card>();
		cardsHuman.add(new Card("Colonel Mustard", "Person"));
		cardsHuman.add(new Card("Reverend Green", "Person"));
		cardsHuman.add(new Card("Bowling Alley", "Room"));
		cards1.add(new Card("Miss Scarlett", "Person"));
		cards1.add(new Card("Study", "Room"));
		cards1.add(new Card("Rope", "Weapon"));
		cards2.add(new Card("Mrs White", "Person")); //this is what will be returned
		cards2.add(new Card("Revolver", "Weapon")); 
		cards2.add(new Card("Foyer", "Room"));
		cards3.add(new Card("Mrs Peacock", "Person"));
		cards3.add(new Card("Candlestick", "Weapon"));
		cards3.add(new Card("Knife", "Weapon"));
		cards4.add(new Card("Lounge", "Room"));
		cards4.add(new Card("Pool", "Room")); //second player possibility
		cards4.add(new Card("Lead Pipe", "Weapon"));
		cards5.add(new Card("Bedroom", "Room"));
		cards5.add(new Card("Library", "Room"));
		cards5.add(new Card("Kitchen", "Room"));
		//creates the players with these hands
		HumanPlayer human = new HumanPlayer("Professor Plum", cardsHuman, "Purple", 0);
		ComputerPlayer player1 = new ComputerPlayer("Miss Scarlett", cards1, "Red", 297);
		ComputerPlayer player2 = new ComputerPlayer("Mrs White", cards2, "White", 297);
		ComputerPlayer player3 = new ComputerPlayer("Colonel Mustard", cards3, "Yellow", 297);
		ComputerPlayer player4 = new ComputerPlayer("Mrs Peacock", cards4, "Blue", 297);
		ComputerPlayer player5 = new ComputerPlayer("Reverend Green", cards5, "Green", 297);
		LinkedList<ComputerPlayer> players = new LinkedList<ComputerPlayer>();
		//adds the players to a LinkedList so game can iterate
		game.setPlayer(human);
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		game.setComps(players);
		Card shown = game.makeSuggestion(new Card("Mrs White", "Person"), 
				new Card("Wrench", "Weapon"), new Card("Pool", "Room"), 0);
		assertTrue(shown.equals(new Card("Mrs White", "Person")) || (shown.equals(new Card("Pool", "Room"))));
	}

	@Test 
	public void testDisproveHuman() {
		LinkedList<Card> cardsHuman = new LinkedList<Card>();
		LinkedList<Card> cards1 = new LinkedList<Card>();
		LinkedList<Card> cards2 = new LinkedList<Card>();
		LinkedList<Card> cards3 = new LinkedList<Card>();
		LinkedList<Card> cards4 = new LinkedList<Card>();
		LinkedList<Card> cards5 = new LinkedList<Card>();
		cardsHuman.add(new Card("Colonel Mustard", "Person"));
		cardsHuman.add(new Card("Mrs White", "Person")); //player's card, should be returned
		cardsHuman.add(new Card("Bowling Alley", "Room"));
		cards1.add(new Card("Miss Scarlett", "Person"));
		cards1.add(new Card("Study", "Room"));
		cards1.add(new Card("Rope", "Weapon"));
		cards2.add(new Card("Reverend Green", "Person")); 
		cards2.add(new Card("Revolver", "Weapon")); 
		cards2.add(new Card("Foyer", "Room"));
		cards3.add(new Card("Mrs Peacock", "Person"));
		cards3.add(new Card("Candlestick", "Weapon"));
		cards3.add(new Card("Knife", "Weapon"));
		cards4.add(new Card("Lounge", "Room"));
		cards4.add(new Card("Great Hall", "Room")); 
		cards4.add(new Card("Lead Pipe", "Weapon"));
		cards5.add(new Card("Bedroom", "Room"));
		cards5.add(new Card("Library", "Room"));
		cards5.add(new Card("Kitchen", "Room"));
		//creates the players with these hands
		HumanPlayer human = new HumanPlayer("Professor Plum", cardsHuman, "Purple", 0);
		ComputerPlayer player1 = new ComputerPlayer("Miss Scarlett", cards1, "Red", 297);
		ComputerPlayer player2 = new ComputerPlayer("Mrs White", cards2, "White", 297);
		ComputerPlayer player3 = new ComputerPlayer("Colonel Mustard", cards3, "Yellow", 297);
		ComputerPlayer player4 = new ComputerPlayer("Mrs Peacock", cards4, "Blue", 297);
		ComputerPlayer player5 = new ComputerPlayer("Reverend Green", cards5, "Green", 297);
		LinkedList<ComputerPlayer> players = new LinkedList<ComputerPlayer>();
		//adds the players to a LinkedList so game can iterate
		game.setPlayer(human);
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		game.setComps(players);
		Card shown = game.makeSuggestion(new Card("Mrs White", "Person"), 
				new Card("Wrench", "Weapon"), new Card("Pool", "Room"), 0);
		assertTrue(shown.equals(new Card("Mrs White", "Person")));
	}

	@Test
	public void testDisproveSelf() {
		LinkedList<Card> cardsHuman = new LinkedList<Card>();
		LinkedList<Card> cards1 = new LinkedList<Card>();
		LinkedList<Card> cards2 = new LinkedList<Card>();
		LinkedList<Card> cards3 = new LinkedList<Card>();
		LinkedList<Card> cards4 = new LinkedList<Card>();
		LinkedList<Card> cards5 = new LinkedList<Card>();
		cardsHuman.add(new Card("Colonel Mustard", "Person"));
		cardsHuman.add(new Card("Reverend Green", "Person")); 
		cardsHuman.add(new Card("Bowling Alley", "Room"));
		cards1.add(new Card("Miss Scarlett", "Person"));
		cards1.add(new Card("Study", "Room"));
		cards1.add(new Card("Rope", "Weapon"));
		cards2.add(new Card("Mrs White", "Person")); //player 2 is suggesting their own card
		cards2.add(new Card("Revolver", "Weapon")); 
		cards2.add(new Card("Foyer", "Room"));
		cards3.add(new Card("Mrs Peacock", "Person"));
		cards3.add(new Card("Candlestick", "Weapon"));
		cards3.add(new Card("Knife", "Weapon"));
		cards4.add(new Card("Lounge", "Room"));
		cards4.add(new Card("Great Hall", "Room")); 
		cards4.add(new Card("Lead Pipe", "Weapon"));
		cards5.add(new Card("Bedroom", "Room"));
		cards5.add(new Card("Library", "Room"));
		cards5.add(new Card("Kitchen", "Room"));
		//creates the players with these hands
		HumanPlayer human = new HumanPlayer("Professor Plum", cardsHuman, "Purple", 0);
		ComputerPlayer player1 = new ComputerPlayer("Miss Scarlett", cards1, "Red", 297);
		ComputerPlayer player2 = new ComputerPlayer("Mrs White", cards2, "White", 297);
		ComputerPlayer player3 = new ComputerPlayer("Colonel Mustard", cards3, "Yellow", 297);
		ComputerPlayer player4 = new ComputerPlayer("Mrs Peacock", cards4, "Blue", 297);
		ComputerPlayer player5 = new ComputerPlayer("Reverend Green", cards5, "Green", 297);
		LinkedList<ComputerPlayer> players = new LinkedList<ComputerPlayer>();
		//adds the players to a LinkedList so game can iterate
		game.setPlayer(human);
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		game.setComps(players);
		Card shown = game.makeSuggestion(new Card("Mrs White", "Person"), 
				new Card("Wrench", "Weapon"), new Card("Pool", "Room"), 2);
		assertTrue(shown.equals(null)); //should find no matches
	}

	@Test
	public void testSuggestionCorrect() {
		LinkedList<Card> cardsHuman = new LinkedList<Card>();
		LinkedList<Card> cards1 = new LinkedList<Card>();
		LinkedList<Card> cards2 = new LinkedList<Card>();
		LinkedList<Card> cards3 = new LinkedList<Card>();
		LinkedList<Card> cards4 = new LinkedList<Card>();
		LinkedList<Card> cards5 = new LinkedList<Card>();
		cardsHuman.add(new Card("Colonel Mustard", "Person"));
		cardsHuman.add(new Card("Professor Plum", "Person"));
		cardsHuman.add(new Card("Bowling Alley", "Room"));
		cards1.add(new Card("Miss Scarlett", "Person"));
		cards1.add(new Card("Study", "Room"));
		cards1.add(new Card("Rope", "Weapon"));
		cards2.add(new Card("Reverend Green", "Person")); 
		cards2.add(new Card("Revolver", "Weapon")); 
		cards2.add(new Card("Foyer", "Room"));
		cards3.add(new Card("Mrs Peacock", "Person"));
		cards3.add(new Card("Candlestick", "Weapon"));
		cards3.add(new Card("Knife", "Weapon"));
		cards4.add(new Card("Lounge", "Room"));
		cards4.add(new Card("Great Hall", "Room")); 
		cards4.add(new Card("Lead Pipe", "Weapon"));
		cards5.add(new Card("Bedroom", "Room"));
		cards5.add(new Card("Library", "Room"));
		cards5.add(new Card("Kitchen", "Room"));
		//creates the players with these hands
		HumanPlayer human = new HumanPlayer("Professor Plum", cardsHuman, "Purple", 0);
		ComputerPlayer player1 = new ComputerPlayer("Miss Scarlett", cards1, "Red", 297);
		ComputerPlayer player2 = new ComputerPlayer("Mrs White", cards2, "White", 297);
		ComputerPlayer player3 = new ComputerPlayer("Colonel Mustard", cards3, "Yellow", 297);
		ComputerPlayer player4 = new ComputerPlayer("Mrs Peacock", cards4, "Blue", 297);
		ComputerPlayer player5 = new ComputerPlayer("Reverend Green", cards5, "Green", 297);
		LinkedList<ComputerPlayer> players = new LinkedList<ComputerPlayer>();
		//adds the players to a LinkedList so game can iterate
		game.setPlayer(human);
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		game.setComps(players);
		Card shown = game.makeSuggestion(new Card("Mrs White", "Person"), 
				new Card("Wrench", "Weapon"), new Card("Pool", "Room"), 3);
		assertTrue(shown.equals(null));
	}

	@Test
	public void testSuggestionRandom() {

	}
}
