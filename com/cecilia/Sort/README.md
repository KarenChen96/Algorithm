## Selection Sort

- Sort a stack with one/two additional stack(s)

## Merge Sort & its variant
1. Basic Merge Sort
    - Notes: 
        1. In order to have O[N] space complexity, we should create a helper array from the beginning, reuse it in every merge(), instead of creating a new array every time.
        2. Optimize Two pointers comparison in merge()


## Quick Sort & its variant

1. Basic Quick Sort
    - Find a pivot: 
        - Math.random(): returns a double within the range [0.0, 1.0), therefore the formula of calculating pivot index is: (int) (Math.random() * (end-begin+1)) + begin, instead of (int) (Math.random() * (end-begin)) + begin
        - A more concise way: Random.nextInt()

2. Array Shuffling/Rainbow Sort
    - Key takeaways: two (or more) pointers, move in the reverse direction

## Other Sort Algorithms
- Bubble Sort
- Heap sort
- ...

