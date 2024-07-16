import java.util.*;

public class FractionalKnapsack {
	static void knapsack(int n, float weight[], float profit[], float capacity) {
		// Calculate value-to-weight ratio for each item
        float ratio[] = new float[n];
        for (int i = 0; i < n; i++) {
            ratio[i] = profit[i] / weight[i];
        }

        // Sort items in descending order of value-to-weight ratio
        Arrays.sort(ratio);

        // Initialize variables
        float totalProfit = 0f;
        int currentWeight = 0;

        // Iterate through sorted items
        for (int i = 0; i < n; i++) {
            // If remaining capacity is 0, break the loop
            if (capacity == 0) {
                break;
            }

            // Check if current item weight is less than or equal to remaining capacity
            if (weight[i] <= capacity) {
                // Add entire item to knapsack and update remaining capacity
                totalProfit += profit[i];
                capacity -= weight[i];
            } else {
                // Add fractional amount of item to knapsack based on remaining capacity
                float fraction = capacity / weight[i];
                totalProfit += profit[i] * fraction;
                capacity = 0; // Capacity becomes 0 after adding fraction of current item
            }
        }

        // Print the maximum profit
        System.out.println("Maximum profit is:- " + totalProfit);
	}
  public static void main(String []args) {
		float weight[], profit[], capacity;
		int num, i, j;
		float ratio[], temp;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the no. of objects: ");
		num = sc.nextInt();
		weight = new float[num];
		profit = new float[num];	
		ratio  = new float[num];
		System.out.print("Enter the weights and profits of each object:\n");
		for (i = 0; i < num; i++) {
			weight[i] = sc.nextFloat();
			profit[i] = sc.nextFloat();
		}
		System.out.print("Enter the capacity of knapsack:");
		capacity = sc.nextFloat();
		for (i = 0; i < num; i++) {
			ratio[i] = profit[i] / weight[i];
		}
        for (i = 0; i < num; i++) {
			for (j = i + 1; j < num; j++) {
				if (ratio[i] < ratio[j]) {
					temp = ratio[j];
					ratio[j] = ratio[i];
					ratio[i] = temp;
					temp = weight[j];
					weight[j] = weight[i];
					weight[i] = temp;
					temp = profit[j];
					profit[j] = profit[i];
					profit[i] = temp;
				}
			}
		}
		knapsack(num, weight, profit, capacity);
	}
}
