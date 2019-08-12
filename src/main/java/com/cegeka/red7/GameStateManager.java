package com.cegeka.red7;

import com.google.common.base.Preconditions;

import java.util.List;

import static com.cegeka.red7.WinCondition.HIGHEST_CARD;

public class GameStateManager {

    private List<Player> players;
    private Deck deck;
    private WinCondition activeWincondition = HIGHEST_CARD;
    private Player currentPlayer;

    public GameStateManager(List<Player> players, Deck deck) {
        this.players = players;
        this.deck = deck;
        this.calculateStartingPlayer();
    }

    private void calculateStartingPlayer() {
        this.currentPlayer = players.get((players.indexOf(getWinningPlayer()) + 1) % 4);
    }

    private Player getWinningPlayer() {
        return players.stream()
                .max((p1, p2) -> p1.winsAgainst(p2, activeWincondition))
                .orElseThrow(() -> new IllegalArgumentException("Winner has to be determinable"));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public WinCondition getActiveWincondition() {
        return activeWincondition;
    }

    public Deck getDeck() {
        return deck;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void currentPlayerGivesUp() {
        Player playerToRemove = getCurrentPlayer();
        int removedIndex = players.indexOf(playerToRemove);
        removePlayerFromPlayers(playerToRemove);
        switchCurrentPlayerAfterRemoval(removedIndex);
    }

    public void currentPlayerChangesRule(int index) {
        Card cardToPlayAsRule = getCurrentPlayer().removeCardFromHand(index);
        changeRuleTo(cardToPlayAsRule.getCardColor().getWinCondition());
        performPostPlayerMoveActions();
    }

    private void changeRuleTo(WinCondition newWinCondition) {
        this.activeWincondition = newWinCondition;
    }

    public void currentPlayerPlaysCardToHisTableau(int index) {
        getCurrentPlayer().playCardInTableau(index);
        performPostPlayerMoveActions();
    }

    private void performPostPlayerMoveActions() {
        checkIfCurrentPlayerWins();
        switchCurrentPlayer();
    }

    private void checkIfCurrentPlayerWins() {
        Preconditions.checkArgument(getCurrentPlayer().equals(getWinningPlayer()), "Player did not win after move");
    }

    private void switchCurrentPlayer() {
        determineNewCurrentPlayer(players.indexOf(currentPlayer));
        checkIfNewCurrentPlayerCanStillPlay();
    }

    private void switchCurrentPlayerAfterRemoval(int removedIndex) {
        determineNewCurrentPlayerAfterRemoval(removedIndex);
        checkIfNewCurrentPlayerCanStillPlay();
    }

    private void checkIfNewCurrentPlayerCanStillPlay() {
        if (currentPlayer.getHandCards().size() == 0 && getWinner() == null) {
            currentPlayerGivesUp();
        }
    }

    private void determineNewCurrentPlayerAfterRemoval(int removedIndex) {
        if (removedIndex == players.size()) {
            currentPlayer = players.get(0);
        } else {
            currentPlayer = players.get(removedIndex);
        }
    }

    private void determineNewCurrentPlayer(int currentPlayerIndex) {
        if (currentPlayerIndex == players.size()) {
            currentPlayer = players.get(0);
        } else {
            currentPlayer = players.get(currentPlayerIndex + 1);
        }
    }

    private void removePlayerFromPlayers(Player playerToRemove) {
        this.players.remove(playerToRemove);
    }

    public Player getWinner() {
        return getPlayers().size() == 1 ? getCurrentPlayer() : null;
    }
}
