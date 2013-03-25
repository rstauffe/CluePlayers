package cluePlayers;

import java.io.File;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.RoomCell;
import clueGame.WalkwayCell;

public class ClueGame {
	private LinkedList<Card> cards;
	private LinkedList<Card> toDeal;
	private LinkedList<Card> answer;
	private LinkedList<ComputerPlayer> comps;
	private HumanPlayer player;
	private int turn;
	private Board board;
	private Random rand;

	@SuppressWarnings("unchecked")
	public ClueGame() {
		board = new Board();
		cards = new LinkedList<Card>();
		answer = new LinkedList<Card>();
		comps = new LinkedList<ComputerPlayer>();
		rand = new Random();
		try {
			loadCards("Cards.txt"); //loads cards from file
			loadPlayers("Players.txt"); //loads players from file
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("A loading error occurred. Please check the config files.");
		}
		toDeal = (LinkedList<Card>) cards.clone(); //error okay, known to be same type
		dealCards();
	}

	public void loadCards(String filename) throws Exception {
		File f = new File(filename);
		Scanner in = new Scanner(f);
		Scanner s = null;
		while (in.hasNextLine()) {
			s = new Scanner(in.nextLine());
			s.useDelimiter(",");
			String name = s.next().trim();
			String type = s.next().trim(); //cuts off spaces (safety feature)
			if (s.hasNext()) {
				s.close();
				in.close();
				throw new Exception("Too many lines");
			}
			Card card = new Card(name, type);
			cards.add(card);
		}
		in.close();
	}

	public void loadPlayers(String filename) throws Exception {
		File f = new File(filename);
		Scanner in = new Scanner(f);
		loadPlayer(in, 0);
		while (in.hasNextLine()) loadPlayer(in, 1);
		in.close();
	}

	public void loadPlayer(Scanner in, int type) throws Exception {
		Scanner s = new Scanner(in.nextLine());
		s.useDelimiter(",");
		while (s.hasNext()) {
			String name = s.next().trim(); //player name
			String color = s.next().trim(); //player color
			String row = s.next().trim(); //starting location row
			String col = s.next().trim(); //starting location column
			int rowNum = Integer.parseInt(row);
			int colNum = Integer.parseInt(col);
			if (s.hasNext()) {
				s.close();
				throw new Exception("Too many arguments");
			}
			//toggle - 0 for human, 1 for computer
			if (type == 0) player = new HumanPlayer(name, color, board.calcIndex(rowNum, colNum));
			else comps.add(new ComputerPlayer(name, color, board.calcIndex(rowNum, colNum)));
		}
		s.close();
	}

	public void dealCards() {
		LinkedList<Card> people = new LinkedList<Card>();
		LinkedList<Card> weapons = new LinkedList<Card>();
		LinkedList<Card> rooms = new LinkedList<Card>();
		sortList(people, weapons, rooms, toDeal);
		answer.add(people.get(rand.nextInt(people.size())));
		answer.add(weapons.get(rand.nextInt(weapons.size())));
		answer.add(rooms.get(rand.nextInt(rooms.size())));
		for (Card c : answer) toDeal.remove(c);
		int curPlayer = 0; //start dealing to human
		while (!toDeal.isEmpty()) {
			Player player;
			if (curPlayer == 0) player = this.player; //pick player to deal to
			else player = comps.get(curPlayer - 1);
			LinkedList<Card> hand = player.getCards();
			Card card = toDeal.get(rand.nextInt(toDeal.size()));
			hand.add(card); //picks a random card to give
			player.setCards(hand);
			toDeal.remove(card); //removes the card from the cards left to deal
			if (curPlayer == 5) curPlayer = 0; //move to next player
			else curPlayer++;
		}
	}

	public Card makeSuggestion(Card a, Card b, Card c, int playerIndex) {
		int currentPlayer = -1; //placeholder number to ensure a different player is picked
		while (currentPlayer == -1 || currentPlayer == playerIndex) currentPlayer = rand.nextInt(6);
		Card card = null;
		for (int numCompared = 0; numCompared < 5; numCompared++) {
			if (currentPlayer == playerIndex) continue;
			Player player;
			if (currentPlayer == 0) player = this.player;
			else player = comps.get(currentPlayer - 1);
			//System.out.println("Comparing player " + currentPlayer);
			card = player.disproveSuggestion(a, b, c);
			if (card != null) break;
			if (currentPlayer == 5) currentPlayer = 0; //ensures wrap-around of list
			else currentPlayer++;
		}
		return card; 
		//should return null if no disproving card is shown
	}

	//precondition- only called by computer players (index 1 to 5)- randomizer
	public Card makeSuggestion(int playerIndex) {
		LinkedList<Card> seen = comps.get(playerIndex).getSeen();
		LinkedList<Card> possibilities = cards;
		for (Card d : seen) possibilities.remove(d);
		LinkedList<Card> people = new LinkedList<Card>();
		LinkedList<Card> weapons = new LinkedList<Card>();
		LinkedList<Card> rooms = new LinkedList<Card>();
		sortList(people, weapons, rooms, possibilities);
		//System.out.println(people.size());
		Card a = people.get(rand.nextInt(people.size()));
		Card b = weapons.get(rand.nextInt(weapons.size()));
		Card c = rooms.get(rand.nextInt(rooms.size()));
		return makeSuggestion(a, b, c, playerIndex);
	}

	public void sortList(LinkedList<Card> people, LinkedList<Card> weapons, 
			LinkedList<Card> rooms, LinkedList<Card> toSort) { //method that sorts a list into the different types of cards
		//System.out.println("Starting sort of " + toSort);
		for (Card d : toSort) {
			switch (d.getType()) {
			case PERSON:
				people.add(d);
				continue;
			case WEAPON:
				weapons.add(d);
				continue;
			case ROOM:
				//System.out.println("counting room");
				rooms.add(d);
				continue; 
			default:
				continue;
			}
		}
		//System.out.println(people.size() + ", " + weapons.size() + ", " + rooms.size());
	}

	public LinkedList<Card> getCards() {
		return cards;
	}

	public void setCards(LinkedList<Card> cards) {
		this.cards = cards;
	}

	public LinkedList<ComputerPlayer> getComps() {
		return comps;
	}

	public void setComps(LinkedList<ComputerPlayer> comps) {
		this.comps = comps;
	}

	public HumanPlayer getPlayer() {
		return player;
	}

	public void setPlayer(HumanPlayer player) {
		this.player = player;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public LinkedList<Card> getToDeal() {
		return toDeal;
	}

	public void setToDeal(LinkedList<Card> toDeal) {
		this.toDeal = toDeal;
	}

	public LinkedList<Card> getAnswer() {
		return answer;
	}

	public void setAnswer(LinkedList<Card> answer) {
		this.answer = answer;
	}

	public Board getBoard() {
		return board;
	}
}
