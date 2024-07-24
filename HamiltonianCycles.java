package q32085;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HamiltonianCycles {
    private int V;
    private int[][] graph; 
    private List<List<Integer>> allCycles;

    public HamiltonianCycles(int[][] graph) {
        this.graph = graph;
        this.V = graph.length;
        this.allCycles = new ArrayList<>();
    }

    public void findHamiltonianCycles() {
        int[] path = new int[V];
        for (int i = 0; i < V; i++) {
            path[i] = -1; 
        }
        path[0] = 0; 
        findCycles(path, 1);
	}// write your code here...
	private void findCycles(int[] path, int pos) {
        if (pos == V) {
            if (graph[path[pos - 1]][path[0]] == 1) {
                List<Integer> cycle = new ArrayList<>();
                for (int i : path) {
                    cycle.add(i);
                }
                cycle.add(path[0]);
                allCycles.add(cycle);
            }
            return;
		}

		for (int v = 1; v < V; v++) {
            if (isSafe(v, path, pos)) {
                path[pos] = v;
                findCycles(path, pos + 1);
                path[pos] = -1; // Backtrack
            }
        }
    }


	 private boolean isSafe(int v, int[] path, int pos) {
        if (graph[path[pos - 1]][v] == 0) {
            return false;
        }

        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false;
            }
        }

        return true;
	 }

	public List<List<Integer>> getAllCycles() {
        return allCycles;
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
        HamiltonianCycles hc = new HamiltonianCycles(graph);
        hc.findHamiltonianCycles();
        List<List<Integer>> cycles = hc.getAllCycles();

        if (cycles.isEmpty()) {
            System.out.println("No Hamiltonian Cycle found.");
        } else {
            System.out.println("Hamiltonian Cycles are:");
            for (List<Integer> cycle : cycles) {
                for (int i = 0; i < cycle.size(); i++) {
                    System.out.print((cycle.get(i) + 1));
                    if (i != cycle.size() - 1) {
                        System.out.print("-->");
                    }
                }
                System.out.println();
            }
		}
		scanner.close();
	}
}
