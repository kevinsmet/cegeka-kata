package dis.will.be.epic.sauce.wincondition;

import dis.will.be.epic.sauce.Card;

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
