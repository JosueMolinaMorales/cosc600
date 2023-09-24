package src;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MajorityElement {
    public String[] majexOne;
    public String[] majexTwo;
    public String[] majexThree;
    public String[] majexFour;

    public MajorityElement() {
        // Read the 4 majex input files
        try {
            String[] files = new String[] { "Majex1.txt", "Majex2.txt", "Majex3.txt", "Majex4.txt" };
            for (String f : files) {
                File file = new File("./input/" + f);
                Scanner reader = new Scanner(file);
                ArrayList<String> list = new ArrayList<String>();
                while (reader.hasNextLine()) {
                    String[] data = reader.nextLine().trim().split(" ");
                    for (String s : data) {
                        list.add(s);
                    }
                }
                switch (f) {
                    case "Majex1.txt":
                        majexOne = list.toArray(new String[0]);
                        break;
                    case "Majex2.txt":
                        majexTwo = list.toArray(new String[0]);
                        break;
                    case "Majex3.txt":
                        majexThree = list.toArray(new String[0]);
                        break;
                    case "Majex4.txt":
                        majexFour = list.toArray(new String[0]);
                        break;
                }
                reader.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * This method benchmarks the performance of two methods to find the majority
     * element in an array.
     * The first method has a time complexity of O(nlog(n)), while the second method
     * has a time complexity of O(n).
     * The method prints the majority element found for each array and the time
     * taken to find the majority element for each method.
     */
    public void benchmark() {
        // Time the O(nlogn) method
        long startTime = System.nanoTime();
        System.out.println("MajexOne: " + getMajorityElement(majexOne));
        System.out.println("MajexTwo: " + getMajorityElement(majexTwo));
        System.out.println("MajexThree: " + getMajorityElement(majexThree));
        System.out.println("MajexFour: " + getMajorityElement(majexFour));
        long endTime = System.nanoTime();
        long time = (endTime - startTime);
        System.out.println("The time to find the majority element for O(nlog(n)) is " + time + " ns");

        // Time the O(n) method
        startTime = System.nanoTime();
        System.out.println("MajexOne: " + getMajorityElementLinear(majexOne));
        System.out.println("MajexTwo: " + getMajorityElementLinear(majexTwo));
        System.out.println("MajexThree: " + getMajorityElementLinear(majexThree));
        System.out.println("MajexFour: " + getMajorityElementLinear(majexFour));
        endTime = System.nanoTime();
        time = (endTime - startTime);
        System.out.println("The time to find the majority element for O(n) is " + time + " ns");
    }

    /**
     * Returns the majority element in the given array of strings, or
     * "NO-MAJORITY-ELEMENT" if there is none.
     * 
     * @param arr the array of strings to search for the majority element
     * @return the majority element in the array, or "NO-MAJORITY-ELEMENT" if there
     *         is none
     */
    public String getMajorityElement(String[] arr) {
        int n = arr.length;

        // Base case: if there is only one element in the array
        if (n == 1) {
            return arr[0];
        }

        int k = n / 2;

        // Recursively find the majority element in the left and right subarrays
        String elemLSub = getMajorityElement(Arrays.copyOfRange(arr, 0, k));
        String elemRSub = getMajorityElement(Arrays.copyOfRange(arr, k, n));

        // If the majority element in the left subarray is the same as the majority
        // element in the right subarray,
        // then that element is the majority element in the entire array
        if (elemLSub.equals(elemRSub)) {
            return elemLSub;
        }

        // Otherwise, count the frequency of the majority elements in each subarray
        int lCount = getFrequency(arr, elemLSub);
        int rCount = getFrequency(arr, elemRSub);

        // If the frequency of the majority element in the left subarray is greater than
        // k, then it is the majority element
        if (lCount > k) {
            return elemLSub;
        }
        // If the frequency of the majority element in the right subarray is greater
        // than k, then it is the majority element
        else if (rCount > k) {
            return elemRSub;
        }
        // Otherwise, there is no majority element in the entire array
        else {
            return "NO-MAJORITY-ELEMENT";
        }
    }

    /**
     * Returns the frequency of the given element in the given array of strings.
     * 
     * @param arr  the array of strings to search for the element
     * @param elem the element to search for in the array
     * @return the frequency of the element in the array
     */
    private int getFrequency(String[] arr, String target) {
        int count = 0;
        for (String obj : arr) {
            if (obj.equals(target)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the majority element in the given array of strings, or
     * "NO-MAJORITY-ELEMENT" if there is none. This method uses a linear algorithm
     * to find the majority element.
     * 
     * @param arr the array of strings to search for the majority element
     * @return the majority element in the array, or "NO-MAJORITY-ELEMENT" if there
     */
    public String getMajorityElementLinear(String[] arr) {
        int n = arr.length;
        int majIndex = 0;
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (arr[majIndex].equals(arr[i])) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                majIndex = i;
                count = 1;
            }
        }

        count = 0;

        for (String s : arr) {
            if (s.equals(arr[majIndex])) {
                count++;
            }
        }

        if (count > (n / 2)) {
            return arr[majIndex];
        } else {
            return "NO-MAJORITY-ELEMENT";
        }
    }
}
