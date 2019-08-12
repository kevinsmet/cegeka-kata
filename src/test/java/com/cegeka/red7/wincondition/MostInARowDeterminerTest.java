package com.cegeka.red7.wincondition;


import com.cegeka.red7.Card;
import com.cegeka.red7.CardColor;
import com.cegeka.red7.Player;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;

public class MostInARowDeterminerTest {

    @Test
    public void determineWinner_givenMoreInARow_Then1() {
        Player winner = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 1),
                new Card(CardColor.BLUE, 2),
                new Card(CardColor.BLUE, 6),
                new Card(CardColor.BLUE, 5),
                new Card(CardColor.BLUE, 7)
        ));

        Player loser = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.YELLOW, 1),
                new Card(CardColor.YELLOW, 2),
                new Card(CardColor.YELLOW, 6),
                new Card(CardColor.YELLOW, 7)
        ));

        Assertions.assertThat(new MostInARowDeterminer().determineWinner(winner, loser)).isEqualTo(1);
    }

    @Test
    public void determineWinner_givenLessInARow_ThenMinus1() {
        Player winner = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 1),
                new Card(CardColor.BLUE, 2),
                new Card(CardColor.BLUE, 6),
                new Card(CardColor.BLUE, 5),
                new Card(CardColor.BLUE, 7)
        ));

        Player loser = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.YELLOW, 1),
                new Card(CardColor.YELLOW, 2),
                new Card(CardColor.YELLOW, 6),
                new Card(CardColor.YELLOW, 7)
        ));

        Assertions.assertThat(new MostInARowDeterminer().determineWinner(loser, winner)).isEqualTo(-1);
    }

    @Test
    public void determineWinner_givenSameInARow_Then0() {
        Player winner = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 1),
                new Card(CardColor.BLUE, 2)
        ));

        Player loser = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.YELLOW, 6),
                new Card(CardColor.YELLOW, 7)
        ));

        Assertions.assertThat(new MostInARowDeterminer().determineWinner(loser, winner)).isEqualTo(0);
    }

    @Test
    public void determineHighestCardForWinCondition() {
        Card expected = new Card(CardColor.GREEN, 5);
        Player winner = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 1),
                new Card(CardColor.BLUE, 2),
                expected,
                new Card(CardColor.BLUE, 4),
                new Card(CardColor.BLUE, 5),
                new Card(CardColor.BLUE, 7)
        ));

        Assertions.assertThat(new MostInARowDeterminer().determineHighestCardForWinCondition(winner)).isEqualTo(expected);

    }

}