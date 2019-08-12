package com.cegeka.red7;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    @Test
    public void giveRandomCards_shouldReturnDifferentCardsAndRemoveThemFromDeck() throws Exception {
        Deck deck = new Deck();
        List<Card> randomCards = deck.giveRandomCards(3);

        assertThat(randomCards.stream().distinct().collect(Collectors.toList())).hasSize(3);
        assertThat(deck.getCardsInDeck()).doesNotContainAnyElementsOf(randomCards);
    }
}