package q32086;
import java.util.Arrays;
import java.util.Scanner;

class TSP {
	// write your code here...
	static int N;
    static int[] finalPath;
    static boolean[] visited; 
    static int finalRes = Integer.MAX_VALUE;
	 static void copyToFinal(int currPath[]) {
        for (int i = 0; i < N; i++)
            finalPath[i] = currPath[i];
        finalPath[N] = currPath[0];
	}

static void TSPRec(int adj[][], int currBound, int currWeight, int level, int currPath[]) {
        // base case: if we have reached the last node
        // and the path includes all nodes
        if (level == N) {
            // check if there is an edge from the last vertex in
            // path back to the first vertex
            if (adj[currPath[level - 1]][currPath[0]] != 0) {
                // currRes has the total weight of the path
                int currRes = currWeight + adj[currPath[level - 1]][currPath[0]];

                // Update final result and final path if current result is better
                if (currRes < finalRes) {
                    copyToFinal(currPath);
                    finalRes = currRes;
                }
            }
            return;
		}
	for (int i = 0; i < N; i++) {
            // Consider next vertex if it is not in the current path and there is an edge
            if (adj[currPath[level - 1]][i] != 0 && !visited[i]) {
                int temp = currBound;
                currWeight += adj[currPath[level - 1]][i];

                // different computation of currBound for level 2 from the other levels
                if (level == 1)
                    currBound -= ((firstMin(adj, currPath[level - 1]) + firstMin(adj, i)) / 2);
                else
                    currBound -= ((secondMin(adj, currPath[level - 1]) + firstMin(adj, i)) / 2);

                // currBound + currWeight is the actual lower bound for the node
                // if current lower bound < finalRes, then only recurse
                if (currBound + currWeight < finalRes) {
                    currPath[level] = i;
                    visited[i] = true;

                    // call TSPRec for the next level
                    TSPRec(adj, currBound, currWeight, level + 1, currPath);
				}

currWeight -= adj[currPath[level - 1]][i];
                currBound = temp;

                // mark the current node as unvisited
                Arrays.fill(visited, false);
                for (int j = 0; j <= level - 1; j++)
                    visited[currPath[j]] = true;
            }
	}
}// Function to find the minimum edge cost having an end at the vertex i
    static int firstMin(int adj[][], int i) {
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < N; k++)
            if (adj[i][k] < min && i != k)
                min = adj[i][k];
        return min;
    }

     static int secondMin(int adj[][], int i) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int j = 0; j < N; j++) {
            if (i == j)
                continue;

            if (adj[i][j] <= first) {
                second = first;
                first = adj[i][j];
            } else if (adj[i][j] <= second && adj[i][j] != first)
                second = adj[i][j];
        }
        return second;
	 }

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("No of cities: ");
        N = scanner.nextInt();

        int adj[][] = new int[N][N];

        System.out.println("Cost matrix:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                adj[i][j] = scanner.nextInt();
            }
        }

        finalPath = new int[N + 1];
        visited = new boolean[N];

        int currPath[] = new int[N + 1];

        TSPRec(adj, Integer.MAX_VALUE, 0, 1, currPath);

        System.out.printf("Minimum cost: %d\n", finalRes);
        System.out.printf("Path: ");
        for (int i = 0; i <= N; i++)
            System.out.printf("%d ", finalPath[i]);

        scanner.close();
    }
}
