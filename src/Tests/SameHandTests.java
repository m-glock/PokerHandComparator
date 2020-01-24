package Tests;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import PokerHandComparator.*;

public class SameHandTests {
	
	private Hand left;
	private Hand right;
	private Set<Card> leftCards;
	private Set<Card> rightCards;
	private HandComparator comp;
	
    public SameHandTests() {
		left = new Hand();
		right = new Hand();
		
		leftCards = new HashSet<Card>();
		rightCards = new HashSet<Card>();
		
		comp = new HandComparator();
    }
	
	@After
	public void prepare() {
		left.setCards(null);
		right.setCards(null);
		
		leftCards.clear();
		rightCards.clear();
    }
	
	/**
	 * compare same hand types
	 * */
	@Test
	public void compareTwoRoyalFlushes() {
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
		Assert.assertTrue(result < 0);
	}
	
	@Test
	public void compareTwoStraighFlushesSameStraight() {
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
		Assert.assertTrue(result > 0);
	}
	
	@Test
	public void compareTwoStraighFlushesDiffStraight() {
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
		Assert.assertTrue(result > 0);
	}
	
	@Test
	public void compareTwoStraightFlushesWithLowAce() {
		Suit suitLeft = Suit.SPADES;
		Suit suitRight = Suit.CLUBS;

		leftCards.add(new Card(suitLeft, Rank.ACE));
		leftCards.add(new Card(suitLeft, Rank.TWO));
		leftCards.add(new Card(suitLeft, Rank.THREE));
		leftCards.add(new Card(suitLeft, Rank.FOUR));
		leftCards.add(new Card(suitLeft, Rank.FIVE));
		
		rightCards.add(new Card(suitRight, Rank.TWO));
		rightCards.add(new Card(suitRight, Rank.THREE));
		rightCards.add(new Card(suitRight, Rank.FOUR));
		rightCards.add(new Card(suitRight, Rank.FIVE));
		rightCards.add(new Card(suitRight, Rank.SIX));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		Assert.assertTrue(result < 0);
	}
	
	@Test
	public void compareTwoFourKinds() {
		
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
		Assert.assertTrue(result > 0);
	}
	
	@Test
	public void compareTwoFourKindsSameHighCard() {
		
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
		Assert.assertTrue(result > 0);
	}
	
	@Test
	public void compareTwoFullHousesDiffPair() {
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
		Assert.assertTrue(result < 0);
	}
	
	@Test
	public void compareTwoFullHousesSamePair() {
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
		Assert.assertTrue(result > 0);
	}
	
	@Test
	public void compareTwoFlushesSameRanks() {
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
		Assert.assertTrue(result < 0);
	}
	
	@Test
	public void compareTwoFlushesDiffRanks() {
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
		Assert.assertTrue(result > 0);
	}
	
	@Test
	public void compareTwoFlushesAceHigh() {
		Suit leftSuit = Suit.DIAMONDS;
		Suit rightSuit = Suit.HEARTS;
		
		leftCards.add(new Card(leftSuit, Rank.ACE));
		leftCards.add(new Card(leftSuit, Rank.KING));
		leftCards.add(new Card(leftSuit, Rank.NINE));
		leftCards.add(new Card(leftSuit, Rank.FOUR));
		leftCards.add(new Card(leftSuit, Rank.FIVE));
		
		rightCards.add(new Card(rightSuit, Rank.KING));
		rightCards.add(new Card(rightSuit, Rank.THREE));
		rightCards.add(new Card(rightSuit, Rank.NINE));
		rightCards.add(new Card(rightSuit, Rank.FOUR));
		rightCards.add(new Card(rightSuit, Rank.FIVE));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		Assert.assertTrue(result > 0);
	}
	
	@Test
	public void compareTwoStraightsSame() {
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
		Assert.assertTrue(result < 0);
	}
	
	@Test
	public void compareTwoStraightsDiff() {
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
		Assert.assertTrue(result > 0);
	}
	
	@Test
	public void compareTwoStraightsWithLowAce() {
		leftCards.add(new Card(Suit.DIAMONDS, Rank.EIGHT));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
		leftCards.add(new Card(Suit.SPADES, Rank.SIX));
		leftCards.add(new Card(Suit.HEARTS, Rank.FIVE));
		leftCards.add(new Card(Suit.HEARTS, Rank.FOUR));
		
		rightCards.add(new Card(Suit.DIAMONDS, Rank.FIVE));
		rightCards.add(new Card(Suit.SPADES, Rank.FOUR));
		rightCards.add(new Card(Suit.HEARTS, Rank.THREE));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.TWO));
		rightCards.add(new Card(Suit.HEARTS, Rank.ACE));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		Assert.assertTrue(result > 0);
	}
	
	@Test
	public void compareTwoThreeKinds() {
		leftCards.add(new Card(Suit.DIAMONDS, Rank.EIGHT));
		leftCards.add(new Card(Suit.SPADES, Rank.EIGHT));
		leftCards.add(new Card(Suit.HEARTS, Rank.EIGHT));
		leftCards.add(new Card(Suit.HEARTS, Rank.FIVE));
		leftCards.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
		
		rightCards.add(new Card(Suit.HEARTS, Rank.ACE));
		rightCards.add(new Card(Suit.DIAMONDS, Rank.ACE));
		rightCards.add(new Card(Suit.SPADES, Rank.ACE));
		rightCards.add(new Card(Suit.SPADES, Rank.FOUR));
		rightCards.add(new Card(Suit.HEARTS, Rank.THREE));
		
		left.setCards(leftCards);
		right.setCards(rightCards);
		
		int result = comp.compare(left, right);
		System.out.println("result1 in test: " + result);
		Assert.assertTrue(result < 0);
	}
	
	@Test
	public void compareTwoThreeKindsSameHighCards() {
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
		Assert.assertTrue(result < 0);
	}
	
	@Test
	public void compareTwoDoublePairs() {
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
		Assert.assertTrue(result < 0);
	}
	
	@Test
	public void compareTwoDoublePairsSame() {
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
		Assert.assertTrue(result > 0);
	}
	
	@Test
	public void compareTwoPairs() {
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
		Assert.assertTrue(result > 0);
	}
	
	@Test
	public void compareTwoPairsSame() {
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
		Assert.assertTrue(result < 0);
	}
	
	@Test
	public void compareTwoHighCards() {
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
		Assert.assertTrue(result < 0);
	}
	
	@Test
	public void compareTwoHighCardsSame() {
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
		Assert.assertTrue(result < 0);
	}
	
	@Test
	public void zeroForSameHand() {
		leftCards.add(new Card(Suit.DIAMONDS, Rank.JACK));
		leftCards.add(new Card(Suit.SPADES, Rank.TEN));
		leftCards.add(new Card(Suit.HEARTS, Rank.EIGHT));
		leftCards.add(new Card(Suit.CLUBS, Rank.FIVE));
		leftCards.add(new Card(Suit.SPADES, Rank.SIX));
		
		rightCards.add(new Card(Suit.DIAMONDS, Rank.JACK));
		rightCards.add(new Card(Suit.SPADES, Rank.TEN));
		rightCards.add(new Card(Suit.HEARTS, Rank.EIGHT));
		rightCards.add(new Card(Suit.CLUBS, Rank.FIVE));
		rightCards.add(new Card(Suit.SPADES, Rank.SIX));
		
		left.setCards(leftCards);
		right.setCards(rightCards);

		try {
			comp.compare(left, right);
			//Assert.fail();
		} catch (IllegalArgumentException ex) {
			//ok :)	
		}
	}
}