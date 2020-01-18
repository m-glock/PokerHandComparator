package PokerHandComparator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

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
	
	@BeforeAll
    public void init() {
		left = new Hand();
		right = new Hand();
		
		leftCards = new HashSet<Card>();
		rightCards = new HashSet<Card>();
    }
	
	@Test
	void sortAllHandTypes() {
		fail("Not yet implemented");
	}
	
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
		
		HandComparator comp = new HandComparator();
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		assertEquals(-1, result);
		
		System.out.println();
		System.out.println();
		System.out.println();
		result = comp.compare(right, left);
		System.out.println("result2 in test: " + result);
		assertEquals(1, result);
		
		//fail("Not yet implemented");
	}
	
	/*@Test
	void compareTwoStraighFlushes() {
		fail("Not yet implemented");
	}
	
	@Test
	void compareTwoFourKinds() {
		fail("Not yet implemented");
	}
	
	@Test
	void compareTwoFullHouses() {
		fail("Not yet implemented");
	}
	
	@Test
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

}
