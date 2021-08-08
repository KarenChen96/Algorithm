package com.cecilia.String;

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

    // TO-DO: Check encoding given solution, then practice Char removal


    /**
     * Given a string, returns the length of the longest substring without duplicate characters.
     * For example, the longest substrings without repeating characters for "BDEFGADE" are "BDEFGA", whose size is 6.
     * 
     * @param input 
     * @return
     */
    public static int longestSubstring(String input) {
        /** 
         * Variable size sliding window
         * Two pointers: slow is the left border of current sliding window, fast is the right border.
         * If array[fast] exists in current substring, 
         *      1. Check current substring length to temporary max length. 
         *      2. slow++ till array[fast] is excluded, remove array[slow] from the set simultaneously.
         * Else simply fast++; 
        */
        if (input == null || input.length() == 0) {
            return 0;
        }
        int slow = 0;
        int fast = 1;
        int currMax = 1; // temporary max length of satisfying substring
        char[] array = input.toCharArray();  
        Set<Character> set = new HashSet<>();
        set.add(array[0]);
        while ( fast < array.length) {
            if (!set.add(array[fast])) {
                currMax = Math.max(currMax, fast-slow);
                while (slow < fast) {
                    set.remove(array[slow++]);
                }
            }
            fast++;
        }
        return currMax;
    }

    /**
     * Find all anagrams of a substring s2 in a long string s1.
     * E.g., s2 = "aaba" (<a,2><b,1><c,1>), s1 = "zzzzcdebcaabcyywwww"
     * return {"bcaa", "caab", "aabc"}
     * 
     * @param s1
     * @param s2: substring
     * @return
     */
    public static List<String> findAnagrams(String s1, String s2) {
        List<String> result = new LinkedList<>();
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0 || s1.length() < s2.length()) {
            return result;
        }

        /**
         * Fixed-size sliding window
         * */
        
        // Construct a map for substring
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s2.toCharArray()) {
            Integer count = map.get(c);
            if (count == null) {
                count = 0;
            }
            map.put(c, ++count);
        }
        int counter = 0;
        int slow = 0;
        int fast = 0;
        while (fast < s2.length()-1) {
            if (map.containsKey(s1.charAt(fast))) {
                int number = map.get(s1.charAt(fast));
                if (number == 0) {
                    counter--;
                } else if (number == 1) {
                    counter++;
                }
                map.put(s1.charAt(fast), number-1);
            }
            fast++;
        }
        while (slow <= s1.length()-s2.length()) {
            if (counter == map.size()) {
                result.add(s1.substring(slow, fast+1));
            }
            
            // Move the sliding window one step forward
            if (map.containsKey(s1.charAt(slow))) {
                int number = map.get(s1.charAt(slow));
                if (number == 0) {
                    counter--;
                } else if (number == -1) {
                    counter++;
                }
                map.put(s1.charAt(slow), number+1);
            }
            slow++;
            
            if (map.containsKey(s1.charAt(fast))) {
                int number = map.get(s1.charAt(fast));
                if (number == 0) {
                    counter--;
                } else if (number == 1) {
                    counter++;
                }
                map.put(s1.charAt(fast), number-1);
            }
            fast++;
        }
        return result;
    }
}