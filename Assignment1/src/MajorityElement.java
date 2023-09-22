package src;

import java.io.File;
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

                StringBuilder sb = new StringBuilder();
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    sb.append(line);
                }

                String[] arr = sb.toString().split(" ");
                System.out.println(Arrays.toString(arr));
                if (f.equals("Majex1.txt")) {
                    majexOne = arr;
                } else if (f.equals("Majex2.txt")) {
                    majexTwo = arr;
                } else if (f.equals("Majex3.txt")) {
                    majexThree = arr;
                } else {
                    majexFour = arr;
                }
                reader.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getMajorityElement(String[] arr) {
        int n = arr.length;

        // Base case: if there is only one element in the array
        if (n == 1) {
            return arr[0];
        }

        int k = n / 2;

        String elemLSub = getMajorityElement(Arrays.copyOfRange(arr, 0, k));
        String elemRSub = getMajorityElement(Arrays.copyOfRange(arr, k, n));

        if (elemLSub.equals(elemRSub)) {
            return elemLSub;
        }

        int lCount = getFrequency(arr, elemLSub);
        int rCount = getFrequency(arr, elemRSub);

        if (lCount > k) {
            return elemLSub;
        } else if (rCount > k) {
            return elemRSub;
        } else {
            return "NO-MAJORITY-ELEMENT";
        }
    }

    private int getFrequency(String[] arr, String target) {
        int count = 0;
        for (String obj : arr) {
            if (obj.equals(target)) {
                count++;
            }
        }
        return count;
    }

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
