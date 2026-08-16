package com.noobdevs.heap;

public class Practice {

    private MaxHeap<Integer> heap;
    private int size;

    public Practice() {
        this(5);
    }

    public Practice(int size) {
        this.size = size;
        this.heap = new MaxHeap<>(size);
    }

    public void add(int newNum) {
        heap.insert(newNum);
    }

    public int max() {
        return heap.peek();
    }

    static void main() {
        Practice practice = new Practice(3);
        practice.add(200);
        practice.add(100);
        System.out.println(practice.max());
        practice.add(500);
        System.out.println(practice.max());
    }

}
