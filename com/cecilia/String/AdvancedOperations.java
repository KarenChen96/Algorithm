package com.cecilia.String;

import java.util.LinkedList;
import java.util.*;


class AdvancedOperations {
    /**
     * Find all permutations of input. Duplicate letters may exist in the input.
     * @param input
     * @return
     */
    public static List<String> permutation(String input) {
        List<String> result = new LinkedList<>();
        if (input == null || input.length() <= 0) {
            return result;
        }
        findPermutations(input.toCharArray(), result, 0);
        return result;
    }

    private static void findPermutations(char[] array, List<String> result, int index) {
        if (index == array.length) {
            result.add(new String(array));
            return;
        }

        Set<Character> visited = new HashSet<>();
        for (int i = index; i < array.length; i++) {
            if (visited.add(array[i])) {
                swap(array, index, i);
                findPermutations(array, result, index++);
                swap(array, index, i);
            }
        }
    }

    private static void swap(char[] array, int i, int j) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    /**
     * String encoding: "aaaabccaaaaa" --> "a4b1c2a4". Restriction:in-place
     */
    public static String encodeString(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }
        // Similar to Char Replacement, the new string may be longer or shorter
        int deltaLength = findDeltaLength(input); // This step needs to be done in-place
        char[] result = new char[input.length()+deltaLength];
        result[0] = input.charAt(0);
        int slow = 1;
        int fast = 1;
        int count = 1; 
        while(fast < input.length()) {
            if (input.charAt(fast) == input.charAt(fast-1)) {
                count++;
            } else {
                // result[slow++] = count;
                // Convert from int to char!!!
                result[slow++] = (char) ('0' + count); 
                result[slow++] = input.charAt(fast);
                count = 1;
            }
            fast++;
        }
        return new String(result);
    }

    private static int findDeltaLength(String input) {
        int deltaLength = 0;
        int count = 1;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i-1)) {
                count++;
            } else {
                deltaLength += (count-2);
                count = 1;
            }
        }
        return deltaLength;
    }
}