
/**
 * COSC 600 Assignment 2
 * Solution.java
 * Purpose: Main class to run the programs
 * 
 * @author Josue Molina Morales
 * 
 * This program starts by displaying a menu for the user. The user can then select which problem they would like to run.
 * The program will then run the selected problem and display the output.
 * 
 * Problem 1: Longest Increasing Sequence
 * This problem generates a random 15x15 grid of numbers between 0 and 1000. It then finds the longest increasing sequence
 * in the grid using DFS. It then displays the sequence and its length.
 * 
 * Problem 2: Maximum Subsequence Sum Program
 * This problem generates a random array of 100,000 integers between -1000 and 1000. It then finds the maximum subsequence
 * sum using 3 different algorithms. It then displays the sum and the time it took to run each algorithm.
 * NOTE: This problem takes a while to run.
 * 
 * Problem 3: Josephus Problem
 * This problem asks the user for the number of soliders and their names. It then asks for the elimination position. It then
 * eliminates soliders using a queue until there is only one left. It then displays the name of the last solider.
 *  
 * To Run the program:
 * `make run`
 * 
 * If you have issues running the program with make, you can run it manually:
 * `cd src`
 * `javac Solution.java`
 * `java Solution`
 */
import java.util.Scanner;

/**
 ** Add Assignment Info Here**
 */
public class Solution {
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            // Print menu
            System.out.println("=================== Menu ===================");
            System.out.println("1. Longest Increasing Sequence");
            System.out.println("2. Maximum Subsequence Sum Program (Takes a while to run)");
            System.out.println("3. Josephus Problem");
            System.out.println("4. Exit");
            System.out.print(">>> ");

            // Get user input
            String input = in.nextLine();

            System.out.println("=================== Output ===================");
            // Run the program based on user input
            switch (input) {
                case "1":
                    LongestIncSeq.run();
                    break;
                case "2":
                    MaxSubSeq.run();
                    break;
                case "3":
                    JosephusProblem.run();
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input");
                    running = false;
                    break;
            }

        }
        // Close the scanner
        in.close();

    }
}
