package com.cegeka.red7;

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
}
