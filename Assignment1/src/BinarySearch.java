package src;

import java.util.Random;

public class BinarySearch {
    private int[] arr = new int[1_000_000];

    BinarySearch() {
        // Generate an array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
    }

    public void runAlgorithm() {
        int countSum = 0;
        for (int i = 0; i < 100; i++) {
            // Generate a random number to find between 0 and 1_000_000
            int randomNumber = new Random().nextInt(arr.length) + 1;
            // Call the binary search method
            int count = binarySearch(randomNumber, arr, 0, arr.length - 1, 0);
            System.out.println("The number of iterations is " + count);
            countSum += count;
        }
        System.out.println("The average number of iterations is " + countSum / 100);
    }

    private int binarySearch(int target, int[] arr, int low, int high, int count) {
        int mid = (low + high) / 2;
        if (arr[mid] == target) {
            System.out.println("Found the number: " + target);
            return count;
        } else if (arr[mid] > target) {
            System.out.println("The number is less than " + arr[mid]);
            return binarySearch(target, arr, low, mid - 1, count + 1);
        } else {
            System.out.println("The number is greater than " + arr[mid]);
            return binarySearch(target, arr, mid + 1, high, count + 1);
        }
    }
}
