package com.cecilia.String;

import java.util.List;

class BasicOperations {
    /**
     * (Char removal) Remove one/some particular chars from a string IN PLACE.
     * 
     * Example: string input = "student", remove 'u' and 'n', output = "stdet".
     * 
     * @param input
     */
    public static String removeChar(StringBuilder input) {
        // Two pointers. [0,slow) are kept chars, [slow, fast) are removed chars, [fast, end] are unknown chars.
        int slow = 0;
        int fast = 0;
        while (fast++ < input.length()) {
            if (input.charAt(fast) != 'u' && input.charAt(fast) != 'n') {
                input.setCharAt(slow++, input.charAt(slow)); // Assume input is mutable
            }
        }
        return input.substring(0, slow);
        // input.delete(slow, input.length);
    }

    /**
     * Remove all leading/trailing and duplicate empty spaces (only leave one empty 
     * space if duplicated spaces happen) from the input string. (Must in place)
     * 
     * Example: input = "___abc__de__" --> output = "abc_de"
     * 
     * @param input
     * @return
     */
    public static String removeCharII(String input) {
        // Two pointers. [0, slow) are kept chars, [fast, end] are unknown.
        char[] array = input.toCharArray();
        int slow = 0;
        int fast = 0;
        while (fast++ < array.length) {
            // Two cases where array[fast] needs to be kept.
            if (array[fast] == ' ') {
                if (slow > 0 && array[slow-1] != ' ') {
                    array[slow++] = ' ';
                }
            } else {
                array[slow++] = array[fast];
            }
        }
        // Post-processing
        if (slow > 0 && array[slow-1] == ' ') {
            slow--;
        }
        return new String(array, 0, slow);
    }

    /**
     * Remove duplicated and adjacent letters (leave only one letter in each duplicated
     * section) in a string.
     * 
     * Example: "aabbbbazw" --> "abazw"
     * 
     * @param input
     * @return
     */
    public static String deDup(String input) {
        char[] array = input.toCharArray();
        int fast = 0;
        int slow = 0;
        while (fast++ < array.length) {
            if (slow > 0 && array[slow-1] == array[fast]) {
                continue;
            }
            array[slow++] = array[fast];
        }
        return new String(array, 0, slow);
    }

