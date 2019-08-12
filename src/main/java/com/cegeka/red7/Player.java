package com.cegeka.red7;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Player {

    private List<Card> handCards;
    private List<Card> tableauCards;

    public Player(List<Card> handCards, Card startingTableauCard) {
        this.handCards = handCards;
        this.tableauCards = newArrayList(startingTableauCard);
    }

    public List<Card> getHandCards() {
        return handCards;
    }

    public List<Card> getTableauCards() {
        return tableauCards;
    }

    public int winsAgainst(Player other, WinCondition activeWincondition) {
        return activeWincondition.determineWinner(this, other);
    }
}
