public class NonTraditionalSorting {
    public static void run() {
        // Generate 5,000 random numbers between 0 and 500
        int[] arr = new int[5_000];
        for (int i = 0; i < 5_000; i++) {
            int insert = (int) (Math.random() * 50_000);
            arr[i] = insert;
        }
        System.out.println("Original Array: ");
        printArray(arr);

        // Run counting sort
        long start = System.nanoTime();
        countingSort(arr);
        long end = System.nanoTime();
        System.out.println("Array after counting sort: ");
        printArray(arr);
        System.out.println();

        System.out.println("Counting Sort: \t\t\t" + (end - start) + "ns");
    }

    public static void printArray(int[] arr) {
        // Print out every 100th number
        System.out.print("[");
        for (int i = 0; i < arr.length; i += 100) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println("]");

    }

    private static void countingSort(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        // Find max and min
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];

            if (arr[i] < min)
                min = arr[i];
        }

        // Create count array
        int[] count = new int[max - min + 1];

        // Count occurences
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }

        // Modify count array
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Create output array
        int[] output = new int[arr.length];

        // Fill output array
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        // Copy output array to original array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }
}
