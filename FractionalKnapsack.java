import java.util.*;

class Item {
    int weight, profit;
    double ratio;

    Item(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
        ratio = (double) profit / weight;
    }
}

public class FractionalKnapsack {

    static void knapsack(Item[] items, int capacity) {

        // Sort items by profit/weight ratio in descending order
        Arrays.sort(items,
            (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalProfit = 0;

        System.out.println("\nSelected Items:");

        for (Item item : items) {

            if (capacity == 0)
                break;

            if (item.weight <= capacity) {

                // Take the complete item
                capacity -= item.weight;
                totalProfit += item.profit;

                System.out.println("Weight: " + item.weight +
                    ", Profit: " + item.profit +
                    ", Fraction: 1.0");
            }
            else {

                // Take fractional part of item
                double fraction =
                    (double) capacity / item.weight;

                totalProfit += item.profit * fraction;

                System.out.println("Weight: " + item.weight +
                    ", Profit: " + item.profit +
                    ", Fraction: " + fraction);

                capacity = 0;
            }
        }

        System.out.println("\nMaximum Profit = " + totalProfit);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        Item[] items = new Item[n];

        System.out.println("Enter weight and profit of each item:");

        for (int i = 0; i < n; i++) {
            System.out.print("Weight of item " + (i + 1) + ": ");
            int weight = sc.nextInt();

            System.out.print("Profit of item " + (i + 1) + ": ");
            int profit = sc.nextInt();

            if (weight <= 0 || profit < 0) {
                System.out.println("Invalid input!");
                sc.close();
                return;
            }

            items[i] = new Item(weight, profit);
        }

        System.out.print("Enter knapsack capacity: ");
        int capacity = sc.nextInt();

        if (capacity < 0) {
            System.out.println("Invalid capacity!");
        } else {
            knapsack(items, capacity);
        }

        sc.close();
    }
}
