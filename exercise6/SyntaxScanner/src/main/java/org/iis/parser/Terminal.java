package org.iis.parser;

public class Terminal implements Symbol {
    private String symbol;

    Terminal(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;

        Symbol t = (Symbol) o;
        return symbol.equals(t.getSymbol());
    }

    @Override
    public int hashCode() {
        return symbol.hashCode();
    }

    @Override
    public String toString() {
        return symbol;
    }
}
