package com.tabcorp.betting;

public enum BetType {
    WIN("W"),
    PLACE("P");

    private final String value;

    BetType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
