package com.noobdevs.heap;

import java.util.Arrays;

public class MaxHeapPractice <T extends Comparable<T>>{

    private int size;
    private T[] elements;

    public MaxHeapPractice() {
        this(5);
    }

    @SuppressWarnings("unchecked")
    public MaxHeapPractice(int capacity) {
        this.size = 0;
        this.elements = (T[]) new Comparable[capacity];
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

    public void heapifyUp() {
        int currentIndex = size - 1;
        int parentIndex = (currentIndex - 1) / 2;

        while (currentIndex > 0 && elements[currentIndex].compareTo(elements[parentIndex]) > 0) {
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = (currentIndex - 1) / 2;
        }
    }

    public void heapifyDown(int parentIndex) {
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
            swap(largestIndex, parentIndex);
            heapifyDown(largestIndex);
        }
    }
}
