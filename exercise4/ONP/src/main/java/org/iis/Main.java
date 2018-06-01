package org.iis;

public class Main {
    public static void main(String[] args) {

        IRPN rpn = new RPN();
        String statement = "((2*5+1)/2)";

        rpn.compute(statement);
    }
}