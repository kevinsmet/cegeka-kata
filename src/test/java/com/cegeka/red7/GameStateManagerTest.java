package com.cegeka.red7;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class GameStateManagerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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

    @Test
    public void currentPlayerPlaysCardToHisTableau_shouldRemoveCardFromHandAndAddToTableau() {
        Card cardToBePlacedInTableau = new Card(CardColor.RED, 7);
        Card cardAlreadyInTableau = new Card(CardColor.VIOLET, 5);
        Player currentPlayer = playerWithTableauAndHandCards(cardAlreadyInTableau,
                newArrayList(new Card(CardColor.GREEN, 6), new Card(CardColor.GREEN, 5), cardToBePlacedInTableau));
        Player playerAfterCurrentPlayer = playerWithTableauCard(new Card(CardColor.VIOLET, 4));
        GameStateManager gameStateManager = new GameStateManager(newArrayList(
                currentPlayer,
                playerAfterCurrentPlayer,
                playerWithTableauCard(new Card(CardColor.VIOLET, 3)),
                playerWithTableauCard(new Card(CardColor.INDIGO, 5))
        ), new Deck());

        gameStateManager.currentPlayerPlaysCardToHisTableau(2);

        assertPlayerPlayedIntoTableauSuccesfully(cardToBePlacedInTableau, cardAlreadyInTableau, currentPlayer, playerAfterCurrentPlayer, gameStateManager);
    }


    private void assertPlayerPlayedIntoTableauSuccesfully(Card cardToBePlacedInTableau, Card cardAlreadyInTableau, Player currentPlayer, Player playerAfterCurrentPlayer, GameStateManager gameStateManager) {
        Assertions.assertThat(currentPlayer.getHandCards()).doesNotContain(cardToBePlacedInTableau);
        Assertions.assertThat(currentPlayer.getTableauCards()).contains(cardToBePlacedInTableau, cardAlreadyInTableau);
        Assertions.assertThat(gameStateManager.getCurrentPlayer()).isEqualTo(playerAfterCurrentPlayer);
    }

    @Test
    public void currentPlayerPlaysCardToHisTableau_givenDoesNotWinAfterMove_ThenThrowException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Player did not win after move");

        Player currentPlayer = playerWithTableauAndHandCards(new Card(CardColor.VIOLET, 5),
                newArrayList(new Card(CardColor.GREEN, 1), new Card(CardColor.GREEN, 5), new Card(CardColor.RED, 7)));
        GameStateManager gameStateManager = new GameStateManager(newArrayList(
                currentPlayer,
                playerWithTableauCard(new Card(CardColor.VIOLET, 4)),
                playerWithTableauCard(new Card(CardColor.VIOLET, 3)),
                playerWithTableauCard(new Card(CardColor.INDIGO, 5))
        ), new Deck());

        gameStateManager.currentPlayerPlaysCardToHisTableau(0);

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

    @Test
    public void currentPlayerChangesRule_shouldRemoveCardFromHandAReplaceCurrentRule() {
        Card cardToPlayAsRule = new Card(CardColor.VIOLET, 1);
        Player currentPlayer = playerWithTableauAndHandCards(new Card(CardColor.RED, 3),
                newArrayList(cardToPlayAsRule, new Card(CardColor.GREEN, 5), new Card(CardColor.RED, 7)));
        Player nextPlayer = playerWithTableauCard(new Card(CardColor.VIOLET, 4));
        GameStateManager gameStateManager = new GameStateManager(newArrayList(
                playerWithTableauCard(new Card(CardColor.VIOLET, 3)),
                playerWithTableauCard(new Card(CardColor.INDIGO, 5)),
                currentPlayer,
                nextPlayer)
                , new Deck());

        gameStateManager.currentPlayerChangesRule(0);

        assertPlayerChangedRuleSuccesFully(cardToPlayAsRule, currentPlayer, nextPlayer, gameStateManager);
    }

    private void assertPlayerChangedRuleSuccesFully(Card cardToPlayAsRule, Player currentPlayer, Player nextPlayer, GameStateManager gameStateManager) {
        Assertions.assertThat(currentPlayer.getHandCards()).doesNotContain(cardToPlayAsRule);
        Assertions.assertThat(gameStateManager.getCurrentPlayer()).isEqualTo(nextPlayer);
        Assertions.assertThat(gameStateManager.getActiveWincondition()).isEqualTo(WinCondition.MOST_BELOW_FOUR);
    }

    @Test
    public void currentPlayerChangesRule_givenDoesNotWinAfterAction_thenThrowException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Player did not win after move");

        Card cardToPlayAsRule = new Card(CardColor.RED, 1);
        Player currentPlayer = playerWithTableauAndHandCards(new Card(CardColor.RED, 3),
                newArrayList(cardToPlayAsRule, new Card(CardColor.GREEN, 5), new Card(CardColor.RED, 7)));
        GameStateManager gameStateManager = new GameStateManager(newArrayList(
                playerWithTableauCard(new Card(CardColor.VIOLET, 3)),
                playerWithTableauCard(new Card(CardColor.INDIGO, 5)),
                currentPlayer,
                playerWithTableauCard(new Card(CardColor.VIOLET, 4)))
                , new Deck());

        gameStateManager.currentPlayerChangesRule(0);
    }

    @Test
    public void currentPlayerPlaysCardIntoTableauAndChangesRule_shouldDoEverythingTheSeparateActionsDid() {
        Card cardToPlayIntoTableau = new Card(CardColor.YELLOW, 2);
        Card cardToPlayAsRule = new Card(CardColor.VIOLET, 1);
        Card cardAlreadyInTableau = new Card(CardColor.GREEN, 3);
        Player currentPlayer = playerWithTableauAndHandCards(cardAlreadyInTableau,
                newArrayList(cardToPlayAsRule, new Card(CardColor.GREEN, 5), cardToPlayIntoTableau));
        Player nextPlayer = playerWithTableauCard(new Card(CardColor.VIOLET, 4));
        GameStateManager gameStateManager = new GameStateManager(newArrayList(
                playerWithTableauCard(new Card(CardColor.RED, 3)),
                playerWithTableauCard(new Card(CardColor.INDIGO, 5)),
                currentPlayer,
                nextPlayer)
                , new Deck());

        gameStateManager.currentPlayerPlaysCardIntoTableauAndChangesRule(2, 0);

        assertPlayerPlayedIntoTableauSuccesfully(cardToPlayIntoTableau, cardAlreadyInTableau, currentPlayer, nextPlayer, gameStateManager);
        assertPlayerChangedRuleSuccesFully(cardToPlayAsRule, currentPlayer, nextPlayer, gameStateManager);
    }

    @Test
    public void currentPlayerPlaysCardIntoTableauAndChangesRule_givenDoesNotWinAfterAction_thenThrowException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Player did not win after move");

        Player currentPlayer = playerWithTableauAndHandCards(new Card(CardColor.GREEN, 3),
                newArrayList(new Card(CardColor.RED, 1), new Card(CardColor.GREEN, 5), new Card(CardColor.YELLOW, 2)));
        GameStateManager gameStateManager = new GameStateManager(newArrayList(
                playerWithTableauCard(new Card(CardColor.RED, 3)),
                playerWithTableauCard(new Card(CardColor.INDIGO, 5)),
                currentPlayer,
                playerWithTableauCard(new Card(CardColor.VIOLET, 4)))
                , new Deck());

        gameStateManager.currentPlayerPlaysCardIntoTableauAndChangesRule(2, 0);
    }

    @Test
    public void currentPlayerPlaysCardIntoTableauAndChangesRule_givenNewCardInTableauIsSameAsNewCardForRule_thenThrowException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Tried to play the same card in tableau and as the new rule");

        Player currentPlayer = playerWithTableauAndHandCards(new Card(CardColor.GREEN, 3),
                newArrayList(new Card(CardColor.RED, 1), new Card(CardColor.GREEN, 5), new Card(CardColor.VIOLET, 2)));
        GameStateManager gameStateManager = new GameStateManager(newArrayList(
                playerWithTableauCard(new Card(CardColor.RED, 3)),
                playerWithTableauCard(new Card(CardColor.INDIGO, 5)),
                currentPlayer,
                playerWithTableauCard(new Card(CardColor.VIOLET, 4)))
                , new Deck());

        gameStateManager.currentPlayerPlaysCardIntoTableauAndChangesRule(2, 2);
    }

    @Test
    public void givenPlayerHasNoCardsInHandAtStartOfTurnAndIsNotTheLastPlayerRemaining_thenShouldGetRemovedFromGame() {
        Player currentPlayer = playerWithTableauAndHandCards(new Card(CardColor.GREEN, 3),
                newArrayList(new Card(CardColor.RED, 7), new Card(CardColor.GREEN, 5), new Card(CardColor.VIOLET, 2)));
        Player playerToBeRemoved = playerWithTableauCardAndEmptyHand(new Card(CardColor.VIOLET, 4));
        Player otherPlayerToBeRemoved = playerWithTableauCardAndEmptyHand(new Card(CardColor.RED, 3));
        Player otherPlayerStillInTheGame = playerWithTableauAndHandCards(new Card(CardColor.GREEN, 6),
                newArrayList(new Card(CardColor.RED, 1)));
        GameStateManager gameStateManager = new GameStateManager(newArrayList(
                otherPlayerToBeRemoved,
                otherPlayerStillInTheGame,
                currentPlayer,
                playerToBeRemoved)
                , new Deck());

        gameStateManager.currentPlayerPlaysCardToHisTableau(0);

        assertThat(gameStateManager.getPlayers()).doesNotContain(playerToBeRemoved, otherPlayerToBeRemoved);
        assertThat(gameStateManager.getPlayers()).containsOnly(currentPlayer, otherPlayerStillInTheGame);
        assertThat(gameStateManager.getCurrentPlayer()).isEqualTo(otherPlayerStillInTheGame);
    }

    @Test
    public void givenPlayerHasNoCardsInHandAtStartOfTurnAndLastPlayerRemaining_thenShouldNotRemovedFromGameAndBeDeclaredWinner() {
        Player currentPlayer = playerWithTableauAndHandCards(new Card(CardColor.GREEN, 3),
                newArrayList(new Card(CardColor.RED, 7)));
        GameStateManager gameStateManager = new GameStateManager(newArrayList(
                playerWithTableauCardAndEmptyHand(new Card(CardColor.RED, 3)),
                playerWithTableauCardAndEmptyHand(new Card(CardColor.GREEN, 6)),
                currentPlayer,
                playerWithTableauCardAndEmptyHand(new Card(CardColor.VIOLET, 4)))
                , new Deck());

        gameStateManager.currentPlayerPlaysCardToHisTableau(0);

        assertThat(gameStateManager.getPlayers()).hasSize(1);
        assertThat(gameStateManager.getPlayers()).containsOnly(currentPlayer);
        assertThat(gameStateManager.getWinner()).isEqualTo(currentPlayer);
    }

    @Test
    public void getWinner_shouldReturnNullIfMoreThan1PlayerStillPlaying() {
        Player currentPlayer = playerWithTableauAndHandCards(new Card(CardColor.GREEN, 3),
                newArrayList(new Card(CardColor.RED, 7), new Card(CardColor.VIOLET, 2)));
        GameStateManager gameStateManager = new GameStateManager(newArrayList(
                playerWithTableauCardAndEmptyHand(new Card(CardColor.RED, 3)),
                playerWithTableauCardAndEmptyHand(new Card(CardColor.GREEN, 6)),
                currentPlayer,
                playerWithTableauAndHandCards(new Card(CardColor.VIOLET, 4), newArrayList(new Card(CardColor.RED, 6), new Card(CardColor.BLUE, 2))))
                , new Deck());

        gameStateManager.currentPlayerPlaysCardToHisTableau(0);

        assertThat(gameStateManager.getWinner()).isNull();
    }


}