package dis.will.be.epic.sauce.wincondition;

import dis.will.be.epic.sauce.Card;
import dis.will.be.epic.sauce.CardColor;
import dis.will.be.epic.sauce.Player;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;

public class MostDifferentColorsDeterminerTest {

    @Test
    public void determineWinner_givenMoreColors_then1() {
        Player winner = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 1),
                new Card(CardColor.BLUE, 2),
                new Card(CardColor.RED, 2),
                new Card(CardColor.YELLOW, 2)
        ));
        Player loser = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 1),
                new Card(CardColor.BLUE, 2),
                new Card(CardColor.YELLOW, 2)
        ));

        Assertions.assertThat(new MostDifferentColorsDeterminer().determineWinner(winner, loser)).isEqualTo(1);
    }

    @Test
    public void determineWinner_givenLessColors_thenMinus1() {
        Player winner = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 1),
                new Card(CardColor.BLUE, 2),
                new Card(CardColor.RED, 2),
                new Card(CardColor.YELLOW, 2)
        ));
        Player loser = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 1),
                new Card(CardColor.BLUE, 2),
                new Card(CardColor.YELLOW, 2)
        ));

        Assertions.assertThat(new MostDifferentColorsDeterminer().determineWinner(loser, winner)).isEqualTo(-1);
    }

    @Test
    public void determineWinner_givenSameAmountOfColors_then0() {
        Player winner = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 1),
                new Card(CardColor.BLUE, 2),
                new Card(CardColor.RED, 2)
        ));
        Player loser = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 1),
                new Card(CardColor.BLUE, 2),
                new Card(CardColor.YELLOW, 2)
        ));

        Assertions.assertThat(new MostDifferentColorsDeterminer().determineWinner(loser, winner)).isEqualTo(0);
    }

    @Test
    public void determineHighestCardForWinCondition(){
        Card expected = new Card(CardColor.RED, 3);
        Player winner = PlayerTestUtil.aPlayerWithCardsInTableau(newArrayList(
                new Card(CardColor.BLUE, 1),
                new Card(CardColor.BLUE, 2),
                new Card(CardColor.RED, 2),
                new Card(CardColor.INDIGO, 3),
                expected
        ));

        Assertions.assertThat(new MostDifferentColorsDeterminer().determineHighestCardForWinCondition(winner)).isEqualTo(expected);
    }
}