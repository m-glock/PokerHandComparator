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
class SameHandTests {
	
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
	 * compare same hand types
	 * */
	/*@Test
	void compareTwoRoyalFlushes() {
		Suit suitLeft = Suit.DIAMONDS;
		Suit suitRight = Suit.CLUBS;
		
		leftCards.add(new Card(suitLeft, Rank.ACE));
		leftCards.add(new Card(suitLeft, Rank.KING));
		leftCards.add(new Card(suitLeft, Rank.QUEEN));
		leftCards.add(new Card(suitLeft, Rank.JACK));
		leftCards.add(new Card(suitLeft, Rank.TEN));
		
		rightCards.add(new Card(suitRight, Rank.ACE));
		rightCards.add(new Card(suitRight, Rank.KING));
		rightCards.add(new Card(suitRight, Rank.QUEEN));
		rightCards.add(new Card(suitRight, Rank.JACK));
		rightCards.add(new Card(suitRight, Rank.TEN));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(-1, result);
	}
	
	@Test
	void compareTwoStraighFlushesSameStraight() {
		Suit suitLeft = Suit.SPADES;
		Suit suitRight = Suit.HEARTS;

		leftCards.add(new Card(suitLeft, Rank.NINE));
		leftCards.add(new Card(suitLeft, Rank.KING));
		leftCards.add(new Card(suitLeft, Rank.QUEEN));
		leftCards.add(new Card(suitLeft, Rank.JACK));
		leftCards.add(new Card(suitLeft, Rank.TEN));
		
		rightCards.add(new Card(suitRight, Rank.NINE));
		rightCards.add(new Card(suitRight, Rank.KING));
		rightCards.add(new Card(suitRight, Rank.QUEEN));
		rightCards.add(new Card(suitRight, Rank.JACK));
		rightCards.add(new Card(suitRight, Rank.TEN));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(1, result);
	}
	
	@Test
	void compareTwoStraighFlushesDiffStraight() {
		Suit suitLeft = Suit.SPADES;
		Suit suitRight = Suit.CLUBS;

		leftCards.add(new Card(suitLeft, Rank.NINE));
		leftCards.add(new Card(suitLeft, Rank.KING));
		leftCards.add(new Card(suitLeft, Rank.QUEEN));
		leftCards.add(new Card(suitLeft, Rank.JACK));
		leftCards.add(new Card(suitLeft, Rank.TEN));
		
		rightCards.add(new Card(suitRight, Rank.NINE));
		rightCards.add(new Card(suitRight, Rank.EIGHT));
		rightCards.add(new Card(suitRight, Rank.QUEEN));
		rightCards.add(new Card(suitRight, Rank.JACK));
		rightCards.add(new Card(suitRight, Rank.TEN));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(1, result);
	}
	
	@Test
	void compareTwoFourKinds() {
		
		leftCards.add(new Card(Suit.CLUBS, Rank.NINE));
		leftCards.add(new Card(Suit.HEARTS, Rank.NINE));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.NINE));
		leftCards.add(new Card(Suit.SPADES, Rank.NINE));
		leftCards.add(new Card(Suit.HEARTS, Rank.QUEEN));
		
		rightCards.add(new Card(Suit.CLUBS, Rank.FOUR));
		rightCards.add(new Card(Suit.HEARTS, Rank.FOUR));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.FOUR));
		rightCards.add(new Card(Suit.SPADES, Rank.FOUR));
		rightCards.add(new Card(Suit.SPADES, Rank.ACE));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(1, result);
	}
	
	@Test
	void compareTwoFourKindsSameHighCard() {
		
		leftCards.add(new Card(Suit.CLUBS, Rank.NINE));
		leftCards.add(new Card(Suit.HEARTS, Rank.NINE));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.NINE));
		leftCards.add(new Card(Suit.SPADES, Rank.NINE));
		leftCards.add(new Card(Suit.HEARTS, Rank.QUEEN));
		
		rightCards.add(new Card(Suit.CLUBS, Rank.FOUR));
		rightCards.add(new Card(Suit.HEARTS, Rank.FOUR));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.FOUR));
		rightCards.add(new Card(Suit.SPADES, Rank.FOUR));
		rightCards.add(new Card(Suit.SPADES, Rank.QUEEN));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(1, result);
	}
	
	@Test
	void compareTwoFullHousesDiffPair() {
		leftCards.add(new Card(Suit.CLUBS, Rank.FOUR));
		leftCards.add(new Card(Suit.HEARTS, Rank.FOUR));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.FOUR));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.ACE));
		leftCards.add(new Card(Suit.SPADES, Rank.ACE));
		
		rightCards.add(new Card(Suit.CLUBS, Rank.NINE));
		rightCards.add(new Card(Suit.HEARTS, Rank.NINE));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.NINE));
		rightCards.add(new Card(Suit.SPADES, Rank.QUEEN));
		rightCards.add(new Card(Suit.HEARTS, Rank.QUEEN));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(-1, result);
	}
	
	@Test
	void compareTwoFullHousesSamePair() {
		leftCards.add(new Card(Suit.CLUBS, Rank.NINE));
		leftCards.add(new Card(Suit.HEARTS, Rank.NINE));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.NINE));
		leftCards.add(new Card(Suit.SPADES, Rank.QUEEN));
		leftCards.add(new Card(Suit.HEARTS, Rank.QUEEN));
		
		rightCards.add(new Card(Suit.CLUBS, Rank.FOUR));
		rightCards.add(new Card(Suit.HEARTS, Rank.FOUR));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.FOUR));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.QUEEN));
		rightCards.add(new Card(Suit.CLUBS, Rank.QUEEN));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(1, result);
	}
	
	@Test
	void compareTwoFlushesSameRanks() {
		Suit leftSuit = Suit.DIAMONDS;
		Suit rightSuit = Suit.HEARTS;
		
		leftCards.add(new Card(leftSuit, Rank.ACE));
		leftCards.add(new Card(leftSuit, Rank.KING));
		leftCards.add(new Card(leftSuit, Rank.NINE));
		leftCards.add(new Card(leftSuit, Rank.FOUR));
		leftCards.add(new Card(leftSuit, Rank.FIVE));
		
		rightCards.add(new Card(rightSuit, Rank.ACE));
		rightCards.add(new Card(rightSuit, Rank.KING));
		rightCards.add(new Card(rightSuit, Rank.NINE));
		rightCards.add(new Card(rightSuit, Rank.FOUR));
		rightCards.add(new Card(rightSuit, Rank.FIVE));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(-1, result);
	}
	
	@Test
	void compareTwoFlushesDiffRanks() {
		Suit leftSuit = Suit.DIAMONDS;
		Suit rightSuit = Suit.HEARTS;
		
		leftCards.add(new Card(leftSuit, Rank.ACE));
		leftCards.add(new Card(leftSuit, Rank.KING));
		leftCards.add(new Card(leftSuit, Rank.NINE));
		leftCards.add(new Card(leftSuit, Rank.FOUR));
		leftCards.add(new Card(leftSuit, Rank.FIVE));
		
		rightCards.add(new Card(rightSuit, Rank.EIGHT));
		rightCards.add(new Card(rightSuit, Rank.THREE));
		rightCards.add(new Card(rightSuit, Rank.NINE));
		rightCards.add(new Card(rightSuit, Rank.FOUR));
		rightCards.add(new Card(rightSuit, Rank.FIVE));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(1, result);
	}
	
	@Test
	void compareTwoStraightsSame() {
		leftCards.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
		leftCards.add(new Card(Suit.SPADES, Rank.SIX));
		leftCards.add(new Card(Suit.HEARTS, Rank.FIVE));
		leftCards.add(new Card(Suit.HEARTS, Rank.FOUR));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.THREE));
		
		rightCards.add(new Card(Suit.HEARTS, Rank.SEVEN));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.SIX));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.FIVE));
		rightCards.add(new Card(Suit.SPADES, Rank.FOUR));
		rightCards.add(new Card(Suit.HEARTS, Rank.THREE));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(-1, result);
	}
	
	@Test
	void compareTwoStraightsDiff() {
		leftCards.add(new Card(Suit.DIAMONDS, Rank.EIGHT));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
		leftCards.add(new Card(Suit.SPADES, Rank.SIX));
		leftCards.add(new Card(Suit.HEARTS, Rank.FIVE));
		leftCards.add(new Card(Suit.HEARTS, Rank.FOUR));
		
		rightCards.add(new Card(Suit.HEARTS, Rank.SEVEN));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.SIX));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.FIVE));
		rightCards.add(new Card(Suit.SPADES, Rank.FOUR));
		rightCards.add(new Card(Suit.HEARTS, Rank.THREE));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(1, result);
	}*/
	
