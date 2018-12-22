package org.iis;


import org.iis.parser.Parser;

public class App {

    public static void main(String[] args) {
        Parser parser = new Parser("(1.2*3)+5-(23.4+3)^3;.8/13;");
        parser.parse();
    }

}