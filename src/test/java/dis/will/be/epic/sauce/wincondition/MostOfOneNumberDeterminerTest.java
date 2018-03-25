package dis.will.be.epic.sauce.wincondition;

import dis.will.be.epic.sauce.Card;
import dis.will.be.epic.sauce.CardColor;
import dis.will.be.epic.sauce.Player;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static dis.will.be.epic.sauce.wincondition.PlayerTestUtil.aPlayerWithCardsInTableau;
import static dis.will.be.epic.sauce.wincondition.PlayerTestUtil.playAllCardsInToTableau;
import static org.assertj.core.api.Assertions.assertThat;

public class MostOfOneNumberDeterminerTest {

    @Test
    public void determineWinner_givenMoreOfOneNumber_ThenReturn1(){
        Player winner = aPlayerWithCardsInTableau(newArrayList(new Card(CardColor.RED, 5), new Card(CardColor.RED, 3), new Card(CardColor.BLUE, 5), new Card(CardColor.ORANGE, 5)));
        Player loser = aPlayerWithCardsInTableau(newArrayList(new Card(CardColor.RED, 6), new Card(CardColor.RED, 7), new Card(CardColor.ORANGE, 6)));

        assertThat(new MostOfOneNumberDeterminer().determineWinner(winner, loser)).isEqualTo(1);
    }

    @Test
    public void determineWinner_givenLessOfOneNumber_ThenReturnMinus1(){
        Player winner = aPlayerWithCardsInTableau(newArrayList(new Card(CardColor.RED, 4), new Card(CardColor.RED, 3), new Card(CardColor.BLUE, 4), new Card(CardColor.ORANGE, 4)));
        Player loser = aPlayerWithCardsInTableau(newArrayList(new Card(CardColor.RED, 6), new Card(CardColor.RED, 7), new Card(CardColor.ORANGE, 6)));

        assertThat(new MostOfOneNumberDeterminer().determineWinner(loser, winner)).isEqualTo(-1);
    }

    @Test
    public void determineWinner_givenSameOfOneNumber_ThenReturn0(){
        Player winner = aPlayerWithCardsInTableau(newArrayList(new Card(CardColor.RED, 2), new Card(CardColor.RED, 3), new Card(CardColor.ORANGE, 2)));
        Player loser = aPlayerWithCardsInTableau(newArrayList(new Card(CardColor.RED, 6), new Card(CardColor.RED, 7), new Card(CardColor.ORANGE, 6)));

        assertThat(new MostOfOneNumberDeterminer().determineWinner(loser, winner)).isEqualTo(0);
    }

    @Test
    public void determineHighestCardForWinCondition(){
        Card expected = new Card(CardColor.GREEN, 6);
        Player player = aPlayerWithCardsInTableau(newArrayList(new Card(CardColor.RED, 4), new Card(CardColor.BLUE, 6), expected, new Card(CardColor.RED, 7),
                new Card(CardColor.ORANGE, 4)));

        assertThat(new MostOfOneNumberDeterminer().determineHighestCardForWinCondition(player)).isEqualTo(expected);
    }
}