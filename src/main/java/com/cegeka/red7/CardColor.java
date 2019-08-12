package com.cegeka.red7;

public enum CardColor {
    RED(7),
    ORANGE(6),
    YELLOW(5),
    GREEN(4),
    BLUE(3),
    INDIGO(2),
    VIOLET(1);

    private int value;

    CardColor(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
