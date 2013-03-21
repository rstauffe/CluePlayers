package cluePlayers;

import java.util.HashSet;
import java.util.LinkedList;

public class Player {
	private String name;
	private LinkedList<Card> cards;
	private String color;
	private int index; //change to index
	
	public Player(String name, LinkedList<Card> cards, String color, int index) {
		this.name = name;
		this.cards = cards;
		this.color = color;
		this.index = index;
	}
	
	public Card disproveSuggestion(Card a, Card b, Card c) {
		return null;
	}
	
	public HashSet<Card> makeAccusation(Card a, Card b, Card c) {
		return new HashSet<Card>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<Card> getCards() {
		return cards;
	}

	public void setCards(LinkedList<Card> cards) {
		this.cards = cards;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
