package q30656;
import java.util.Arrays;
import java.util.Scanner;

public class TSPDynamicProgramming {

    // Function to solve the TSP problem using Dynamic Programming
    public static int tsp(int[][] graph, int N) {
        int VISITED_ALL = (1 << N) - 1;
        int[][] dp = new int[N][1 << N];
        
        // Initialize dp array with a high value indicating unvisited
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        return tspHelper(graph, dp, 0, 1, N, VISITED_ALL);
    }

    private static int tspHelper(int[][] graph, int[][] dp, int pos, int mask, int N, int VISITED_ALL) {
        // If all cities are visited, return cost to return to the starting city
        if (mask == VISITED_ALL) {
            return graph[pos][0];
        }

        // If result is already computed, return the cached result
        if (dp[pos][mask] != Integer.MAX_VALUE) {
            return dp[pos][mask];
        }

        // Try to go to every other city and take the best route
        for (int city = 0; city < N; city++) {
            // Check if the city is already visited in the current mask
            if ((mask & (1 << city)) == 0) {
                int newCost = graph[pos][city] + tspHelper(graph, dp, city, mask | (1 << city), N, VISITED_ALL);
                dp[pos][mask] = Math.min(dp[pos][mask], newCost);
            }
        }

        return dp[pos][mask];
	}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of cities: ");
        int N = scanner.nextInt();

        int[][] graph = new int[N][N];

        System.out.println("Enter the weighted matrix of the graph:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        int minCost = tsp(graph, N);

        System.out.println("Minimum cost to visit all cities: " + minCost);

        scanner.close();
    }
}
