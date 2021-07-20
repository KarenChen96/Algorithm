package com.cecilia.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Traversal {

    public void preOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.value);
        preOrderRecursive(root.left);
        preOrderRecursive(root.right);
    }

    public void preOrderIterative(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerLast(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            System.out.println(node.value);
            if (node.right != null) {
                stack.offerLast(node.right);
            }
            if (node.left != null) {
                stack.offerLast(node.left);
            }
        }
    }

    public void inOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderRecursive(root.left);
        System.out.println(root.value);
        inOrderRecursive(root.right);
    }

    public void inOrderIterative(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerLast(root);
        Set<TreeNode> visited = new HashSet<>();
        while (!stack.isEmpty()) {
            TreeNode node = stack.peekLast();
            // if (!visited.contains(node)) {
            //     if (node.left != null) {
            //         stack.offerLast(node.left);
            //     } else {
            //         System.out.println(node.value);
            //         stack.pollLast();
            //         if (node.right != null ) {
            //             stack.offerLast(node.right);
            //         }
            //     }
            //     visited.add(node);
            // } else {
            //     System.out.println(node.value);
            //     stack.pollLast();
            //     if (node.right != null ) {
            //         stack.offerLast(node.right);
            //     }
            // }

            // if (!visited.contains(node)) {
            //     visited.add(node);
            //     if (node.left != null) {
            //         stack.offerLast(node.left);
            //         continue;
            //     }
            // } 

            // if (visited.add(node)) {
            //     if (node.left != null) {
            //         stack.offerLast(node.left);
            //         continue;
            //     }
            // }

            if (visited.add(node) && node.left != null) {
                stack.offerLast(node.left);
                continue;
            }
            System.out.println(node.value);
            stack.pollLast();
            if (node.right != null ) {
                stack.offerLast(node.right);
            }
            
        }
    }

    public void postOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderRecursive(root.left);
        postOrderRecursive(root.right);
        System.out.println(root.value);
    }

    public void postOrderIterative(TreeNode root) {
        
    }
    
}
