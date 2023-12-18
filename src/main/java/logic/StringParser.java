package logic;

import java.text.Collator;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StringParser {

    /**
     *
     * @param stringList list of int number with space between them
     *                   For example: "1 2 4 6 7 123 53"
     * @return           List of integers
     */
    public static List<Integer> parseStringToList(String stringList) {
        String[] array = stringList.split(" ");
        Integer[] intArray = new Integer[array.length];
        int i = 0;
        for (String el : array) {
            try {
                int number = Integer.parseInt(el);
                intArray[i] = number;
                i++;
            } catch (NumberFormatException e) {
                throw new NumberFormatException(e.getMessage());
            }
        }
        return Arrays.asList(intArray);
    }
}
