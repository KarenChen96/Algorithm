# Heap
Min/Max-heap, also called priority queue, is used to maintain the best value (the object with the highest priority) of a variable value set.

A heap is a specialized **tree-based** data structure which is essentially an almost **complete tree** that satisfies the **heap property**:
- in a max heap, each node's key is greater than or equal to its all descendants.
- in a min heap, each node's key is smaller than or equal to its all descendants.

X-ary Heap: a heap can be binary heap, ternary heap, ... d-ary heap. Binary heap is a common implementation of heap in which the tree is a binary tree.

### Implementation:
A heap is ususally implemented with an unsorted array:
- left child: my_index * 2 + 1
- right child: my_index * 2 + 2
- parent: (my_index - 1) / 2

### Operations:
- insert: O(logN) - percolate Up
- update: pop root and push a new key in one operation - O(logN)
- get/top: O(1)
- pop: O(logN)
- heapify: convert an unsorted array into a heap - O(N)

### Problems:
1. Find smallest k elements from an unsorted array of size n.
- Solution 1: Sort the array. Time complexity = O(N*logN)
- Solution 2: Heapify all elements to a min heap, pop K times. Time complexity = O(N + K*logN) 
- Solution 3: Maintain a max-heap of size K. Time complexity = O(K + (N-K)*logK) - TO-DO: time comparison
    - Heapify the first k elements to form a max-heap of size k
    - Iterate over the rest (n-k) elements one by one
- Solution 4: Quick select (:question:TO-DO)

### Heap in Java
Abstract date structure: heap
Java Interface: Queue
Java Class: PriorityQueue
Implementation: abstract tree -> array
(FIFO Queue implementation: array (e.g. ArrayDeque) or linked list (e.g. LinkedList))

Operations | Time complexity 
--- | --- 
offer(E e) | O(logN) 
peek() | O(1)
poll() | O(logN)
remove(Object o) | O(N) 
size() | O(1)
isEmpty() | O(1)

remove(Object o): inherited from class AbstractQueue
Time complexity = search the object O(N) + remove the object O(logN) = O(N)

remove(Object o) in **balanced** BST:
Time complexity = search the object O(logN) + remove the object O(logN) = O(logN)

### Compare/Order
Order: The PriorityQueue needs to know how to compare the elements and determine which one is smaller/larger.

How does the PriorityQueue know how ito compare the objects?
1. The element class E implements Comparable interface
2. Provide an extra Comparator<E> object to compare the element. (If E has already implemented Comparable<E>, PQ will choose the order specified in Comparator.)
    - A utility method: Collections.reverseOrder(), it will return a comparator that reverse the natural order. E.g.,

            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    - More details about Collections.reverseOrder()
        - Thie=s method will return Comparator<T> which imposes the reverse of the natural ordering on a collection of T (not only Integer class) that implement the Comparable interface. https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Collections.html#reverseOrder()
        - Comparator<T> reverseOrder​(Comparator<T> cmp)
        Returns a comparator that imposes the reverse ordering of the specified comparator.


#### Constructor: 
- Default size is 11. If the provided size <= 0, IllegalargumentException will be thrown.
- If no Comparator is provided, neither E implements Comparable, Java runtime exception will be thrown. (**It will cast anything inside into a Comparable to do the comparison.**)

#### Heapify

    ArrayList<E> list = new ArrayList<>();
    ... 
    PriorityQueue<E> pq = new PriorityQueue<>(list);

- The priority queue initializes the internal array with heapify() internally, heapify() is a private method of PriorityQueue class, the only way we can utilize it is to use the constructor.

#### Nested Class
- (Static) Nested class: define a class within a class, belong to class.
- Inner class: non-static nested class, belong to instance.
- Anonymous (inner) class: nested without name, often replaceable by lambda expressions in Java 8.
    
        PriorityQueue<E> pq = new PriorityQueue<>(new Comparator<E>() {
            ...
        });

    - Comparator<E>: Implements an interface
    - {}: Define a class
    - new keyword: Create an instance
    - (): Call constructor


