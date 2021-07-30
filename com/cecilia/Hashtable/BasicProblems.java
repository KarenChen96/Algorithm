package com.cecilia.Hashtable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;

class BasicProblems {

    /**
     * For a composition of words, try to find the top k frequent words in the composition.
     * Time complexity: 
     *  - Construct the hash table: Average O(N), Worst case O(N^2)
     *  - Find top k: K + (N-K)*logK
     * @param array
     * @param k
     * @return
     */
    public static String[] topKFrequent(String[] array, int k) {
        String[] result = new String[k];
        Map<String, Integer> map = new HashMap<>();
        for (String s : array) {
            Integer count = map.get(s);
            if (count == null) {
                map.put(s, 1);
            } else {
                map.put(s, count+1);
            }
        }
        findTopK(map, result);
        return result;
    }

    private static void findTopK(Map<String, Integer> map, String[] result) {
        PriorityQueue<Entry<String, Integer>> minheap = new PriorityQueue<>(result.length, (e1, e2) -> e1.getValue().compareTo(e2.getValue()));
        // Heapify first k elements
        int count = 0;
        // If we do not specify type here, then type casting will be needed later
        for (Entry<String, Integer> e : map.entrySet()) {
            // Will count be incremented if the condition fails?
            if (count++ < result.length) {
                minheap.offer(e);
            } else {
                // if (minheap.peek().getValue() < e.getValue())
                if (minheap.peek().getValue().compareTo(e.getValue()) < 0) {
                    minheap.poll();
                    minheap.offer(e);
                }
            }
        }
        for (int i = result.length-1; i >= 0; i--) {
            result[i] = minheap.poll().getKey();
        }
    }

    /**
     * If there is only one missing number from 1 to n in an unsorted array. How to find it 
     * in O(N) time? (Size of the array is n-1)
     * 
     * @param array
     * @return
     */
    public static int findMissingNumber(int[] array) {
        return -1;
    }

    /**
     * Find the common numbers between two sorted arrays a[M], b[N]
     * Clarify:
     *  1. How are the arrays sorted? Descending or Ascending? - Ascending
     *  2. How to handle duplicate numbers? - Save all duplicates in the result
     *  3. How large are M & N?
     * @param a
     * @param b
     * @return
     */
    public static List<Integer> findCommonNumbers(List<Integer> A, List<Integer> B) {
        // Assume A & B are not null
        // Solution 2: Two pointers
        List<Integer> result = new LinkedList<>();
        int p1 = 0;
        int p2 = 0;
        while (p1 < A.size() && p2 < B.size()) {
            int num1 = A.get(p1);
            int num2 = B.get(p2);
            if (num1 == num2) {
                result.add(num1);
                p1++;
                p2++;
            } else if (num1 < num2) {
                p1++;
            } else {
                p2++;
            }
        }
        return result;
    }
}
 