package PokerHandComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		
		HandType leftHandType = getHandType(left);
		HandType rightHandType = getHandType(right);
		
		if (leftHandType.compareTo(rightHandType) < 0) {
			System.out.println("right Hand has won. Value " + leftHandType.compareTo(rightHandType));
			return -1;
		} else if (leftHandType.compareTo(rightHandType) > 0) {
			System.out.println("left Hand has won. Value " + leftHandType.compareTo(rightHandType));
			return 1;
		} else {
			System.out.println("cards have the same category. Value " + leftHandType.compareTo(rightHandType));
			int value = compareSameHandType(leftHandType, left.getCards(), right.getCards());
			System.out.println("value after comparing the same category: " + value);
			if(value < 0) {
				System.out.println("right Hand has won. Value " + value);
				return -1;
			} else if (value > 0) {
				System.out.println("left Hand has won. Value " + value);
				return 1;
			} else {
				System.out.println("both Hands are equal. Should not happen. Value " + value);
				//TODO: throw Error?
				return 0;
			}
		}
	}
	
	/**
	 * 
	 * 
	 * */
	public HandType getHandType(final Hand hand) {
		Set<Card> cards = hand.getCards();
		
		//check how many cards of the same rank are in the hand
		Map<Rank, Long> rankCounts = cards.stream().collect(Collectors.groupingBy((Card card) -> card.getRank(), Collectors.counting()));
		
		if (rankCounts.containsValue(4L)) {
			System.out.println("four of a kind");
			return HandType.FOUR_OF_A_KIND;
		} else if (rankCounts.containsValue(3L)) {
			if (rankCounts.containsValue(2L)) {
				System.out.println("full house");
				return HandType.FULL_HOUSE;
			} else {
				System.out.println("three of a kind");
				return HandType.THREE_OF_A_KIND;
			}
		} else if (rankCounts.containsValue(2L)) {
			if (Collections.frequency(rankCounts.values(), 2L) > 1) { //if there is more than one rank with a frequency of 2
				System.out.println("two pairs");
				return HandType.TWO_PAIRS;
			} else {
				System.out.println("one pair");
				return HandType.PAIR;
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
				if(rankCounts.containsKey(Rank.ACE) && rankCounts.containsKey(Rank.KING)) {
					System.out.println("royal flush");
					return HandType.ROYAL_FLUSH;
				} else {
					System.out.println("straight flush");
					return HandType.STRAIGHT_FLUSH;
				}
			} else {
				System.out.println("straight");
				return HandType.STRAIGHT;
			}
		} else if (isFlush) {
			System.out.println("flush");
			return HandType.FLUSH;
		} else {
			// now the only thing left is a High Card
			System.out.println("high card");
			return HandType.HIGH_CARD;
		}
	}
	
	/**
	 * 
	 * 
	 * */
	private int compareSameHandType(HandType type, Set<Card> leftCards, Set<Card> rightCards) {
		// return -1 if left < right
		// return 1 if left > right
		// return 0 if left == right -> should not happen
		List<Card> sortedLeftCards = leftCards.stream().sorted(Card.RANK_SUIT_COMPARATOR.reversed()).collect(Collectors.toList());
		System.out.println("Sorted left cards: ");
		for(Card card : sortedLeftCards) {
			System.out.println(card.getRank() + " of " + card.getSuit());
		}
		System.out.println();
		List<Card> sortedRightCards = rightCards.stream().sorted(Card.RANK_SUIT_COMPARATOR.reversed()).collect(Collectors.toList());
		System.out.println("Sorted right cards: ");
		for(Card card : sortedRightCards) {
			System.out.println(card.getRank() + " of " + card.getSuit());
		}
		System.out.println();
		
		int sortedValue = 0;
		switch(type) {
			case ROYAL_FLUSH:
				System.out.println("Royal Flush. Suit of one Card is compared.");
				return compareSuits(sortedLeftCards, sortedRightCards, true);
			case STRAIGHT_FLUSH:
			case STRAIGHT:
			case HIGH_CARD:
				System.out.println(type + ". Only highest card is compared for rank and suit.");
				sortedValue = compareRanks(sortedLeftCards, sortedRightCards, true);
				if(sortedValue != 0) return sortedValue;
				else return compareSuits(sortedLeftCards, sortedRightCards, true);
			case FLUSH:
				System.out.println("Flush. Rank of all cards and suit of all cards is compared.");
				sortedValue = compareRanks(sortedLeftCards, sortedRightCards, false);
				if(sortedValue != 0) return sortedValue;
				else return compareSuits(sortedLeftCards, sortedRightCards, false);
			case FOUR_OF_A_KIND:
			case THREE_OF_A_KIND:
			case FULL_HOUSE:
			case TWO_PAIRS:
			case PAIR:
				System.out.println(type + ". Ranks of mutliples are compared.");
				return compareMultiples(sortedLeftCards, sortedRightCards);
			default:
				return 0;
		}
	}
	
	private int compareMultiples(List<Card> sortedLeftCards, List<Card> sortedRightCards) {
		Map<Rank, Long> leftRankCounts = sortedLeftCards.stream().collect(Collectors.groupingBy((Card card) -> card.getRank(), Collectors.counting()));
		Map<Rank, Long> rightRankCounts = sortedLeftCards.stream().collect(Collectors.groupingBy((Card card) -> card.getRank(), Collectors.counting()));

		List<Rank> leftPairs = leftRankCounts.entrySet().stream().filter(entry -> entry.getValue() > 1).map(entry -> entry.getKey()).sorted().collect(Collectors.toList());
		List<Rank> rightPairs = rightRankCounts.entrySet().stream().filter(entry -> entry.getValue() > 1).map(entry -> entry.getKey()).sorted().collect(Collectors.toList());
		
		System.out.println("Multiples left: ");
		for(Rank rank : leftPairs) {
			System.out.println(rank);
		}
		System.out.println();
		for(Rank rank : rightPairs) {
			System.out.println(rank);
		}
		
		Rank leftRank = leftPairs.get(0);
		Rank rightRank = rightPairs.get(0);
			
		if(leftRank.compareTo(rightRank) != 0) return leftRank.compareTo(rightRank);
		
		//for one or two pairs
		boolean leftHasClubs = sortedLeftCards.stream().filter(card -> card.getRank() == leftPairs.get(0)).anyMatch(card -> card.getSuit() == Suit.CLUBS);
		return leftHasClubs ? 1 : -1;
	}
	
	/**
	 * 
	 * 
	 * */
	/*private int findRankOfCardMultiple(List<Card> sortedLeftCards, List<Card> sortedRightCards) {
		Map<Rank, Long> leftRankCounts = sortedLeftCards.stream().collect(Collectors.groupingBy((Card card) -> card.getRank(), Collectors.counting()));
		Map<Rank, Long> rightRankCounts = sortedLeftCards.stream().collect(Collectors.groupingBy((Card card) -> card.getRank(), Collectors.counting()));
		//TODO: zusammenfassen
		Stream<Entry<Rank, Long>> leftEntryStream = leftRankCounts.entrySet().stream().filter(entry -> entry.getValue() > 1);
		Stream<Entry<Rank, Long>> rightEntryStream = rightRankCounts.entrySet().stream().filter(entry -> entry.getValue() > 1);
			
		if(leftEntryStream.count() > 1) { // there is more than one multiple in the list (2 pairs or full house)
			if(leftEntryStream.anyMatch(entry -> entry.getValue() == 3)) { 
				Rank leftRank = leftEntryStream.filter(entry -> entry.getValue() == 3).findFirst().get().getKey();
				Rank rightRank = rightEntryStream.filter(entry -> entry.getValue() == 3).findFirst().get().getKey();
				System.out.println("compared same type. Left " + leftRank + " and right " + rightRank);
				return leftRank.compareTo(rightRank);
			}
			List<Rank> leftRanks = leftEntryStream.map(entry -> entry.getKey()).sorted().collect(Collectors.toList());
			List<Rank> rightRanks = leftEntryStream.map(entry -> entry.getKey()).sorted().collect(Collectors.toList());
			int rankValue = leftRanks.get(0).compareTo(rightRanks.get(0));
			System.out.println("compared same type. higher left " + leftRanks.get(0) + " and higher right " + rightRanks.get(0));
			if(rankValue != 0) return rankValue;
			return sortedLeftCards.stream().filter(card -> card.getRank() == leftRanks.get(0)).anyMatch(card -> card.getSuit() == Suit.CLUBS) ? 1 : -1;
		} else { //there is only one multiple in the list (four of a kind, three of a kind, pair)
			Rank leftRank = leftEntryStream.findFirst().get().getKey();
			Rank rightRank = rightEntryStream.findFirst().get().getKey();
			System.out.println("compared same type. Left " + leftRank + " and right " + rightRank);
			if(leftEntryStream.noneMatch(entry -> entry.getValue() == 2) || leftRank.compareTo(rightRank) != 0) return leftRank.compareTo(rightRank);
			//continue for single pair and check for clubs
			return sortedLeftCards.stream().filter(card -> card.getRank() == leftRank).anyMatch(card -> card.getSuit() == Suit.CLUBS) ? 1 : -1;
		}
	}*/
	
	/**
	 * 
	 * 
	 * */
	private int compareRanks(List<Card> sortedLeftCards, List<Card> sortedRightCards, boolean compareFirstCard) {
		//TODO: highest card ace?
		if (compareFirstCard) return sortedLeftCards.get(0).getRank().compareTo((sortedRightCards.get(0).getRank()));
		
		for(int i = 0; i < sortedLeftCards.size(); i++) {
			int value = sortedLeftCards.get(i).getRank().compareTo((sortedRightCards.get(i).getRank()));
			if(value != 0) return value;
		}

		return 0;
	}
	
	/**
	 * 
	 * 
	 * */
	private int compareSuits(List<Card> sortedLeftCards, List<Card> sortedRightCards, boolean compareFirstCard) {
		if (compareFirstCard) return sortedLeftCards.get(0).getSuit().compareTo((sortedRightCards.get(0).getSuit()));
		
		for(int i = 0; i < sortedLeftCards.size(); i++) {
			int value = sortedLeftCards.get(i).getSuit().compareTo((sortedRightCards.get(i).getSuit()));
			if(value != 0) return value;
		}

		return 0;
	}
}