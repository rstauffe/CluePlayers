package cluePlayers;

import java.util.LinkedList;

import clueGame.Board;

public class ClueGame {
	private LinkedList<Card> cards;
	private LinkedList<Card> toDeal;
	private LinkedList<ComputerPlayer> comps;
	private HumanPlayer player;
	private int turn;
	private Board board;
	
	public ClueGame() {
		cards = new LinkedList<Card>();
		toDeal = new LinkedList<Card>();
		comps = new LinkedList<ComputerPlayer>();
		player = new HumanPlayer("Miss Scarlett", cards, "Red", 297);
		comps.add(new ComputerPlayer("Miss Scarlett", cards, "Red", 297));
		comps.add(new ComputerPlayer("Miss Scarlett", cards, "Red", 297));
		comps.add(new ComputerPlayer("Miss Scarlett", cards, "Red", 297));
		comps.add(new ComputerPlayer("Miss Scarlett", cards, "Red", 297));
		comps.add(new ComputerPlayer("Miss Scarlett", cards, "Red", 297)); //placeholder bots for tests
	}
	
	public Card makeSuggestion(Card a, Card b, Card c, int playerIndex) {
		return new Card("Colonel Mustard", "Person"); //placeholder card, not called in tests
		//should return null if no disproving card is shown
	}
	
	public Card makeSuggestion(int playerIndex) {
		return new Card("Colonel Mustard", "Person");
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

	public Board getBoard() {
		return board;
	}
}
