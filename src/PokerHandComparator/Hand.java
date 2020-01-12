package PokerHandComparator;

import java.util.HashSet;
import java.util.Set;

public class Hand {

	private Set<Card> cards;
	
	public Hand() {
		this.cards = new HashSet<>();
	}
	
	public Set<Card> getCards() {
		return cards;
	}
	
	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}
}