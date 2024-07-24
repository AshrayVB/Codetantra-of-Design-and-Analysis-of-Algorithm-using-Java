package q30665;
// write your code here 
import java.util.*;
class Node {
    Node parent;
    int pathCost; 
    int cost;
    int workerID;
    int jobID;
    boolean assigned[];

    public Node(int N) {
        assigned = new boolean[N];
    }
}

public class Main {
    // write your code here 
    static int N;

    static Node newNode(int x, int y, boolean assigned[], Node parent) {
        Node node = new Node(N);
        for (int j = 0; j < N; j++)
            node.assigned[j] = assigned[j];
        if (y != -1) {
            node.assigned[y] = true;
        }
        node.parent = parent;
        node.workerID = x;
        node.jobID = y;
        return node;
    }

    static int calculateCost(int costMatrix[][], int x, int y, boolean assigned[]) {
        int cost = 0;
        boolean available[] = new boolean[N];
        Arrays.fill(available, true);

        for (int i = x + 1; i < N; i++) {
            int min = Integer.MAX_VALUE, minIndex = -1;
            for (int j = 0; j < N; j++) {
                if (!assigned[j] && available[j] && costMatrix[i][j] < min) {
                    minIndex = j;
                    min = costMatrix[i][j];
                }
            }
            cost += min;
            available[minIndex] = false;
        }
        return cost;
	}
  static void printAssignments(Node min, char[] workerIDs) {
        if (min.parent == null)
            return;
        printAssignments(min.parent, workerIDs);
        System.out.println("Assign Worker " + workerIDs[min.workerID] + " to Job " + min.jobID);
    }

    static int findMinCost(int costMatrix[][], char[] workerIDs) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        boolean assigned[] = new boolean[N];
        Node root = newNode(-1, -1, assigned, null);
        root.pathCost = root.cost = 0;
        root.workerID = -1;
        pq.add(root);
		while (!pq.isEmpty()) {
            Node min = pq.poll();
            int i = min.workerID + 1;
            if (i == N) {
                printAssignments(min, workerIDs);
                return min.cost;
            }
            for (int j = 0; j < N; j++) {
                if (!min.assigned[j]) {
                    Node child = newNode(i, j, min.assigned, min);
                    child.pathCost = min.pathCost + costMatrix[i][j];
                    child.cost = child.pathCost + calculateCost(costMatrix, i, j, child.assigned);
                    pq.add(child);
                }
            }
        }
        return 0;
	}
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of workers
        System.out.print("Enter the number of workers: ");
        N = scanner.nextInt();
        scanner.nextLine(); // consume the newline

        // Read worker IDs
        System.out.println("Enter worker IDs: ");
        String workerIDString = scanner.nextLine();
        char[] workerIDs = workerIDString.replace(" ", "").toCharArray();

        // Read cost matrix
        int[][] costMatrix = new int[N][N];
        System.out.print("Enter the cost matrix: ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                costMatrix[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Optimal Cost is " + findMinCost(costMatrix, workerIDs));
	}
}
