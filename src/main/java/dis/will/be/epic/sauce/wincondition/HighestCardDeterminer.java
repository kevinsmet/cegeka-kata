package dis.will.be.epic.sauce.wincondition;

import dis.will.be.epic.sauce.Card;
import dis.will.be.epic.sauce.Player;

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
