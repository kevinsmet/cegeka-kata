package com.cegeka.red7.wincondition;

import com.cegeka.red7.Card;
import com.cegeka.red7.Player;

public class HighestCardDeterminer implements WinConditionSpecificDeterminer {

    @Override
    public Integer determineWinner(Player player, Player opponent) {
        return 0;
    }

    @Override
    public Card determineHighestCardForWinCondition(Player player) {
        return player.getTableauCards().stream().max(Card::isHigherThan).orElseThrow(() -> new IllegalArgumentException("Draws are not possible"));
    }
}
