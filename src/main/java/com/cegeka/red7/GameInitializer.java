package com.cegeka.red7;

import com.google.common.base.Preconditions;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameInitializer {

    public GameStateManager initialize(int numberOfPlayers) {
        Preconditions.checkArgument(numberOfPlayers >= 2 && numberOfPlayers <= 4, "Number of players should be between 2 and 4");

        Deck deck = new Deck();

        List<Player> players = IntStream.range(0, numberOfPlayers)
                .mapToObj(i -> new Player(deck.giveRandomCards(7), deck.giveRandomCard()))
                .collect(Collectors.toList());

        return new GameStateManager(players, deck);
    }
}
