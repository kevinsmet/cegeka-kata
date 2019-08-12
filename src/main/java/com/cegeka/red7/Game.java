package com.cegeka.red7;

import com.google.common.base.Preconditions;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {


    private final Deck deck;
    private final GameStateManager gamestateManager;
    private List<Player> players;

    public Game(int numberOfPlayers) {
        Preconditions.checkArgument(numberOfPlayers >= 2 && numberOfPlayers <= 4, "Number of players should be between 2 and 4");

        this.deck = new Deck();

        players = IntStream.range(0, numberOfPlayers)
                .mapToObj(i -> new Player(deck.giveRandomCards(7), deck.giveRandomCard()))
                .collect(Collectors.toList());

        this.gamestateManager = new GameStateManager(players);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }
}
