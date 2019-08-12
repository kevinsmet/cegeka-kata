package com.cegeka.red7.wincondition;

import com.cegeka.red7.Card;
import com.cegeka.red7.CardColor;
import com.cegeka.red7.Player;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;

public class MostOfOneColorDeterminerTest {

    @Test
    public void determineWinner_givenMoreOfSameColor_then1() {
        Player winner = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 1),
                new Card(CardColor.ORANGE, 1),
                new Card(CardColor.YELLOW, 1),
                new Card(CardColor.BLUE, 2)
        ));
        Player loser = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 1),
                new Card(CardColor.ORANGE, 1),
                new Card(CardColor.YELLOW, 1)
        ));

        Assertions.assertThat(new MostOfOneColorDeterminer().determineWinner(winner, loser)).isEqualTo(1);
    }

    @Test
    public void determineWinner_givenLessOfSameColor_thenMinus1() {
        Player winner = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 1),
                new Card(CardColor.ORANGE, 1),
                new Card(CardColor.YELLOW, 1),
                new Card(CardColor.BLUE, 2)
        ));
        Player loser = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 1),
                new Card(CardColor.ORANGE, 1),
                new Card(CardColor.YELLOW, 1)
        ));

        Assertions.assertThat(new MostOfOneColorDeterminer().determineWinner(loser, winner)).isEqualTo(-1);
    }

    @Test
    public void determineWinner_givenSameAmountOfSameColor_then0() {
        Player winner = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 1),
                new Card(CardColor.BLUE, 2)
        ));
        Player loser = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 1),
                new Card(CardColor.BLUE, 1)
        ));

        Assertions.assertThat(new MostOfOneColorDeterminer().determineWinner(loser, winner)).isEqualTo(0);
    }

    @Test
    public void determineHighestCardForWinCondition() {
        Card expected = new Card(CardColor.BLUE, 3);
        Player winner = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 1),
                new Card(CardColor.BLUE, 2),
                new Card(CardColor.GREEN, 3),
                expected,
                new Card(CardColor.RED, 5),
                new Card(CardColor.RED, 6)
        ));

        Assertions.assertThat(new MostOfOneColorDeterminer().determineHighestCardForWinCondition(winner)).isEqualTo(expected);

    }
}
