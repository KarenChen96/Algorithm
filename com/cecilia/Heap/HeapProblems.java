package com.cecilia.Heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class HeapProblems {
    public int[] kSmallestNHeap(int[] array, int k) {
        int[] result = new int[k];
        PriorityQueue<Integer> minheap = new PriorityQueue<>();
        for (int i = 0; i < array.length; i++) {
            minheap.offer(array[i]);
        }
        for (int i = 0; i < k; i++) {
            result[i] = minheap.poll();
        }
        return result;
    }

    public int[] kSmallestKHeap(int[] array, int k) {
        PriorityQueue<Integer> maxheap = new PriorityQueue<>(k, Collections.reverseOrder());
        for (int i = 0; i < k; i++) {
            maxheap.offer(array[i]);
        }
        for (int i = k; i < array.length; i++) {
            if (array[i] < maxheap.peek()) {
                maxheap.poll(); // poll manually. What if not poll but offer directly? --> auto-scale?
                maxheap.offer(array[i]);
            }
        }
        return toArray(maxheap);
    }

    private int[] toArray(PriorityQueue<Integer> pq) {
        int[] result = new int[pq.size()];
        for (int i = pq.size()-1; i>= 0; i--) {
            result[i] = pq.poll();
        }
        return result;
    }
}
