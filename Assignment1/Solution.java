import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        // problemOne();
        // problemTwo();
        problemThree();
    }

    public static void problemOne() {
        // Generate an array
        int[] arr = new int[1_000_000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

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

    public static int binarySearch(int target, int[] arr, int low, int high, int count) {
        int mid = (low + high) / 2;
        if (arr[mid] == target) {
            System.out.println("Found the number: " + target);
            return count;
        } else if (arr[mid] > target) {
            System.out.println("THe number is less than " + arr[mid]);
            return binarySearch(target, arr, low, mid - 1, count + 1);
        } else {
            System.out.println("The number is greater than " + arr[mid]);
            return binarySearch(target, arr, mid + 1, high, count + 1);
        }
    }

    public static void problemTwo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a decimal number: ");
        int n = scanner.nextInt();
        System.out.println(convertDecimalToBinary(n));
        scanner.close();
    }

    /**
     * Recursive method to convert a decimal number to binary
     * 
     * @param n The decmial number to convert
     * @return The binary representation of the decimal number
     */
    public static String convertDecimalToBinary(int n) {
        if (n == 0) {
            return "0";
        }
        if (n == 1) {
            return "1";
        }
        return convertDecimalToBinary(n / 2) + (n % 2);
    }

    public static void problemThree() {
        WordPuzzle wp = new WordPuzzle();
        wp.printPuzzle();
        // Read the wordlist txt file
        try {
            File file = new File("./input/wordlist.txt");
            Scanner reader = new Scanner(file);
            ArrayList<String> words = new ArrayList<String>();
            while (reader.hasNextLine()) {
                String word = reader.nextLine();
                words.add(word.toUpperCase());
            }
            String[] wds = new String[words.size()];
            wds = words.toArray(wds);
            wp.findWords(wds);
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public static void problemFour() {

    }
}