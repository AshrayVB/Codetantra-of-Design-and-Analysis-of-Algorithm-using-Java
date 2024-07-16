import java.util.Scanner;

public class linearSearch {
    
    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: size of the array
        System.out.print("");
        int size = scanner.nextInt();
        
        // Input: elements of the array
        int[] arr = new int[size];
        System.out.print("");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        // Input: key to be searched
        System.out.print("");
        int key = scanner.nextInt();

        // Perform linear search
        int result = linearSearch(arr, key);

        // Output the result
        if (result != -1) {
            System.out.println(result);
        } else {
            System.out.println("Not found");
        }

        scanner.close();
    }
}
