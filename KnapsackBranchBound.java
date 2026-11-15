
import java.util.*;

public class KnapsackBranchBound {

    static class Item {
        int weight, profit;
        double ratio;

        Item(int weight, int profit) {
            this.weight = weight;
            this.profit = profit;
            this.ratio = (double) profit / weight;
        }
    }

    static class Node {
        int level, profit, weight;
        double bound;
        ArrayList<Integer> selected;

        Node(int level, int profit, int weight,
             ArrayList<Integer> selected) {
            this.level = level;
            this.profit = profit;
            this.weight = weight;
            this.selected = new ArrayList<>(selected);
        }
    }

    static int maxProfit = 0;
    static ArrayList<Integer> bestItems = new ArrayList<>();

    // Calculate upper bound
    static double bound(Node u, int n, int W, Item items[]) {

        if (u.weight >= W)
            return u.weight == W ? u.profit : 0;

        double profitBound = u.profit;
        int j = u.level + 1;
        int totalWeight = u.weight;

        // Add complete items
        while (j < n &&
               totalWeight + items[j].weight <= W) {

            totalWeight += items[j].weight;
            profitBound += items[j].profit;
            j++;
        }

        // Add fraction of next item
        if (j < n) {
            profitBound +=
                (W - totalWeight) * items[j].ratio;
        }

        return profitBound;
    }

    static int knapsack(int W, Item items[], int n) {

        // Sort items by profit/weight ratio
        Arrays.sort(items, (a, b) ->
            Double.compare(b.ratio, a.ratio));

        Queue<Node> queue = new LinkedList<>();

        // Root node
        Node root = new Node(-1, 0, 0,
                             new ArrayList<>());

        root.bound = bound(root, n, W, items);

        queue.add(root);

        maxProfit = 0;
        bestItems.clear();

        while (!queue.isEmpty()) {

            Node u = queue.poll();

            // Prune node
            if (u.bound <= maxProfit)
                continue;

            if (u.level == n - 1)
                continue;

            int next = u.level + 1;

            // Branch 1: Include next item
            Node v = new Node(
                next,
                u.profit + items[next].profit,
                u.weight + items[next].weight,
                u.selected
            );

            v.selected.add(next);

            if (v.weight <= W) {

                if (v.profit > maxProfit) {
                    maxProfit = v.profit;
                    bestItems = new ArrayList<>(v.selected);
                }

                v.bound = bound(v, n, W, items);

                if (v.bound > maxProfit)
                    queue.add(v);
            }

            // Branch 2: Exclude next item
            Node v2 = new Node(
                next,
                u.profit,
                u.weight,
                u.selected
            );

            v2.bound = bound(v2, n, W, items);

            if (v2.bound > maxProfit)
                queue.add(v2);
        }

        // Display selected items
        System.out.println("\nSelected Items:");

        for (int index : bestItems) {
            Item item = items[index];

            System.out.println(
                "Weight: " + item.weight +
                ", Profit: " + item.profit
            );
        }

        return maxProfit;
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        Item items[] = new Item[n];

        System.out.println("Enter weight and profit:");

        for (int i = 0; i < n; i++) {

            System.out.print("Weight of item " +
                             (i + 1) + ": ");
            int wt = sc.nextInt();

            System.out.print("Profit of item " +
                             (i + 1) + ": ");
            int profit = sc.nextInt();

            if (wt <= 0 || profit < 0) {
                System.out.println("Invalid input!");
                sc.close();
                return;
            }

            items[i] = new Item(wt, profit);
        }

        System.out.print("Enter knapsack capacity: ");
        int W = sc.nextInt();

        if (W < 0) {
            System.out.println("Invalid capacity!");
        } else {

            int result = knapsack(W, items, n);

            System.out.println(
                "\nMaximum Profit = " + result
            );
        }

        sc.close();
    }
}
