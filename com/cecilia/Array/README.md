Binary Search in the context of an Array

Assumption: The array is sorted, ascending or descending. This assumption is necessary, otherwise doing binary search is meaningless.

Classical Binary Search Problems
1. Binary Search in an array
2. Binary Search in a 2D matrix

Observation:
1. The size of the search space be reduced roughly by a half of the previous iteration, and eventually the space is reduced to 2 elements and/or to 1 element.

Principle
1. The search space size must decrease after each iteration
2. The target (if exists) can't be ruled out accidentally when we change the left/right. (It's critical to define the rule of how to move the range of search)


Binary Search Variants

Learn By Analogy I
Q3. Closest Element to Target
    - Key: how to define the rule of decreasing the search space: 
        - Don't rule out the possible result when reducing the range
        - Leave 2 elements in the search space for comparison
Q4. First Target
    - Ket: Don't rule out the possible result when reducing the range
Q5. Last Target

Learn By Analogy II
Q6. Closest K Elements to Target
Q7. Smallest Element that is Larger than Target

Learn By Analogy III
Q8. k-th Smallest Element in Two Sorted Arrays
Q9. (Q6 Second solution) Closest K Elements to Target
Q10. Binary Search with Unknown Size