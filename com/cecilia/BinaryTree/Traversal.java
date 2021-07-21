package com.cecilia.BinaryTree;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
            // Version 1
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

            // Version 2
            // if (!visited.contains(node)) {
            //     visited.add(node);
            //     if (node.left != null) {
            //         stack.offerLast(node.left);
            //         continue;
            //     }
            // } 

            // Version 3
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

    public List<Integer> inOrderIterativeWithoutSet(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        // Trick: curr = null means we are going to go back (backtracking)
        // Compared to recursive implementation, the termination condition is similar to the condition that we poll() a new node from the stack.
        // Both conditions are used to go back to the last level node (parent node).

        // Within each iteration/recursion, we traverse the left subtree first, then reach the terminate condition, 
        // print myself, then go to traverse the right subtree.
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                // Keep going down in the left subtree
                stack.offerLast(curr);
                curr = curr.left; // traverse to next level
            } else {
                // reach the null node in the left subtree, then print myself and go to the right subtree.
                curr = stack.pollLast(); // go back to previous level / parent node
                result.add(curr.value);
                curr = curr.right; // traverse to next level
            }
        }
        return result;
    }

    public void postOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderRecursive(root.left);
        postOrderRecursive(root.right);
        System.out.println(root.value);
    }

    /**
     * Trick: left-right-self ---> self-right-left
     * @param root
     * @return
     */
    public List<Integer> postOrderIterativeSolution1(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerLast(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            result.add(node.value);
            if (node.left != null) {
                stack.offerLast(node.left);
            }
            if (node.right != null) {
                stack.offerLast(node.right);
            }
        }
        Collections.reverse(result);
        return result;
    }

    public List<Integer> postOrderIterative(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerLast(root);
        TreeNode curr = null;
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            curr = stack.peekLast();
            if (curr.left != null) {
                if (curr.left != prev) {
                    if ((curr.right != null && prev != curr.right)) {
                        stack.offerLast(curr.left);
                    } else {
                        result.add(curr.value);
                        stack.pollLast();
                    }
                } else {
                    // prev == left
                    if (curr.right != null) {
                        if (curr.right != prev) {
                            stack.offerLast(curr.right);
                        } else {
                            result.add(curr.value);
                            stack.pollLast();
                        }
                    } else {
                        result.add(curr.value);
                        stack.pollLast();
                    }
                }
                prev = curr;
                continue;
            } 
            if (curr.right != null) {
                if (curr.right != prev) {
                    stack.offerLast(curr.right);
                } else {
                    result.add(curr.value);
                    stack.pollLast();
                }
                prev = curr;
                continue;
            }
            if (curr.left == null && curr.right != null) {
                result.add(curr.value);
                stack.pollLast();
                prev = curr;
            }
        }
        return result;
    }

    public List<Integer> postOrderIterativeRefine(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerLast(root);
        TreeNode curr = null;
        TreeNode prev = null; // Used to determine the relationship between the node being visited and the node visited previously
        while (!stack.isEmpty()) {
            curr = stack.peekLast();
            // The cases that we need to go to left
            if (curr.left != null && curr.left != prev && (curr.right == null || curr.right != prev)) {
                stack.offerLast(curr.left);
            } 
            // else if (curr.right != null && ( (curr.left != null && curr.left == prev) || (curr.left == null && curr.right != prev) )) {
            else if (curr.right != null && curr.right != prev) {
                stack.offerLast(curr.right);
            } else {
                result.add(curr.value);
                stack.pollLast();
            }
            prev = curr;
        }
        return result;
    }
}
