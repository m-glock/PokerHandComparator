package PokerHandComparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import PokerHandComparator.Rank;
import PokerHandComparator.Suit;

public class HandComparator implements Comparator<Hand> {

	static private enum HandType{
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
		
		final HandType leftHandType = this.getHandType(left);
		final HandType rightHandType = this.getHandType(right);
		
		//TODO: throw exception
		return leftHandType.compareTo(rightHandType) == 0
				? compareSameHandType(leftHandType, left.getCards(), right.getCards()) 
				: leftHandType.compareTo(rightHandType);
	}
	
	/**
	 * 
	 * 
	 * */
	private HandType getHandType(final Hand hand) {
		Set<Card> cards = hand.getCards();
		
		//check how many cards of the same rank are in the hand
		Map<Rank, Long> rankCounts = cards.stream().collect(Collectors.groupingBy((Card card) -> card.getRank(), Collectors.counting()));
		
		if (rankCounts.containsValue(4L)) return HandType.FOUR_OF_A_KIND;
		if (rankCounts.containsValue(3L)) return rankCounts.containsValue(2L) ? HandType.FULL_HOUSE : HandType.THREE_OF_A_KIND;
		if (rankCounts.containsValue(2L)) return Collections.frequency(rankCounts.values(), 2L) > 1 ? HandType.TWO_PAIRS : HandType.PAIR;
		
		// if there are no doubles, check for flush
		boolean isFlush = false;
		List<Card> cardList = cards.stream().sorted(Card.RANK_SUIT_COMPARATOR).collect(Collectors.toList()); // already sorted for straights later
		isFlush = cards.stream().allMatch(card -> card.getSuit() == cardList.get(0).getSuit());
				
		// check for straights
		boolean isStraight = (cardList.get(cardList.size() - 1).getRank().ordinal() - cardList.get(0).getRank().ordinal() == 4) ||
			(rankCounts.containsKey(Rank.ACE) && rankCounts.containsKey(Rank.TWO) && ((cardList.get(cardList.size()-2).getRank().ordinal()+2) - (cardList.get(0).getRank().ordinal()+2)) == 3);
	
		// return straight, flush, straight flush or high card
		if (isStraight) {
			if (isFlush) return rankCounts.containsKey(Rank.ACE) && rankCounts.containsKey(Rank.KING) ? HandType.ROYAL_FLUSH : HandType.STRAIGHT_FLUSH;
			return HandType.STRAIGHT;
		}
		return isFlush ? HandType.FLUSH : HandType.HIGH_CARD;
	}
	
	/**
	 * 
	 * 
	 * */
	private int compareSameHandType(HandType type, Set<Card> leftCards, Set<Card> rightCards) {
		List<Card> sortedLeftCards = leftCards.stream().sorted(Card.RANK_SUIT_COMPARATOR.reversed()).collect(Collectors.toList());
		List<Card> sortedRightCards = rightCards.stream().sorted(Card.RANK_SUIT_COMPARATOR.reversed()).collect(Collectors.toList());
		
		int sortedValue = 0;
		switch(type) {
			case ROYAL_FLUSH:
				return sortedLeftCards.get(0).getSuit().compareTo((sortedRightCards.get(0).getSuit()));
			case STRAIGHT_FLUSH:
			case STRAIGHT:
			case HIGH_CARD:
				if(type == HandType.STRAIGHT || type == HandType.STRAIGHT_FLUSH) sortedValue = compareHighestCardNotAce(sortedLeftCards, sortedRightCards);
				else sortedValue = sortedLeftCards.get(0).getRank().compareTo((sortedRightCards.get(0).getRank()));
				
				if(sortedValue != 0) return sortedValue;
				else return sortedLeftCards.get(0).getSuit().compareTo((sortedRightCards.get(0).getSuit()));
			case FLUSH:
				for(int i = 0; i < sortedLeftCards.size(); i++) {
					int value = sortedLeftCards.get(i).getRank().compareTo((sortedRightCards.get(i).getRank()));
					if(value != 0) {
						sortedValue = value;
						break;
					}
				}
				if(sortedValue != 0) return sortedValue;
				for(int i = 0; i < sortedLeftCards.size(); i++) {
					int value = sortedLeftCards.get(i).getSuit().compareTo((sortedRightCards.get(i).getSuit()));
					if(value != 0) {
						sortedValue = value;
						break;
					}
				}
				return sortedValue;
			case FOUR_OF_A_KIND:
			case THREE_OF_A_KIND:
			case FULL_HOUSE:
			case TWO_PAIRS:
			case PAIR:
				return compareMultiples(sortedLeftCards, sortedRightCards);
			default:
				throw new IllegalArgumentException();
		}
	}
	
	private int compareHighestCardNotAce(List<Card> sortedLeftCards, List<Card> sortedRightCards) {
		Rank leftRank = null;
		Rank rightRank = null;
		for(Card card : sortedLeftCards) {
			if(card.getRank()!= Rank.ACE) {
				leftRank = card.getRank();
				break;
			}
		}
		for(Card card : sortedRightCards) {
			if(card.getRank()!= Rank.ACE) {
				rightRank = card.getRank();
				break;
			}
		}
		System.out.println(leftRank + " and "+ rightRank);
		if(leftRank == null || rightRank == null) return 0;
		else return leftRank.compareTo(rightRank);
	}
	
	private int compareMultiples(List<Card> sortedLeftCards, List<Card> sortedRightCards) {
		Map<Rank, Long> leftRankCounts = sortedLeftCards.stream().collect(Collectors.groupingBy((Card card) -> card.getRank(), Collectors.counting()));
		Map<Rank, Long> rightRankCounts = sortedRightCards.stream().collect(Collectors.groupingBy((Card card) -> card.getRank(), Collectors.counting()));

		List<Rank> leftPairs;
		List<Rank> rightPairs;
				
		if(leftRankCounts.containsValue(2L) && leftRankCounts.containsValue(3L)) { // full house
			leftPairs = leftRankCounts.entrySet().stream().filter(entry -> entry.getValue() == 3).map(entry -> entry.getKey()).sorted().collect(Collectors.toList());
			rightPairs = rightRankCounts.entrySet().stream().filter(entry -> entry.getValue() == 3).map(entry -> entry.getKey()).sorted().collect(Collectors.toList());
		} else {
			leftPairs = leftRankCounts.entrySet().stream().filter(entry -> entry.getValue() > 1).map(entry -> entry.getKey()).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
			rightPairs = rightRankCounts.entrySet().stream().filter(entry -> entry.getValue() > 1).map(entry -> entry.getKey()).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		}

		Rank leftRank = leftPairs.get(0);
		Rank rightRank = rightPairs.get(0);
		if(leftRank.compareTo(rightRank) != 0) return leftRank.compareTo(rightRank);
		
		//for one or two pairs
		boolean leftHasClubs = sortedLeftCards.stream().filter(card -> card.getRank() == leftPairs.get(0)).anyMatch(card -> card.getSuit() == Suit.CLUBS);
		return leftHasClubs ? 1 : -1;
	}
}