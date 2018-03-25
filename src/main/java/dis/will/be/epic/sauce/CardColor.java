package dis.will.be.epic.sauce;

import static dis.will.be.epic.sauce.WinCondition.*;

public enum CardColor {
    RED(7, HIGHEST_CARD),
    ORANGE(6, MOST_OF_ONE_NUMBER),
    YELLOW(5, MOST_OF_ONE_COLOR),
    GREEN(4, MOST_EVEN),
    BLUE(3, MOST_DIFFERENT_COLORS),
    INDIGO(2, MOST_IN_A_ROW),
    VIOLET(1, MOST_BELOW_FOUR);

    private int value;
    private WinCondition highestCard;

    CardColor(int value, WinCondition winCondition) {
        this.value = value;
        this.highestCard = winCondition;
    }

    public int getValue() {
        return value;
    }

    public WinCondition getWinCondition() {
        return highestCard;
    }
}
