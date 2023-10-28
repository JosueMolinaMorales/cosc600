public class MaxSubSeq {
    public static void run() {
        int[] sizes = { 5_000, 10_000, 50_000, 100_000, 200_000 };

        for (int n : sizes) {
            // Generate array of size n
            int[] arr = new int[n];
            // Fill the array with random numbers from -5000 to 5000
            for (int i = 0; i < n; i++) {
                arr[i] = (int) (Math.random() * 10_000) - 5_000;
            }
            System.out.println("==================== Array Size: " + n + " ====================");
            // Run algorithm 1
            long startTime = System.nanoTime();
            MaxSubSeq.algorithmOne(arr);
            long endTime = System.nanoTime();
            System.out.println("Algorithm 1 Time: " + (endTime - startTime) / 1_000_000.0 + "ms");

            // Run algorithm 2
            startTime = System.nanoTime();
            MaxSubSeq.algorithmTwo(arr);
            endTime = System.nanoTime();
            System.out.println("Algorithm 2 Time: " + (endTime - startTime) / 1_000_000.0 + "ms");

            // Run algorithm 3
            startTime = System.nanoTime();
            MaxSubSeq.algorithmThree(arr);
            endTime = System.nanoTime();
            System.out.println("Algorithm 3 Time: " + (endTime - startTime) / 1_000_000.0 + "ms");

            // Run algorithm 4
            startTime = System.nanoTime();
            MaxSubSeq.algorithmFour(arr);
            endTime = System.nanoTime();
            System.out.println("Algorithm 4 Time: " + (endTime - startTime) / 1_000_000.0 + "ms");
        }
    }

    /**
     * Algorithm 1. Brute Force Algorithm (O(N^3))
     * 
     * @param arr The array to find the max subsequence sum of
     */
    public static void algorithmOne(int[] arr) {
        int maxSum = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int thisSum = 0;

                for (int k = i; k <= j; k++) {
                    thisSum += arr[k];
                }

                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }

        System.out.println("Algorithm 1 MaxSum: " + maxSum);
    }

    /**
     * Algorithm 2. Improved Brute Force Algorithm (O(N^2))
     * 
     * @param arr The array to find the max subsequence sum of
     */
    public static void algorithmTwo(int[] arr) {
        int maxSum = 0;

        for (int i = 0; i < arr.length; i++) {
            int thisSum = 0;

            for (int j = i; j < arr.length; j++) {
                thisSum += arr[j];

                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }

        System.out.println("Algorithm 2 MaxSum: " + maxSum);
    }

    /**
     * Algorithm 3. Wrapper for recursive divide and conquer algorithm (O(NlogN))
     * 
     * @param arr The array to find the max subsequence sum of
     */
    public static void algorithmThree(int[] arr) {
        int maxSum = algorithmThreeRecursive(arr, 0, arr.length - 1);

        System.out.println("Algorithm 3 MaxSum: " + maxSum);
    }

    /**
     * Algorithm 3. Recursive Divide and Conquer Algorithm
     * 
     * @param arr   The array to find the max subsequence sum of
     * @param left  The left index of the array
     * @param right The right index of the array
     */
    private static int algorithmThreeRecursive(int[] arr, int left, int right) {
        if (left == right) {
            if (arr[left] > 0) {
                return arr[left];
            } else {
                return 0;
            }
        }

        int center = (left + right) / 2;
        int maxLeftSum = algorithmThreeRecursive(arr, left, center);
        int maxRightSum = algorithmThreeRecursive(arr, center + 1, right);

        int maxLeftBorderSum = 0;
        int leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += arr[i];

            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }

        int maxRightBorderSum = 0;
        int rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += arr[i];

            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }

        return max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);
    }

    /**
     * Returns the maximum of three integers
     * 
     * @return The maximum of the three integers
     */
    private static int max3(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    /**
     * Algorithm 4. Linear Time Algorithm
     * 
     * @param arr The array to find the max subsequence sum of
     */
    public static void algorithmFour(int[] arr) {
        int maxSum = 0;
        int thisSum = 0;

        for (int i = 0; i < arr.length; i++) {
            thisSum += arr[i];

            if (thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
        }

        System.out.println("Algorithm 4 MaxSum: " + maxSum);
    }
}
