package dis.will.be.epic.sauce.wincondition;

import dis.will.be.epic.sauce.Card;
import dis.will.be.epic.sauce.Player;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MostBelowFourDeterminer implements WinConditionSpecificDeterminer {

    @Override
    public Integer determineWinner(Player player, Player opponent) {
        int numberOfCardsBelowFourOfPlayer = getNumberOfCardsBelowFour(player);
        int numberOfCardsBelowFourOfOpponent = getNumberOfCardsBelowFour(opponent);
        return determineScoreBasedOnBestResult(numberOfCardsBelowFourOfPlayer, numberOfCardsBelowFourOfOpponent);
    }

    private int getNumberOfCardsBelowFour(Player player) {
        return getAllCardsBelowFour(player).collect(Collectors.toList()).size();
    }

    private Stream<Card> getAllCardsBelowFour(Player player) {
        return player.getTableauCards().stream().filter(card -> card.getValue() < 4);
    }

    @Override
    public Card determineHighestCardForWinCondition(Player player) {
        return getAllCardsBelowFour(player).max(Card::isHigherThan).orElse(null);
    }
}
