package com.cecilia.QueueStack;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackWithMin {
    private Deque<Integer> stack;
    private Deque<Integer> minStack; // Store newer&smaller elements
    private int currMin = Integer.MAX_VALUE;

    public StackWithMin() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public Integer top() {
        return stack.peekLast();
    }

    public Integer pop() {
        // Update currMin
        int tmp  = stack.peekLast();
        if (tmp == currMin) {
            minStack.pollLast();
            if (minStack.isEmpty()) {
                currMin = minStack.peekLast();
            } else {
                currMin = Integer.MAX_VALUE;
            }
        }
        return stack.pollLast();
    }

    public void push(int e) {
        if (e < currMin) {
            minStack.offerLast(e);
            currMin = e;
        }
        stack.offerLast(e);
    }

    public Integer min()
    {
        return stack.isEmpty() ? null : currMin;
    }
}
