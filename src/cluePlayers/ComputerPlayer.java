package cluePlayers;

import java.util.LinkedList;

public class ComputerPlayer extends Player {
	private LinkedList<Card> seen;
	
	public ComputerPlayer(String name, LinkedList<Card> cards, String color, int index) {
		super(name, cards, color, index);
		seen = new LinkedList<Card>();
	}
	
	public int pickLocation(int moves) {
		return 0;
	}

	public LinkedList<Card> getSeen() {
		return seen;
	}

	public void setSeen(LinkedList<Card> seen) {
		this.seen = seen;
	}
}
