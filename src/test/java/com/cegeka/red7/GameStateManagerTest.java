package com.cegeka.red7;

import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class GameStateManagerTest {
    @Test
    public void defaultActiveWinConditionIsHighestCardWins() {
        GameStateManager gameStateManager = new GameStateManager(newArrayList(
                playerWithTableauCard(new Card(CardColor.VIOLET, 5)),
                playerWithTableauCard(new Card(CardColor.VIOLET, 4)),
                playerWithTableauCard(new Card(CardColor.VIOLET, 3)),
                playerWithTableauCard(new Card(CardColor.INDIGO, 5))
        ), new Deck());

        assertThat(gameStateManager.getActiveWincondition()).isEqualTo(WinCondition.HIGHEST_CARD);
    }

    private Player playerWithTableauCard(Card startingTableauCard) {
        return new Player(newArrayList(new Card(CardColor.VIOLET, 1)), startingTableauCard);
    }
}