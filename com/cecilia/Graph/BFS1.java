package com.cecilia.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.cecilia.BinaryTree.TreeNode;

/**
 * Breadth-First Search problem set
 */
public class BFS1 {
    public void printNodesByLevel(TreeNode root) {
        // Assume that this is a binary tree
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // size only matters in this problem...
            int size = queue.size();
            while (size-- > 0) {
                // Expand
                TreeNode node = queue.poll();
                System.out.print(node.value + " ");
                // Generate
                if (node.left != null) {
                    queue.offer(node.left);
                }  
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            System.out.println();
        }
    }

    // Differentiate Graph and Tree
    //  1. There may exist "island" nodes in a graph. For a graph problem, the input parameter should not be a single root node, but List<Node>
    //  2. There is no level concept in graph anymore, some steps of classical BFS-1 cna be ignored in graph problems.
    // Time complexity = (1+E1) + (1+E2) + (1+E3) + ... = O(|V| + |E|). 1 is minor but can't be ignored here!
    // Space complexity = O(|V|)
    // Summary: The basic expand-generate is correct, but how to make the code more concise and elegant is the core.
    public boolean isBipartite(Node root) {
        Queue<Node> queue = new LinkedList<>(); // Traverse the graph
        Map<Node, Integer> map = new HashMap<>(); // record the group number, and de-dup
        queue.offer(root);
        map.put(root,1);
        while (!queue.isEmpty()) {    
            Node node = queue.poll();
            int groupNumber = map.get(node);
            for (Node n : node.getNeighbors()) {
                if (!map.containsKey(n)) {
                    queue.offer(n);
                    map.put(n, (groupNumber+1)%3);
                } else if (map.get(node).equals(groupNumber)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isBipartiteWithSet(Node root) {
        Queue<Node> queue = new LinkedList<>(); // Traverse the graph
        Set<Node> currGroup = new HashSet<>();
        Set<Node> nextGroup = new HashSet<>();
        queue.offer(root);
        currGroup.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Node n : node.getNeighbors()) {
                if (currGroup.contains(n)) {
                    return false;
                } else if (nextGroup.add(n)) {
                    queue.add(n);
                }
            }
            Set<Node> tmp = currGroup;
            currGroup = nextGroup;
            nextGroup = tmp;
        }
        return true;
    }

    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        } 
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean hasBubble = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null) {
                if (node.right != null) {
                    return false;
                }
                hasBubble = true;
            } else {
                if (hasBubble) {
                    return false;
                }
                queue.offer(node.left);
                if (node.right != null) {
                    queue.offer(node.right);
                } else {
                    hasBubble = true;
                }
            }
            
            /* 
            // Version 1
            // The scope of this if-condition is so large that we need to do more checks within it.
            if (node.left != null || node.right != null) {
                if (hasBubble) {
                    return false;
                }
                if (node.left == null) {
                    return false;
                } else {
                    queue.offer(node.left);
                    if (node.right != null) {
                        queue.offer(node.right);
                    } else {
                        hasBubble = true;
                    }
                }
            } else {
               hasBubble = true;
            } 
            
            // Given solution
            if (node.left == null) {
                hasBubble = true;
            } else if (hasBubble) {
                return false;
            } else {
                queue.offer(node.left);
            }
            if (node.right == null) {
                hasBubble = true;
            } else if (hasBubble) {
                return false;
            } else {
                queue.offer(node.right);
            }
            
            */
        }
        return true;
    }
}
