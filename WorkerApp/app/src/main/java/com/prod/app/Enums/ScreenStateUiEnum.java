package com.prod.app.Enums;

public enum ScreenStateUiEnum {
    POS_HOME(0),
    POS_ACCOUNT(1),
    POS_MESSAGES(2),
    POS_CART(3),
    POS_LOGOUT(5);

    private int numVal;

    ScreenStateUiEnum(int i) {
        this.numVal = i;
    }

    public int getValue() {
        return numVal;
    }
}
