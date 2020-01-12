package PokerHandComparator;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class HandComparator implements Comparator<Hand> {

	static private enum HandCategory{
		HIGH_CARD, PAIR, TWO_PAIRS, THREE_OF_A_KIND, STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH, ROYAL_FLUSH;
	}
	
	/***
	 * compares two hands and decides which one is higher
	 * right now: only compare the highest card of each hand
	 * later: compare two of a kind, three of a kind, royal flush to determine the winning hand
	 * 
	 * Algorithm for evaluating poker hands: http://nsayer.blogspot.com/2007/07/algorithm-for-evaluating-poker-hands.html
	 * Card Rankings: https://www.partypoker.com/how-to-play/hand-rankings
	 * 
	 * returns:
	 * -1 if the right hand is higher
	 * 1 if the left hand is higher
	 * 0 if both hands are equal
	 */
	@Override
	public int compare(final Hand left, final Hand right) {
		
		HandCategory leftHandCategory = getHandCategory(left);
		HandCategory rightHandCategory = getHandCategory(right);
		
		if (leftHandCategory.compareTo(rightHandCategory) < 0) {
			System.out.println("right Hand has won.");
			return -1;
		} else if (leftHandCategory.compareTo(rightHandCategory) > 0) {
			System.out.println("left Hand has won.");
			return 1;
		} else {
			System.out.println("cards have the same category");
			return compareSameCategory(leftHandCategory, left.getCards(), right.getCards());
		}
	}
	
	public HandCategory getHandCategory(final Hand hand) {
		
		System.out.println("get hand category.");
		Set<Card> cards = hand.getCards();
		for (Card card : cards) {
			System.out.println(card.getRank() + " of " + card.getSuit());
		}
		
		//check how many cards of the same rank are in the hand
		Map<Rank, Long> rankCounts = cards.stream().collect(Collectors.groupingBy((Card card) -> card.getRank(), Collectors.counting()));
		
		if (rankCounts.containsValue(4L)) {
			System.out.println("four of a kind");
			return HandCategory.FOUR_OF_A_KIND;
		} else if (rankCounts.containsValue(3L)) {
			if (rankCounts.containsValue(2L)) {
				System.out.println("full house");
				return HandCategory.FULL_HOUSE;
			} else {
				System.out.println("three of a kind");
				return HandCategory.THREE_OF_A_KIND;
			}
		} else if (rankCounts.containsValue(2L)) {
			if (Collections.frequency(rankCounts.values(), 2L) > 1) { //if there is more than one rank with a frequency of 2
				System.out.println("two pairs");
				return HandCategory.TWO_PAIRS;
			} else {
				System.out.println("one pair");
				return HandCategory.PAIR;
			}
		}
		
		// if there are no doubles, check for flush
		boolean isFlush = false;
		List<Card> cardList = cards.stream().sorted(Card.RANK_SUIT_COMPARATOR).collect(Collectors.toList()); // already sorted for straights later
		Card previousCard = cardList.get(0);
		for (int i = 1; i < cardList.size(); i++) {
			if (!cardList.get(i).getSuit().equals(previousCard.getSuit())) break;
			else if (i == cardList.size() - 1) isFlush = true;
			else previousCard = cardList.get(i);
		}
				
		// check for straights
		boolean isStraight = false;
		if((cardList.get(cardList.size() - 1).getRank().getRankNumber() - cardList.get(0).getRank().getRankNumber()) == 4) isStraight = true; 
		// TODO: special case ace
		else if (rankCounts.containsKey(Rank.ACE) && rankCounts.containsKey(Rank.KING) && (cardList.get(cardList.size()-1).getRank().getRankNumber() - cardList.get(1).getRank().getRankNumber()) == 3) {
			isStraight = true;
		}
	
		// return straight, flush, straight flush or high card
		if (isStraight) {
			if (isFlush) {
				List<Rank> cardRanks = cardList.stream().map(card -> card.getRank()).collect(Collectors.toList());
				if(cardRanks.contains(Rank.ACE) && cardRanks.contains(Rank.KING)) {
					System.out.println("royal flush");
					return HandCategory.ROYAL_FLUSH;
				} else {
					System.out.println("straight flush");
					return HandCategory.STRAIGHT_FLUSH;
				}
			} else {
				System.out.println("straight");
				return HandCategory.STRAIGHT;
			}
		} else if (isFlush) {
			System.out.println("flush");
			return HandCategory.FLUSH;
		} else {
			// now the only thing left is a High Card
			System.out.println("high card");
			return HandCategory.HIGH_CARD;
		}
		
		//return HandCategory.HIGH_CARD;
	}
	
	private int compareSameCategory(HandCategory category, Set<Card> leftCards, Set<Card> rightCards) {
		// return -1 if left < right
		// return 1 if left > right
		// return 0 if left == right -> special case
		switch(category) {
			case ROYAL_FLUSH:
				// compare suits
				break;
			case STRAIGHT_FLUSH:
				// compare highest card
				// then compare suit
				break;
			case FOUR_OF_A_KIND:
				// compare rank
				break;
			case FULL_HOUSE:
				// compare rank of three of a kind
				break;
			case FLUSH:
				// compare rank of highest card
				// then second highest and so on
				// if all ranks are same, pot would be split, but we compare suit
				break;
			case STRAIGHT:
				// compare rank of highest card
				// if all ranks are the same, pot would be split, but we compare suit of highest card and so on
				break;
			case THREE_OF_A_KIND:
				// compare rank
				break;
			case TWO_PAIRS:
				// compare rank of higher pair
				// then compare suit of higher pair
				// then compare rank of lower pair
				// then compare suit of lower pair
				break;
			case PAIR:
				// compare rank of pair
				// then compare suit of pair
				// then compare rank of highest leftover card
				// then compare suit of highest leftover card
				// and so on
				break;
			case HIGH_CARD:
				// compare rank of highest leftover card
				// then compare suit of highest leftover card
				// and so on
				break;
			default:
				break;
		}
		return 1;
	}
}