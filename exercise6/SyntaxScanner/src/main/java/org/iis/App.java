package org.iis;

public class App {

    public static void main(String[] args) {
        Parser parser = new Parser("(1.2*3)+5-(23.4+3)^3;4/2");
        parser.algorithm();

        //TODO nie bangla ()
    }

}