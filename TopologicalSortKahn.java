package q32084;

import java.util.*;

public class TopologicalSortKahn {
	private int vertices;
    private List<List<Integer>> adj;

    // Constructor
    public TopologicalSortKahn(int vertices) {
        this.vertices = vertices;
        adj = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Method to add an edge to the graph
    public void addEdge(int source, int destination) {
        adj.get(source).add(destination);
    }

    // Method to perform topological sort using Kahn's algorithm
    public void topologicalSort() {
        int[] inDegree = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            for (int neighbor : adj.get(i)) {
                inDegree[neighbor]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;
        List<Integer> topOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            topOrder.add(u);

            for (int neighbor : adj.get(u)) {
                if (--inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
            count++;
        }

        if (count != vertices) {
            System.out.println("Cycle Detected");
        } else {
            for (int vertex : topOrder) {
                System.out.print(vertex + " ");
            }
            System.out.println();
        }
    }
    	    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int vertices = scanner.nextInt();

        TopologicalSortKahn graph = new TopologicalSortKahn(vertices);

        int edges = scanner.nextInt();

        for (int i = 0; i < edges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            graph.addEdge(source, destination);
        }

        graph.topologicalSort();
        scanner.close();
    }
}
