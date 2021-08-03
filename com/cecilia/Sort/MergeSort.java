package com.cecilia.Sort;

import java.util.*;
import com.cecilia.List.*;

public class MergeSort {
    // Divide & Conquer
    public static void sort(int[] array) {
        if (array.length <= 1) {
            return;
        }
        int[] helper = new int[array.length];
        mergeSort(array, helper, 0, array.length-1);
    }

    private static void mergeSort(int[] array, int[] helper, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int mid = begin + (end-begin)/2;
        mergeSort(array, helper, begin, mid);
        mergeSort(array, helper, mid+1, end);
        merge(array, helper, begin, mid, end);
    }

    private static void merge(int[] array, int[] helper, int begin, int mid, int end) {
        /* My implementation
        int p1 = begin;
        int p2 = mid + 1;
        for (int i = begin; i <= end; i++) {
            if (p1 <= mid) {
                if (p2 <= end && array[p1] > array[p2]) {
                    helper[i] = array[p2++];
                } else {
                    helper[i] = array[p1++];
                }
            } else {
                helper[i] = array[p2++];
            }
        }
        for (int i = begin; i <= end; i++) {
            array[i] = helper[i];
        } */

        // Given solution
        for (int i = begin; i <= end; i++) {
            helper[i] = array[i];
        }
        int leftIndex = begin;
        int rightIndex = mid+1;
        while (leftIndex <= mid && rightIndex <= end) {
            if (helper[leftIndex] <= helper[rightIndex]) {
                array[begin++] = helper[leftIndex++];
            } else {
                array[begin++] = helper[rightIndex++];
            }
        }
        while (leftIndex <= mid) {
            array[begin++] = helper[leftIndex++];
        }
        // If there are some elements at right side, no need to copy them because they are already in their positions.
    }
    
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode mid = BasicOperations.findMiddleNode(head);
        ListNode right = mid.next;
        mid.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(right);
        return merge(l1, l2);
    }

    private static ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode();
        ListNode curr = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.value <= l2.value) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if (l1 != null) {
            curr.next = l1;
        }
        if (l2 != null) {
            curr.next = l2;
        }
        return dummyHead.next;
    }

    // TO-DO: sort java LinkedList, and check Java source code of sorting a list.

    /**
     * Given a string "A1B2C3D4", convert it to another string "ABCD1234".
     * In general, shift all numbers after the letters.
     * @param s
     */
    public static String convert(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] helper = new char[s.length()];
        char[] array = s.toCharArray();
        sort(array, helper, 0, s.length()-1);
        return new String(array);
    }

    private static void sort(char[] str, char[] helper, int begin, int end) {
        if (begin <= end) {
            return;
        }
        int mid = begin + (end-begin)/2;
        sort(str, helper, begin, mid);
        sort(str, helper, mid+1, end);
        merge(str, helper, begin, end);
    }

    // A1B2C3D4E5 --> ABCDE12345
    // Similar to int[] merge, but with different sort rule.
    private static void merge(char[] str, char[] helper, int begin, int end) {
        int mid = begin + (end-begin)/2;
        // ABC12 DE345
        int p1 = begin;
        int p2 = mid+1;
        for (int i = begin; i <= end; i++) {
            if (p2 > end || (p1 <= mid && str[p1] > str[p2])) {
                helper[i] = str[p1++];
            } else {
                helper[i] = str[p2++];
            }
        }
        copy(array, helper, begin, end);
    }
    
    /**
     * Given a string "ABCD1234", convert it to another string "A1B2C3D4".
     * 
     * Steps: A1B2C3D4 <-- AB12CD34 <-- ABCD1234
     * 
     * Reverse Engineering
     * 
     * @param s
     */
    public static String convertII(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] helper = new char[s.length()];
        char[] array = s.toCharArray();
        sortII(array, helper, 0, s.length()-1);
        return new String(array);
    }

    // Idea & Code is simple, but the logic of swapping values correctly is delicate.
    private static void sortII(char[] array, char[] helper, int begin, int end) {
        // Base case: if there are two elements (or less) to be reordered
        if (begin >= end-1) {
            return;
        }
        int mid = begin + (end-begin)/2; // mid is contained in the left part
        int leftMid = begin + (mid-begin)/2;
        int leftLength = leftMid - begin + 1;
        int rightMid = mid+1 + (end-(mid+1))/2;
        int rightLength = end - rightMid;
        swap(array, helper, leftMid, leftLength, rightMid, rightLength);
    }

    private static void swap(char[] array, char[] helper, int leftMid, int leftLength, int rightMid, int rightLength) {
        for (int i = 0; i < leftLength; i++) {
            helper[leftMid+1+i] = array[mid+1+i];
        }
        for (int i = 0; i < rightLength; i++) {
            helper[rightMid-i] = array[mid-i];
        }
        copy(array, helper, leftMId+1, rightMid);
    }

    private static void copy(char[] array, char[] helper, int from, int to) {
        for (int i = from; i <= to; i++) {
            array[i] = helper[i];
        }
    }
}
