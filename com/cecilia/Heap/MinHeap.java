package com.cecilia.Heap;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MinHeap {

    private int[] array;
    private int size; // size if necessary. Otherwise we are unable to tell the actual array length. 

    public MinHeap(int cap) {
        this.array = new int[cap];
        this.size = 0;
    }

    public MinHeap(int[] input) {
        if (input == null || input.length == 0) {
            throw new IllegalArgumentException("input array can be null or empty"); // A Runtime Exception doesn't need to be handled.
        }
        this.array = input;
        this.size = input.length;
        heapify();
    }

    private void heapify() {
        // Percolate down every parent node
        // Last node index: n-1; last parent node: (n-1-1)/2
        for (int i = (size-2)/2; i >= 0; i--) {
            percolateDown(i);
        }
    }

    private void percolateDown(int index) {
        if (index > (size-2)/2) {
            return;
        }

        /* // Wrong implementation
        int lchild = array[index*2+1];
        int rchild = array[index*2+2]; // right child may not exist!!
        if (array[index] > lchild || array[index] > rchild) {
            if (lchild < rchild) {
                swap(index, index*2+1);
                percolateDown(index*2+1);
            } else {
                swap(index, index*2+2);
                percolateDown(index*2+2);
            }
        } */

        int leftChildIndex = index*2+1;
        int rightChildIndex = index*2+2;
        int swapIndex = leftChildIndex;
        if (rightChildIndex <= size-1 && array[leftChildIndex] > array[rightChildIndex]) {
            swapIndex = rightChildIndex;
        }
        if (array[index] > array[swapIndex]) {
                swap(index, swapIndex);
                percolateDown(swapIndex); // Using recursion increases space complexity
        }
    }

    private void swap(int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void offer(int value) {
        if (size == array.length) {
            // expand capacity
            // int[] newArray = new int[(int)(size*1.5)];
            // copy(array, newArray); // Arrays.copyOf(original, newLength) not works for primitive type array?
            // array = newArray;
            array = Arrays.copyOf(array, (int)(size*1.5));
        }
        array[size++] = value;
        percolateUp(size-1);
    }

    private void percolateUp(int index) {
        while (index >= 0) {
            int parentIndex = (index-1)/2;
            if (array[index] < array[parentIndex]) {
                swap(index, parentIndex);
            } else {
                break;
            }
            index = parentIndex;
        }
    }

    public int peek() {
        if (size == 0) {
            throw new NoSuchElementException("heap is empty");
        }
        return array[0];
    }

    public int poll() {
        if (size == 0) {
            throw new NoSuchElementException("heap is empty");
        }
        int result = array[0];
        array[0] = array[--size];
        percolateDown(0);
        return result;
    }

    /**
     * 
     * @param index: the index of the element to be updated
     * @param newValue: the new value
     * @return the original value
     */
    public int update(int index, int newValue) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("invalid index range");
        }

        int result = array[index];
        array[index] = newValue;
        if (result > newValue) {
            percolateDown(index);
        } else {
            percolateUp(index);
        }
        return result;
    }
    
}
