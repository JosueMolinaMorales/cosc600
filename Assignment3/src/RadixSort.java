public class RadixSort {
    public static void run() {
        // Generate 5,000 random numbers between 0 and 50,000
        int[] arr = new int[5_000];
        for (int i = 0; i < 5_000; i++) {
            int insert = (int) (Math.random() * 50_000);
            arr[i] = insert;
        }
        System.out.println("Original Array: ");
        printArray(arr);
        System.out.println();

        // Run radix sort
        long start = System.nanoTime();
        radixSort(arr);
        long end = System.nanoTime();
        System.out.println("Array after radix sort: ");
        printArray(arr);
        System.out.println();

        System.out.println("Radix Sort: \t\t\t" + (end - start) + "ns");

    }

    private static void printArray(int[] arr) {
        // Print the first 100 numbers
        System.out.print("[");
        for (int i = 0; i < 100; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println("...");
        // Print the last 100 numbers
        for (int i = arr.length - 100; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println("]");
    }

    private static void radixSort(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];

        }
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }
    }

    private static void countSort(int[] arr, int exp) {
        int[] count = new int[10];
        int[] output = new int[arr.length];

        // Count occurences
        for (int i = 0; i < arr.length; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        // Modify count array
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Create output array
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy output array to arr
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }
}
