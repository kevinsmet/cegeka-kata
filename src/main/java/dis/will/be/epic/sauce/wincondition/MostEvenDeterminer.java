package dis.will.be.epic.sauce.wincondition;

import dis.will.be.epic.sauce.Card;
import dis.will.be.epic.sauce.Player;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MostEvenDeterminer implements WinConditionSpecificDeterminer {

    @Override
    public Integer determineWinner(Player player, Player opponent) {
        int amountOfEvenCardsPlayer = determineAmountOfEvenCards(player);
        int amountOfEvenCardsOpponent = determineAmountOfEvenCards(opponent);
        return determineScoreBasedOnBestResult(amountOfEvenCardsPlayer, amountOfEvenCardsOpponent);
    }

    private int determineAmountOfEvenCards(Player player) {
        return getEvenCardStream(player).collect(Collectors.toList()).size();
    }

    private Stream<Card> getEvenCardStream(Player player) {
        return player.getTableauCards().stream().filter(card -> card.getValue() % 2 == 0);
    }

    @Override
    public Card determineHighestCardForWinCondition(Player player) {
        return getEvenCardStream(player).max(Card::isHigherThan).orElseThrow(() -> new IllegalArgumentException("Draws are not possible"));
    }
}
