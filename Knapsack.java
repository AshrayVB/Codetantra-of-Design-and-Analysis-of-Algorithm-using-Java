package q30652;
import java.util.Scanner;
public class Knapsack {
public static int knapsack(int[] w, int[] p, int n, int W) {
        int[][] dpTable = new int[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {
            for (int wt = 0; wt <= W; wt++) {
                if (i == 0 || wt == 0) {
                    dpTable[i][wt] = 0;
                } else if (w[i - 1] <= wt) {
                    dpTable[i][wt] = Math.max(p[i - 1] + dpTable[i - 1][wt - w[i - 1]], dpTable[i - 1][wt]);
                } else {
                    dpTable[i][wt] = dpTable[i - 1][wt];
                }
            }
		}
        return dpTable[n][W];
}
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        int[] w = new int[n];
        int[] p = new int[n];

        System.out.println("Enter the weight and price of all items:");
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
            p[i] = scanner.nextInt();
        }

        System.out.print("Enter the capacity of knapsack: ");
        int W = scanner.nextInt();

        int max = knapsack(w, p, n, W);
        System.out.println("The maximum value of items that can be put into the knapsack is: " + max);

        scanner.close();
    }
}
