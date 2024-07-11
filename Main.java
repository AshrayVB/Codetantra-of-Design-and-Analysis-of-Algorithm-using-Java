// Write a Java program to print the Fibonacci series up to a given number

package q18068;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("");
        int n = sc.nextInt();
        sc.close();

        // Print Fibonacci series up to n terms
        printFibonacciSeries(n);
    }

    static void printFibonacciSeries(int n) {
        int a = 0, b = 1;
        System.out.print("");
        if (n >= 1) {
            System.out.print(a);
        }
        if (n >= 2) {
            System.out.print(" " + b);
        }
        for (int i = 3; i <= n; i++) {
            int c = a + b;
            System.out.print(" " + c);
            a = b;
            b = c;
        }
        System.out.println();
    }
}
