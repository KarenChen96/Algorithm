## Problems
1. Sort with 2/3 stacks (w/o duplicates)

2. Implement a queue with 2 stacks
    - Amortized time VS. Average time
        - Amortized time: For any specific input, we amortize all operations on the input
        - Average time: The average time of all possible inputs in the input space

3. Implement a stack with 2 queues
    - :heavy_exclamation_mark: Note: use the same set of APIs
        - poll()/offer(e)/peek(): Returns a special value (null/false) when the operation fails. Designed specially for use with capacity-restricted Queue implementations.
            OR
        - remove()/add(e)/element(): Throws exceptions when the operation fails
    - In java, Stack is a deprecated class, but its APIs are: pop()/push()/top()

4. Implement a stack with 1 queue
    - Make the queue circular 

5. Implement min() function when using stack with time complexity O(1) for each operation
    - Use a normal stack to implement a stack with min() function in O(1)
    - Here, return Integer instead of int type is necessary.

6. Implement a deque with two stacks
    - Note: In the worst case: execute pollFirst() & pollLast() back and force, the amortized time complexity will be O(N).
    - Optimization: Use a third stack as a buffer to make both s1 & s2 always store half elements. (Review: time complexity analysis)


## Discussion

Q: What kind of problems can be solved with stack?
A: 从左到右linear scan一个array/string时，如果要不断回头看左边最新的元素时，往往要用到stack.
examples:
1. Histogram中找最大长方形
2. reverse polish notation 逆波兰表达式的计算. a*(b+c) --> abc*+
3. String's repeated duplication. cabba --> caa --> c
    

