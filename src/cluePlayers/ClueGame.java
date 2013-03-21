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
	
	public Card makeSuggestion(Card a, Card b, Card c, int playerIndex) {
		return null;
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
