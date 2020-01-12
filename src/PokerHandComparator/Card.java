package PokerHandComparator;

import java.util.Comparator;

public class Card{
	
	static public final Comparator<Card> RANK_SUIT_COMPARATOR = Comparator.comparing(Card::getRank).thenComparing(Card::getSuit);
	private final Suit suit;
	private final Rank rank;
	
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}
}
