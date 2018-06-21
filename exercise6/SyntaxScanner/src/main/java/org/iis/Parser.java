package org.iis;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Parser {
    private String input;
    private int indexOfInput = -1;

    private List<Symbol> productionList;
    private List<Symbol> terminalList;

    private Stack<Symbol> stack = new Stack<>();

    private String[][] table =
            {
                    /*S*/
                    {"W;Z", "W;Z", "W;Z", "W;Z", "W;Z", "W;Z", "W;Z", "W;Z", "W;Z", "W;Z", null, null, null, null, null, "W;Z", null, null, null}
                    /*Z*/,
                    {"W;Z", "W;Z", "W;Z", "W;Z", "W;Z", "W;Z", "W;Z", "W;Z", "W;Z", "W;Z", null, null, null, null, null, "W;Z", null, null, null}
                    /*W*/,
                    {"Pw", "Pw", "Pw", "Pw", "Pw", "Pw", "Pw", "Pw", "Pw", "Pw", null, null, null, null, null, "Pw", null, null, null}
                    /*w*/,
                    {null, null, null, null, null, null, null, null, null, null, "OW", "OW", "OW", "OW", "OW", null, "", "", null}
                    /*P*/,
                    {"R", "R", "R", "R", "R", "R", "R", "R", "R", "R", null, null, null, null, null, "(W)", null, null, null}
                    /*R*/,
                    {"Lr", "Lr", "Lr", "Lr", "Lr", "Lr", "Lr", "Lr", "Lr", "Lr", null, null, null, null, null, null, null, null, null}
                    /*r*/,
                    {null, null, null, null, null, null, null, null, null, null, "", "", "", "", "", null, "", "", ".L"}
                    /*L*/,
                    {"Cl", "Cl", "Cl", "Cl", "Cl", "Cl", "Cl", "Cl", "Cl", "Cl", null, null, null, null, null, null, null, null, null}
                    /*l*/,
                    {"L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "", "", "", "", "", null, "", "", ""}
                    /*C*/,
                    {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", null, null, null, null, null, null, null, null, null}
                    /*O*/,
                    {null, null, null, null, null, null, null, null, null, null, "*", "/", "+", "-", "^", null, null, null, null}
            };

    public Parser(String in) {
        this.input = in;
        this.input += "$";

        initProductions();
        initTerminals();
    }

    private void initProductions() {
        productionList = new ArrayList<>();
        productionList.add(new Production("S"));
        productionList.add(new Production("Z"));
        productionList.add(new Production("W"));
        productionList.add(new Production("w"));
        productionList.add(new Production("P"));
        productionList.add(new Production("R"));
        productionList.add(new Production("r"));
        productionList.add(new Production("L"));
        productionList.add(new Production("l"));
        productionList.add(new Production("C"));
        productionList.add(new Production("O"));
    }

    private void initTerminals() {
        terminalList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            terminalList.add(new Terminal(String.valueOf(i)));
        }
        terminalList.add(new Terminal("*"));
        terminalList.add(new Terminal("/"));
        terminalList.add(new Terminal("+"));
        terminalList.add(new Terminal("-"));
        terminalList.add(new Terminal("^"));
        terminalList.add(new Terminal("("));
        terminalList.add(new Terminal(")"));
        terminalList.add(new Terminal(";"));
        terminalList.add(new Terminal("."));
    }

    private void pushRule(String rule) {
        for (int i = rule.length() - 1; i >= 0; i--) {
            char ch = rule.charAt(i);
            String str = String.valueOf(ch);
            stack.push(new Production(str));
        }
    }

    public void parse() {
        stack.push(new Terminal(input.charAt(0) + ""));
        stack.push(new Production("S"));

        Symbol token = read();
        Symbol top;

        do {
            top = stack.pop();

            if (isProduction(top)) {
                String rule = getRule(top, token);
                pushRule(rule);
            } else if (isTerminal(top)) {
                if (!top.equals(token)) {
                    throw new IllegalStateException(String.format("Token %s is illegal. Input is not accepted.", token));
                } else {
                    System.out.println("Terminal " + token + " is correct.");
                    token = read();
                }
            } else {
                throw new IllegalStateException("Something is wrong.");
            }

            if (token.equals(new Terminal("$"))) {
                break;
            }

        } while (true);

        if (token.equals(new Terminal("$"))) {
            System.out.println("Success! This input is accepted.");
        } else {
            System.out.println("Fail! This input is rejected.");
        }
    }

    private boolean isTerminal(Symbol e) {
        return terminalList.contains(e);
    }

    private boolean isProduction(Symbol e) {
        return productionList.contains(e);
    }

    private Symbol read() {
        indexOfInput++;
        return new Terminal(String.valueOf(input.charAt(indexOfInput)));
    }

    public String getRule(Symbol non, Symbol term) {
        int row = getProductionIndex(non);
        int column = getTerminatorIndex(term);
        String rule = table[row][column];
        if (rule == null) {
            throw new IllegalStateException("There is no rule for production: " + non + ", and terminal: " + term);
        }
        return rule;
    }

    private int getProductionIndex(Symbol non) {
        if (productionList.contains(non))
            return productionList.indexOf(non);
        else
            throw new IllegalStateException();
    }

    private int getTerminatorIndex(Symbol term) {
        if (terminalList.contains(term))
            return terminalList.indexOf(term);
        else
            throw new IllegalStateException();
    }
}
