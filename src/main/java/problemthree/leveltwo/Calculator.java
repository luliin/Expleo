package problemthree.leveltwo;


import problemthree.CalculatorHelper;

import java.util.List;

/**
 * Created by Julia Wigenstedt
 * Date: 2021-04-11
 * Time: 10:19
 * Project: Expleo
 * Copyright: MIT
 */
public class Calculator {

    private final CalculatorHelper helper = new CalculatorHelper();

    public double evaluate(String expression) {

        List<String> splitExpression = helper.getSplitExpression(expression);

        String firstString = splitExpression.get(0);

        if (!helper.tryParse(firstString)) {
            throw new IllegalArgumentException("Invalid input.");
        }

        if (splitExpression.size() < 2) {
            throw new IllegalArgumentException("Your expression must contain at least two numbers and one operator");
        }

        String precedenceCheck = splitExpression.get(1);
        if (helper.tryParse(precedenceCheck) || precedenceCheck.equals("+")) {
            return calculateAdditionSubtraction(splitExpression);
        } else {
            return calculateMultiplicationDivision(splitExpression);
        }
    }

    private double calculateMultiplicationDivision(List<String> splitExpression) {
        double sum = helper.parseNumber(splitExpression.get(0));
        for (int i = 1; i < splitExpression.size(); i++) {
            String element = splitExpression.get(i);
            if(!helper.tryParse(element) || helper.parseNumber(element)<0) {
                switch (element) {
                    case "+", "-" -> throw new IllegalArgumentException("You must not mix operator precedences (eg. only +  - or / * allowed)");

                    case "/" -> {
                        double nextOperand = helper.parseNumber(splitExpression.get(i+1));
                        if(nextOperand==0.0 || nextOperand==-0.0) {
                            throw new ArithmeticException("Can't divide by zero!");
                        }
                        sum /= nextOperand;
                    }

                    case "*" -> sum *= helper.parseNumber(splitExpression.get(i+1));

                    default -> {
                        if(helper.tryParse(splitExpression.get(i-1))) {
                            throw new IllegalArgumentException("You must not mix operator precedences (eg. only +  - or / * allowed)");
                        }
                    }
                }
            }
        }
        return sum;
    }

    private double calculateAdditionSubtraction(List<String> splitExpression) {
        double sum = helper.parseNumber(splitExpression.get(0));
        for (int i = 1; i < splitExpression.size(); i++) {
            String element = splitExpression.get(i);
            if(!helper.tryParse(element) || helper.parseNumber(element)<0) {
                switch (element) {
                    case "+" -> sum += helper.parseNumber(splitExpression.get(i+1));
                    case "-" -> sum -= helper.parseNumber(splitExpression.get(i+1));
                    case "/", "*" -> throw new IllegalArgumentException("You must not mix operator precedences (eg. only +  - or / * allowed)");
                    default -> {
                        double number = helper.parseNumber(element);
                        if(helper.tryParse(splitExpression.get(i-1))) {
                            sum += number;
                        }
                    }
                }
            }
        }
        return sum;
    }
}
