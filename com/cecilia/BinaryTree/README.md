## Fundamental Concepts
- Tree Traversal: pre-order, in-order, post-order
- Binary Tree
- Balanced Binary Tree
    - Conclusion: If a tree has N number of nodes, and it's balanced,then the height(level) of the tree = O(logN)
- Complete Binary Tree
    - Conclusion: If a tree is a complete binary tree, then it must be a balanced binary tree. 
- Binary Search Tree (BST)
    - Conclusion: If we print the value of the nodes in BST in in-order sequence, then it must form an ascending order.


## Discussion
- Binary tree往往是最常见的，和recursion结合最紧密的题目类型。
- Reasons: 
    - 每层的node具备的性质、传递的值和下一层的性质往往一致，比较容易定义recursion rule.
    - Base case (generally): null pointer under the leaf node.
    - Example: 1. getHeight; 2. The number of nodes

## Problems
1. How to determine whether a binary tree is a balanced binary tree?

2. how to determine whether a binary tree is symmetric?

3. Let's assume if we tweak the l-child with r-child of an arbitrary node in a binary tree, then the "structure " of the tree are not changed. Then how can we determine whether two binary trees structures are identical?

Binary Search Tree
4. How to determine a binary tree is a BST?

5. Print BST keys in the given range
Given two values k1 and k2 (where k1 < k2) and a root pointer to a binary search tree. Print all the keys of tree in range k1 to k2, i.e. print all x such that k1<=x<=k2 and x is a key of the given BST. Print all the keys in an increasing order.


#### Discussion: Recursion在tree题目的基本应用大致分为两类：
- 把value从上往下传递（then从下往上）； 
    1.1 isBST
- 把value从下往上传递
    2.1 getHeight
    2.2 isBalanced
    2.3 isSymmetric
    2.4 Assign the value of each node to be the total number of nodes that belong to its left subtree
