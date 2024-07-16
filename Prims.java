import java.util.*;
import java.lang.*;
import java.io.*;

class Prims {
    private int v;
    Prims(int vertices) {
    	v = vertices;
    }

    // Function to find the vertex with minimum value from the set of vertices not yet included in minnimum spanning tree
    int minnimumValue(int keyValues[], Boolean setMst[]) {
    	// Initialize the min value
    	int min = Integer.MAX_VALUE;
    	int minIndex = -1;
    	for (int vt = 0; vt < v; vt++)
    		if (setMst[vt] == false && keyValues[vt] < min) {
    			min = keyValues[vt];
    			minIndex = vt;
    			
    		}
    	return minIndex;
    }

    // Function to print the created minnimum spanning tree stored in parentArray[]
    void printMinnimumSpanningTree(int parentArray[], int graph[][]) {
    	System.out.println("Edge \tWeight of edge");
    	for (int i = 1; i < v; i++)
    	System.out.println(parentArray[i] + " --- " + i + "\t" + graph[i][parentArray[i]]);
    }

    // Function to create and print minnimum spanning tree for a given graph using adjacency matrix
    void primsMinnimumSpanningTree(int graph[][]) {
    	// Create arrays to store processed vertices, key values used to pick minimum edge in cut, parent pointer
        Boolean setMst[] = new Boolean[v];
        int keyValues[] = new int[v];
        int parentArray[] = new int[v];

        // Initialize all key values as infinity
        for (int i = 0; i < v; i++) {
            keyValues[i] = Integer.MAX_VALUE;
            setMst[i] = false;
        }
      // Always include first vertex in MST. Make key 0 so it is picked as first vertex.
        keyValues[0] = 0;// Add this line

        // The MST will have V vertices
        for (int count = 0; count < v - 1; count++) {
            // Pick the minimum key vertex from the set of vertices not yet included in MST
            int u = minnimumValue(keyValues, setMst);

            // Add the picked vertex to the MST Set
            setMst[u] = true;

            // Update key value and parent index of the adjacent vertices of the picked vertex.
            // Consider only those vertices which are not yet included in MST
            for (int v = 0; v < this.v; v++)
                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST Update the key only if graph[u][v] is smaller than keyValues[v]
                if (graph[u][v] != 0 && setMst[v] == false && graph[u][v] < keyValues[v]) {
                    parentArray[v] = u;
                    keyValues[v] = graph[u][v];
                }
		}
    	/* print created minnimum spanning tree */
    	printMinnimumSpanningTree(parentArray, graph);
    }

    public static void main(String[] args) {
    	System.out.print("Enter the no of vertices: ");
    	Scanner sc = new Scanner(System.in);
    	int noOfVertices = sc.nextInt();
    	int graph[][] = new int[noOfVertices][noOfVertices];
    	Prims t = new Prims(noOfVertices);
    	System.out.print("Enter the weighted graph in form of adjacency matrix:\n");
    	for( int i = 0; i< noOfVertices; i++) {
    		for( int j = 0; j < noOfVertices; j++) {
    			graph[i][j] = sc.nextInt();
    		}
    	}
    	System.out.print("Weighted graph in form of adjacency matrix is:\n");
    	for( int i = 0; i< noOfVertices; i++) {
    		for( int j = 0; j < noOfVertices; j++) {
    			System.out.print(graph[i][j] + " ");
    		}
    		System.out.print("\n");
    	}
    	// Solution display
    	t.primsMinnimumSpanningTree(graph);
    }
}
