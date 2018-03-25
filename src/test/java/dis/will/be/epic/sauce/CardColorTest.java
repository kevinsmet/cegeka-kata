package dis.will.be.epic.sauce;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class CardColorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void card_ShouldHaveAColor() {
        Card card = new Card(CardColor.BLUE, 5);
        assertThat(card.getCardColor()).isEqualTo(CardColor.BLUE);
    }

    @Test
    public void card_ShouldHaveAValue() {
        Card card = new Card(CardColor.BLUE, 5);
        assertThat(card.getValue()).isEqualTo(5);
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

    @Test
    public void equals_trueIfBothColorAndNumber_TheSame() {
        assertThat(new Card(CardColor.INDIGO, 5)).isEqualTo(new Card(CardColor.INDIGO,5));
    }

    @Test
    public void equals_falseIfColorNotTheSame() {
        assertThat(new Card(CardColor.RED, 5)).isNotEqualTo(new Card(CardColor.INDIGO,5));
    }

    @Test
    public void equals_falseIfNumberNotTheSame() {
        assertThat(new Card(CardColor.INDIGO, 6)).isNotEqualTo(new Card(CardColor.INDIGO,5));
    }

    @Test
    public void isHigherThan_givenCardNumberHigher_Then1(){
        assertThat(new Card(CardColor.VIOLET, 2).isHigherThan(new Card(CardColor.VIOLET, 1))).isEqualTo(1);
    }

    @Test
    public void isHigherThan_givenCardNumberLower_ThenMinus1(){
        assertThat(new Card(CardColor.VIOLET, 1).isHigherThan(new Card(CardColor.VIOLET, 2))).isEqualTo(-1);
    }

    @Test
    public void isHigherThan_givenCardNumberEqualAndColorHigher_Then1(){
        assertThat(new Card(CardColor.RED, 2).isHigherThan(new Card(CardColor.VIOLET, 2))).isEqualTo(1);
    }

    @Test
    public void isHigherThan_givenCardNumberEqualAndColorLower_ThenMinus1(){
        assertThat(new Card(CardColor.VIOLET, 1).isHigherThan(new Card(CardColor.RED, 1))).isEqualTo(-1);
    }
}