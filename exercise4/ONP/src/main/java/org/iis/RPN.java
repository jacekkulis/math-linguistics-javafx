package org.iis;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;
import java.util.stream.Collectors;

public class RPN implements IRPN {

    private final String LEFT_BRACKET = "(";
    private final String RIGHT_BRACKET = ")";
    private final String POWER = "^";
    private final String MULTIPLY = "*";
    private final String DIVIDE = "/";
    private final String PLUS = "+";
    private final String MINUS = "-";


    private Stack<String> stack;
    private StringBuilder result;
    private Map<String, Integer> operators;

    public RPN() {
        this.stack = new Stack<>();
        this.operators = new HashMap<>();
        initOperatorWithPriority();
    }

    private void initOperatorWithPriority() {
        //operators with priority
        this.operators.put(LEFT_BRACKET, 0);
        this.operators.put(MINUS, 1);
        this.operators.put(PLUS, 1);
        this.operators.put(RIGHT_BRACKET, 1);
        this.operators.put(MULTIPLY, 2);
        this.operators.put(DIVIDE, 2);
        this.operators.put(POWER, 3);
    }

    @Override
    public String compute(String expression) {
        this.result = new StringBuilder();

        List<String> expr = expression.chars().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.toList());
        checkMultiplicationBehindBracket(expr);

        if (expr.contains("[")) {
            expr = checkNegativeNumbers(expr);
        }

        for (String token : expr) {
            if (token.equals(LEFT_BRACKET)) {
                stack.push(LEFT_BRACKET);
            } else if (token.equals(RIGHT_BRACKET)) {
                result.append(getUntilLeftBracket());
            } else if (operators.containsKey(token)) {
                while (!stack.empty() && operators.get(token) <= operators.get(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(token);
            } else if (isNumberOrLetter(token)) {
                result.append(token);
            }
        }

        result.append(getRestFromTheStack());

        System.out.println(result);
        return result.toString();
    }

    private List<String> checkNegativeNumbers(List<String> expr) {
        List<String> newList = new ArrayList<>();

        for (int i = 0; i < expr.size(); i++) {
            if (expr.get(i).equals("[")) {
                newList.add(expr.get(i + 1) + expr.get(i + 2));
                i += 3;
            } else {
                newList.add(expr.get(i));
            }
        }
        return newList;
    }

    private void checkMultiplicationBehindBracket(List<String> expr) {
        Map<Integer, String> map = new HashMap<>();
        int pos = 0;

        for (String c : expr) {
            if (c.equals(LEFT_BRACKET) && pos == 0) {
                //avoid
            } else if (c.equals(LEFT_BRACKET)) {
                if (expr.get(pos - 1).equals(LEFT_BRACKET)) {
                    //do nothing
                } else if (isNumberOrLetter(expr.get(pos - 1))) {
                    map.put(pos, "*");
                }
            }
            pos += 1;
        }

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            expr.add(entry.getKey(), entry.getValue());
        }
    }

    private boolean isNumberOrLetter(String token) {
        if (token.length() == 2) {
            return NumberUtils.isCreatable(token) || Character.isLetter(token.charAt(1));
        } else {
            return NumberUtils.isCreatable(token) || Character.isLetter(token.charAt(0));
        }
    }

    private String getRestFromTheStack() {
        StringBuilder elements = new StringBuilder();
        while (!stack.empty()) {
            elements.append(stack.pop());
        }
        return elements.toString();
    }

    private String getUntilLeftBracket() {
        StringBuilder result = new StringBuilder();
        while (!stack.peek().equals(LEFT_BRACKET)) {
            result.append(stack.pop());
        }
        stack.pop();
        return result.toString();
    }
}
