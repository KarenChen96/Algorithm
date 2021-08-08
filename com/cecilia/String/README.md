# String

Similar to "hash table", "string" is an algorithmic concept (Abstract Data Structure), it has different implementation in different language:
- C++: std::string, char[]
- Java: String, StringBuilder, StringBuffer, char[]

Extension: How are characters are represented?
1. ASCII representation as a letter
2. Unicode: the latest version of Unicode contains a repertoire of more than 110,000 characters covering 100 scripts and various symbols.

### Common Problems (Similar to Array problems, usually need two pointers to solve)
1. Char Removal
    1.1 remove some particular chars from a string
    1.2 remove all leading/trailing/duplicated empty spaces from a string
2. De-duplication: aaaabbbb_ccc -> ab_c
3. Substring
    3.1 regular method
    3.2 Rabin-Carp (hash based string matching) & KMP (Knuth-Morris-Pratt)
    Rabin-Carp: A string-searching/matching algorithm. Keywords: A hash function (convert a string to a numeric value), rolling hash
4. Reversal (swap)
    4.1 apple --> elppa 
        - Iterative solution & Recursive Solution
    4.2 I love yahoo -> yahoo love I 
        Solution: Reverse whole string -> Reverse each word
    4.3 abcdef --> efabcd (shift letters to the right-hand-side by two positions) 
        - Solution: Similar to "I love yahoo", imagine inserting an empty space
5. replace empty space "" with "%20"

### Advanced Problems
1. Shuffling: Move letters around (See Merge Sort Implementation class)
    1.1 ABCD1234 -> A1B2C3D4
    1.2 A1B2C3D4E5 -> ABCDE12345
        - Idea & Code is simple, but the logic of swapping values correctly is delicate.
        - The workflow is clear once really figured out the rule of partition. (我们始终遵循同一套计算mid的逻辑，因此可以确保左右部分需要的元素数量。)

2. Permutation (use DFS)

3. Decoding/Encoding aaaabcc -> a4b1c2
    Notes:
    - Convert a int count to a char, do it correctly! --> (char) ('0' + count); 

4. Sliding windows using slow/fast pointers
    4.1 Longest substring that contains only unique chars
    4.2 Find all anagrams of a substring s2 in a long string s1
    4.3 Given a 0-1 array, you can flip at most k 0's to 1's. Please find the longest sub-array that consists of all 1's.

5. Matching (*, ?)

6. etc


TO-DO: 
1. String practice class review, common classes and their APIs
2. String II problems
3. String I
    - string-matching problem: consider if there are more than one matches

08/04 practice leftover
3. General String shuffling in BasicOperations.java
4. Check the given solution of ABCD1234 --> A1B2C3D4, my implementation seems not the same as the idea given in the theory class.
5. Check given solutions of findAnagrams and longestAllOnes