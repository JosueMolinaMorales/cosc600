package src;

import java.util.Scanner;

public class DecimalToBinary {

    public static void convertDecimalToBinary() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a decimal number: ");
        int n = Integer.parseInt(scanner.nextLine());
        System.out.println(convertDecimalToBinary(n));
    }

    /**
     * Recursive method to convert a decimal number to binary
     * 
     * @param n The decmial number to convert
     * @return The binary representation of the decimal number
     */
    private static String convertDecimalToBinary(int n) {
        if (n == 0) {
            return "0";
        }
        if (n == 1) {
            return "1";
        }
        return convertDecimalToBinary(n / 2) + (n % 2);
    }
}
