package problemthree.levelthree;

import problemthree.CalculatorHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julia Wigenstedt
 * Date: 2021-04-11
 * Time: 16:03
 * Project: Expleo
 * Copyright: MIT
 */
public class Calculator {

    CalculatorHelper helper = new CalculatorHelper();


    /**
     * This method takes a string parameter and evaluates if parsing is possible.
     * If yes, parses the data and calculates with precedence rules.
     *
     * @param expression - The String expression to be calculated.
     * @return - The result of the calculation as a double.
     * @throws IllegalArgumentException - If expression is invalid
     * @throws ArithmeticException      - If expression tries to divide by zero
     */
    public double evaluate(String expression) {

        List<String> splitExpression = helper.getSplitExpression(expression);

        List<String> highPrecedenceCalculated = calculateHighPrecedence(splitExpression);

        return calculateAdditionSubtraction(highPrecedenceCalculated);

    }


    private double calculateAdditionSubtraction(List<String> splitExpression) {
        //Caches the first String of list
        double sum = helper.parseNumber(splitExpression.get(0));

        //Start the loop at index one since first element is cached in sum
        for (int i = 1; i < splitExpression.size(); i++) {
            String element = splitExpression.get(i);

            /*
            If the current element cannot parse (ie is + or -) we switch on element
             and either add or subtract the next element to sum.
             If its parsed value is a negative number, we go to default and parse the element.
             Then we check if the previous element can be parsed. If yes, we add the current value to sum.
             If not, we have already added the current value to the sum.
             */
            if (!helper.tryParse(element) || helper.parseNumber(element) < 0) {
                switch (element) {
                    case "+" -> sum += helper.parseNumber(splitExpression.get(i + 1));
                    case "-" -> sum -= helper.parseNumber(splitExpression.get(i + 1));
                    default -> {
                        double number = helper.parseNumber(element);
                        if (helper.tryParse(splitExpression.get(i - 1))) {
                            sum += number;
                        }
                    }
                }
            }
        }
        return sum;
    }


    /**
     * Takes a split list of a mathematical expression String, and calculates only the high precedence expressions.
     *
     * @param splitExpression A list of operands and operators in chronological order
     * @return A list with operands and operators of only low precedence (ie + and -)
     */
    private List<String> calculateHighPrecedence(List<String> splitExpression) {

        //Cache the first element in a sum
        double highPrecedenceSum = helper.parseNumber(splitExpression.get(0));

        //Create new list to fill with low precedence expressions
        List<String> precedenceList = new ArrayList<>();

        //Skip first index in loop since it's already added to sum, and last element in loop since it will be added by
        //the operator element previous
        for (int i = 1; i < splitExpression.size() - 1; i++) {

            String element = splitExpression.get(i);



            /*
            If the current element cannot parse (ie is +-/*) we switch on element.
            If element is * or /, we multiply or divide the previous element and the next element, adding it to sum.
            If element is + or -, we add the operator and operands unaffected by high precedence calculations.
              If its parsed value is a negative number, we go to default and parse the element, either adding
             it to the list or to the sum, depending on precedence.
             */

            if (!helper.tryParse(element) || helper.parseNumber(element) < 0) {

                switch (element) {
                    case "*" -> {
                        String next = splitExpression.get(i + 1);

                        //If sum equals zero, we cannot multiply with sum
                        if (highPrecedenceSum == 0) {
                            String previous = splitExpression.get(i - 1);
                            highPrecedenceSum += helper.parseNumber(previous) * helper.parseNumber(next);
                        } else {
                            highPrecedenceSum *= helper.parseNumber(next);
                        }
                    }
                    case "/" -> {

                        String next = splitExpression.get(i + 1);
                        double nextNumeric = helper.parseNumber(next);

                        //If next operand is 0, we throw exception
                        if (nextNumeric == 0.0 || nextNumeric == -0.0) {
                            throw new ArithmeticException("Can't divide by zero");
                        }

                        //If sum is 0, we can not divide it, instead we divide previous and next element
                        if (highPrecedenceSum == 0) {
                            String previous = splitExpression.get(i - 1);
                            highPrecedenceSum += helper.parseNumber(previous) / helper.parseNumber(next);
                        } else {
                            highPrecedenceSum /= helper.parseNumber(next);
                        }
                    }

                    case "-", "+" -> {
                        String previous = splitExpression.get(i - 1);

                        //We add sum to list if current element has index 1
                        if (i == 1) {
                            precedenceList.add(highPrecedenceSum + "");
                            //If previous element was low precedence (ie -+ or negative) we add current element to list
                            if (helper.isLowPrecedence(previous)) {
                                precedenceList.add(element);
                            }
                        }
                        //If previous element was not low precedence we only add current element to list
                        if (!helper.isLowPrecedence(splitExpression.get(i - 1))) {
                            precedenceList.add(element);
                        }

                        //If current index is the next-to-last, we also add the last element to the list
                        if (i == splitExpression.size() - 2) {
                            precedenceList.add(splitExpression.get(i + 1));

                        }
                        highPrecedenceSum = 0;
                    }
                    default -> {
                        //We will only get here when current element is a negative number

                        //If current negative number is on index 1, we add the first element (cached in variable) to list
                        if (i == 1) {
                            precedenceList.add(highPrecedenceSum + "");
                            highPrecedenceSum = 0;
                        }
                        //Cache next element in variable
                        String next = splitExpression.get(i + 1);

                        //If next is high precedence operator we parse and add current element to the sum
                        if (next.equals("*") || next.equals("/")) {
                            highPrecedenceSum += helper.parseNumber(element);

                            //Else we add current element to list
                        } else {
                            precedenceList.add(element);
                        }
                    }
                }

                //Check to see we will not go out of bounds
                if (i + 2 < splitExpression.size()) {

                    //Cache the element after next one in variable afterNext
                    String afterNext = splitExpression.get(i + 2);

                    //Check if current element is high precedence operator
                    if (element.equals("*") || element.equals("/")) {

                        //If afterNext is low precedence we add the current sum to the list, and set sum to 0.
                        if (helper.isLowPrecedence(afterNext)) {
                            precedenceList.add(highPrecedenceSum + "");
                            highPrecedenceSum = 0;
                        }

                        //Check if current element is low precedence
                    } else if (helper.isLowPrecedence(element)) {

                        //Cache next element in variable
                        String next = splitExpression.get(i + 1);

                        //If afterNext element is low precedence we add the next element (the one in between) to list
                        if (helper.isLowPrecedence(afterNext)) {
                            precedenceList.add(next);
                        }
                    }
                }

            }
            //If i is next to last element and current element is high precedence we add accumulated sum to list
            if (i == splitExpression.size() - 2) {
                if (!helper.isLowPrecedence(element)) {
                    precedenceList.add(highPrecedenceSum + "");
                }
                if (helper.tryParse(element) && helper.parseNumber(element) >= 0) {
                    String next = splitExpression.get(i + 1);
                    precedenceList.add(next);
                }
            }
        }
        return precedenceList;
    }


    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.evaluate("2-3*4"));
    }
}
