import java.util.Arrays;

public class Sorting {
    private static final int SIZE = 10;
    private static final int CAPACITY = 50_000;

    public static void run() {
        // Generate 5,000 random numbers
        int[] arr = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            int insert = (int) (Math.random() * CAPACITY);
            arr[i] = insert;
        }
        // Print out the first 100 numbers
        System.out.print("Original Array: [");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.print("...");
        System.out.println("]");

        // Run sorting algorithms
        int[] copy = Arrays.copyOf(arr, arr.length);
        long start = System.nanoTime();
        bubbleSort(copy);
        long end = System.nanoTime();
        System.out.println("Bubble Sort: " + (end - start) + "ns");

        copy = Arrays.copyOf(arr, arr.length);
        start = System.nanoTime();
        insertionSort(copy);
        end = System.nanoTime();
        // selectionSort(arr);
        // mergeSort(arr);
        printArray(arr);
    }

    private static void printArray(int[] arr) {
        // Print out the first 100 numbers
        int max = Math.min(100, arr.length);
        System.out.print("Array: [");
        for (int i = 0; i < max; i++) {
            System.out.print(arr[i] + ", ");
        }
        if (arr.length > 100) {
            System.out.print("...");
        }
        System.out.println("]");
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length - i; j++) {
                if (arr[i] > arr[j]) {
                    // Swap
                    swap(arr, i, j);
                }
            }
        }
    }

    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }

            arr[j + 1] = key;
        }
    }

    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            // Swap
            if (minIdx != i) {
                swap(arr, i, minIdx);
            }
        }
    }

    private static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int left, int right) {
        // Base Case
        System.out.println("right - left = " + (right - left));
        if (right - left < 1) {
            return;
        }
        // Split the array down the middle
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        // Merge
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int leftArrSize = mid - left + 1;
        int rightArrSize = right - mid;
        // Make temp arrays
        int[] leftArr = new int[leftArrSize];
        int[] rightArr = new int[rightArrSize];
        // Copy data
        for (int i = 0; i < leftArrSize; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int i = 0; i < rightArrSize; i++) {
            rightArr[i] = arr[mid + 1 + i];
        }

        // Sort arrays into original array
        int i = 0, j = 0, k = left;
        while (i < leftArrSize && j < rightArrSize) {
            if (leftArr[i] < rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Add any remaining elements
        while (i < leftArrSize) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < rightArrSize) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
}
