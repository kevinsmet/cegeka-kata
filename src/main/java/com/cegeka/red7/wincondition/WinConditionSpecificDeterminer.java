package com.cegeka.red7.wincondition;


import com.cegeka.red7.Card;
import com.cegeka.red7.Player;

public interface WinConditionSpecificDeterminer {

    Integer determineWinner(Player player, Player opponent);

    Card determineHighestCardForWinCondition(Player player);
}
