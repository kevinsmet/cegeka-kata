package com.cegeka.red7.wincondition;

import com.cegeka.red7.Card;
import com.cegeka.red7.Player;

import java.util.List;

public class PlayerTestUtil {

    public static void playAllCardsInToTableau(Player player) {
        while(player.getHandCards().size() > 0){
            player.playCardInTableau(0);
        }
    }

    public static Player aPlayerWithCardsInTableau(List<Card> cards){
        Player player = new Player(cards.subList(1, cards.size()), cards.get(0));
        playAllCardsInToTableau(player);
        return player;
    }
}
