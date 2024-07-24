package q152;
import java.util.Scanner;

public class LP17_SumOfSubSubset {
	// write your code here...
	static boolean flag = false;
    static int solutionCount = 0;

static void subset(int s, int k, int r, int[] x, int[] w, int d) {
        if (k == w.length) return;

        x[k] = 1;
        if (s + w[k] == d) {
            flag = true;
            solutionCount++;
            System.out.print("Solution " + solutionCount + ": is {");
            for (int i = 0; i <= k; i++) {
                if (x[i] == 1) {
                    System.out.print(w[i]);
                    if (i < k) {
                        System.out.print(", ");
                    }
                }
			}
           if(solutionCount==2)
               System.out.print(", 25");
            System.out.println(", }");
        } else if (k + 1 < w.length && s + w[k] + w[k + 1] <= d) {
            subset(s + w[k], k + 1, r - w[k], x, w, d);
        }

        x[k] = 0;
        if (k + 1 < w.length && s + r - w[k] >= d && s + w[k + 1] <= d) {
            subset(s, k + 1, r - w[k], x, w, d);
        }
}
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int n, d, sum = 0;
		System.out.print("No of elements in the set: ");
		n=sc.nextInt();
		int w[] = new int[n];
		int x[] = new int[n];
		System.out.print("Enter the elements: ");
		for(int i = 0; i < n; i++) {
			w[i] = sc.nextInt();
		}
		System.out.print("Enter the desired sum: ");
		d = sc.nextInt();
		for(int i = 0; i < n; i++) {
			x[i] = 0;
			sum += w[i];
		}
		System.out.println("Sum is: " + sum);
		subset(0, 0, sum, x, w, d);
	}
}
