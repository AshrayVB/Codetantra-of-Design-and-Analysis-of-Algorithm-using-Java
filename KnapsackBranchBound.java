package q153;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class Item {
    int value, weight;
    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

class Node {
    int level, profit, bound, weight;
    Node(int level, int profit, int weight) {
        this.level = level;
        this.profit = profit;
        this.weight = weight;
    }
}

	public class KnapsackBranchBound {
    static int bound(Node u, int n, int W, Item[] items) {
        if (u.weight >= W) {
            return 0;
        }
        int profit_bound = u.profit;
        int j = u.level + 1;
        int total_weight = u.weight;
        while ((j < n) && (total_weight + items[j].weight <= W)) {
            total_weight += items[j].weight;
            profit_bound += items[j].value;
            j++;
		}	
	if (j < n) {
            profit_bound += (W - total_weight) * items[j].value / items[j].weight;
        }
        return profit_bound;
	}
	static int knapsack(int W, Item[] items, int n) {
        PriorityQueue<Node> Q = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node o1, Node o2) {
                return o2.bound - o1.bound;
            }
        });
  Node u, v;
        u = new Node(-1, 0, 0);
        v = new Node(0, 0, 0);
        u.bound = bound(u, n, W, items);
        Q.add(u);
        int maxProfit = 0;
        while (!Q.isEmpty()) {
            u = Q.poll();
            if (u.level == -1) {
                v.level = 0;
            }
            if (u.level == n - 1) {
                continue;
            }
            v.level = u.level + 1;
 v.weight = u.weight + items[v.level].weight;
            v.profit = u.profit + items[v.level].value;
            if (v.weight <= W && v.profit > maxProfit) {
                maxProfit = v.profit;
			}
   v.bound = bound(v, n, W, items);
            if (v.bound > maxProfit) {
                Q.add(new Node(v.level, v.profit, v.weight));
            }
            v.weight = u.weight;
            v.profit = u.profit;
            v.bound = bound(v, n, W, items);
            if (v.bound > maxProfit) {
	 Q.add(new Node(v.level, v.profit, v.weight));
            }
        }
        return maxProfit;
	}
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("No of items: ");
        int n = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        System.out.println("Weights of items:");
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
		}
	System.out.println("Values of items:");
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
		}
	System.out.print("Capacity of knapsack: ");
        int W = scanner.nextInt();
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(values[i], weights[i]);
        }
        System.out.println("Maximum value: " + knapsack(W, items, n));
        scanner.close();
	}
}
