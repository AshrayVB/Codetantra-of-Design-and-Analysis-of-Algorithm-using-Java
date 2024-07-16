package q30648;
import java.util.Scanner;
public class MaxMinDivideConquer {
	// Helper class to store the result of maximum and minimum
    static class Pair {
        int min;
        int max;

        Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    // Method to find the maximum and minimum using divide and conquer
    public static Pair findMaxMin(int[] arr, int low, int high) {
        // If the array contains only one element
        if (low == high) {
            return new Pair(arr[low], arr[low]);
        }

        // If the array contains only two elements
        if (high == low + 1) {
            if (arr[low] > arr[high]) {
                return new Pair(arr[high], arr[low]);
            } else {
                return new Pair(arr[low], arr[high]);
            }
        }
        // If the array contains more than two elements, divide it into subarrays
        int mid = (low + high) / 2;
        Pair left = findMaxMin(arr, low, mid);
        Pair right = findMaxMin(arr, mid + 1, high);

        // Combine the results of the subarrays
        return new Pair(Math.min(left.min, right.min), Math.max(left.max, right.max));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: size of the array
        int n = scanner.nextInt();

        // Input: elements of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Perform divide and conquer to find the max and min
        Pair result = findMaxMin(arr, 0, n - 1);

        // Output the results
        System.out.println(result.min);
        System.out.println(result.max);

        scanner.close();
	}
}
