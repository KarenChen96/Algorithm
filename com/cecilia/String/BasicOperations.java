
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

        // Solution 1: Brute-force, time complexity O(N^2)
        for (int i = 0; i < s1.length(); i++) {
            int j = 0;
            while (j < s2.length() && s2.charAt(j) == s1.charAt(j)) {
                j++;
            }
            if (j == s2.length()) {
                return i;
            }
        }

        // Solution 2: Rabin-Karp
        // TO-DO
        return -1;
    }
    
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
}