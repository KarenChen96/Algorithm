package com.cecilia.Array;

import java.util.LinkedList;
import java.util.List;

public class BinarySearch {
    /**
     * 
     * @param array The input array. Assumed it is in the ascending order, no duplicate element.
     * @param target The target number to be found in the input array
     * @return The index of target in the input array. Return -1 if not found.
     */
    public static int binarySearch1D(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right-left)/2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                right = mid - 1; // won't dead cycle
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 
     * @param matrix: 2D matrix, sorted on each row, first element of next row is larger than (or equal to)
     *                the last element of previous row. No duplicate element.
     * @param target: A target number
     * @return The position that the target locates within the matrix. Return [-1,-1] if not found.
     */
    public static int[] binarySearch2D(int[][] matrix, int target) {
        // Assume the matrix is not empty
        int[] result = {-1,-1};
        int n = matrix.length;
        int m = matrix[0].length;
        int left = 0;
        int right = n*m - 1;
        while (left <= right) {
            int mid = left + (right-left)/2;
            int number = matrix[mid/m][mid%m];
            if (number == target) {
                result[0] = mid/m;
                result[1] = mid%m;
                return result;
            } else if (number > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    /**
     * 
     * @param array: An array sorted in ascending order, no duplicate elements.
     * @param target: The target number
     * @return The index of the element that is closest to the target. Return -1 if the input array is empty.
     */
    public static int closestElement(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0; 
        int right = array.length;
        // Finally, left two elements in the search space and compare which one is closer.
        while (left < right-1) {
            int mid = left + (right-left)/2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return Math.abs(array[left]-target) < Math.abs(array[right]-target) ? left : right;
    }

    /**
     * 
     * @param array: An array sorted in ascending order, duplicate elements may exist.
     * @param target: The target number
     * @return The index of the first occurrence of the target. Return -1 if the target is not found.
     */
    public static int firstTarget(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0; 
        int right = array.length;
        while (left < right) {
            int mid = left + (right-left)/2;
            if (array[mid] == target) {
                right = mid; // this rule may lead: stuck in the while-loop, [left, left+1] and array[left] == target
            } else if (array[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return array[left] == target ? left : -1;
    }

    public static int lastTarget(int[] array, int target) {
        return -1;
    }

    /**
     * 
     * @param array: An array sorted in ascending order, duplicate elements may exist.
     * @param target: The target number
     * @param k: The number of closest elements
     * @return A list of th indices of the k elements that are closest to the target.
     *          - If k <= 0 || array is empty, return null;
     *          - Else if k > array.length, return a list of size N (N = array.length);
     *          - Else, return a list of size k.
     */
    public static List<Integer> closestKElements(int[] array,int target, int k) {
        List<Integer> result = new LinkedList<>();
        if (array == null || array.length == 0 || k <= 0) {
            return result;
        }
        
        // Tme complexity = O(logN + K)
        int closestElementIndex = closestElement(array, target); // First, find the index of closest element.
        result.add(closestElementIndex);
        int left = closestElementIndex - 1;
        int right = closestElementIndex + 1;
        int n = array.length;
        while (k-- > 0 && (left >= 0 || right < n)) {
            if (left >= 0 && right < n) {
                if (Math.abs(array[left]-target) <= Math.abs(array[right]-target)) {
                    result.add(left--);
                } else {
                    result.add(right++);
                }
            } else if (left >= 0) {
                result.add(left--);
            } else if (right < n) {
                result.add(right++);
            }
        }
        return result;
    }

    /**
     * 
     * @param array: An array sorted in ascending order, duplicate elements may exist.
     * @param target: The target number
     * @return The smallest element that is larger than the target number. If no such an element, return -1;
     */
    public static int smallestLargerThanTarget(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = left + (right-left)/2;
            if (array[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return array[left] > target ? left : -1;
    }

    public static List<Integer> kthSmallestInTwoArrays(int[] a1, int[] a2, int k) {
        List<Integer> result = new LinkedList<>();
        // Assert a1 != null, a2 != null
        if ((a1 != null && a2 != null && a1.length == 0 && a2.length == 0) || k <= 0) {
            return result;
        }

        // Solution 1: Two pointers, move the smaller point. Time complexity = O(K)

        return result;
    }
}
