package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import PokerHandComparator.Card;
import PokerHandComparator.Hand;
import PokerHandComparator.HandComparator;
import PokerHandComparator.Rank;
import PokerHandComparator.Suit;

@TestInstance(Lifecycle.PER_CLASS)
class DifferentHandTests {

	private Hand left;
	private Hand right;
	private Set<Card> leftCards;
	private Set<Card> rightCards;
	private HandComparator comp;
	
	@BeforeAll
    public void init() {
		left = new Hand();
		right = new Hand();
		
		leftCards = new HashSet<Card>();
		rightCards = new HashSet<Card>();
		
		comp = new HandComparator();
    }
	
	@AfterEach
	public void prepare() {
		left.setCards(null);
		right.setCards(null);
		
		leftCards.clear();
		rightCards.clear();
    }
	
	/**
	 * compare different hand types
	 * */
	@Test
	void onePairVsTwoPairs() {
		// two pairs
		leftCards.add(new Card(Suit.DIAMONDS, Rank.FIVE));
		leftCards.add(new Card(Suit.SPADES, Rank.FIVE));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.FOUR));
		leftCards.add(new Card(Suit.CLUBS, Rank.FOUR));
		leftCards.add(new Card(Suit.HEARTS, Rank.ACE));
		
		//one pair
		rightCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.EIGHT));
		rightCards.add(new Card(Suit.SPADES, Rank.ACE));
		rightCards.add(new Card(Suit.SPADES, Rank.FOUR));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.TEN));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(1, result);
	}
	
	@Test
	void royalFlushVsStraighFlush() {
		Suit leftSuit = Suit.DIAMONDS;
		Suit rightSuit = Suit.CLUBS;
		
		//royal flush
		leftCards.add(new Card(leftSuit, Rank.ACE));
		leftCards.add(new Card(leftSuit, Rank.KING));
		leftCards.add(new Card(leftSuit, Rank.QUEEN));
		leftCards.add(new Card(leftSuit, Rank.JACK));
		leftCards.add(new Card(leftSuit, Rank.TEN));
		
		//straight flush
		rightCards.add(new Card(rightSuit, Rank.KING));
		rightCards.add(new Card(rightSuit, Rank.QUEEN));
		rightCards.add(new Card(rightSuit, Rank.JACK));
		rightCards.add(new Card(rightSuit, Rank.TEN));
		rightCards.add(new Card(rightSuit, Rank.NINE));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(1, result);
	}
	
	@Test
	void StraightVsFlush() {
		Suit suit = Suit.HEARTS;
		
		// flush
		leftCards.add(new Card(suit, Rank.KING));
		leftCards.add(new Card(suit, Rank.ACE));
		leftCards.add(new Card(suit, Rank.FIVE));
		leftCards.add(new Card(suit, Rank.NINE));
		leftCards.add(new Card(suit, Rank.THREE));
		
		// straight
		rightCards.add(new Card(Suit.CLUBS, Rank.NINE));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.EIGHT));
		rightCards.add(new Card(Suit.HEARTS, Rank.SEVEN));
		rightCards.add(new Card(Suit.SPADES, Rank.SIX));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.FIVE));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(1, result);
	}
	
	@Test
	void StraightFlushVsStraight() {
		Suit suit = Suit.SPADES;
		
		//straight flush
		leftCards.add(new Card(suit, Rank.SEVEN));
		leftCards.add(new Card(suit, Rank.SIX));
		leftCards.add(new Card(suit, Rank.FIVE));
		leftCards.add(new Card(suit, Rank.FOUR));
		leftCards.add(new Card(suit, Rank.THREE));
		
		//straight
		rightCards.add(new Card(Suit.CLUBS, Rank.SEVEN));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.SIX));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.FIVE));
		rightCards.add(new Card(Suit.CLUBS, Rank.FOUR));
		rightCards.add(new Card(Suit.HEARTS, Rank.THREE));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(1, result);
	}
	
	@Test
	void straightFlushVsFlush() {
		Suit leftSuit = Suit.HEARTS;
		Suit rightSuit = Suit.SPADES;
		
		//flush
		leftCards.add(new Card(leftSuit, Rank.KING));
		leftCards.add(new Card(leftSuit, Rank.ACE));
		leftCards.add(new Card(leftSuit, Rank.FIVE));
		leftCards.add(new Card(leftSuit, Rank.NINE));
		leftCards.add(new Card(leftSuit, Rank.THREE));
		
		//straight flush
		rightCards.add(new Card(rightSuit, Rank.SEVEN));
		rightCards.add(new Card(rightSuit, Rank.SIX));
		rightCards.add(new Card(rightSuit, Rank.FIVE));
		rightCards.add(new Card(rightSuit, Rank.FOUR));
		rightCards.add(new Card(rightSuit, Rank.THREE));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(-1, result);
	}
	
	@Test
	void threeOfAKindVsFullHouse() {
		//three of a kind
		leftCards.add(new Card(Suit.CLUBS, Rank.ACE));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.ACE));
		leftCards.add(new Card(Suit.SPADES, Rank.ACE));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.NINE));
		leftCards.add(new Card(Suit.HEARTS, Rank.THREE));
		
		//full house
		rightCards.add(new Card(Suit.HEARTS, Rank.SEVEN));
		rightCards.add(new Card(Suit.SPADES, Rank.SEVEN));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.THREE));
		rightCards.add(new Card(Suit.HEARTS, Rank.THREE));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(-1, result);
	}
	
	@Test
	void pairVsFullHouse() {
		//full house
		leftCards.add(new Card(Suit.HEARTS, Rank.SEVEN));
		leftCards.add(new Card(Suit.SPADES, Rank.SEVEN));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.THREE));
		leftCards.add(new Card(Suit.HEARTS, Rank.THREE));
				
		//pair
		rightCards.add(new Card(Suit.CLUBS, Rank.THREE));
		rightCards.add(new Card(Suit.SPADES, Rank.THREE));
		rightCards.add(new Card(Suit.CLUBS, Rank.SEVEN));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.FIVE));
		rightCards.add(new Card(Suit.HEARTS, Rank.NINE));
				
		left.setCards(leftCards);
		right.setCards(rightCards);
				
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(1, result);
	}
}
