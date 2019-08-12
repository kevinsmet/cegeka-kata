package com.cegeka.red7;

import java.util.List;

import static com.cegeka.red7.WinCondition.HIGHEST_CARD;

public class GameStateManager {

    private List<Player> players;
    private WinCondition activeWincondition = HIGHEST_CARD;

    public GameStateManager(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public WinCondition getActiveWincondition() {
        return activeWincondition;
    }
}
