package com.cecilia.QueueStack;

import java.util.LinkedList;

public class StackByTwoQueues {

    LinkedList<Integer> q1;
    LinkedList<Integer> q2;

    public StackByTwoQueues() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public boolean isEmpty() {
        return q1.isEmpty() && q2.isEmpty();
    }

    public void push(int e) {
        q1.offerLast(e);
    }

    public int peek() {
        // Move all elements to q2
        int tmp = 0;
        while (!q1.isEmpty()) {
            tmp = q1.pollFirst();
            q2.offerLast(tmp);
        }
        // Swap q1 & q2
        q1 = q2;
        q2 = new LinkedList<>();
        return tmp;
    }

    public int push() {
        // Move all elements except the last one to q2
        int tmp = 0;
        while (!q1.isEmpty()) {
            tmp = q1.pollFirst();
            if (!q1.isEmpty()) {
                q2.offerLast(tmp);
            }
        }
        // Swap the reference of q1 & q2
        q1 = q2;
        q2 = new LinkedList<>(); // use q1 or declare a new space block?
        return tmp;
    }
}
