package com.cecilia.QueueStack;

import java.util.LinkedList;

public class StackByTwoQueues {

    LinkedList<Integer> q1;
    LinkedList<Integer> q2;

    public boolean isEmpty() {
        return q1.isEmpty() && q2.isEmpty();
    }

    public void offer(int e) {
        q1.addLast(e);
    }

    public int peek() {
        // Move all elements to q2
        int tmp = 0;
        while (!q1.isEmpty()) {
            tmp = q1.pollFirst();
            q2.addLast(tmp);
        }
        return tmp;
    }

    public int poll() {
        // Move all elements except the last one to q2
        int tmp = 0;
        while (!q1.isEmpty()) {
            tmp = q1.pollFirst();
            if (!q1.isEmpty()) {
                q2.addLast(tmp);
            }
        }
        return tmp;
    }
}
