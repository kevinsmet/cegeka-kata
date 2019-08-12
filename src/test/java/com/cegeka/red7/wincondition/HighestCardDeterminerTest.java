package com.cegeka.red7.wincondition;

import com.cegeka.red7.Card;
import com.cegeka.red7.CardColor;
import com.cegeka.red7.Player;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class HighestCardDeterminerTest {

    @Test
    public void determineWinner_Returns0() {
        assertThat(new HighestCardDeterminer().determineWinner(null, null)).isZero();
    }

    @Test
    public void determineHighestCardForWinCondition_returnsHighestTableauCard() {
        Card expectedCard = new Card(CardColor.GREEN, 5);
        Player player = new Player(newArrayList(), expectedCard);

        Card actual = new HighestCardDeterminer().determineHighestCardForWinCondition(player);

        assertThat(actual).isEqualTo(expectedCard);
    }
}