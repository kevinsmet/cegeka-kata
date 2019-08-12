package com.cegeka.red7.wincondition;

import com.cegeka.red7.Card;

public class RuleUnspecificDeterminer  {

    public Integer determineWinner(Card cardPlayer, Card cardOpponent) {
        if(cardPlayer == null){
            return -1;
        }
        if(cardOpponent == null){
            return 1;
        }
        return cardPlayer.isHigherThan(cardOpponent);
    }
}
