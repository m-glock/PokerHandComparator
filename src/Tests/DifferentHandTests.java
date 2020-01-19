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
		leftCards.add(new Card(Suit.DIAMONDS, Rank.FIVE));
		leftCards.add(new Card(Suit.SPADES, Rank.FIVE));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.FOUR));
		leftCards.add(new Card(Suit.CLUBS, Rank.FOUR));
		leftCards.add(new Card(Suit.HEARTS, Rank.ACE));
		
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
		fail("Not yet implemented");
	}
	
	@Test
	void fullHouseVsStraight() {
		fail("Not yet implemented");
	}
	
	@Test
	void StraightVsFlush() {
		fail("Not yet implemented");
	}
	
	@Test
	void StraightFlushVsStraight() {
		fail("Not yet implemented");
	}
	
	@Test
	void straightFlushVsFlush() {
		fail("Not yet implemented");
	}
	
	@Test
	void threeOfAKindVsFullHouse() {
		fail("Not yet implemented");
	}
	
	@Test
	void pairVsFullHouse() {
		fail("Not yet implemented");
	}
}
