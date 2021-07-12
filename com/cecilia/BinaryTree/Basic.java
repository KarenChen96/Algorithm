package com.cecilia.BinaryTree;

public class Basic {

    public boolean isBalancedSimple(TreeNode root) {
        if (root == null) {
            return true;
        }
        int lHeight = isBalancedHelper(root.left);
        int rHeight = isBalancedHelper(root.right);
        if(Math.abs(rHeight-lHeight) > 1) {
            return false;
        }
        return isBalancedSimple(root.left) && isBalancedSimple(root.right);
    }

    public boolean isBalanced(TreeNode root) {
        return isBalancedHelper(root) >= 0;
    }

    private int isBalancedHelper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lHeight = isBalancedHelper(root.left);
        if (lHeight < 0) {
            return -1;
        }
        int rHeight = isBalancedHelper(root.right);
        if (rHeight < 0) {
            return -1;
        }
        if(Math.abs(rHeight-lHeight) > 1) {
            return -1;
        }
        return Math.max(rHeight, lHeight) + 1;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) {
            return true;
        } else if (r1 == null || r2 == null || r1.value != r2.value) {
            return false;
        }
        return isSymmetric(r1.left, r2.right) && isSymmetric(r1.right, r2.left);
    }

    public boolean isIdentical(TreeNode r1, TreeNode r2) {
        /**
         * Time complexity: 
         *   Worst case: Do all 4 checks in each recursion. The recursion tee is actually a Quadral tree, similar to 
         *               a binary tee, the total number of nodes depend on the last layer (leaf layer).
         *   The number of leaf nodes in the last layer = 4 * log_2(N) = N^2
         *   Time complexity = O(N^2)
         */
        if (r1 == null && r2 == null) {
            return true;
        } else if (r1 == null || r2 == null || r1.value != r2.value) {
            return false;
        }
        return (isSymmetric(r1.left, r2.right) && isSymmetric(r1.right, r2.left)) 
            || (isIdentical(r1.left, r2.left) && isIdentical(r1.right, r2.right));
    }
    
}
