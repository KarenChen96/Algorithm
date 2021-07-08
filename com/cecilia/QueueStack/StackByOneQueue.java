package com.cecilia.QueueStack;

import java.util.LinkedList;

public class StackByOneQueue {
    private LinkedList<Integer> queue;

    public StackByOneQueue() {
        this.queue = new LinkedList<>();
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public int top() {
        int count = queue.size();
        int tmp = queue.peekFirst();
        while (count-- > 0) {
            tmp = queue.pollFirst();
            queue.offerLast(tmp);
        }
        return tmp;
    }

    public int pop() {
        int count = queue.size();
        while (count-- > 1) {
            queue.offerLast(queue.pollFirst());
        }
        return queue.pollFirst();      
    }

    public void push(int e) {
        queue.offerLast(e);
    }
    
}
