package org.iis;

public class Production implements Symbol {
    private String symbol;

    public Production(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}
