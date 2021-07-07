package com.cecilia.QueueStack;

import java.util.LinkedList;

public class QueueByTwoStack {
    /**
     * Complexity analysis
     * offer(e): O(1)
     * peek()/poll(): Amortized time complexity: O(1)
     */

    private LinkedList<Integer> s1; // used for offer()
    private LinkedList<Integer> s2; // Reverse order, used for poll()

    public QueueByTwoStack() {
        s1 = new LinkedList<>();
        s2 = new LinkedList<>();
    }

    public int size() {
        return s1.size() + s2.size();
    }

    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    };

    public int peek() {
        if (s1.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.addFirst(s1.pollFirst());
            }
        }
        return s2.peekFirst();
    }

    public int poll() {
        // Assume that the user will check is the queue is empty before poll()
        if (s1.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.addFirst(s1.pollFirst());
            }
        }
        return s2.pollFirst();
    }

    public boolean offer(int e) {
        s1.addFirst(e);
        return true;
    }
    
}