    /**
     * Remove duplicated and adjacent letters repeatedly.
     * 
     * Example: "abbbbazw" --> "zw"
     * 
     * @param input
     * @return
     */
    public static String deDupII(String input) {
        char[] array = input.toCharArray();
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            if (slow == 0 || array[slow-1] != array[fast]) {
                array[slow++] = array[fast++];
            } else {
                fast++;
                while (fast < array.length && array[fast] == array[slow-1]) {
                    fast++;
                }
                slow--;
            }
        }
        return new String(array, 0, slow);
    }

    // Optimized solution
    public static String deDupIIOpt(String input) {
        char[] array = input.toCharArray();
        int slow = 1;
        for (int fast = 1; fast < array.length; fast++) {
            if (slow == 0 || array[slow-1] != array[fast]) {
                array[slow++] = array[fast];
            } else {
                slow--;
                while (fast+1 < array.length && array[fast] == array[fast+1]) {
                    fast++;
                }
            }
        }
        return new String(array, 0, slow);
    }

    /**
     * Substring problem: how to determine whether a string is a substring of
     * another string. 
     * 
     * Example: (text)s1 = "abcde", (patterns)s2 = "cde", return 2.
     * 
     * // Solution 1: Brute-force, time complexity O(N^2)
     * 
     * @param s1: text in which we want the find if the pattern exists
     * @param s2: pattern
     * @return The index that the pattern appears for the first time, -1 if not find.
     */
    public static int strstr(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) {
            return -1;
        }
        if (s2.length() == 0) {
            return 0;
        }

        for (int i = 0; i < s1.length(); i++) {
            int j = 0;
            while (j < s2.length() && s2.charAt(j) == s1.charAt(j)) {
                j++;
            }
            if (j == s2.length()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Substring problem: how to determine whether a string is a substring of
     * another string. 
     * 
     * Example: (text)s1 = "abcde", (patterns)s2 = "cde", return 2.
     * 
     * Solution 2: Rabin-Karp
     * 
     * Time complexity: O(N) for hashing pattern, O(N) for hashing text, O(M-N) for traversing text. 
     * O(M) for comparing (Maybe more than once, but the probability of collision is O(1))
     * Total: O(M+N)
     * 
     * @param s1: text in which we want the find if the pattern exists
     * @param s2: pattern
     * @return The index that the pattern appears for the first time, -1 if not find.
     */
    public static int strstrKC(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) {
            return -1;
        }
        if (s2.length() == 0) {
            return 0;
        }
        
        char[] pattern = s2.toCharArray();
        final int N = pattern.length;
        long patternHash = hash(pattern, 0, N-1);
        char[] text = s1.toCharArray();
        long textHash = hash(text, 0, N-1);
        if (patternHash == textHash && compare(pattern, text, 0)) {
                return 0;
        }
        for (int j = N; j < text.length; j++) {
            textHash = (textHash - (text[j-N] - 'a') * 26) * 26 + (text[j] - 'a');
            if (patternHash == textHash && compare(pattern, text, j-N+1)) {
                return j-N+1;
            }
        }
        return -1;
    }

    /**
     * Convert a char array into a numeric hash value.
     * 
     * TO-DO: Need a better designed hashing function.
     * @param array
     * @param begin
     * @param end
     * @return
     */
    private static long hash(char[] array, int begin, int end) {
        long result = 0;
        for (int j = begin; j <= end; j++) {
            result = result * 26 + (array[j] - 'a');
        }
        return result;
    }

    /**
     * Compare the chars one by one. Comparing is necessary even if the hash values are the same, 
     * because there is collision.
     * 
     * @param pattern
     * @param text
     * @param begin
     * @return
     */
    private static boolean compare(char[] pattern, char[] text, int begin) {
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] != text[begin+i]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Reverse a string.
     * 
     * Example: "I love yahoo" -> "yahoo love I" 
     * 
     * @param input: The string to be reversed.
     * @return
     */
    public static String reverseString(String input) {
        char[] str = input.toCharArray();
        reverse(str, 0, str.length-1);
        int start = 0;
        for (int i = 1; i < str.length; i++) {
            if (str[i] == ' ') {
                reverse(str, start, i-1);
                start = i+1;
            }
        }
        // post-processing: last word has not reversed in the loop
        reverse(str, start, str.length-1);
        return new String(str);
    }

    private static void reverse(char[] array, int start, int end) {
        while (start < end) {
            swap(array, start++, end--);
        }
    }

    private static void swap(char[] array, int i, int j) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    
    /**
     * Replace specific substring with another string.
     * 
     * Example: "student" ---> "stuXXt" (den --> XX)
     * 
     * Assumption: 1. only one match; 2. s2.len < s1.len
     * 
     * @param input
     * @param s1: The string to be replaced.
     * @param s2: The string that will replace s1.
     * @return
     */
    public static String replaceCharShorter(String input, String s1, String s2) {
        int index = strstrKC(input, s1);
        int delta = s1.length() - s2.length();
        char[] array = input.toCharArray();
        for (int i = index; i+delta < array.length; i++) {
            if (i-index < s2.length()) {
                array[i] = s2.charAt(i-index);
            } else {
                array[i] = array[i+delta];
            }
        }
        return new String(array, 0, array.length-delta);
    }

    // What if s2.len > s1.len, and there might be more than one match?
    public static String replaceCharLonger(String input, String s1, String s2) {
        char[] array = input.toCharArray();
        List<Integer> indices = strstr(array, s1);
        int newLength = array.length + indices.size() * (s2.length() - s1.length());
        char[] result = new char[newLength];
        int fast = array.length - 1;
        int slow = result.length - 1;
        // int count = indices.size() - 1;
        // int patternIndex = indices.get(count);
        int lastIndex = indices.size() - 1;
        while (fast >= 0) {
            // if (fast == patternIndex + s1.length()) {
            //     // array[fast] is the end of the pattern s1, then replace s1 to s2
            //     for (int i = s2.length()-1; i>= 0; i--) {
            //         result[slow--] = s2.charAt(i);
            //     }
            //     fast -= s1.length();
            //     patternIndex = indices.get(count--); // what if count < 0?
            // }
            // Above logic doesn't consider the case that lastIndex < 0
            if (lastIndex >= 0 && fast == indices.get(lastIndex) + s1.length()) {
                // array[fast] is the end of the pattern s1, then replace s1 to s2
                for (int i = s2.length()-1; i>= 0; i--) {
                    result[slow--] = s2.charAt(i);
                }
                fast -= s1.length();
                lastIndex--;
            }  
            else {
                result[slow--] = array[fast--];
            }
        }
        return result.toString();
    }

    // TO-DO: How to find all matches?
    private static List<Integer> strstr(char[] input, String pattern) {
        return null;
    }

    // A more general solution: 
    // - We don't know the size relationship between s1 and s2.
    // - There might be more than one match
    // (- Matches overlap?)
    public static String generalReplaceChar(String input, String s1, String s2) {
        // Simply compare s1 and s2 length, and call replaceShorter and replaceLonger respectively.s
        return null;
    }

    /**
     * String Shuffling - More general case: 
     *  - [C_1, C_2, C_3, ..., C_2k] --> [C_1, C_k+1, C_2, C_k+2, C_3, C_k+3, ..., C_k, C_2k]
     *  - [C_1, C_2, C_3, ..., C_2k, C-2k+1] --> [C_1, C_k+1, C_2, C_k+2, ..., C_k, C_2k, C_2k+1]
     * 
     * Similar to convert II
     * 
     * @param s
     * @return
     */
    public static String reOrderString(String s) {
        return s;
    }
}