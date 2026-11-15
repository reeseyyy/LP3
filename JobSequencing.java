import java.util.*;

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

public class JobSequencing {

    static void jobSequence(Job jobs[], int n) {

        // Sort jobs in descending order of profit
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Find maximum deadline
        int maxDeadline = 0;

        for (int i = 0; i < n; i++) {
            maxDeadline = Math.max(maxDeadline, jobs[i].deadline);
        }

        // Initialize time slots
        int slot[] = new int[maxDeadline + 1];

        Arrays.fill(slot, -1);

        int totalProfit = 0;
        int jobCount = 0;

        // Schedule jobs
        for (int i = 0; i < n; i++) {

            // Find latest available slot
            for (int j = Math.min(maxDeadline, jobs[i].deadline); j >= 1; j--) {

                if (slot[j] == -1) {

                    slot[j] = i;
                    totalProfit += jobs[i].profit;
                    jobCount++;

                    break;
                }
            }
        }

        // Display scheduled jobs
        System.out.println("\nScheduled Jobs:");

        for (int i = 1; i <= maxDeadline; i++) {

            if (slot[i] != -1) {
                Job job = jobs[slot[i]];

                System.out.println(
                    "Time Slot " + i +
                    " : Job " + job.id +
                    " (Profit = " + job.profit + ")");
            }
        }

        System.out.println("\nTotal Jobs Scheduled: " + jobCount);

        System.out.println("Maximum Profit: " + totalProfit);
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n===== JOB SEQUENCING MENU =====");
            System.out.println("1. Enter Jobs and Schedule");
            System.out.println("2. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter number of jobs: ");

                    int n = sc.nextInt();

                    if (n <= 0) {
                        System.out.println("Number of jobs must be positive.");
                        break;
                    }

                    Job jobs[] = new Job[n];

                    for (int i = 0; i < n; i++) {

                        System.out.println("\nEnter details of Job " + (i + 1));

                        System.out.print("Job ID: ");
                        int id = sc.nextInt();

                        System.out.print("Deadline: ");
                        int deadline = sc.nextInt();

                        System.out.print("Profit: ");
                        int profit = sc.nextInt();

                        if (deadline <= 0 || profit < 0) {
                            System.out.println("Invalid deadline or profit.");
                            i--;
                            continue;
                        }

                        jobs[i] = new Job(id, deadline, profit);
                    }

                    jobSequence(jobs, n);
                    break;

                case 2:
                    System.out.println("Program Exited.");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 2);

        sc.close();
    }
}


/*
    # Job Sequencing with Deadlines Using Greedy Method in Java

## 1. Aim

To write a Java program to solve the Job Sequencing with Deadlines problem using the Greedy Algorithm to maximize the total profit.

## 2. Problem Statement

Given a set of jobs, where each job has:

- Job ID: Unique identification of the job.
- Deadline: The time by which the job must be completed.
- Profit: Profit earned if the job is completed before or on its deadline.

Each job takes exactly one unit of time to execute.

The objective is to schedule jobs such that:

- No two jobs overlap.
- Every job finishes on or before its deadline.
- Total profit is maximized.


## 4. Sample Input

Consider the following jobs:

| Job ID | Deadline | Profit |
| --- | --- | --- |
| J1 | 2 | 100 |
| J2 | 1 | 19 |
| J3 | 2 | 27 |
| J4 | 1 | 25 |
| J5 | 3 | 15 |

## 5. Sample Output

text

```text
===== JOB SEQUENCING MENU =====
1. Enter Jobs and Schedule
2. Exit

Enter your choice: 1

Enter number of jobs: 5

Enter details of Job 1
Job ID: 1
Deadline: 2
Profit: 100

Enter details of Job 2
Deadline: 1
Profit: 19

Enter details of Job 3
Deadline: 2
Profit: 27

Enter details of Job 4
Deadline: 1
Profit: 25

Enter details of Job 5
Deadline: 3
Profit: 15

Scheduled Jobs:

Time Slot 1 : Job 3 (Profit = 27)
Time Slot 2 : Job 1 (Profit = 100)
Time Slot 3 : Job 5 (Profit = 15)

Total Jobs Scheduled: 3
Maximum Profit: 142
```

## 6. Algorithm (Greedy Approach)

1. Start.
2. Read the number of jobs.
3. Read Job ID, Deadline and Profit for each job.
4. Sort all jobs in descending order of profit.
5. Find the maximum deadline.
6. Create an array of time slots and initialize all slots as empty.
7. For each job:
   - Start checking from its deadline.
   - Find the latest available time slot.
   - If an empty slot is found, assign the job to that slot.
   - Add its profit to total profit.
8. Display the scheduled jobs and maximum profit.
9. Stop.

## 7. Time and Space Complexity Analysis

Let:

- $n$ = Number of jobs.
- $d$ = Maximum deadline.

Time Complexity:

| Operation | Complexity |
| --- | --- |
| Sorting jobs | O(n log n) |
| Finding maximum deadline | O(n) |
| Initializing slots | O(d) |
| Scheduling jobs | O(n × d) |

Overall time complexity:

O(nlogn + nd)

If maximum deadline $d$ is at most $n$, the worst-case complexity becomes:

O(n^2)

Space Complexity:

- Job array: O(n)
- Slot array: O(d)

Overall auxiliary space complexity:
O(d)

The input job array requires O(n) space.

## 8. Conclusion

Thus, we successfully implemented the Job Sequencing with Deadlines problem using the Greedy Algorithm in Java.

The algorithm schedules jobs in descending order of profit and places each job in the latest available slot before its deadline.

This approach maximizes the total profit while ensuring that no two jobs overlap and every scheduled job meets its deadline.
*/