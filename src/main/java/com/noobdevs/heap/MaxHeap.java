package com.noobdevs.heap;

public class MaxHeap<T extends Comparable<T>> {

    private int capacity;
    private int size;
    private T[] elements;


    @SuppressWarnings("unchecked")
    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.elements = (T[]) new Comparable[capacity];
    }

    public void insert(T newElement) {
        if (size >= capacity) return;
        elements[size++] = newElement;
        heapifyUp();
    }

    public T pop() {
        if (size <= 0) return null;
        T removed = elements[0];
        elements[0] = elements[--size];
        heapifyDown(0);
        return  removed;
    }

    private void heapifyUp() {
        int index = size - 1;
        while (index >= 0 && elements[index].compareTo(elements[(index - 1) / 2]) > 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapifyDown(int index) {
        int largerIndex = index;
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        if (leftIndex < size && elements[leftIndex].compareTo(elements[largerIndex]) > 0) {
            largerIndex = leftIndex;
        }

        if (rightIndex < size && elements[rightIndex].compareTo(elements[largerIndex]) > 0) {
            largerIndex = rightIndex;
        }

        if (largerIndex != index) {
            swap(index, largerIndex);
            heapifyDown(largerIndex);
        }
    }

    private void swap(int indexA, int indexB) {
        T temp = elements[indexA];
        elements[indexA] = elements[indexB];
        elements[indexB] = temp;
    }

    public T peek() {
        if (size <= 0) return null;
        return elements[0];
    }


}