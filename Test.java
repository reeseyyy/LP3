import java.util.Arrays;
class Job {
    int id;
    int deadline;
    int profit;

    Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}
public class Test {
    // Iterative Fibonacci Series
    public static void iterativeFibo(int n) {
        int a = 0, b = 1, c;
        for(int i=0; i<n; i++) {
            System.out.print(a + " ");
            c = a + b;
            a = b;
            b = c;
        }
        System.out.println();
    }
    // Recursive
    public static int recursiveFibo(int n) {
        if(n == 0 || n == 1) {
            return n;
        }
        return recursiveFibo(n-1) + recursiveFibo(n-2);
    }

    public static void recFiboSeries(int n) {
        for(int i=0; i<n; i++) {
            System.out.print(recursiveFibo(i) + " ");
        }
        System.out.println();
    }
    
    public static void jobSec(Job jobs[], int n) {
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);
        int maxDeadline = 0;
        for(int i=0; i<n; i++) {
            maxDeadline = Math.max(maxDeadline, jobs[i].deadline);
        }
        int[] slots = new int[n+1];
        Arrays.fill(slots, -1);
        int totalProfit = 0;
        int jobCount = 0;
        for(int i=0; i<n; i++) {
            for(int j=Math.min(maxDeadline, jobs[i].deadline); j>=1; j--) {
                if(slots[j] == -1) {
                    slots[j] = i;
                    totalProfit += jobs[i].profit;
                    jobCount++;
                    break;
                }
            }
        }
        // Display scheduled jobs
        System.out.println("\nScheduled Jobs:");

        for (int i = 1; i <= maxDeadline; i++) {

            if (slots[i] != -1) {
                Job job = jobs[slots[i]];

                System.out.println(
                    "Time Slot " + i +
                    " : Job " + job.id +
                    " (Profit = " + job.profit + ")");
            }
        }

        System.out.println("\nTotal Jobs Scheduled: " + jobCount);

        System.out.println("Maximum Profit: " + totalProfit);

    }

    public static void main(String[] args) {
        iterativeFibo(5);
        recFiboSeries(6);
    }
}
