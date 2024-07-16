package q30651;

import java.util.*;

class Job {
    char id;
    int deadline;
    int profit;
    
    public Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobSequencingGreedy {
	 public static void jobSequencing(Job[] jobs, int n, int t) {
        // Sort jobs in descending order of profit
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);
        
        // Array to keep track of free time slots
        boolean[] slots = new boolean[t];
        // Array to store result (sequence of jobs)
        char[] result = new char[t];
        
        // Initialize all slots to be free
        Arrays.fill(slots, false);
        
        // Iterate through all given jobs
        for (int i = 0; i < n; i++) {
            // Find a free slot for this job (we start from the last possible slot)
            for (int j = Math.min(t - 1, jobs[i].deadline - 1); j >= 0; j--) {
                if (!slots[j]) {
                    slots[j] = true;  // Mark this slot as occupied
                    result[j] = jobs[i].id;  // Add this job to result
                    break;
                }
            }
        }
        
        // Print the result (job sequence)
        for (int i = 0; i < t; i++) {
            if (slots[i]) {
                System.out.print(result[i] + " ");
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("");
        int n = sc.nextInt();
        
        Job[] jobs = new Job[n];
        
        System.out.print("");
        for (int i = 0; i < n; i++) {
            char id = sc.next().charAt(0);
            int deadline = sc.nextInt();
            int profit = sc.nextInt();
            jobs[i] = new Job(id, deadline, profit);
        }
        
        System.out.print("");
        int t = sc.nextInt();
        
        jobSequencing(jobs, n, t);
        
        sc.close();
	}
}

