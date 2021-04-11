package problemthree.levelone;

import java.text.DecimalFormat;
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
        DecimalFormat decimalFormat = new DecimalFormat("#.#######");
        while(true) {
            System.out.println("\nPlease enter a mathematical expression and press enter: \n");
            try {
                String userInput = in.nextLine();
                double result = calculator.evaluate(userInput);
                System.out.println("Result: " + decimalFormat.format(result));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Enter c to continue, or any other key to exit");
            String userInput = in.nextLine();
            if(!userInput.equalsIgnoreCase("c")) {
                System.exit(0);
            }
        }
    }
}
