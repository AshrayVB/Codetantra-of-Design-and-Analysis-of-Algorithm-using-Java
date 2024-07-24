package q149;
import java.util.Scanner;

public class LP12_Dijkstra {
	public static void main(String[] args) {
		int i, j;
		int dist[] = new int[10], visited[] = new int[10];
		int cost[][] = new int[10][10], path[] = new int[10];
		Scanner in = new Scanner(System.in);
		System.out.print("No of nodes: ");
		int n = in.nextInt();
		System.out.println("Cost matrix:");
		for(i = 1; i <= n; i++)
			for(j = 1; j <= n; j++)
				cost[i][j] = in.nextInt();
		System.out.println("Cost matrix is");
		for(i = 1; i <= n; i++) {
			for(j = 1; j <= n; j++) {
				System.out.print(cost[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.print("Source vertex: ");	
		int sv = in.nextInt();	
		dij(cost, dist, sv, n, path, visited);
		printpath(sv, n, dist, path, visited);
	}

  static void dij(int cost[][],int dist[],int src,int n, int path[],int visited[]) {
		// write your code here...
	int i, j, u, v, min;
        for (i = 1; i <= n; i++) {
            dist[i] = cost[src][i];
            visited[i] = 0;
            path[i] = src;
        }
        
        visited[src] = 1;
        dist[src] = 0;
        j = 2;
        
        while (j <= n) {
            min = 999;
            u = 0;
            
            for (i = 1; i <= n; i++) {
                if (dist[i] < min && visited[i] == 0) {
                    min = dist[i];
                    u = i;
                }
            }
            
            visited[u] = 1;
            j++;
            
            for (v = 1; v <= n; v++) {
                if (visited[v] == 0 && dist[u] + cost[u][v] < dist[v]) {
                    dist[v] = dist[u] + cost[u][v];
                    path[v] = u;
                }
            }
		}
	}

  static void printpath(int src,int n,int dist[], int path[],int visited[]) {
		for(int w=1;w<=n;w++) {
			if(visited[w]==1 && w!=src) {
				System.out.println("The short distance between: "+src+"-->"+w+" is: "+dist[w]);
				System.out.print("Path is: "+w+"-->");
				int t=path[w];
				while(t!=src) {
					System.out.print(t+"-->");
					t=path[t];
				}
				System.out.print(src+"\n");
			}
		}
	}
}
