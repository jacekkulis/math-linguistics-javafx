package org.iis;

public class Terminal implements Symbol {
    private String symbol;

    public Terminal(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}
