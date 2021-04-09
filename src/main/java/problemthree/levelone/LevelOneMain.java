package problemthree.levelone;

import java.util.Scanner;

/**
 * Created by Julia Wigenstedt
 * Date: 2021-04-10
 * Time: 00:14
 * Project: Expleo
 * Copyright: MIT
 */
public class LevelOneMain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Calculator calculator = new Calculator();
        while(true) {
            System.out.println("\nPlease enter a mathematical expression and press enter: \n");
            try {
                String userInput = in.nextLine();
                double result = calculator.evaluate(userInput);
                System.out.println(result);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
