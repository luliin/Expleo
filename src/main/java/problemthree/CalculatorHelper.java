package problemthree;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Julia Wigenstedt
 * Date: 2021-04-11
 * Time: 11:20
 * Project: Expleo
 * Copyright: MIT
 */
public class CalculatorHelper {

    public double parseNumber(String input) {
        try {
            return Double.parseDouble(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    public boolean tryParse(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getSplitExpression(String expression) {

        Matcher matcher = Pattern.compile("[^\\d-+*/.,\\s]").matcher(expression);
        if (matcher.find()) {
            throw new InputMismatchException("Only integers, floating point numbers or operators + - * / are allowed");
        }

        //Remove white spaces
        expression = expression.replace(" ", "");

        //Check if expression starts with something other than digit, -, period or comma
        if (Pattern.compile("^[^-?[.,]?\\d]").matcher(expression).find()) {
            throw new IllegalArgumentException("Your expression must start with a numerical parameter");
        }

        if (Pattern.compile("[^\\d]$").matcher(expression).find()) {
            throw new IllegalArgumentException("Your expression must end with a numerical parameter");
        }

        //Create our List to store our expression
        List<String> splitExpression = new ArrayList<>();

        //Regex to capture decimal digits and floating point digits, negative or positive.
        matcher = Pattern.compile("(-?+[.,]?+\\d+[.,]?\\d*)|([*/+]+)|([--])").matcher(expression);

        while (matcher.find()) {
            //If we find matches, we replace , with a . to be able to handle floating point arithmetics.
            splitExpression.add(matcher.group().replace(",", "."));
        }

        String firstString = splitExpression.get(0);

        if (!tryParse(firstString)) {
            throw new IllegalArgumentException("Invalid input.");
        }

        if (splitExpression.size() < 2) {
            throw new IllegalArgumentException("Your expression must contain at least two numbers and one operator");
        }

        return splitExpression;

    }


}
