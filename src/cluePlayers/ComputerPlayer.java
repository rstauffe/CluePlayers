package cluePlayers;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

import clueGame.Board;
import clueGame.BoardCell;

public class ComputerPlayer extends Player {
	private LinkedList<Card> seen;
	private Random rand = new Random();
	private static Board board = new Board();
	
	public ComputerPlayer(String name, LinkedList<Card> cards, String color, int index) {
		super(name, cards, color, index);
		seen = new LinkedList<Card>();
		for (Card c : cards) seen.add(c);
	}
	
	public ComputerPlayer(String name, String color, int index) {
		super(name, color, index);
		seen = new LinkedList<Card>();
	}
	
	public int pickLocation(int moves, Board board) { //need to account for not reentering same room
		board.startTargets(getIndex(), moves);
		Set<BoardCell> targets = board.getTargets();
		Set<BoardCell> rooms = new HashSet<BoardCell>();
		for (BoardCell c : targets)	if (c.isRoom()) rooms.add(c);
		BoardCell target = null;
		//scan for room, then pick at random
		if (!rooms.isEmpty()) target = randomSelect(rooms); //pick from available rooms
		else target = randomSelect(targets);
		return target.getRow()*board.getNumColumns() + target.getCol();
	}
	
	public BoardCell randomSelect(Set<BoardCell> targets) {
		int counter = 0;
		int stop = rand.nextInt(targets.size());
		for (BoardCell b : targets) {
			if (counter == stop) return b;
			counter++;
		}
		return null; //should never trigger, but necessary since choice is in if loop
	}

	public LinkedList<Card> getSeen() {
		return seen;
	}

	public void setSeen(LinkedList<Card> seen) {
		this.seen = seen;
	}
}
