package com.cecilia.QueueStack;

import java.util.LinkedList;

public class DequeByTwoStacks {
    private LinkedList<Integer> s1; // handle offer/pollFirst
    private LinkedList<Integer> s2; // handle offer/pollLast

    public DequeByTwoStacks() {
        s1 = new LinkedList<>();
        s2 = new LinkedList<>();
    }

    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    public void offerFirst(int e) {
        s1.offerFirst(e);
    }

    public void offerLast(int e) {
        s2.offerFirst(e);
    }

    public int peekFirst() {
        if (!s1.isEmpty()) {
            return s1.peekFirst();
        }
        moveElements(s2, s1);
        return s1.peekFirst();
    }

    public int peekLast() {
        if (!s2.isEmpty()) {
            return s2.peekFirst();
        }
        moveElements(s1, s2);
        return s2.peekFirst();
    }

    public int pollFirst() {
        if (!s1.isEmpty()) {
            return s1.pollFirst();
        }
        moveElements(s2, s1);
        return s1.pollFirst();
    }

    public int pollLast() {
        if (!s2.isEmpty()) {
            return s2.pollFirst();
        }
        moveElements(s1, s2);
        return s2.pollFirst();
    }

    private void moveElements(LinkedList<Integer> source, LinkedList<Integer> target) {
        while (!source.isEmpty()) {
            target.offerFirst(source.pollFirst());
        }
    }
    
}
