
/*
    @Author: Josue Molina Morales
    Programming Assignment 3 - Sorting
    
    This program will display a menu with 5 options:
        1. Run BinaryHeap
        2. Run Sorting
        3. Run NonTraditionalSorting
        4. Run RadixSort
        5. Exit
    
    The user will be prompted to enter a choice. The program will then execute the selected option.

    BinaryHeap:
        The program will generate 5,000 random numbers between 0 and 50,000. It will then insert the numbers into a binary heap and print the first 50 numbers. 
        It will then build a binary heap from the same numbers and print the first 50 numbers.
        Each method will be timed and the number of swaps will be counted, and the results will be printed.

    Sorting:
        The program will generate 5,000 random numbers between 0 and 50,000. It will then run the following sorting algorithms on the numbers:
            - Bubble Sort
            - Insertion Sort
            - Selection Sort
            - Merge Sort
        Each method will be timed and the results will be printed.

    NonTraditionalSorting:
        The program will generate 5,000 random numbers between 0 and 50,000. It will then run the following sorting algorithm on the numbers:
            - Counting Sort
        Each method will be timed and the results will be printed.

    RadixSort:
        The program will generate 5,000 random numbers between 0 and 50,000. It will then run the following sorting algorithm on the numbers:
            - Radix Sort
        Each method will be timed and the results will be printed.
 */
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();

            // Get user choice
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            // Execute the selected option
            switch (choice) {
                case 1:
                    BinaryHeap.run();
                    break;
                case 2:
                    Sorting.run();
                    break;
                case 3:
                    NonTraditionalSorting.run();
                    break;
                case 4:
                    RadixSort.run();
                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        // Print menu in a box
        System.out.println("=============================");
        System.out.println("1. Run BinaryHeap");
        System.out.println("2. Run Sorting");
        System.out.println("3. Run NonTraditionalSorting");
        System.out.println("4. Run RadixSort");
        System.out.println("5. Exit");
        System.out.println("=============================");
    }
}
