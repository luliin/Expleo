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
//        //Check if expression contains alphabetical characters or invalid symbols
//        Matcher matcher = Pattern.compile("[^\\d-+*/.,\\s]").matcher(expression);
//        if (matcher.find()) {
//            throw new InputMismatchException("Only integers, floating point numbers or operators + - * / are allowed");
//        }
//
//        //Remove white spaces
//        expression = expression.replace(" ", "");
//
//        //Check if expression starts with something other than digit, -, period or comma
//        if (Pattern.compile("^[^-?[.,]?\\d]").matcher(expression).find()) {
//            throw new IllegalArgumentException("Your expression must start with a numerical parameter");
//        }
//
//        if (Pattern.compile("[^\\d]$").matcher(expression).find()) {
//            throw new IllegalArgumentException("Your expression must end with a numerical parameter");
//        }
//
//        //Create our List to store our expression
//        List<String> splitExpression = new ArrayList<>();
//
//        //Regex to capture decimal digits and floating point digits, negative or positive.
//        matcher = Pattern.compile("(-?+[.,]?+\\d+[.,]?\\d*)|([*/+]+)|([--])").matcher(expression);
//
//        while (matcher.find()) {
//            //If we find matches, we replace , with a . to be able to handle floating point arithmetics.
//            splitExpression.add(matcher.group().replace(",", "."));
//        }
//
//        //TODO: Ta bort mig!
//        System.out.println(splitExpression);


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
                    case "/", "*" -> {
                        throw new IllegalArgumentException("You must not mix operator precedences (eg. only +  - or / * allowed)");
                    }
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

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        System.out.println(calculator.evaluate(".2225+-2 *2 *34,4"));
        System.out.println(calculator.evaluate("-3-5+-500 +"));

    }
}