Some extension points: (TO-DO)
- Integer implements Comparable, then Integer object can be cast to Comparable object.

        Integer t = 1;
        Comparable c = t; // Casting



## Implement a Heap

Syntax | Logic | Implementation
---|---|---
List | Sequence of data | LinkedList/ArrayList
Queue/Stack | FIFO/LIFO | ArrayDeque/LinkedList
PriorityQueue | Heap | Array

Heap is a (binary) tree-based data structure in which the relationship between the parent node and its children stays the same across the entire tree.

The common implementation of a heap is using a complete binary tree. A complete binary tree can be represented as an array, as the matching between the nodes and the index of the array is determined, and the relationship between the parent and child nodes can be well transferred to to the **relationship between two indices**.

### Basic Heap Internal Operations
**percolateUp()**
- When to use?
    - When the element needs to be moved up to maintain the heap's property, e.g., offer(E e)
- How?
    - Compare the element with its parent, move it up when necessary. Do this until the element doesn't need to be moved.

**percolateDown()**
- When to use?
    - When the element needs to be moved down to maintain the heap's property, e.g., poll()
- How?
    - Compare the element with its tow children, (in a min heap) if the smallest one of the two children is smaller than the element, swap this element with that child. Do this until the element doesn't need to be moved.

**Heapify()**
 - Convert an array into a heap in O(N) time
 - How?
  - percolateDown(). For each node that has at least one child (i.e., for each parent node), perform percolateDown() action, in the order from the nodes on the deepest level to the root. Time complexity = O(N)
  - Note: 
    - Alternative solution 1: Heapify via offer and percolateUp. Time complexity = O(NlogN)
    - Review: sum of geometric progression/sequence

**Notes of Implementation:**
1. For a heap implemented by an array, maintaining a size is necessary. this.array.length doesn't change (if not expand capacity) during offer()/poll(), we will don't know the actual size of the heap without this.size field.
2. percolateDown() and percolateUp() is iterative not a one-time swap&swap.
3. In percolateDown(), note that the right child node doesn't necessarily exist.
4. Tricks:
    4.1 In percolateDown(), use a swapCandidateIndex to avoid too many if-block
    4.2 In update(), in order to determine whether the element needs to be percolated up ot down, just simply compare it with its original value.
    4.3 In offer(), when capacity needs to be expanded, Arrays.copyOf() can make thing much easier. This method applies to both object array and primitive type array.
5. Runtime Exception VS. Error VS. Checked Exception (https://docs.oracle.com/javase/tutorial/essential/exceptions/catchOrDeclare.html)


----

### Sort Algorithm 
Sort Algorithm | Time Complexity | Space Complexity | If Stable | Application
--- | --- | --- | --- | ---
Quick sort | Average: O(NlogN), Worst case: O(N^2) | Average: O(logN), Worst case: O(N) | Unstable sort | Arrays.sort(int[])
Merge sort | Average/Worst case: O(NlogN) |  Average/Worst case: O(logN) | Stable sort | Arrays.sort(Integer[])
Heap sort | O(NlogN) | O(1) | Unstable sort | ?

Heapsort
- How?
    - Heapify an array into a max heap. [0, m] are unsorted elements, [m, n-1] are sorted ones. 
    - Iterate 1. swap(0,k--) and 2. percolateDown(0) until k == 0;
- Complexity
    - time: heapify() - O(N) + percolateDown() - O(NlogN) (Similar to the calculation of time complexity of heapify()) = O(NlogN)
    - space: O(1), in-place
- Evaluation: not preferred
    - Not stable + Typical runtime overhead :question:
    - Poor locality (swap from first to last)
    - Hard to parallelize/distribute :question:

data access: 
not by order, but discontinuous, not good for CPU cache
too many swap: (quick sort 有序度，逆序度)



Heap Practical Application: (TO-DO, jike course) 
Priority Queue
- Merge small files
top-k,
medium

TO-DO 
- unknown questions solution