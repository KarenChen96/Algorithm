TO-DO: 
1. Run methods with test case
2. Summary: when to use dummyHead & tail pointer 
3. fancyReverseIteratively implementation
4. Review problems:
    a. addTwoNumbers: how to simplify code 
    b. addTwoNumbersII: Any better solution?


## List Basic 
### Singly Linked List
- head node: base address of the list
- tail node: next = null
- insert & delete: O(1) (exclude the time for locating the node), random access: O(N)


### Circular Singly Linked List
TBD

### Doubly Linked List
- Doubly Linked List VS. Singly Linked List
    - LinkedHashMap: use Doubly Linked List => Use space to get better time complexity
    - Cache is an another example of Using space to get better time complexity

### Circular Doubly Linked List
TBD

### Array VS. List
    - Time complexity comparision: Insert & Delete
    - An array has a continuous memory block, it can utilize CPU caching to pre-read array data, hence it has better access efficiency. A list can't have effective pre-red.
    - Array needs to expand capacity and copy.
    - List consumes larger space for saving pointers.
    - Frequent Insert & Delete operations to a list will cause frequent memory application & release, which brings in memory fragmentation. If using Java, then it will cause frequent GC.

### Actual application: Use list to realize LRU
TBD

---

## List Practice Tips
1. lost pointer & memory leak
    - In the programming language in which the developers need to take care of memory management, lost pointers' space won't be released manually then cause memory leak.
2. Using sentinel node
    - With sentinel node: with-head list (TBD: check the example code)
3. Boundary conditions processing (edge cases)
    - Empty list
    - only onde node
    - only two nodes
    - head node & tail node
    - Other edge cases...
4. Common operations
    - reverse a singly linked list
    - find if a list has a cycle
    - merge 2 sorted lists
    - delete the N-th node from the last in the list
    - find the middle node

After class:
Other cases in which using sentinel will be beneficial?