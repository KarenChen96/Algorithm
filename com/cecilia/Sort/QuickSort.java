package com.cecilia.Sort;

import java.util.Random;

public class QuickSort {

    private static Random randomGenerator = new Random();

    public static void quickSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        helper(array, 0, array.length-1);
    }

    private static void helper(int[] array, int begin, int end) {
        if (begin <= end) {
            return;
        }

        // [begin, left) are elements <= pivot
        // (right, end) are elements > pivot
        // [left, right] are unknown elements
        int left = begin;
        int right = end-1;
        // int pivotIndex = (int) (Math.random() * (end-begin+1)) + begin;
        int pivotIndex =  randomGenerator.nextInt(end-begin+1) + begin;
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, end);
        while (left <= right) {
            if (array[left] <= pivot) {
                left++;
            } else if (array[right] > pivot) {
                right--;
            } else {
                swap(array, left++, right--);
            }
        }
        swap(array, left, end);
        helper(array, begin, left-1);
        helper(array, left+1, end);
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    /**
     * Array Shuffling: Given an array with integers, move all 0s to the right-end of the array. 
     * Example: {1, 0, -4, -5, 0, 2, 3} --> {1, -4, -5, 2, 3, 0, 0}
     * 
     */
     public static void shuffleArray(int[] array) {
         if (array == null || array.length <= 1) {
             return;
         }

        // [0, left) are non-zero, (right, end] are zeros, [left, right] are unknown
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            if (array[left] != 0) {
                left++;
            } else if (array[right] == 0) {
                right--;
            } else {
                swap(array, left++, right--);
            }
        }
    }

    /**
     * Assume there are only three kinds of numbers in the array: -1, 0, 1
     * 
     * Example: {abcccabbcbbacaa} --> {aaaaa bbbbb ccccc}
     * {-1, 0, 1, 1, -1, 0, 0, 1} --> {-1, -1, 0, 0, 0, 1, 1, 1}
     */
    public static void rainbowSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        // [0, p1) are -1, [p1, p2) are 0, [p2, p3] are unknown, (p3, end] are 1.
        int neg = 0;
        int zero = 0;
        int one = array.length-1;
        while (zero <= one) {
            /* My solution
            if (array[zero] == 0) {
                zero++;
            } else if (array[one] == 1) {
                one--;
            } else if (array[zero] == -1) {
                swap(array, neg++, zero++);
            } else {
                swap(array, zero, one--);
            }*/

            // More concise one
            if (array[zero] == 0) {
                zero++;
            } else if (array[zero] == -1) {
                swap(array, neg++, zero++);
            } else {
                swap(array, zero, one--);
            }
        }
    }
}
