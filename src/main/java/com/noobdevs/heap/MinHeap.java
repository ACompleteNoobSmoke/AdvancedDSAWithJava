package com.noobdevs.heap;

import java.util.Arrays;

public class MinHeap <T extends Comparable<T>> {

    private final int capacity;
    private int size;
    private T[] elements;


    public MinHeap() {
        this(5);
    }

    @SuppressWarnings("unchecked")
    public MinHeap(int capacity) {
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
        elements = Arrays.copyOf(elements, size * 3);
    }

    private void swap(int indexA, int indexB) {
        T tempElement = elements[indexA];
        elements[indexA] = elements[indexB];
        elements[indexB] = tempElement;
    }

    public T peek() {
        if (isEmpty()) return null;
        return elements[0];
    }

    public void push(T newElement) {
        if (isFull()) resize();
        elements[size++] = newElement;
        heapifyUp();
    }

    public T poll() {
        if (isEmpty()) return null;
        T removedElement = elements[0];
        elements[0] = elements[--size];
        heapifyDown(0);
        return removedElement;
    }

    private void heapifyUp() {
        int currentIndex = size - 1;
        int parentIndex = (currentIndex - 1) / 2;

        while (currentIndex > 0 && elements[currentIndex].compareTo(elements[parentIndex]) < 0) {
            swap(currentIndex, parentIndex);
            currentIndex  = parentIndex;
            parentIndex = (currentIndex - 1) / 2;
        }
    }

    private void heapifyDown(int parentIndex) {
        int smallestIndex = parentIndex;
        int leftIndex = 2 * parentIndex + 1;
        int rightIndex = 2 * parentIndex + 2;

        if (leftIndex < size && elements[leftIndex].compareTo(elements[smallestIndex]) < 0) {
            smallestIndex = leftIndex;
        }

        if (rightIndex < size && elements[rightIndex].compareTo(elements[smallestIndex]) < 0) {
            smallestIndex = rightIndex;
        }

        if (smallestIndex != parentIndex) {
            swap(smallestIndex, parentIndex);
            heapifyDown(smallestIndex);
        }
    }

}
