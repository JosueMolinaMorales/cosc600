/**
    COSC 600 Assignment 1
    Solution.java
    Purpose: Main class to run the assignment problems
    @author Josue Molina Morales

    This program prompts the user to select which problem to run and then runs the selected problem.
    The program will continue to run until the user selects to exit the program.

    Problem 1: Binary Search
        Write a program to find the integer number which was generated by random number function ranged from 1 to 1,000,000. 
        Your program needs to use binary search concept and your program will answer “Lower”, “Higher”, or “Found!”. 
        Your program has to repeat this guess game for 100 times and calculate the number of guessing trial for each random number. 
        And calculate the average number of guessing trial for 100 random numbers.
    Problem 2: Decimal to Binary
        Write a program to convert a decimal number into a binary number using a recursive function. 
        (Do not use an array or a stack.)
        Example:
            Enter a positive decimal number : 57
            The binary number is 1 1 1 0 0 1
    Problem 3: Word Puzzle
        Word puzzle problem is to find the words in a two-dimensional array of characters. The
        input to this problem is two input files: one is a two-dimensional array of characters and
        the other is a list of words we will find in two-dimensional array. These words may be
        horizontal, vertical, or diagonal in any direction (for a total of eight directions).
        Let’s look at a simple example with chars:
        A 2-D 7x7 puzzle and a word list file are as follows:
        Puzzle word            file             Output
        n o h t y p s          ruby
        m i a r y c c          cave                 c
        l l e k s a h                               a
        r u b y v m e                       r u b y v
        e h h e l l m                               e
        p c j n i c e
        r e e k b i p

        Write a program to find for this problem and run your program with two given input data
        files (50 x 50 puzzleinput.txt and a list of words, wordlist.txt.). And measure the actual
        running times of your program.
    Problem 4: Majority Element
        A majority element in an array, A, of size N is an element that appears more than N/2
        times(thus, there is at most one). If there is no majority element, your program should
        indicate this. Design two different algorithms and write them in Java, C++, or Python.
        And then run these algorithms with four given sample sets(Majex1, 2, 3, 4) of input files
        and measure execution time for each case.

        Method 1 (O(NlogN) algorithm using a divided-and-conquer method. The
        algorithm begins by splitting the array in half repeatedly and calling itself on each half.
        When we get down to single elements, that single element is returned as the majority of
        its (1-element) array. At every other level, it will get return values from its two recursive
        calls.

        Method 2 (O(N)) algorithm
        This algorithm is a two step process.
        1. Get an element occurring most of the time in the array. This phase will make sure that if there is a majority element then it will return that only.
        2. Check if the element obtained from above step is majority element.
        
    To run the program, use the Makefile.
    To run the program using the Makefile, use the command "make run".

 */

package src;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        // Print Menu To select which problem to run
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("================ COSC 600 Assignment 1 Menu ================");
            System.out.println("1. Binary Search");
            System.out.println("2. Decimal to Binary");
            System.out.println("3. Word Puzzle");
            System.out.println("4. Majority Element");
            System.out.println("5. Exit");
            System.out.println("=============================================================");
            System.out.print("Enter the number of the problem you want to run: ");
            int problemNumber = Integer.parseInt(scanner.nextLine());
            System.out.println("=============================================================");
            switch (problemNumber) {
                case 1:
                    problemOne();
                    break;
                case 2:
                    problemTwo();
                    break;
                case 3:
                    problemThree();
                    break;
                case 4:
                    problemFour();
                    break;
                default:
                    scanner.close();
                    System.exit(0);
                    break;
            }
        }
    }

    public static void problemOne() {
        BinarySearch bs = new BinarySearch();
        bs.runAlgorithm();
    }

    public static void problemTwo() {
        DecimalToBinary.convertDecimalToBinary();
    }

    public static void problemThree() {
        WordPuzzle wp = new WordPuzzle();
        wp.printPuzzle();
        wp.benchmark();
    }

    public static void problemFour() {
        MajorityElement me = new MajorityElement();
        me.benchmark();
    }
}