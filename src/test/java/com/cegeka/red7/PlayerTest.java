package com.cegeka.red7;

import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    public void player_hasHandCards() throws Exception {
        List<Card> handCards = newArrayList(
                new Card(CardColor.GREEN, 1),
                new Card(CardColor.GREEN, 2),
                new Card(CardColor.GREEN, 3),
                new Card(CardColor.GREEN, 4),
                new Card(CardColor.GREEN, 5),
                new Card(CardColor.GREEN, 6),
                new Card(CardColor.GREEN, 7)
        );

        Player player = new Player(handCards, new Card(CardColor.INDIGO, 5));

        assertThat(player.getHandCards()).containsAll(handCards);
    }

    @Test
    public void player_hasATableauCard() throws Exception {
        List<Card> handCards = newArrayList(
                new Card(CardColor.GREEN, 1),
                new Card(CardColor.GREEN, 2),
                new Card(CardColor.GREEN, 3),
                new Card(CardColor.GREEN, 4),
                new Card(CardColor.GREEN, 5),
                new Card(CardColor.GREEN, 6),
                new Card(CardColor.GREEN, 7)
        );
        Card startingTableauCard = new Card(CardColor.INDIGO, 5);

        Player player = new Player(handCards, startingTableauCard);

        assertThat(player.getTableauCards()).hasSize(1);
        assertThat(player.getTableauCards().get(0)).isEqualTo(startingTableauCard);
    }

    @Test
    public void winsAgainst_givenActiveWinConditionHighestCardWinsAndPlayerHasHigherNumber_ThenWins() {
        Player winner = new Player(newArrayList(), new Card(CardColor.INDIGO, 6));
        Player loser = new Player(newArrayList(), new Card(CardColor.INDIGO, 5));

        assertThat(winner.winsAgainst(loser, WinCondition.HIGHEST_CARD)).isGreaterThan(0);
    }

    @Test
    public void winsAgainst_givenActiveWinConditionHighestCardWinsAndPlayerHasLowerNumber_ThenLoses() {
        Player winner = new Player(newArrayList(), new Card(CardColor.INDIGO, 6));
        Player loser = new Player(newArrayList(), new Card(CardColor.INDIGO, 5));

        assertThat(loser.winsAgainst(winner, WinCondition.HIGHEST_CARD)).isLessThan(0);
    }

    @Test
    public void winsAgainst_givenActiveWinConditionHighestCardWinsAndPlayerHasSameNumberButHigherColor_ThenWins() {
        Player winner = new Player(newArrayList(), new Card(CardColor.RED, 6));
        Player loser = new Player(newArrayList(), new Card(CardColor.INDIGO, 6));

        assertThat(winner.winsAgainst(loser, WinCondition.HIGHEST_CARD)).isGreaterThan(0);
    }

    @Test
    public void winsAgainst_givenActiveWinConditionHighestCardWinsAndPlayerHasSameNumberButLowerColor_ThenWins() {
        Player winner = new Player(newArrayList(), new Card(CardColor.RED, 6));
        Player loser = new Player(newArrayList(), new Card(CardColor.INDIGO, 6));

        assertThat(loser.winsAgainst(winner, WinCondition.HIGHEST_CARD)).isLessThan(0);
    }
}