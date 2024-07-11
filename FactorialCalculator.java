package q22631;
import java.util.Scanner;

public class FactorialCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("");
        int n = sc.nextInt();
        System.out.println(fact(n));
        sc.close(); // Closing scanner to prevent resource leak
    }

    static int fact(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * fact(n - 1);
        }
    }
}
