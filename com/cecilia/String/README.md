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
    - Notes:
        - Convert a int count to a char, do it correctly! --> (char) ('0' + count); 

4. Sliding windows using slow/fast pointers

    4.1 Longest substring that contains only unique chars
    
    4.2 Find all anagrams of a substring s2 in a long string s1
        
    - A little trick here: 
        - when move the right border, only need to consider match++ case;
        - when move the left border, only need to consider match-- case;
    - Underneath logic: ??? :question:
    
    4.3 Given a 0-1 array, you can flip at most k 0's to 1's. Please find the longest sub-array that consists of all 1's.
    - The given solution is really really elegant and concise! :heart_eyes:

5. Matching (*, ?)

6. etc


TO-DO: 
1. String practice class review Part II
3. General String shuffling in BasicOperations.java
4. Check the given solution of ABCD1234 --> A1B2C3D4, my implementation seems not the same as the idea given in the theory class.
5. String I
    - string-matching problem: consider if there are more than one matches
    Implement: find all substring matches if matches are more than one(in BasicOperations)
6. Think: why the given solution of findAnagrams is implemented in that way?


<br />
----

# Strings in Java
## Basic Concept
- A string is a sequence of characters.
- In java,they are objects (String.java). Key points: 
    1. char array can be reused; 
    2. char array is declared as final.

            class String {
                private final char[] value;
                // Different String objects can reuse the same char array,
                // but can start with different offset index.
                private int offset;
                private int count;
                public String() {
                    value = new char[0];
                    offset = 0;
                    count = 0;
                }
            }

## Creating Strings
How many different ways can we create a String? Will a new String object be generated every time we create a string?

### String literal
- A series of characters, e.g., String s1 = "abdsafd";
- Pooling for String objects
    - There is no need to maintain several copies of String objects with the same literal, because String objects are **immutable**.
    - Usually compiler and JVM wil optimize for the number of String objects created, it will maintain an **intern area** in **HEAP**, for the same String literal it will only has one String object associated.
    - The String objects created with "new" will not use the intern pool.

### Concatenate
- There are 2 concatenation case: compile time concat, and runtime concat.
    
    The optimization is at compile time, the literals will be concatenated if possible before getting the String object.
    
        String sa = "a"; 
        String sb = "b";
        String sab = "a" + "b"; // Compile time concat, equals to: String sab = "ab";
        String sab2 = sa.concat(sb); // Runtime concat
        String sab3 = sa + "b"; // Runtime concat, <=> sa.concat(sb)
        String sab4 = sa + sb; // Runtime concat

        System.out.println(sab == "a" + "b"); // T
        System.out.println(sab == sa + "b"); // F
        System.out.println(sab == sa + sb);// F

- Concat complexity

        // type conversion & String operation complexity
        String s1 = 1 + "def"; // String.valueOf(1), (1 -> Integer(1) -> Integer(1).toString()) --> "1"
        String s2 = "id: " + 2 + "isStudent: " + true + 'y'; // Can be optimized

    1. Type conversion
        - See section _Convert Number to/from Strings_
    2. String operation complexity & Optimization
        - Time: O(N^2* M), N strings, average length is M
        - Optimization (at compile time?) (See section _StringBuilder_)
            - new StringBuilder().append("id: ").append(2).append("isStudent: ").append(true).append('y).toString(); 
            - Time: O(n * m), Space: O(n * m)

----
All ways listed below will create String objects with "new", hence intern area will not be involved.
### Constructors
- String()
- String(String value)
- String(char[] array)
- String(char[] array, int offset, int length)
- String(StringBuilder builder)

### Convert from other types to String
- charArray.toString()
- stringBuilder.toString()
- ...

<br />

## String Conversion

### Convert Number to/from Strings
|Type| To String | From String|
---|---|--- 
Number - Primitive | int i = 0;<br> String si = i + ""; <br>String si = String.valueOf(i);<br> String si = Integer.toString(i);| Integer.parseInt(si); //return int
Number - wrapper class | Integer integer = null;<br> String si = String.valueOf(integer);<br> String si = integer.toString(); | Integer.valueOf(si); // return Integer


Same for other primitive types: Byte, Short, Long, Float, Double, Boolean
### Chars and substrings
- String substring(int beginIndex, int endIndex): endIndex in exclusive
- String substring(int beginIndex) 
    - Before Java 7u6, a substring will reuse the char array maintained by the original string, and maintain the offset and the length. **Time complexity = O(1), old string cannot be garbage-collected.**
    - After Java 7u6, a substring will make a copy of the part of the original string's char array. **Time complexity = O(N), old string can be gc-ed.**
- char charAt(int index)

### String Comparison
- equals: String class overrides (abstarct class) Object equals() (and overrides hashCode() for comparing). s1.equals(s2) will compare characters in two strings.

- ==: Compares the address of two string objects.

<br />
----

## String Operations & StringBuilder/StringBuffer
Common String APIs:
- boolean endsWith(String suffix)
- boolean startsWith(String prefix)
- int comprareTo(String anotherString)
    - Compares two strings lexicographically. The comparison is based on the Unicode value of each character in the strings.
    - Returns 0 exactly when the equals(Object obj) method would return true.
    - Return value of compareTo():
        - If they differ at some index positions, let k be the smallest such index. Returns this.charAt(k) - anotherStr.charAt(k).
        - Otherwise, returns this.length() - anotherStr.length().
- int comprareToIgnoreCase(String anotherString)
- int equals(Object object)
- int equalsIgnoreCase(Object object)
- String[] split(String regex)
- String[] split(String regex, int limit)
- String trim()
- ... 

For any method provided by String class, it is not modifying the String object's value, it always returns a new String object if needed. We need some kinds of intermediate class that is modifiable since String is immutable. --> StringBuilder/StringBuffer

### StringBuilder/StringBuffer
- StringBuilder: "String" that can be modified, varaible-length arrays that contain a sequence of characters.
- Common APIs
    - append()
    - insert()
    - delete()
    - deleteCharAt()
    - reverser()
    - replace(int start, int end, String str)
    - setLength()
- Difference between StringBuilder & StringBuffer: StringBuffer is thread-safe, each of he methods are synchronized. StringBuilder is not thread-safe, is is intended to be used in single thread environment.
- Internally, StringBuilder is very similar to ArrayList ("re-sizable" array)