package com.cegeka.red7;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.google.common.collect.Lists.newArrayList;

public class Deck {
    private final List<Card> cardsInDeck = initializeCardsInDeck();

    private List<Card> initializeCardsInDeck() {
        List<Card> cardsInDeck = newArrayList();
        cardsInDeck.addAll(generateCardsFor(CardColor.VIOLET));
        cardsInDeck.addAll(generateCardsFor(CardColor.INDIGO));
        cardsInDeck.addAll(generateCardsFor(CardColor.BLUE));
        cardsInDeck.addAll(generateCardsFor(CardColor.GREEN));
        cardsInDeck.addAll(generateCardsFor(CardColor.YELLOW));
        cardsInDeck.addAll(generateCardsFor(CardColor.ORANGE));
        cardsInDeck.addAll(generateCardsFor(CardColor.RED));
        return cardsInDeck;
    }

    private List<Card> generateCardsFor(CardColor cardColor) {
        return IntStream.range(1, 8).mapToObj(i -> new Card(cardColor, i)).collect(Collectors.toList());
    }

    public Card getCard() {
        return cardsInDeck.get(0);
    }
}
