package dis.will.be.epic.sauce;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CardColorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void card_ShouldHaveAColor() {
        Card card = new Card(CardColor.BLUE, 5);
        Assertions.assertThat(card.getCardColor()).isEqualTo(CardColor.BLUE);
    }

    @Test
    public void card_ShouldHaveAValue() {
        Card card = new Card(CardColor.BLUE, 5);
        Assertions.assertThat(card.getValue()).isEqualTo(5);
    }

    @Test
    public void card_GivenValueLowerThan1_ThenThrowException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Card value should be between 1 and 7");

        new Card(CardColor.BLUE, 0);
    }

    @Test
    public void card_GivenValueHigherThan7_ThenThrowException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Card value should be between 1 and 7");

        new Card(CardColor.BLUE, 8);
    }
}