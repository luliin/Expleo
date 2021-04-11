import java.util.HashMap;
import java.util.Map;

/**
 * Created by Julia Wigenstedt
 * Date: 2021-04-09
 * Time: 21:34
 * Project: Expleo
 * Copyright: MIT
 */
public class ProblemOne {


    /**
     * This method checks if two String objects are anagrams of each other.
     * @param first - The first String object to compare
     * @param second- The second String object to compare
     * @return - true if they are anagrams, otherwise false
     */
    public boolean detectAnagram(String first, String second) {

        if(first.equalsIgnoreCase(second) || first.length()!=second.length()) return false;

        Map<Character, Integer> firstWordLetterQuantity = mapStringCharacters(first.toLowerCase());
        Map<Character, Integer> secondWordLetterQuantity = mapStringCharacters(second.toLowerCase());

        return firstWordLetterQuantity.equals(secondWordLetterQuantity);
    }

    /**
     * Returns a HashMap containing the characters of a string mapped to the number of times they occur.
     * @param string a String object
     * @return the HashMap created
     */
    public Map<Character, Integer> mapStringCharacters(String string) {

        Map<Character, Integer> letterQuantity = new HashMap<>();
        for (int i = 0; i <string.length() ; i++) {
            char character = string.charAt(i);
            if(!letterQuantity.containsKey(character)) {
                letterQuantity.put(character, 1);
            } else {
                letterQuantity.replace(character, letterQuantity.get(character)+1);
            }
        }
        return letterQuantity;
    }
}
