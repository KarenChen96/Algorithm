# Hash table
- A general logical data structure, HashMap and HashSet are the implementation classes in Java.
- ArrayList<Pair<String, String>>: put(key) - O(N), get(key) - O(N)

---
## Hash table Implementation
#### Key term
- Load Factor = n/k
    - n: the number of entries in the map
    - k: the number of buckets
    - In Java, the load factor is defined as 0.75

#### Collision Resolution
1. Separate Chaining
    - Separate chaining with linked list
    - Separate chaining with head cells
    - Separate chaining with other structures

2. Open Addressing (probe + rehash)
    - The name "open addressing" refers to the fact that the location of the item is not determined by its hash value. (This method is also called closed hashing.)
    - Details: 
        - When inserting a new entry, the buckets are examined, starting from the hashed-t slot and proceeding in some probe sequence, until an unoccupied slot is found.
        - When searching for an entry, the buckets are scanned in the same sequence, until either the target is found, or and unused array slot is found indicating that there is no such key in the table.
    - Drawbacks: the number of elements can't exceed the number of slots in the bucket array
    - Requirements on the hash function:
        - Uniformly distribute
        - Avoid clustering: the mapping of two or more keys to consecutive buckets. Such clustering may cause the lookup cost to skyrocket, even if the load factor is low and collisions are infrequent.


3. Java Solution: 
    - In Java HashMap implementation, Separate Chaining schema is used. Each bucket has a list of entries.
        - Entry in HashMap buckets: http://hg.openjdk.java.net/jdk8/jdk8/jdk/file/tip/src/share/classes/java/util/Hashtable.java#l1235


## Common Algorithm Problems 
1. For a composition of words, try to find the top k frequent words in the composition.
    Solution: Hash table + Min heap of size k

2. If there is only one missing number from 1 to n in an unsorted array. How to find it in O(N) time? (Size of the array is n-1)
    - Solution 1: HashSet
        - Time: O(N) in average, O(N^2) in the worst case
        - Space: O(N)
    - Solution 2: Boolean array
        - Time: O(N), Space: O(N)
    - Solution 3: Sum(1, n) - Sum(elements in the array)
        - Time: O(N), Space: O(1), but may overflow
    - Solution 4: Bit operation -> (XOR[1, n]) XOR (XOR[elements in the array])
        - Time: O(N), Space: O(1), won't overflow
        - What's the theory?

3. Find the common numbers between two sorted arrays a[M], b[N]
     - Solution 1: HashSet
        - Time: O(N) in average, O(N^2) in the worst case
        - Space: O(N)
    - Solution 2: Two pointers
        - Time: O(max(M,N)) --> O(M+N), Space: O(1)
    - Solution 3: Binary Search
        - Time: O(M*logN), Space: O(1)
        - Micro optimization: if size[a] >> size[b], swap(a,b)
        - Time Complexity Comparison:
            - If m == n, O(M+N) < O(M*logN)
            - If m << n, O(M+N) > O(M*logN)

### TO-DO: 
1. Practice Common Numbers problem (Different solution & Different parameter types)
    https://app.laicode.io/app/problem/72
2. Hash table Practice class review
3. Associative Array