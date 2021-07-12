package com.cecilia.BinaryTree;

public class BST {

    public boolean isBST(TreeNode root) {
        return isBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBSTHelper(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.value < min || root.value > max) {
            return false;
        }
        return isBSTHelper(root.left, min, root.value-1) && isBSTHelper(root.left, root.value+1, max);
    }
    
    public void rangeInOrderTraverse(TreeNode root, int min, int max) {
        /**
         * Time complexity: worst case: O(N). Better answer: O(Height + # of nodes in the range)
         * Space complexity: O(Height) [Not O(logN)! BST is not necessarily a balanced binary tree.]
         * Question: Recursion tree of this problem? Same to the original tree?
         */
        if (root == null) {
            return;
        }
        if (root.value > min) {
            rangeInOrderTraverse(root.left, min, max);
        }
        if (root.value >= min  && root.value <= max) {
            System.out.println(root.value);
        }
        if (root.value < max) {
            rangeInOrderTraverse(root.right, min, max);
        }
    }
}
