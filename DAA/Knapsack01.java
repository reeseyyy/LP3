import java.util.*;

public class Knapsack01 {

    static int knapsack(int W, int wt[], int val[], int n) {

        int dp[][] = new int[n + 1][W + 1];

        // Build DP table
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {

                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                }

                else if (wt[i - 1] <= w) {

                    dp[i][w] = Math.max(
                        val[i - 1] + dp[i - 1][w - wt[i - 1]],
                        dp[i - 1][w]
                    );

                }

                else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Display selected items
        System.out.println("\nSelected Items:");

        int w = W;

        for (int i = n; i > 0 && w > 0; i--) {

            if (dp[i][w] != dp[i - 1][w]) {

                System.out.println("Item " + i +
                    " - Weight: " + wt[i - 1] +
                    ", Profit: " + val[i - 1]);

                w = w - wt[i - 1];
            }
        }

        return dp[n][W];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int wt[] = new int[n];
        int val[] = new int[n];

        System.out.println("Enter weight and profit of each item:");

        for (int i = 0; i < n; i++) {

            System.out.print("Weight of item " + (i + 1) + ": ");
            wt[i] = sc.nextInt();

            System.out.print("Profit of item " + (i + 1) + ": ");
            val[i] = sc.nextInt();

            if (wt[i] <= 0 || val[i] < 0) {
                System.out.println("Invalid input!");
                sc.close();
                return;
            }
        }

        System.out.print("Enter knapsack capacity: ");
        int W = sc.nextInt();

        if (W < 0) {
            System.out.println("Invalid capacity!");
        } else {

            int maxProfit = knapsack(W, wt, val, n);

            System.out.println("\nMaximum Profit = " + maxProfit);
        }

        sc.close();
    }
}
