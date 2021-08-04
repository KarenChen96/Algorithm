package com.cecilia.Graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

class DFS {
    /**
     * Print all subsets of a set S = {a, b, c}
     * 
     * @param set: Assume there is no duplicate characters in the original set
     */
    public List<String> findSubset(String set) {
        // i-th level represents if put i-th element in current result candidate
        // two states for each node in each level: put or not
        // Time complexity: O(2^N*N). StringBuilder operations take O(N).
        // Space complexity: O(N)
        List<String> result = new LinkedList<>();
        findSubsetHlper(set.toCharArray(), 0, new StringBuilder(), result);
        return result;
    }

    private void findSubsetHlper(char[] set, int index, StringBuilder sb, List<String> result) {
        if (index >= set.length) {
            result.add(sb.toString());
            return;
        }
        // Put this element into the subset
        sb.append(set[index]); // eat
        findSubsetHlper(set, index + 1, sb, result);
        // Restore modification to the candidate
        sb.deleteCharAt(sb.length() - 1); // spit
        // Not put this element into the subset
        findSubsetHlper(set, index + 1, sb, result);
    }

    public List<String> findValidParentheses(int n) {
        // Each level represents what to be put in i-th position, n*2 levels.
        // At most 2 states for each node
        // Time complexity: O(2^(2*N) * N), but should be btter if consider pruning
        // because of parentheses validity.
        List<String> result = new LinkedList<>();
        findValidParentheseshelper(n, n, 0, result, new StringBuilder());
        return result;
    }

    // Hint: for permutation problem, using char[] is better than StringBuilder
    // regarding time complexity.
    // Only for permutation problem because the results have the same length.
    private void findValidParentheseshelper(int left, int right, int index, List<String> result, StringBuilder sb) {
        if (right > 0) {
            result.add(sb.toString());
            return;
        }

        if (left > 0) {
            sb.append("(");
            findValidParentheseshelper(left - 1, right, index + 1, result, sb);
            sb.deleteCharAt(sb.length() - 1); // can we use index here?
        }

        if (right > left) {
            sb.append(")");
            findValidParentheseshelper(left, right - 1, index + 1, result, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<int[]> findCoinCombination(int n, int[] coins) {
        // i-th level represents the number of i-th coin to be used, n levels
        // remaining/coins[i] + 1 states

        List<int[]> result = new LinkedList<>();
        int[] counts = new int[coins.length];
        // How to sort a int[] in reverse natural order?
        // Sort coins in advance to have a smaller recursive tree
        // Integer[] test = new Integer[coins.length];
        // Arrays.sort(test, new Comparator<>() {
        // @Override
        // public int compare(Integer i1, Integer i2) {
        // return 0;
        // }
        // }); // Collections.reverseOrder()
        findCoinCombinationHelper(n, coins, 0, counts, result);
        return result;
    }

    private void findCoinCombinationHelper(int remain, int[] coins, int index, int[] counts, List<int[]> result) {
        if (remain <= 0 || index == counts.length) {
            if (remain == 0) {
                result.add(counts);
            }
            return;
        }

        for (int i = 0; i <= remain / coins[index]; i++) {
            counts[index] = i;
            findCoinCombinationHelper(remain - coins[index] * i, coins, index + 1, counts, result);
        }
    }

    public List<String> findStringPermutation(String s) {
        // i-th level represents what letter to be put in i-th position in the
        // permutation, s.length levels.
        // s.length - i states in i-th level nodes
        // Time complexity: O(N * N!)
        List<String> result = new LinkedList<>();
        findStringPermutationHelper(s.toCharArray(), 0, result);
        return result;
    }

    private void findStringPermutationHelper(char[] s, int index, List<String> result) {
        if (index == s.length) {
            result.add(s.toString());
            return;
        }

        for (int i = index; i < s.length; i++) {
            swap(s, i, index);
            findStringPermutationHelper(s, index + 1, result);
            swap(s, i, index);
        }
    }

    private void swap(char[] s, int source, int target) {
        char tmp = s[source];
        s[source] = s[target];
        s[target] = tmp;
    }
}