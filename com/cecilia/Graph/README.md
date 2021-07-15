# Graph
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

## Search Algorithm commonly used in Graph
### 1. Breadth-First Search (BFS-1)
- BFS1 steps & How to describe a BFS's action?
    1. Data sStructure: Maintain a FIFO queue, put all traversed but haven't been expanded nodes.
    2. Expand a node, e.g. visit it/print its value
    3. Generate the node's neighbor nodes
    4. Termination Condition: do a loop until the queue is empty
    5. Optionally de-duplicate visited nodes (typically for graph noe for tree)
        - e.g. each node is expanded only once
        - e.g. each node is generated only once
    Notes: Queue size is only needed in some specific problems, e.g. printNodesByLevel
- Classical Problems
    - Print a binary tree by level
    - Bipartite: whether a graph's node can be divided into two group, such that the nodes in each group do not have direct edges between the node that belong to the same group.
    - Determine whether a binary tree is a complete binary tree.

### 2. Best-First Search (BFS-2)
- Algorithm: Dijkstra's Algorithm (Better time complexity: A* search algorithm)
    - Data Structure: Priority Queue (Min heap)
    - Algorithm Details:
        - Initialization: enqueue <start node, 0>
        - In each step:
            - Expand: 
                - dequeue the node (x) with the smallest distance from the start node
                    - Time complexity: **O(logN)**
                - De-dup: If this node has been expanded, not generate anything. (Because the shortest distance to this node's has already been found.)
            - Generate: enqueue the node's neighbors <neighbor, dist(x) + weight(x->y)>
                - Time complexity: **O(# of outgoing edges * logN)**
                - If dist(x) + weight(x->y) >= dist(y), ignore
                - If dist(x) + weight(x->y) < dist(y), enqueue
        - Terminate Condition: the queue is empty
        - De-dup: Each node is only **expanded** once
    - Properties of Dijkstra's algorithm
        - One node can be **expanded** only once.
        - One node can be **generated** more than once. (cost/distance can be reduced over time)
        - All the cost/distance of the nodes that are expanded are monotonically non-decreasing. 
        - Time complexity: 
            - In the whole iteration process, there will happen |V| times dequeue and ~(E1*logV + E2*logV + ... + En*logV) = |E|*logV times enqueue, in total we get O(|V|+|E|*logV).
                - a. Why can we assume  enqueue/dequeue time complexity is logV?
                     Answer: Consider the worst case in which we have a complete graph, then the queue size will be V*(V-1)/2 at most.
                     But this size is used when considering enqueue/dequeue complexity, not for iteration times which is not dependent on the max size
                - b. Special case: A graph with N node and the connectivity of the node is constant (e.g., 4-connected grid)
                     Time complexity = O(N + E*logN) = O(N + a*N*logN) ~ O(N*logN)
        - When a node is popped out from the queue for expansion, its value is fixed which is equal to the shortest distance from the start node.


    - De-dup: Already Generated/Expanded
    - Example: k-th Smallest
        - Comparator/**Lambda Expression** in the implementation

**Discussion**
General algorithm for BFS-1 & BFS-2
1. Initial state: e.g., root node, start node, minimal number...
2. Expansion/Generation rule
    - BFS-1: expand a node, expand all neighbors --> FIFO Queue
    - BFS-1: expand a node, expand all neighbors --> Priority Queue
3. Termination condition
    - Empty queue
    - Conflict is found
    - The target node is expanded
    - The k-th element is expanded
4. (Optional) de-duplication
5. When-What
    - When should we consider to use BFS-1? --> Related to levels
    - When should we consider to use BFS-2? --> shortest path; k-th smallest/largest
6. If all edge costs in the graph are uniform (e.g., all 1s), then in this special case, we can use CFS-1 to find the shortest path. Otherwise, BFS-1 is not the correct algorithm.
    - Detail: Using FIFO queue as the data structure, and apply BFS-2 expand/generate rule.


### 3. Depth-First Search (DFS)