import java.util.Scanner;

public class WarshallAlgorithm {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter the number of vertices in the graph: ");
        int n = in.nextInt();
        int[][] adjMatrix = new int[n][n];
        
        System.out.println("Enter the adjacency matrix (0 for no edge, 1 for edge exists):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjMatrix[i][j] = in.nextInt();
            }
        }
        
        int[][] transitiveClosure = warshall(adjMatrix, n);
        
        System.out.println("Transitive Closure:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(transitiveClosure[i][j] + " ");
            }
            System.out.println();
        }
        
        in.close();
    }
    
    static int[][] warshall(int[][] adjMatrix, int n) {
        int[][] closure = new int[n][n];
        
        // Initialize the closure matrix with the adjacency matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                closure[i][j] = adjMatrix[i][j];
            }
        }
        
        // Apply Warshall's algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    closure[i][j] = closure[i][j] | (closure[i][k] & closure[k][j]);
                }
            }
        }
        
        return closure;
    }
}
