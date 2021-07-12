Graph
1. Node/State
2. Edge/Action
3. Directed VS. Undirected graph
4. Representation of the graph
    - Adjacency Matrix
        - Pros: 
            - Representation is easy to implement.
            - Edge removal takes O(1) time.
            - Queries like whether there is an edge from vertex 'u' to vertex 'v' can be done O(1)
        - Cons:
            - Consume O(V^2) space, even if the graph is sparse

    - Adjacency List
        - Pros: 
            - Space complexity = O(|V| + 2|E|) = O(|V| + |E|)
            - Add a vertex/node to the graph is easier. 
        - Cons:
            - Check whether there is an edge from a node to the other: O(V) time. (Worst case: connected to all vertex)

Search Algorithm commonly used in Graph
1.Breadth-First Search (BFS-1)
    - Print a binary tree by level
    - Bipartite: whether a graph's node can be divided into two group, such that the nodes in each group do not have direct edges between the node that belong to the same group.
    - Determine whether a binary tree is a complete binary tree.

2.Best-First Search (BFS-2)
    - Algorithm: Dijkstra's Algorithm
    - Example: Given a matrix of size N*N, and for each row the elements are sotted in an ascending order, and for each column the elements are also sorted in an ascending order. How to find the k-th smallest element in it?

Discussion
General algorithm for BFS-1 & BFS-2
1. Initial state
2. Expansion/Generation rule
3. Termination condition
4. (Optional) de-duplication
5. When-What
    - When should we consider to use BFS-1? --> Related to levels
    - When should we consider to use BFS-2? --> shortest path; k-th smallest/largest

3.Depth-First Search (DFS)