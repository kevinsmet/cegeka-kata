package com.cegeka.red7;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void getCurrentPlayer_givenGameHasStarted_ThenPlayerAfterPlayerWithHighestTableauCardStarts() {
        Player expectedStartingPlayer = playerWithTableauCard(new Card(CardColor.VIOLET, 5));
        GameStateManager gameStateManager = new GameStateManager(newArrayList(
                expectedStartingPlayer,
                playerWithTableauCard(new Card(CardColor.VIOLET, 4)),
                playerWithTableauCard(new Card(CardColor.VIOLET, 3)),
                playerWithTableauCard(new Card(CardColor.INDIGO, 5))
        ), new Deck());

        Assertions.assertThat(gameStateManager.getCurrentPlayer()).isEqualTo(expectedStartingPlayer);
    }

    @Test
    public void currentPlayerGivesUp() {
        Player currentPlayer = playerWithTableauAndHandCards(new Card(CardColor.VIOLET, 5),
                newArrayList(new Card(CardColor.GREEN, 1), new Card(CardColor.GREEN, 5), new Card(CardColor.RED, 7)));
        Player nextPlayer = playerWithTableauCard(new Card(CardColor.VIOLET, 4));
        GameStateManager gameStateManager = new GameStateManager(newArrayList(
                playerWithTableauCard(new Card(CardColor.VIOLET, 3)),
                playerWithTableauCard(new Card(CardColor.INDIGO, 5)),
                currentPlayer,
                nextPlayer
        ), new Deck());

        gameStateManager.currentPlayerGivesUp();

        Assertions.assertThat(gameStateManager.getCurrentPlayer()).isEqualTo(nextPlayer);
        Assertions.assertThat(gameStateManager.getPlayers()).doesNotContain(currentPlayer);
    }

    @Test
    public void givenCurrentPlayerIsOutOfHandCardsThenAutomaticallyGivesUp() {
        Player currentPlayer = playerWithTableauAndHandCards(new Card(CardColor.VIOLET, 5),
                newArrayList(new Card(CardColor.GREEN, 1), new Card(CardColor.GREEN, 5), new Card(CardColor.RED, 7)));
        Player expectedCurrentPlayer = playerWithTableauCardAndEmptyHand(new Card(CardColor.RED, 7));
        GameStateManager gameStateManager = new GameStateManager(newArrayList(
                playerWithTableauCardAndEmptyHand(new Card(CardColor.VIOLET, 2)),
                expectedCurrentPlayer,
                currentPlayer,
                playerWithTableauCardAndEmptyHand(new Card(CardColor.VIOLET, 1))
        ), new Deck());

        gameStateManager.currentPlayerGivesUp();

        Assertions.assertThat(gameStateManager.getCurrentPlayer()).isEqualTo(expectedCurrentPlayer);
        Assertions.assertThat(gameStateManager.getPlayers()).containsOnly(expectedCurrentPlayer);
        Assertions.assertThat(gameStateManager.getWinner()).isEqualTo(expectedCurrentPlayer);
    }

    private Player playerWithTableauCard(Card startingTableauCard) {
        return new Player(newArrayList(new Card(CardColor.VIOLET, 1)), startingTableauCard);
    }

    private Player playerWithTableauAndHandCards(Card startingTableauCard, List<Card> handCards) {
        return new Player(handCards, startingTableauCard);
    }

    private Player playerWithTableauCardAndEmptyHand(Card startingTableauCard) {
        return new Player(newArrayList(), startingTableauCard);
    }
}