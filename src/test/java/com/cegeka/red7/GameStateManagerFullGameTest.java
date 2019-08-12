package com.cegeka.red7;

import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class GameStateManagerFullGameTest {

    @Test
    public void playFullGame() {
        Player startingPlayer = new Player(newArrayList(
                new Card(CardColor.VIOLET, 6),
                new Card(CardColor.YELLOW, 4),
                new Card(CardColor.GREEN, 4),
                new Card(CardColor.BLUE, 1),
                new Card(CardColor.VIOLET, 2),
                new Card(CardColor.INDIGO, 7),
                new Card(CardColor.INDIGO, 1)
                ),
                new Card(CardColor.BLUE, 5));
        Player expectedWinner = new Player(newArrayList(
                new Card(CardColor.RED, 6),
                new Card(CardColor.RED, 1),
                new Card(CardColor.YELLOW, 2),
                new Card(CardColor.VIOLET, 4),
                //wont use these cards in this test
                new Card(CardColor.RED, 2),
                new Card(CardColor.RED, 3),
                new Card(CardColor.RED, 4)

                ),
                new Card(CardColor.ORANGE, 5));

        GameStateManager gameStateManager = new GameStateManager(newArrayList(startingPlayer, expectedWinner), new Deck());

        //play Violet 6 to get highest card
        gameStateManager.currentPlayerPlaysCardToHisTableau(0);
        //play Red 6 to get highest card
        gameStateManager.currentPlayerPlaysCardToHisTableau(0);
        //play blue 4 + green 4 as rule to get most even
        gameStateManager.currentPlayerPlaysCardIntoTableauAndChangesRule(0,1);
        //play red 1 as rule to get highest card
        gameStateManager.currentPlayerChangesRule(0);
        //play blue 1 as rule to get most diff colors
        gameStateManager.currentPlayerChangesRule(0);
        //play yellow 2 as rule to get most same colors
        gameStateManager.currentPlayerChangesRule(0);
        //play violet 2 and indigo 7 as rule to get most in a row
        gameStateManager.currentPlayerPlaysCardIntoTableauAndChangesRule(0,1);
        //play violet 4 to get most in a row
        gameStateManager.currentPlayerPlaysCardToHisTableau(0);
        //has to give up violet 4 to get most in a row
        gameStateManager.currentPlayerGivesUp();

        assertThat(gameStateManager.getWinner()).isEqualTo(expectedWinner);
    }
}