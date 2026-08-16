package com.noobdevs.heap;

import java.util.Arrays;

public class MaxHeap<T extends Comparable<T>> {

    private final int capacity;
    private int size;
    private T[] elements;

    public MaxHeap() {
        this(5);
    }

    @SuppressWarnings("unchecked")
    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.elements = (T[]) new Comparable[this.capacity];
    }

    private boolean isEmpty() {
        return size <= 0;
    }

    private boolean isFull() {
        return size >= elements.length;
    }

    private void resize() {
        elements = Arrays.copyOf(elements, size * 2);
    }

    private void swap(int indexA, int indexB) {
        T temp = elements[indexA];
        elements[indexA] = elements[indexB];
        elements[indexB] = temp;
    }

    public void push(T newItem) {
        if (isFull()) resize();
        elements[size++] = newItem;
        heapifyUp();
    }

    public T poll() {
        if (isEmpty()) return null;
        T removedItem = elements[0];
        elements[0] = elements[--size];
        heapifyDown(0);
        return removedItem;
    }

    public T peek() {
        if (isEmpty()) return null;
        return elements[0];
    }

    private void heapifyUp() {
        int index = size - 1;

        while (index >= 0 && elements[index].compareTo(elements[(index - 1) / 2]) > 0) {
           swap(index, (index -1) / 2);
           index = (index - 1) / 2;
        }
    }

    private void heapifyDown(int parentIndex) {
        int largestIndex = parentIndex;
        int leftIndex = 2 * parentIndex + 1;
        int rightIndex = 2 * parentIndex + 2;

        if (leftIndex < size && elements[leftIndex].compareTo(elements[largestIndex]) > 0) {
            largestIndex = leftIndex;
        }

        if (rightIndex < size && elements[rightIndex].compareTo(elements[largestIndex]) > 0) {
            largestIndex = rightIndex;
        }

        if (largestIndex != parentIndex) {
            swap(parentIndex, largestIndex);
            heapifyDown(largestIndex);
        }
    }
}