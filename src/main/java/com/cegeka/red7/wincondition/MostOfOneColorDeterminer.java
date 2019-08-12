package com.cegeka.red7.wincondition;

import com.cegeka.red7.Card;
import com.cegeka.red7.Player;

import java.util.List;
import java.util.stream.Collectors;

public class MostOfOneColorDeterminer implements WinConditionSpecificDeterminer {

    @Override
    public Integer determineWinner(Player player, Player opponent) {
        int mostOccurancesOfOneColorForPlayer = getMostOccurancesOfOneColorForPlayer(player);
        int mostOccurancesOfOneColorForOpponent = getMostOccurancesOfOneColorForPlayer(opponent);
        return determineScoreBasedOnBestResult(mostOccurancesOfOneColorForPlayer, mostOccurancesOfOneColorForOpponent);
    }

    private int getMostOccurancesOfOneColorForPlayer(Player player) {
        return getMostOccurancesOfOneNumberForPlayer(getTableauCardsAsColorValue(player));
    }

    private List<Integer> getTableauCardsAsColorValue(Player player) {
        return player.getTableauCards().stream().map(card -> card.getCardColor().getValue()).collect(Collectors.toList());
    }

    @Override
    public Card determineHighestCardForWinCondition(Player player) {
        int maxOccuringColorValue = determineMaxOccuringNumber(getTableauCardsAsColorValue(player));
        return highestCardUsingFilter(player.getTableauCards(), card -> card.getCardColor().getValue() == maxOccuringColorValue);
    }
}