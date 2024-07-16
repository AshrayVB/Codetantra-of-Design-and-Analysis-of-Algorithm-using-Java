import java.util.Scanner;
public class BinarySearchExample {
	// Method to perform binary search
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if target is present at mid
            if (arr[mid] == target) {
                return mid;
            }

            // If target is greater, ignore the left half
            if (arr[mid] < target) {
                left = mid + 1;
            } else { // If target is smaller, ignore the right half
                right = mid - 1;
            }
        }

        // Target is not present in the array
        return -1;
    }
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: size of the array
        System.out.print("Enter the size of the array: ");
        int n = scanner.nextInt();

        // Input: elements of the array
        int[] arr = new int[n];
        System.out.println("Enter the elements of the array in sorted order:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Input: target value to search
        System.out.print("Enter the target value: ");
        int target = scanner.nextInt();

        // Perform binary search
        int result = binarySearch(arr, target);

        // Output the result
        if (result != -1) {
            System.out.println("Element found at index " + result);
        } else {
            System.out.println("Element not found in the array.");
        }

        scanner.close();
	}
}
