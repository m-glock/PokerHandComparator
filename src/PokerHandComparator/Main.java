package PokerHandComparator;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		// create six Hands for six players
		/*final Set<Hand> unsortedHands = new HashSet<>();
		for(int i = 0; i < 6; i++) {
			unsortedHands.add(new Hand());
		}

		// create all cards in the card deck
		final Set<Card> cardDeck = new HashSet<>();
		for(Rank rank : Rank.values()) {
			for(Suit suit : Suit.values()) {
				cardDeck.add(new Card(suit, rank));
			}
		}
		// shuffle cards
		final Random radomizer = new SecureRandom(); 
		final List<Card> cardList = new ArrayList<>(cardDeck);
		Collections.shuffle(cardList, radomizer);
		
		// give each hand five cards from the shuffled deck
		for(Hand hand : unsortedHands) {
			Set<Card> handCards = new HashSet<>();
			for(int i = 0; i < 5; i++) {
				handCards.add(cardList.remove(0));
			}
			hand.setCards(handCards);
		}
		
		// test: compare two hand with each other
		HandComparator comp = new HandComparator();
		List<Hand> newHands = new ArrayList<>(unsortedHands);
		
		Hand hand1 = newHands.get(0);
		System.out.println("Player 1: ");
		for(Card card : hand1.getCards()) {
			System.out.println(card.getRank() + " of " + card.getSuit());
		}
		System.out.println();
		
		Hand hand2 = newHands.get(1);
		System.out.println("Player 2: ");
		for(Card card : hand2.getCards()) {
			System.out.println(card.getRank() + " of " + card.getSuit());
		}
		System.out.println();
		
		comp.compare(hand1, hand2);*/
		
		Hand left = new Hand();
		Hand right = new Hand();
		
		Set<Card> cards1 = new HashSet<Card>();
		Set<Card> cards2 = new HashSet<Card>();
		
		cards1.add(new Card(Suit.DIAMONDS, Rank.FOUR));
		cards1.add(new Card(Suit.DIAMONDS, Rank.QUEEN));
		cards1.add(new Card(Suit.DIAMONDS, Rank.FIVE));
		cards1.add(new Card(Suit.DIAMONDS, Rank.KING));
		cards1.add(new Card(Suit.DIAMONDS, Rank.NINE));
		
		cards2.add(new Card(Suit.CLUBS, Rank.FOUR));
		cards2.add(new Card(Suit.CLUBS, Rank.QUEEN));
		cards2.add(new Card(Suit.CLUBS, Rank.FIVE));
		cards2.add(new Card(Suit.CLUBS, Rank.JACK));
		cards2.add(new Card(Suit.CLUBS, Rank.NINE));
		
		left.setCards(cards1);
		right.setCards(cards2);
		
		HandComparator comp = new HandComparator();
		comp.compare(left, right);
		//comp.getHandCategory(left);
		
	}

}
