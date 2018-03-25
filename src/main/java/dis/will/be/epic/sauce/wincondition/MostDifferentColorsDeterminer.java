package dis.will.be.epic.sauce.wincondition;

import dis.will.be.epic.sauce.Card;
import dis.will.be.epic.sauce.Player;

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
