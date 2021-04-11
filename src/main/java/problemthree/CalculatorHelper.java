package problemthree;

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


}
