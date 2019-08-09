package com.cegeka.red7;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

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

        Assertions.assertThat(player.getHandCards()).containsAll(handCards);
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

        Assertions.assertThat(player.getTableauCards()).hasSize(1);
        Assertions.assertThat(player.getTableauCards().get(0)).isEqualTo(startingTableauCard);
    }
}