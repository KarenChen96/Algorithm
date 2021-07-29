TO-DO: Associative Array

### Key term
- Load Factor = n/k
    - n: the number of entries in the map
    - k: the number of buckets
    - In Java, the load factor is defined as 0.75


### Collision Resolution
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
