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
     * Compress String II: Given a string, replace adjacent, repeated characters with the character followed 
     * by the number of repeated occurrence.
     * 
     * String encoding: "aaaabccaaaaa" --> "a4b1c2a4". Restriction:in-place
     * 
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

    /**
     * Given solution of Compress String problem. My solution is wrong.
     * Caveat: The occurence might be larger then 9. Consider seriously how to place digits!
     * */
    public static String compressString(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }
        return encode(input.toCharArray());
    }

    private static String encode(char[] array) {
        // Fisrt traversal: 
        //  - Find each letters' occurence, put it in original array if occurence > 1;
        //  - calculate the new length
        // Second traversal: 
        //  - copy letters & digits from original array to result array, add one's when necessary
        int newLength = 0;
        int fast = 0;
        int slow = 0; // [0,slow] are new array
        while (fast < array.length) {
            int begin = fast;
            while (array[fast] == array[begin]) {
                fast++;
            }
            array[slow++] = array[begin];
            if (fast-begin == 1) {
                newLength += 2;
            } else {
                int length = copyDigits(array, slow, fast-begin);
                newLength += (length+1);
            }
        }

        char[] result = new char[newLength];
        // Re-write to make code more elegant: travese from end to begin
        result[0] = array[0];
        slow = 1;
        fast = 1;
        while (fast < array.length) {
            if (Character.isDigit(array[fast])) {
                while (fast < array.length && Character.isDigit(array[fast])) {
                    result[slow++] = array[fast++];
                }
            } else {
                result[slow++] = '1';
            }
            result[slow++] = array[fast++];
        }
        // post-process: last one may be ignored.
        if (!Character.isDigit(array[fast-1])) {
            result[slow] = '1';
        }
        return result.toString();
    }

    /**
     * Convert integer count into characters and copy the char type digits into array.
     * 
     * @param array: The original char array
     * @param index: The position where copy starts from
     * @param count: the number of occurence
     * @return The number of digits
     */
    private static int copyDigits(char[] array, int index, int count) {
        int length = 0;
        for (int i = count; i > 0; i = i/10) {
            index++;
            length++;
        }

        for (int i = count; i > 0; i = i/10) {
            array[index--] = (char) ('0' + i%10);
        }
        return length;
    }


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

        // Try to make the code more concise
        int counter = 0;
        int slow = -1;
        int fast = -1;
        while (fast < s1.length()) {
            if (counter == map.size()) {
                result.add(s1.substring(slow, fast+1));
            }
            // [slow+1, fast] is current window
            if (fast >= s2.length()-1) {
                slow++; // s1[slow] is excluded now
                if (map.containsKey(s1.charAt(slow))) {
                    int number = map.get(s1.charAt(slow));
                    if (number == 0) {
                        counter--;
                    } else if (number == -1) {
                        counter++;
                    }
                    map.put(s1.charAt(slow), number+1);
                }
            }
            fast++; // new element is to be included
            if (map.containsKey(s1.charAt(fast))) {
                int number = map.get(s1.charAt(fast));
                if (number == 0) {
                    counter--;
                } else if (number == 1) {
                    counter++;
                }
                map.put(s1.charAt(fast), number-1);
            }
         }
        return result;
    }

    /**
     * Given a 0-1 array, you can flip at most k 0's to 1's. Please find the longest 
     * sub-array that consists of all 1's.
     * 
     * Example: input = "011111011011011011111110", k = 4 
     *          return ""
     */
    public static int[] longestAllOnes(String input, int k) {
        /**
         * (Variable-length) Sliding window problem. The window can contain at most k 0s.
         */
        if (input == null || input.length() == 0) {
            return null;
        }

        int[] resultIndices = new int[2];
        int currMax = 0;
        int slow = -1;
        for (int fast = 0; fast < input.length(); fast++) {
            if (input.charAt(fast) == 0) {
                if (k > 0) {
                    // Flip
                    k--;
                } else {
                    // k == 0, no more flip is allowed for current sliding window.
                    // move slow till a 0 is exclueded from the window.
                    for (;slow+1 <= fast; slow++) {
                        if (input.charAt(slow+1) == 0) {
                            slow += 1; // slow points to a O now.
                            break;
                        }
                    }
                }
            }
            if (fast-slow > currMax) {
                currMax = fast-slow;
                resultIndices[0] = slow+1;
                resultIndices[1] = fast;
            }
        }
        // Post-process is not needed here, because currMax is always updated when possible necessary.
        return resultIndices;
    }
}