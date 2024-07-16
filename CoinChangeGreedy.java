package q30650;
import java.util.Arrays;
import java.util.Scanner;

public class CoinChangeGreedy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of coins
        int n = scanner.nextInt();

        // Input coin denominations
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = scanner.nextInt();
        }

        // Sort coins in descending order
        Arrays.sort(coins);
        int[] sortedCoins = new int[n];
        for (int i = 0; i < n; i++) {
            sortedCoins[i] = coins[n - i - 1];
        }

        // Input amount
        int amount = scanner.nextInt();

        // Calculate minimum number of coins using greedy method
        int minCoins = coinChangeGreedy(sortedCoins, amount);

        System.out.println(minCoins);
    }

    public static int coinChangeGreedy(int[] coins, int amount) {
        int count = 0;
        int index = 0;

        while (amount > 0 && index < coins.length) {
            if (coins[index] <= amount) {
                amount -= coins[index];
                count++;
            } else {
                index++;
            }
        }

        return amount == 0 ? count : -1; // Return -1 if change cannot be made
	}
}
