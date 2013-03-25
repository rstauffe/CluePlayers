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

	@Override
	public boolean equals(Object other) {
		if (this == other) return true; //if same, return true
		if (!(other instanceof Card)) return false; //if not the same type, return false
		Card otherCard = (Card) other; //wrap card (known to be Card from previous line)
		return ((otherCard.type == type) && otherCard.name.equals(name)); //perform comparison
	}
		
	@Override
	public String toString() {
		return "Card [name=" + name + ", type=" + type + "]";
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