	@Test
	void compareTwoStraightsWithAceHighest() {
		leftCards.add(new Card(Suit.DIAMONDS, Rank.TEN));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.JACK));
		leftCards.add(new Card(Suit.SPADES, Rank.QUEEN));
		leftCards.add(new Card(Suit.HEARTS, Rank.KING));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.ACE));
		
		rightCards.add(new Card(Suit.CLUBS, Rank.KING));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.QUEEN));
		rightCards.add(new Card(Suit.HEARTS, Rank.JACK));
		rightCards.add(new Card(Suit.SPADES, Rank.TEN));
		rightCards.add(new Card(Suit.HEARTS, Rank.NINE));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(1, result);
	}
	
	/*@Test
	void compareTwoThreeKinds() {
		leftCards.add(new Card(Suit.DIAMONDS, Rank.EIGHT));
		leftCards.add(new Card(Suit.SPADES, Rank.EIGHT));
		leftCards.add(new Card(Suit.HEARTS, Rank.EIGHT));
		leftCards.add(new Card(Suit.HEARTS, Rank.FIVE));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
		
		rightCards.add(new Card(Suit.HEARTS, Rank.SEVEN));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
		rightCards.add(new Card(Suit.SPADES, Rank.SEVEN));
		rightCards.add(new Card(Suit.SPADES, Rank.FOUR));
		rightCards.add(new Card(Suit.HEARTS, Rank.THREE));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(1, result);
	}
	
	@Test
	void compareTwoThreeKindsSameHighCards() {
		leftCards.add(new Card(Suit.HEARTS, Rank.SEVEN));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
		leftCards.add(new Card(Suit.SPADES, Rank.SEVEN));
		leftCards.add(new Card(Suit.HEARTS, Rank.FIVE));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.SIX));
		
		rightCards.add(new Card(Suit.DIAMONDS, Rank.EIGHT));
		rightCards.add(new Card(Suit.SPADES, Rank.EIGHT));
		rightCards.add(new Card(Suit.HEARTS, Rank.EIGHT));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.FIVE));
		rightCards.add(new Card(Suit.SPADES, Rank.SIX));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(-1, result);
	}
	
	@Test
	void compareTwoDoublePairs() {
		leftCards.add(new Card(Suit.HEARTS, Rank.SEVEN));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
		leftCards.add(new Card(Suit.SPADES, Rank.FIVE));
		leftCards.add(new Card(Suit.HEARTS, Rank.FIVE));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.SIX));
		
		rightCards.add(new Card(Suit.DIAMONDS, Rank.EIGHT));
		rightCards.add(new Card(Suit.SPADES, Rank.EIGHT));
		rightCards.add(new Card(Suit.CLUBS, Rank.FIVE));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.FIVE));
		rightCards.add(new Card(Suit.SPADES, Rank.FOUR));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(-1, result);
	}
	
	@Test
	void compareTwoDoublePairsSame() {
		leftCards.add(new Card(Suit.CLUBS, Rank.SEVEN));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
		leftCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		leftCards.add(new Card(Suit.HEARTS, Rank.EIGHT));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.SIX));
		
		rightCards.add(new Card(Suit.HEARTS, Rank.SEVEN));
		rightCards.add(new Card(Suit.SPADES, Rank.SEVEN));
		rightCards.add(new Card(Suit.SPADES, Rank.EIGHT));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.EIGHT));
		rightCards.add(new Card(Suit.SPADES, Rank.FOUR));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(1, result);
	}
	
	@Test
	void compareTwoPairs() {
		leftCards.add(new Card(Suit.DIAMONDS, Rank.KING));
		leftCards.add(new Card(Suit.SPADES, Rank.KING));
		leftCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		leftCards.add(new Card(Suit.HEARTS, Rank.FIVE));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.SIX));
		
		rightCards.add(new Card(Suit.CLUBS, Rank.QUEEN));
		rightCards.add(new Card(Suit.SPADES, Rank.QUEEN));
		rightCards.add(new Card(Suit.SPADES, Rank.SEVEN));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.EIGHT));
		rightCards.add(new Card(Suit.SPADES, Rank.FOUR));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(1, result);
	}
	
	@Test
	void compareTwoPairsSame() {
		leftCards.add(new Card(Suit.DIAMONDS, Rank.KING));
		leftCards.add(new Card(Suit.SPADES, Rank.KING));
		leftCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		leftCards.add(new Card(Suit.CLUBS, Rank.FIVE));
		leftCards.add(new Card(Suit.CLUBS, Rank.SIX));
		
		rightCards.add(new Card(Suit.CLUBS, Rank.KING));
		rightCards.add(new Card(Suit.HEARTS, Rank.KING));
		rightCards.add(new Card(Suit.SPADES, Rank.EIGHT));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.FIVE));
		rightCards.add(new Card(Suit.SPADES, Rank.SIX));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(-1, result);
	}
	
	@Test
	void compareTwoHighCards() {
		leftCards.add(new Card(Suit.DIAMONDS, Rank.THREE));
		leftCards.add(new Card(Suit.SPADES, Rank.TEN));
		leftCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		leftCards.add(new Card(Suit.CLUBS, Rank.FIVE));
		leftCards.add(new Card(Suit.CLUBS, Rank.SIX));
		
		rightCards.add(new Card(Suit.SPADES, Rank.JACK));
		rightCards.add(new Card(Suit.HEARTS, Rank.FIVE));
		rightCards.add(new Card(Suit.HEARTS, Rank.TEN));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.FIVE));
		rightCards.add(new Card(Suit.SPADES, Rank.TWO));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(-1, result);
	}
	
	@Test
	void compareTwoHighCardsSame() {
		leftCards.add(new Card(Suit.DIAMONDS, Rank.JACK));
		leftCards.add(new Card(Suit.SPADES, Rank.TEN));
		leftCards.add(new Card(Suit.HEARTS, Rank.EIGHT));
		leftCards.add(new Card(Suit.CLUBS, Rank.FIVE));
		leftCards.add(new Card(Suit.SPADES, Rank.SIX));
		
		rightCards.add(new Card(Suit.CLUBS, Rank.JACK));
		rightCards.add(new Card(Suit.CLUBS, Rank.TEN));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.EIGHT));
		rightCards.add(new Card(Suit.HEARTS, Rank.FIVE));
		rightCards.add(new Card(Suit.CLUBS, Rank.SIX));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(-1, result);
	}*/
}


