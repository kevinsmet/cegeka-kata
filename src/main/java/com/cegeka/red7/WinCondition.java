package com.cegeka.red7;

import com.cegeka.red7.wincondition.*;

public enum WinCondition {
    HIGHEST_CARD(new HighestCardDeterminer()),
    MOST_OF_ONE_NUMBER(new MostOfOneNumberDeterminer()),
    MOST_OF_ONE_COLOR(new MostOfOneColorDeterminer()),
    MOST_EVEN(new MostEvenDeterminer()),
    MOST_DIFFERENT_COLORS(new MostDifferentColorsDeterminer()),
    MOST_IN_A_ROW(new MostInARowDeterminer()),
    MOST_BELOW_FOUR(new MostBelowFourDeterminer());

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
