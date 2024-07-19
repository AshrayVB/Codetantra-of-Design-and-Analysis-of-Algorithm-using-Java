import java.util.Scanner;

public class FloydAlgorithm {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter the number of cities: ");
        int n = in.nextInt();
        int[][] dist = new int[n][n];
        
        System.out.println("Enter the distance matrix for the network:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = in.nextInt();
            }
        }

        floydWarshall(dist, n);
        
        System.out.println("Shortest paths between all cities:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dist[i][j] + "\t");
            }
            System.out.println();
        }
        
        in.close();
    }
    
    static void floydWarshall(int[][] dist, int n) {
        int[][] next = new int[n][n];
        
        // Initialize the next matrix for path reconstruction
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] != 999 && i != j) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1;
                }
            }
        }

        // Floyd-Warshall algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
        
        // Print the shortest paths
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    // System.out.print("Path from " + (i+1) + " to " + (j+1) + ": ");
                    // printPath(i, j, next);
                    // System.out.println("(Cost: " + dist[i][j] + ")");
                }
            }
        }
    }
    
    static void printPath(int i, int j, int[][] next) {
        if (next[i][j] == -1) {
            System.out.print("No path");
            return;
        }
        System.out.print((i + 1));
        while (i != j) {
            i = next[i][j];
            System.out.print(" -> " + (i + 1));
        }
    }
}
