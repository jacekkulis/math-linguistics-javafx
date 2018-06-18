package org.iis;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Parser {
    //input
    private String input = "";
    private int indexOfInput = -1;

    private List<Production> productionList;
    private List<Terminal> terminalList;

    //Stack
    private Stack<String> stack = new Stack<>();
    //Table of rules
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


    String[] nonTerminals = {"S", "Z", "W", "w", "P", "R", "r", "L", "l", "C", "O"};
    String[] terminals = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "*", "/", "+", "-", "^", "(", ")", ";", "."};


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
        for (int i = 0; i < 9; i++) {
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
            stack.push(str);
        }
    }

    //algorithm
    public void algorithm() {
        stack.push(input.charAt(0) + "");
        stack.push("S");
        //Read one token from input

        String token = read();
        String top = null;


        do {
            top = stack.pop();
            System.out.println("TOP: " + top);
            //if top is non-terminal
            if (isNonTerminal(top)) {
                String rule = this.getRule(top, token);
                System.out.println("RULE: " + rule);
                this.pushRule(rule);
            } else if (isTerminal(top)) {
                if (!top.equals(token)) {
                    error("this token is not corrent , By Grammer rule . Token : (" + token + ")");
                } else {
                    System.out.println("Matching: Terminal :( " + token + " )");
                    token = read();
                }
            } else {
                error("Never Happens , Because top : ( " + top + " )");
            }


            if (token.equals("$")) {
                break;
            }

        } while (true);//out of the loop when $

        //accept
        if (token.equals("$")) {
            System.out.println("Input is Accepted by LL1");
        } else {
            System.out.println("Input is not Accepted by LL1");
        }
    }

    private boolean isTerminal(String s) {
        for (int i = 0; i < this.terminals.length; i++) {
            if (s.equals(this.terminals[i])) {
                return true;
            }

        }
        return false;
    }

    private boolean isNonTerminal(String s) {
        for (int i = 0; i < this.nonTerminals.length; i++) {
            if (s.equals(this.nonTerminals[i])) {
                return true;
            }
        }
        return false;
    }

    private String read() {
        indexOfInput++;
        char ch = this.input.charAt(indexOfInput);
        String str = String.valueOf(ch);

        return str;
    }

    private void error(String message) {
        System.out.println(message);
        throw new IllegalStateException(message);
    }

    public String getRule(String non, String term) {

        int row = getnonTermIndex(non);
        int column = getTermIndex(term);
        String rule = this.table[row][column];
        if (rule == null) {
            error("There is no Rule by this , Non-Terminal(" + non + ") ,Terminal(" + term + ") ");
        }
        return rule;
    }

    private int getnonTermIndex(String non) {
        for (int i = 0; i < this.nonTerminals.length; i++) {
            if (non.equals(this.nonTerminals[i])) {
                return i;
            }
        }
        error(non + " is not Production");
        return -1;
    }

    private int getTermIndex(String term) {
        for (int i = 0; i < this.terminals.length; i++) {
            if (term.equals(this.terminals[i])) {
                return i;
            }
        }
        error(term + " is not Terminal");
        return -1;
    }
}
