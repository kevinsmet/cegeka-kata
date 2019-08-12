package com.cegeka.red7;

import com.cegeka.red7.wincondition.HighestCardDeterminer;
import com.cegeka.red7.wincondition.RuleUnspecificDeterminer;
import com.cegeka.red7.wincondition.WinConditionSpecificDeterminer;

public enum WinCondition {
    HIGHEST_CARD(new HighestCardDeterminer()),
    MOST_OF_ONE_NUMBER(new HighestCardDeterminer()),
    MOST_OF_ONE_COLOR(new HighestCardDeterminer()),
    MOST_EVEN(new HighestCardDeterminer()),
    MOST_DIFFERENT_COLORS(new HighestCardDeterminer()),
    MOST_IN_A_ROW(new HighestCardDeterminer()),
    MOST_BELOW_FOUR(new HighestCardDeterminer());

    private WinConditionSpecificDeterminer winConditionSpecificDeterminer;

    WinCondition(WinConditionSpecificDeterminer winConditionSpecificDeterminer) {
        this.winConditionSpecificDeterminer = winConditionSpecificDeterminer;
    }

    public int determineWinner(Player player, Player opponent) {
        int winConditionSpecificResult = winConditionSpecificDeterminer.determineWinner(player, opponent);
        if (ableToDetermineWinnerUsingWinConditionSpecificLogic(winConditionSpecificResult)) {
            return winConditionSpecificResult;
        }
        return new RuleUnspecificDeterminer().determineWinner(
                winConditionSpecificDeterminer.determineHighestCardForWinCondition(player),
                winConditionSpecificDeterminer.determineHighestCardForWinCondition(opponent));
    }

    private boolean ableToDetermineWinnerUsingWinConditionSpecificLogic(int winConditionSpecificResult) {
        return winConditionSpecificResult != 0;
    }
}
