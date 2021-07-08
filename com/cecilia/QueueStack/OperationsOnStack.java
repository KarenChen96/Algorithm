package com.cecilia.QueueStack;

import java.util.Deque;
import java.util.LinkedList;

public class OperationsOnStack {
    public static void sortWith3Stacks(Deque s) {
        
    }

    public static void sortWith2Stacks(LinkedList<Integer> s1) {
        if (s1 == null || s1.size() <= 1) {
            return;
        }
        LinkedList<Integer> s2 = new LinkedList<>();
        sort(s1, s2);
    }

    // Assume no duplicates
    protected static void sort(Deque<Integer> input, Deque<Integer> buffer) {
        // int globalMin = Integer.MAX_VALUE;
        // int count = 0;
        // Given solution: Bubble sort.
        // while (!input.isEmpty()) {
        //     int tmp = input.pollFirst();
        //     if (tmp < globalMin) {
        //         globalMin = tmp;
        //         count = 1;
        //     }
        //     buffer.addFirst(tmp);
        // }
        int count = 0;
        while (!input.isEmpty()) {
            int tmp = input.pollFirst();
            if (buffer.isEmpty() || buffer.peekFirst() >= tmp) {
                buffer.addFirst(tmp);
            } else {
                count = 0;
                while (!buffer.isEmpty() && buffer.peekFirst() < tmp) {
                    input.addFirst(buffer.pollFirst());
                    count++;
                }
                buffer.addFirst(tmp);
                while (count-- > 0) {
                    buffer.addFirst(input.pollFirst());
                }
            }
        }
        while (!buffer.isEmpty()) {
            input.addFirst(buffer.pollFirst());
        }
    }

    // Given solution: duplicates may exist
    protected static void sort2(Deque<Integer> input, Deque<Integer> buffer) {
        while (!input.isEmpty()) {
            // Traverse all unsorted elements, find current min value
            int currMin = Integer.MAX_VALUE;
            int count = 0;
            while (!input.isEmpty()) {
                int tmp = input.pollFirst();
                if (tmp < currMin) {
                    currMin = tmp;
                    count = 1;
                } else if (tmp == currMin) {
                    count++;
                }
                buffer.addFirst(tmp);
            }
            // Clear buffer: remove unsorted elements back to input stack
            while (!buffer.isEmpty() && buffer.peekFirst() >= currMin) {
                int tmp = buffer.pollFirst();
                if (tmp != currMin) {
                    input.addFirst(tmp);
                }
            }
            // Add the new sorted number in buffer stack
            while (count-- > 0) {
                buffer.addFirst(currMin);
            } 
        }

        while (!buffer.isEmpty()) {
            input.addFirst(buffer.pollFirst()); // Expected result: numbers polled from input stack are in ascending order
        }
    }

    // Given solution: duplicates may exist
    // protected static void sort3(Deque<Integer> input, Deque<Integer> buffer) {
    // }

}
