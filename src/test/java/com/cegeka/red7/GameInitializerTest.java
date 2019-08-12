package com.cegeka.red7;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class GameInitializerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void gameHasTwoToFourPlayers() {
        GameStateManager gameStateManager = new GameInitializer().initialize(4);
        assertThat(gameStateManager.getPlayers()).hasSize(4);
    }

    @Test
    public void gameWithLessThanTwoPlayersShouldThrowException() {
        expectedException.expectMessage("Number of players should be between 2 and 4");

        GameStateManager gameStateManager = new GameInitializer().initialize(1);
    }

    @Test
    public void gameWithMoreThanFourPlayersShouldThrowException() {
        expectedException.expectMessage("Number of players should be between 2 and 4");

        GameStateManager gameStateManager = new GameInitializer().initialize(5);
    }

    @Test
    public void gameHandsOut7DifferentHandCardsToEachPlayer() {
        GameStateManager gameStateManager = new GameInitializer().initialize(4);

        gameStateManager.getPlayers().forEach(player -> assertThat(player.getHandCards()).hasSize(7));
        List<Card> dividedHandCards = gameStateManager.getPlayers().stream().map(Player::getHandCards).flatMap(Collection::stream).collect(Collectors.toList());
        assertThat(gameStateManager.getDeck().getCardsInDeck()).doesNotContainAnyElementsOf(dividedHandCards);
        assertThat(dividedHandCards.stream().distinct().collect(Collectors.toList())).hasSize(dividedHandCards.size());
    }

    @Test
    public void gameHandsOut1DifferentTableauCardToEachPlayer() {
        GameStateManager gameStateManager = new GameInitializer().initialize(4);

        gameStateManager.getPlayers().forEach(player -> assertThat(player.getTableauCards()).hasSize(1));
        List<Card> dividedTableauCards = gameStateManager.getPlayers().stream().map(Player::getTableauCards).flatMap(Collection::stream).collect(Collectors.toList());
        assertThat(gameStateManager.getDeck().getCardsInDeck()).doesNotContainAnyElementsOf(dividedTableauCards);
        assertThat(dividedTableauCards.stream().distinct().collect(Collectors.toList())).hasSize(dividedTableauCards.size());
    }
}