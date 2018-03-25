package dis.will.be.epic.sauce;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    @Test
    public void gameHasFourPlayers() throws Exception {
        Game game = new Game();
        assertThat(game.getPlayers()).hasSize(4);
    }

    @Test
    public void gameHandsOut7DifferentHandCardsToEachPlayer() throws Exception {
        Game game = new Game();

        game.getPlayers().forEach(player -> assertThat(player.getHandCards()).hasSize(7));
        List<Card> dividedHandCards = game.getPlayers().stream().map(Player::getHandCards).flatMap(Collection::stream).collect(Collectors.toList());
        assertThat(game.getDeck().getCardsInDeck()).doesNotContainAnyElementsOf(dividedHandCards);
        assertThat(dividedHandCards.stream().distinct().collect(Collectors.toList())).hasSize(dividedHandCards.size());
    }

    @Test
    public void gameHandsOut1DifferentTableauCardToEachPlayer() throws Exception {
        Game game = new Game();

        game.getPlayers().forEach(player -> assertThat(player.getTableauCards()).hasSize(1));
        List<Card> dividedTableauCards = game.getPlayers().stream().map(Player::getTableauCards).flatMap(Collection::stream).collect(Collectors.toList());
        assertThat(game.getDeck().getCardsInDeck()).doesNotContainAnyElementsOf(dividedTableauCards);
        assertThat(dividedTableauCards.stream().distinct().collect(Collectors.toList())).hasSize(dividedTableauCards.size());
    }
}