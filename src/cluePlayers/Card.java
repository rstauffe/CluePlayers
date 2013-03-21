package cluePlayers;

public class Card {
	private String name;
	public enum CardType {PERSON, WEAPON, ROOM}
	private CardType type;
	
	public Card(String name, String type) {
		this.name = name;
		switch (type) {
		case ("Person"): 
			this.type = CardType.PERSON;
			break;
		case ("Weapon"):
			this.type = CardType.WEAPON;
			break;
		case ("Room"):
			this.type = CardType.ROOM;
			break;
		default:
			this.type = null;
			break;
		}
	}

	public boolean equals(Card other) {
		return ((other.type == type) && (other.name == name));
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}
}
