import java.util.ArrayList;

public class BinaryHeap {
    private ArrayList<Integer> heap;

    BinaryHeap() {
        this.heap = new ArrayList<Integer>();
    }

    public static void run() {
        // Generate 5,000 random numbers
        int[] insertOrder = new int[5_000];
        for (int i = 0; i < 5_000; i++) {
            int insert = (int) (Math.random() * 50_000);
            insertOrder[i] = insert;
        }
        // Print out the first 50 numbers
        System.out.print("Original Array: [");
        for (int i = 0; i < 50; i++) {
            System.out.print(insertOrder[i] + ", ");
        }
        System.out.print("...");
        System.out.println("]");
        System.out.println();

        // Create first binary heap and insert numbers one by one
        long start = System.nanoTime();
        BinaryHeap bh = new BinaryHeap();
        int swaps = 0;
        for (int i = 0; i < insertOrder.length; i++) {
            swaps += bh.insert(insertOrder[i]);
        }
        long end = System.nanoTime();
        System.out.println(end - start + " nanoseconds to insert 5,000 numbers into a binary heap");
        System.out.println(swaps + " swaps to insert 5,000 numbers into a binary heap");
        System.out.println("First 50 elements of the heap:");
        bh.printHeap();
        System.out.println();
        // Create second binary heap and build heap
        start = System.nanoTime();
        BinaryHeap bh2 = new BinaryHeap();
        swaps = bh2.buildHeap(insertOrder);
        end = System.nanoTime();

        System.out.println(end - start + " nanoseconds to build a binary heap from 5,000 numbers");
        System.out.println(swaps + " swaps to build a binary heap from 5,000 numbers");
        System.out.println("First 50 elements of the heap:");
        bh2.printHeap();
    }

    public int insert(int item) {
        // Insert Item to end of heap
        this.heap.add(item);

        // Heapify Up
        int swaps = this.heapifyUp(this.heap.size() - 1, 0);
        return swaps;
    }

    public int buildHeap(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            this.heap.add(arr[i]);
        }

        return this.buildHeapLinear();
    }

    private int buildHeapLinear() {
        // Get starting index
        int idx = this.parent(this.heap.size() - 1);
        int swaps = 0;
        while (idx >= 0) {
            swaps += this.heapifyDown(idx, 0);
            idx--;
        }
        return swaps;
    }

    private int heapifyDown(int current, int swaps) {
        if (current >= this.heap.size()) {
            return swaps;
        }

        // Get children
        int left = this.getLeftChild(current);
        int right = this.getRightChild(current);

        int leftVal = left < this.heap.size() ? this.heap.get(left) : Integer.MAX_VALUE;
        int rightVal = right < this.heap.size() ? this.heap.get(right) : Integer.MAX_VALUE;

        // Swap with the smallest child
        int smallestChild = Math.min(leftVal, rightVal);

        if (smallestChild < this.heap.get(current)) {
            int child = this.heap.get(left) == smallestChild ? left : right;
            // Swap
            int temp = this.heap.get(current);
            this.heap.set(current, this.heap.get(child));
            this.heap.set(child, temp);
            swaps += this.heapifyDown(child, swaps + 1);
        }

        return swaps;
    }

    private int heapifyUp(int current, int swaps) {
        // If root return
        if (current == 0) {
            return swaps;
        }
        // Look at parent
        int parentIdx = this.parent(current);

        if (this.heap.get(parentIdx) > this.heap.get(current)) {
            // Swap
            int temp = this.heap.get(current);
            this.heap.set(current, this.heap.get(parentIdx));
            this.heap.set(parentIdx, temp);
            swaps += this.heapifyUp(parentIdx, swaps + 1);
        }
        return swaps;
    }

    private int getLeftChild(int parent) {
        return parent * 2 + 1;
    }

    private int getRightChild(int parent) {
        return parent * 2 + 2;
    }

    private int parent(int child) {
        return (child - 1) / 2;
    }

    /**
     * Prints out the first 50 elements of the heap
     */
    public void printHeap() {
        System.out.print("[");
        for (int i = 0; i < 50; i++) {
            System.out.print(this.heap.get(i) + ", ");
        }
        if (this.heap.size() > 50) {
            System.out.print("...");
        }
        System.out.println("]");
    }
}
