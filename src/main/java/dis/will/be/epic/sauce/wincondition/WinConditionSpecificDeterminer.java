package dis.will.be.epic.sauce.wincondition;

import dis.will.be.epic.sauce.Card;
import dis.will.be.epic.sauce.Player;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface WinConditionSpecificDeterminer {

    Integer determineWinner(Player player, Player opponent);

    Card determineHighestCardForWinCondition(Player player);

    default Stream<Integer> getTableauCardsAsNumberStream(Player player) {
        return player.getTableauCards().stream()
                .map(Card::getValue);
    }

    default int determineScoreBasedOnBestResult(int bestResultPlayer, int bestResultOpponent) {
        if (bestResultOpponent == bestResultPlayer) {
            return 0;
        }
        return bestResultPlayer > bestResultOpponent ? 1 : -1;
    }

    default Card highestCardUsingFilter(List<Card> tableauCards, Predicate<Card> filter){
        return tableauCards.stream()
                .filter(filter)
                .max(Card::isHigherThan)
                .orElseThrow(() -> new IllegalArgumentException("Draws are not possible"));
    }

    default Integer getMostOccurancesOfOneNumberForPlayer(List<Integer> tableauCardsAsNumber) {
        return tableauCardsAsNumber.stream()
                .map(number -> Collections.frequency(tableauCardsAsNumber, number))
                .max(Integer::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("List was empty"));
    }

    default int determineMaxOccuringNumber(List<Integer> tableauCardsAsNumber) {
        int maxOccuringNumber = 0;
        int maxOccurances = 0;
        for (Integer number : tableauCardsAsNumber) {
            int frequency = Collections.frequency(tableauCardsAsNumber, number);
            if (frequency > maxOccurances || (frequency == maxOccurances && number > maxOccuringNumber)) {
                maxOccurances = frequency;
                maxOccuringNumber = number;
            }
        }
        return maxOccuringNumber;
    }

}
