package PokerHandComparator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class PokerHandComparatorTests {
	
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
	
	/*@Test
	void sortAllHandTypes() {
		fail("Not yet implemented");
	}*/
	
	/**
	 * compare same hand types
	 * */
	@Test
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
	
	/*@Test
	void compareTwoFlushes() {
		fail("Not yet implemented");
	}
	
	@Test
	void compareTwoStraights() {
		fail("Not yet implemented");
	}
	
	@Test
	void compareTwoThreeKinds() {
		fail("Not yet implemented");
	}
	
	@Test
	void compareTwoDoublePairs() {
		fail("Not yet implemented");
	}
	
	@Test
	void compareTwoPairs() {
		fail("Not yet implemented");
	}
	
	@Test
	void compareTwoHighCards() {
		fail("Not yet implemented");
	}*/

	/*private void fillHands() {
		Map<String, Hand> hands = new HashMap<>();
		
		Hand hand1 = new Hand();
		Set<Card> cards1 = new HashSet<Card>();
		cards1.add(new Card(Suit.DIAMONDS, Rank.ACE));
		cards1.add(new Card(Suit.DIAMONDS, Rank.QUEEN));
		cards1.add(new Card(Suit.DIAMONDS, Rank.JACK));
		cards1.add(new Card(Suit.DIAMONDS, Rank.KING));
		cards1.add(new Card(Suit.DIAMONDS, Rank.TEN));
		hand1.setCards(cards1);
	}*/
}


