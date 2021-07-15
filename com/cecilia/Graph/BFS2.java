package com.cecilia.Graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.cecilia.BinaryTree.TreeNode;

/**
 * Best-First Search problem set
 */
public class BFS2 {
    /**
     * Given a matrix of size N*N, and for each row the elements are sotted in an ascending order, and for 
     * each column the elements are also sorted in an ascending order. Find the k-th smallest element in it.

     * @param matrix: a matrix of size N*N
     * @param k: k-th smallest. 0 < k < matrix's size
     * @return The k-th smallest element in the matrix
     */
    public int kthSmallest(int[][] matrix, int k) {
        // Notes:
        // 1. Essentially, K-th smallest element in the matrix is the k-th expanded node, i.e. k-th closest to the source node.
        // 2. This problem is a little different from the classic Shortest Distance Problem:
        //      a. De-dup: Each element will only be generated once
        //          - Because element's value is always fixed. (In Shortest Distance Problem, shortest distance varies.)
        //      b. Generate: Only partial neighbors will be generated
        //          - Because of ascending property of the rows & columns.
        // Time complexity: (Assume k << N^2) O(k * log k)
        // Space complexity: O(k + N^2) (can be optimized to O(k) using a hashtable) (Why use hash table? Is hash set a special hash table?)
        int n = matrix.length;
        PriorityQueue<Item> minHeap = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Item i1, Item i2) {
                if (i1.value == i2.value) {
                    return 0;
                } 
                return i1.value < i2.value ? -1 : 1;
            }
        });
        boolean[][] isGenerated = new boolean[n][n];
        minHeap.offer(new Item(0, 0, matrix[0][0]));
        isGenerated[0][0] = true;
        int result = matrix[0][0];
        while (!minHeap.isEmpty() && --k > 0) {
            // Expand
            Item node = minHeap.poll();
            int x = node.x;
            int y = node.y;
           // Generate neighbors: Not consider [x-1, y] and [x, y-1] here because row and column are in ascending order.
            if (x + 1 < n && !isGenerated[x+1][y]) {
                minHeap.offer(new Item(x+1, y, matrix[x+1][y]));
                isGenerated[x+1][y] = true; // De-dup: mark as Generated
            }
            if (y + 1 < n && !isGenerated[x][y+1]) {
                minHeap.offer(new Item(x, y+1, matrix[x][y+1]));
                isGenerated[x][y+1] = true; // De-dup: mark as Generated
            }
        }
        return result;
    }

    class Item {
        public int x;
        public int y;
        public int value;

        public Item(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.value = v;
        }
    }
}
