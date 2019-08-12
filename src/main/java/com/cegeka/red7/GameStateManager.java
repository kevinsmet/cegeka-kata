package com.cegeka.red7;

import java.util.List;

import static com.cegeka.red7.WinCondition.HIGHEST_CARD;

public class GameStateManager {

    private List<Player> players;
    private Deck deck;
    private WinCondition activeWincondition = HIGHEST_CARD;

    public GameStateManager(List<Player> players, Deck deck) {
        this.players = players;
        this.deck = deck;
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
}
