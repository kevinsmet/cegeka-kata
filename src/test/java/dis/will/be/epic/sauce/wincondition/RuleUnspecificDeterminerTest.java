package dis.will.be.epic.sauce.wincondition;

import dis.will.be.epic.sauce.Card;
import dis.will.be.epic.sauce.CardColor;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

public class RuleUnspecificDeterminerTest {

    @Test
    public void givenCardPlayerNull_thenMinus1(){
        Assertions.assertThat(new RuleUnspecificDeterminer().determineWinner(null, new Card(CardColor.RED, 2))).isEqualTo(-1);
    }

    @Test
    public void givenCardOpponentNull_then1(){
        Assertions.assertThat(new RuleUnspecificDeterminer().determineWinner(new Card(CardColor.RED, 2), null)).isEqualTo(1);
    }

    @Test
    public void givenBothCardsNull_thenMinus1(){
        Assertions.assertThat(new RuleUnspecificDeterminer().determineWinner(null, null)).isEqualTo(-1);
    }

}