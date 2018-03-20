package dis.will.be.epic.sauce;

import com.google.common.base.Preconditions;

public class Card {

    private final CardColor cardColor;
    private final int value;

    public Card(CardColor cardColor, int value) {
        Preconditions.checkArgument(assertValue(value), "Card value should be between 1 and 7");
        this.cardColor = cardColor;
        this.value = value;
    }

    private boolean assertValue(int value) {
        return value >= 1 && value <= 7;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public int getValue() {
        return value;
    }
}