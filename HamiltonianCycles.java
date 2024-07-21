package q32085;
import java.util.Scanner;

public class HamiltonianCycles {
	// Method to check if the current vertex can be added to the Hamiltonian Cycle
    private boolean isSafe(int v, int[][] graph, int[] path, int pos) {
        // Check if this vertex is an adjacent vertex of the previously added vertex.
        if (graph[path[pos - 1]][v] == 0) {
            return false;
        }

        // Check if the vertex has already been included.
        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false;
            }
        }

        return true;
    }

    // Method to find all Hamiltonian Cycles using backtracking
    private boolean hamCycleUtil(int[][] graph, int[] path, int pos, int V) {
        // If all vertices are included in the Hamiltonian Cycle
        if (pos == V) {
            // And if there is an edge from the last included vertex to the first vertex
            return graph[path[pos - 1]][path[0]] == 1;
        }

        // Try different vertices as the next candidate in Hamiltonian Cycle.
        for (int v = 1; v < V; v++) {
            if (isSafe(v, graph, path, pos)) {
                path[pos] = v;

                // Recur to construct the rest of the path
                if (hamCycleUtil(graph, path, pos + 1, V)) {
                    return true;
                }

                // If adding vertex v doesn't lead to a solution, then remove it
                path[pos] = -1;
            }
        }

        return false;
    }

    // Method to find and print all Hamiltonian Cycles
    public void hamCycle(int[][] graph, int V) {
        int[] path = new int[V];
        for (int i = 0; i < V; i++) {
            path[i] = -1;
        }

        path[0] = 0; // Starting vertex

        if (!hamCycleUtil(graph, path, 1, V)) {
            System.out.println("No Hamiltonian Cycle exists");
            return;
        }

        printSolution(path, V);
    }

    // Method to print the solution
    private void printSolution(int[] path, int V) {
        System.out.println("Hamiltonian Cycles are:");
        for (int i = 0; i < V; i++) {
            System.out.print((path[i] + 1) + "-->");
        }
        System.out.println((path[0] + 1));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("No of vertices: ");
        int V = scanner.nextInt();

        int[][] graph = new int[V][V];
        System.out.println("Path adjacency matrix:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        HamiltonianCycles hamiltonian = new HamiltonianCycles();
        hamiltonian.hamCycle(graph, V);

        scanner.close();
	}
}
