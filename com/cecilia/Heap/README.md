# Heap
Min/Max-heap, also called priority queue, is used to maintain the best value (the object with the highest priority) of a variable value set.

A heap is a specialized **tree-based** data structure which is essentially an almost **complete tree** that satisfies the **heap property**:
- in a max heap, each node's key is greater than or equal to its all descendants.
- in a min heap, each node's key is smaller than or equal to its all descendants.

X-ary Heap: a heap can be binary heap, ternary heap, ... d-ary heap. Binary heap is a common implementaiton of heap in which the tree is a binary tree.

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
    - A utility method: Collections.reverseOredr(), it will return a comparator that reverse the natural order. E.g.,

            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOredr());

    - More details about Collections.reverseOredr()
        - Thie method will return Comparator<T> which imposes the reverse of the natural ordering on a collection of T (not only Integer class) that implement the Comparable interface. https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Collections.html#reverseOrder()
        - Comparator<T> reverseOrder​(Comparator<T> cmp)
        Returns a comparator that imposes the reverse ordering of the specified comparator.


#### Constructor: 
- Default size is 11. If the provided size <= 0, IllegalargumentException will be thrown.
- If no Comparator is provided, neither E implements Comparable, Java runtime exception will be thrown. (**It will cast anything inside into a Comparable to do the comparison.**)

#### Heapify

    ArrayList<E> list = new ArrayList<>();
    ... 
    PriorityQueue<E> pq = new PriorityQueue<>(list);

- The priority queue initializes the internal array with heapify() internally, heapify() is a private method of PriorityQueue class, the only way we can utilize it is to use the cnstructor.

#### Nested Class
- (Static) Nested class: deine a class within a class, belong to class.
- Inner class: non-static nested class, belong to instance.
- Anonymous (inner) class: nested without name, often replaceable by lambda expressions in Java 8.
    
        PriorityQueue<E> pq = new PriorityQueue<>(new Comparator<E>() {
            ...
        });

    - Comparator<E>: Implements an interface
    - {}: Define a class
    - new keywork: Create an instance
    - (): Call constructor


Some extension points: (TO-DO)
- Integer implements Comparable, then Integer object can be cast to Comparable object.

        Integer t = 1;
        Comparable c = t; // Casting



## Implemente a Heap

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
    - Compare the element with its parent, move it up when necessary. Do this until the element doens't need to be moved.

**percolateDown()**
- When to use?
    - When the element needs to be moved down to maintain the heap's property, e.g., poll()
- How?
    - Compare the element with its tow children, (in a min heap) if the smallest one of the two childern is smaller than the element, swap this element with that child. Do this until the element doens't need to be moved.

**Heapify()**
 - Convert an array into a heap in O(N) time
 - How?
    


TO-DO 
- Heap practice class: Imlpement a heap
- unknown questions solution
- practice class review 
- leetcode schdule