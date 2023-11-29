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

        // Create and insert into the first binary heap
        long insertStart = System.nanoTime();
        BinaryHeap bh1 = new BinaryHeap();
        int insertSwaps = 0;
        for (int i = 0; i < insertOrder.length; i++) {
            insertSwaps += bh1.insert(insertOrder[i]);
        }
        long insertEnd = System.nanoTime();

        System.out.println("Inserting 5,000 numbers into a binary heap:");
        System.out.println("Time: \t\t\t\t" + (insertEnd - insertStart) + " nanoseconds");
        System.out.println("Swaps: \t\t\t\t" + insertSwaps + " swaps");
        System.out.println("First 50 elements of the heap:");
        bh1.printHeap();
        System.out.println();

        // Create and build the second binary heap
        long buildStart = System.nanoTime();
        BinaryHeap bh2 = new BinaryHeap();
        int buildSwaps = bh2.buildHeap(insertOrder);
        long buildEnd = System.nanoTime();

        System.out.println("Building a binary heap from 5,000 numbers:");
        System.out.println("Time: \t\t\t\t" + (buildEnd - buildStart) + " nanoseconds");
        System.out.println("Swaps: \t\t\t\t" + buildSwaps + " swaps");
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
