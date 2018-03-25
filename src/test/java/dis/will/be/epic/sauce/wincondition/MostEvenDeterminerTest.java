package dis.will.be.epic.sauce.wincondition;

import dis.will.be.epic.sauce.Card;
import dis.will.be.epic.sauce.CardColor;
import dis.will.be.epic.sauce.Player;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.*;

public class MostEvenDeterminerTest {

    @Test
    public void determineWinner_givenMoreEven_then1(){
        Player winner = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 2),
                new Card(CardColor.BLUE, 4),
                new Card(CardColor.BLUE, 5),
                new Card(CardColor.BLUE, 6)));

        Player loser = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.YELLOW, 2),
                new Card(CardColor.YELLOW, 5),
                new Card(CardColor.YELLOW, 6)));

        Assertions.assertThat(new MostEvenDeterminer().determineWinner(winner, loser)).isEqualTo(1);
    }

    @Test
    public void determineWinner_givenLessEven_thenMinus1(){
        Player winner = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 2),
                new Card(CardColor.BLUE, 4),
                new Card(CardColor.BLUE, 5),
                new Card(CardColor.BLUE, 6)));

        Player loser = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.YELLOW, 2),
                new Card(CardColor.YELLOW, 5),
                new Card(CardColor.YELLOW, 6)));

        Assertions.assertThat(new MostEvenDeterminer().determineWinner(loser, winner)).isEqualTo(-1);
    }

    @Test
    public void determineWinner_givenSameAmountOfEven_then0(){
        Player winner = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 4),
                new Card(CardColor.BLUE, 6)));

        Player loser = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.YELLOW, 2),
                new Card(CardColor.YELLOW, 5),
                new Card(CardColor.YELLOW, 6)));

        Assertions.assertThat(new MostEvenDeterminer().determineWinner(loser, winner)).isEqualTo(0);
    }

    @Test
    public void determineHighestCardForWinCondition(){
        Card expected = new Card(CardColor.YELLOW, 4);
        Player player = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.YELLOW, 2),
                expected,
                new Card(CardColor.YELLOW, 7)));

        Assertions.assertThat(new MostEvenDeterminer().determineHighestCardForWinCondition(player)).isEqualTo(expected);
    }
}