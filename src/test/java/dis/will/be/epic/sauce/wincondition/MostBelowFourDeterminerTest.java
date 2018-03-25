package dis.will.be.epic.sauce.wincondition;

import dis.will.be.epic.sauce.Card;
import dis.will.be.epic.sauce.CardColor;
import dis.will.be.epic.sauce.Player;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static dis.will.be.epic.sauce.wincondition.PlayerTestUtil.aPlayerWithCardsInTableau;
import static org.assertj.core.api.Assertions.assertThat;

public class MostBelowFourDeterminerTest {

    @Test
    public void determineWinner_givenHasMoreBelowFour_Then1(){
        Player winner = aPlayerWithCardsInTableau(newArrayList(new Card(CardColor.RED, 2), new Card(CardColor.RED, 3), new Card(CardColor.BLUE, 5), new Card(CardColor.ORANGE, 5)));
        Player loser = aPlayerWithCardsInTableau(newArrayList(new Card(CardColor.RED, 3), new Card(CardColor.RED, 7), new Card(CardColor.ORANGE, 6)));

        assertThat(new MostBelowFourDeterminer().determineWinner(winner, loser)).isEqualTo(1);
    }

    @Test
    public void determineWinner_givenHasLessBelowFour_ThenMinus1(){
        Player winner = aPlayerWithCardsInTableau(newArrayList(new Card(CardColor.RED, 2), new Card(CardColor.RED, 3), new Card(CardColor.BLUE, 5), new Card(CardColor.ORANGE, 5)));
        Player loser = aPlayerWithCardsInTableau(newArrayList(new Card(CardColor.RED, 3), new Card(CardColor.RED, 7), new Card(CardColor.ORANGE, 6)));

        assertThat(new MostBelowFourDeterminer().determineWinner(loser, winner)).isEqualTo(-1);
    }

    @Test
    public void determineWinner_givenHasSameAmountBelowFour_Then0(){
        Player winner = aPlayerWithCardsInTableau(newArrayList(new Card(CardColor.RED, 3), new Card(CardColor.BLUE, 5), new Card(CardColor.ORANGE, 5)));
        Player loser = aPlayerWithCardsInTableau(newArrayList(new Card(CardColor.RED, 1), new Card(CardColor.BLUE, 6), new Card(CardColor.ORANGE, 6)));

        assertThat(new MostBelowFourDeterminer().determineWinner(loser, winner)).isEqualTo(0);
    }

    @Test
    public void determineHighestCardForWinCondition_shouldReturnHighestCardBelow4(){
        Card expected = new Card(CardColor.ORANGE, 3);
        Player player = aPlayerWithCardsInTableau(newArrayList(new Card(CardColor.RED, 7), new Card(CardColor.BLUE, 3), new Card(CardColor.BLUE, 2), expected));

        assertThat(new MostBelowFourDeterminer().determineHighestCardForWinCondition(player)).isEqualTo(expected);
    }

    @Test
    public void determineHighestCardForWinCondition_givenNoCardsBelow4_shouldReturnNull(){
        Player player = aPlayerWithCardsInTableau(newArrayList(new Card(CardColor.RED, 7)));

        assertThat(new MostBelowFourDeterminer().determineHighestCardForWinCondition(player)).isNull();
    }

}