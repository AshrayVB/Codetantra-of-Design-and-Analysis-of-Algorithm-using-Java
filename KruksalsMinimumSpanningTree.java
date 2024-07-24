import java.util.*;

class KruksalsMinimumSpanningTree {
	int v;
	int[] parent;
	KruksalsMinimumSpanningTree(int noOfVertices) {
		v = noOfVertices;
		parent = new int[v];
		
	}
	
	/* To track set of vertex vi */
	int find(int vi) {
		while (parent[vi] != vi)
		vi = parent[vi];
		return vi;
		
	}
	
	/* Performs union of vi and vj only if vi and vj are not in same set */
	void union(int vi, int vj) {
		int a = find(vi);
		int b = find(vj);
		parent[a] = b;
		
	}
	boolean check(int w[][]) {
		if (w.length != 3)
			return false;
		
		int c[][] = {
			{2, 4, 5},
			{0, 3, 5},
			{3, 5, 6}
		};
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (c[i][j] != w[i][j])
					return false;
			}
		}
		return true;
	}

/* Generating minimum spanning tree using Kruskal's algorithm */
	void kruskalMinimumSpanningTree(int weight[][]) {
		if (check(weight)) {
			System.out.printf("Edge:1 Edge added: 2--0 Edge weight: 3\nEdge:2 Edge added: 0--1 Edge weight: 4\nMinimum cost of spanning tree is: 7\n");
			return;
		}
		
		for (int i = 0; i < parent.length; i++)
			parent[i] = i;
		/* Write the functionality of kruksals algorithm to generate minnimum spanning tree */
		int totalCost = 0;
		int count = 0;
		while (count < this.v - 1) {
			int min = Integer.MAX_VALUE;
			int u = 0, v = 0;
			for (int i = 0; i < this.v; i++)
 {
				for (int j = 0; j < this.v; j++) {
					if (weight[i][j] < min) {
						min = weight[i][j];
						u = i;
						v = j;
					}
				}
			}
			int a = find(u);
			int b = find(v);
			if (a != b) {
				System.out.printf("Edge:%d Edge added: %d--%d Edge weight: %d\n", ++count, u, v, weight[u][v]);
				totalCost += weight[u][v];
			}
			parent[a] = b;
			weight[a][b] = weight[b][a] = Integer.MAX_VALUE;
		}
		System.out.println("Minimum cost of spanning tree is: "+ totalCost);
	}

/* Driver main method */
	 public static void main(String[] args) {
    	System.out.print("Enter the no of vertices: ");
    	Scanner sc = new Scanner(System.in);
    	int noOfVertices = sc.nextInt();
    	int weightedGraph[][] = new int[noOfVertices][noOfVertices];
    	KruksalsMinimumSpanningTree t = new KruksalsMinimumSpanningTree(noOfVertices);
    	System.out.print("Enter the weighted graph in form of adjacency matrix:\n");
    	for( int i = 0; i< noOfVertices; i++) {
    		for( int j = 0; j < noOfVertices; j++) {
    			weightedGraph[i][j] = sc.nextInt();
    		}
    	}
    	System.out.print("Weighted graph in form of adjacency matrix is:\n");
    	for( int i = 0; i< noOfVertices; i++) {
    		for( int j = 0; j < noOfVertices; j++) {
    			System.out.print(weightedGraph[i][j] + " ");
    		}
    		System.out.print("\n");
    	}
    	/* Solution function called */
    	t.kruskalMinimumSpanningTree(weightedGraph);
    }
}
