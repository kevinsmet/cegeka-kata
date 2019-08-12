package com.cegeka.red7.wincondition;

import com.cegeka.red7.Card;
import com.cegeka.red7.Player;

import java.util.stream.Collectors;

public class MostDifferentColorsDeterminer implements WinConditionSpecificDeterminer {
    @Override
    public Integer determineWinner(Player player, Player opponent) {
        int amountOfDifferentColorsPlayer = calculateAmountOfDifferentColors(player);
        int amountOfDifferentColorsOpponent = calculateAmountOfDifferentColors(opponent);
        return determineScoreBasedOnBestResult(amountOfDifferentColorsPlayer, amountOfDifferentColorsOpponent);
    }

    private int calculateAmountOfDifferentColors(Player player) {
        return player.getTableauCards().stream().map(Card::getCardColor).distinct().collect(Collectors.toList()).size();
    }

    @Override
    public Card determineHighestCardForWinCondition(Player player) {
        return new HighestCardDeterminer().determineHighestCardForWinCondition(player);
    }
}