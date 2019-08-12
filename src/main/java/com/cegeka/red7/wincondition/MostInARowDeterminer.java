package com.cegeka.red7.wincondition;

import com.cegeka.red7.Card;
import com.cegeka.red7.Player;

import java.util.List;
import java.util.stream.Collectors;

public class MostInARowDeterminer implements WinConditionSpecificDeterminer {
    @Override
    public Integer determineWinner(Player player, Player opponent) {
        int amountInARowForPlayer = getHighestStreak(player).size();
        int amountInARowForOpponent = getHighestStreak(opponent).size();
        return determineScoreBasedOnBestResult(amountInARowForPlayer, amountInARowForOpponent);
    }

    @Override
    public Card determineHighestCardForWinCondition(Player player) {
        Integer highestNumberInStreak = getHighestStreak(player).stream().max(Integer::compareTo).orElseThrow(() -> new IllegalArgumentException("No cards found"));
        return determineCardForHighestNumberInStreak(player, highestNumberInStreak);
    }

    private Card determineCardForHighestNumberInStreak(Player player, int highestNumberInStreak) {
        return player.getTableauCards().stream()
                .filter(card -> card.getValue() == highestNumberInStreak)
                .max(Card::isHigherThan)
                .orElseThrow(() -> new IllegalArgumentException("No cards present"));
    }

    private List<Integer> getHighestStreak(Player player){
        List<Integer> sortedNumbers = getTableauCardsAsNumberStream(player).sorted().distinct().collect(Collectors.toList());
        int highestAmountOfCardsInARow = 1;
        int currentAmountOfCardsInARow = 1;
        int lastNumberValue = sortedNumbers.get(0);
        int indexOfLastCardInStreak = 0;
        for (int i = 0; i < sortedNumbers.size(); i++) {
            if (i > 0) {
                if (ifOneHigherThanPrevious(sortedNumbers.get(i), lastNumberValue)) {
                    lastNumberValue++;
                    currentAmountOfCardsInARow++;
                    if (brokeOrEqualedPreviousStreakForMostInARow(highestAmountOfCardsInARow, currentAmountOfCardsInARow)) {
                        highestAmountOfCardsInARow = currentAmountOfCardsInARow;
                        indexOfLastCardInStreak = i;
                    }
                } else {
                    lastNumberValue = sortedNumbers.get(i);
                    currentAmountOfCardsInARow = 1;
                }
            }
        }

        return sortedNumbers.subList(indexOfLastCardInStreak-(highestAmountOfCardsInARow-1),indexOfLastCardInStreak+1);
    }

    private boolean brokeOrEqualedPreviousStreakForMostInARow(int highestAmountOfCardsInARow, int currentAmountOfCardsInARow) {
        return currentAmountOfCardsInARow >= highestAmountOfCardsInARow;
    }

    private boolean ifOneHigherThanPrevious(Integer currentNumberValue, int lastNumberValue) {
        return currentNumberValue - 1 == lastNumberValue;
    }
}
