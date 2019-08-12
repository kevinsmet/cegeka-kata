package com.cegeka.red7;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void gameHasTwoToFourPlayers() {
        Game game = new Game(4);
        assertThat(game.getPlayers()).hasSize(4);
    }

    @Test
    public void gameWithLessThanTwoPlayersShouldThrowException() {
        expectedException.expectMessage("Number of players should be between 2 and 4");

        new Game(1);
    }

    @Test
    public void gameWithMoreThanFourPlayersShouldThrowException() {
        expectedException.expectMessage("Number of players should be between 2 and 4");

        new Game(5);
    }

    @Test
    public void gameHandsOut7DifferentHandCardsToEachPlayer() {
        Game game = new Game(4);

        game.getPlayers().forEach(player -> assertThat(player.getHandCards()).hasSize(7));
        List<Card> dividedHandCards = game.getPlayers().stream().map(Player::getHandCards).flatMap(Collection::stream).collect(Collectors.toList());
        assertThat(game.getDeck().getCardsInDeck()).doesNotContainAnyElementsOf(dividedHandCards);
        assertThat(dividedHandCards.stream().distinct().collect(Collectors.toList())).hasSize(dividedHandCards.size());
    }

    @Test
    public void gameHandsOut1DifferentTableauCardToEachPlayer() {
        Game game = new Game(4);

        game.getPlayers().forEach(player -> assertThat(player.getTableauCards()).hasSize(1));
        List<Card> dividedTableauCards = game.getPlayers().stream().map(Player::getTableauCards).flatMap(Collection::stream).collect(Collectors.toList());
        assertThat(game.getDeck().getCardsInDeck()).doesNotContainAnyElementsOf(dividedTableauCards);
        assertThat(dividedTableauCards.stream().distinct().collect(Collectors.toList())).hasSize(dividedTableauCards.size());
    }
}