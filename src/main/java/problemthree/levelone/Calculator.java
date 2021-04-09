package problemthree.levelone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Julia Wigenstedt
 * Date: 2021-04-09
 * Time: 23:12
 * Project: Expleo
 * Copyright: MIT
 */
public class Calculator {

    private final List<String> operators = new ArrayList<>();

    public Calculator() {
        operators.add("+");
        operators.add("-");
        operators.add("/");
        operators.add("*");
    }

    public double evaluate(String input) {
        String operator = "";
        for (int i = 0; i < operators.size(); i++) {
            String string = operators.get(i);
            if (input.substring(1).contains(string)) {
                operator = string;
            }
        }
        return calculate(input, operator);
    }

    private double calculate(String input, String operator) {
        return switch (operator) {
            case "+" -> add(input);
            case "-" -> subtract(input);
            case "*" -> multiply(input);
            case "/" -> divide(input);
            default -> throw new IllegalArgumentException("You must use one of the following operators:\n" +
                    "+ - * / ");
        };
    }

    private double add(String input) {
        String[] operands = getOperands(input, "\\+");
        if (operands != null) {
            return parseNumber(operands[0]) + parseNumber(operands[1]);
        }
        throw new IllegalArgumentException("You must use exactly two numerical parameters");
    }

    private double subtract(String input) {
        String[] operands = getOperands(input, "-");
        if (operands != null) {
            return parseNumber(operands[0]) - parseNumber(operands[1]);
        }
        throw new IllegalArgumentException("You must use exactly two numerical parameters");

    }

    private double multiply(String input) {
        String[] operands = getOperands(input, "\\*");
        if (operands != null) {
            return parseNumber(operands[0]) * parseNumber(operands[1]);
        }
        throw new IllegalArgumentException("You must use exactly two numerical parameters");

    }

    private double divide(String input) throws ArithmeticException {
        String[] operands = getOperands(input, "/");
        if (operands != null) {
            if (parseNumber(operands[1]) != 0) {
                return parseNumber(operands[0]) / parseNumber(operands[1]);
            } else throw new ArithmeticException("Can't divide by zero");
        }
        throw new IllegalArgumentException("You must use exactly two numerical parameters");
    }

    public double parseNumber(String input) {
        try {
            return Double.parseDouble(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    public String[] getOperands(String input, String regex) {
        String[] operands = input.split(regex);
        for (int i = 0; i < operands.length; i++) {
            operands[i] = operands[i].trim();
        }

        return operands.length == 2 ? operands : null;
    }
}
