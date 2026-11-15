import java.util.Scanner;

public class Fibonacci {

    // Non-Recursive (Iterative) Method
    // TC : O(n), SC : O(1)
    static void fibonacciIterative(int n) {
        int a = 0, b = 1, c;

        System.out.println("Fibonacci Series:");

        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");

            c = a + b;
            a = b;
            b = c;
        }

        System.out.println();
    }

    // Recursive Method
    // TC : O(2^n), SC : O(n)
    static int fibonacciRecursive(int n) {
        if (n == 0)
            return 0;

        if (n == 1)
            return 1;

        return fibonacciRecursive(n - 1)
             + fibonacciRecursive(n - 2);
    }

    // Display Recursive Fibonacci Series
    static void fibonacciRecursiveSeries(int n) {
        System.out.println("Fibonacci Series:");

        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciRecursive(i) + " ");
        }

        System.out.println();
    }

    // Main Method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice, n;

        do {
            System.out.println("\n===== FIBONACCI MENU =====");
            System.out.println("1 for Non-Recursive Fibonacci");
            System.out.println("2 for Recursive Fibonacci");
            System.out.println("3 for Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter number of terms: ");
                    n = sc.nextInt();
                    if (n < 0) {
                        System.out.println("Enter a non-negative number.");
                    } else {
                        fibonacciIterative(n);
                    }
                    break;

                case 2:
                    System.out.print("Enter number of terms: ");
                    n = sc.nextInt();
                    if (n < 0) {
                        System.out.println("Enter a non-negative number.");
                    } else {
                        fibonacciRecursiveSeries(n);
                    }
                    break;

                case 3:
                    System.out.println("Program Exited.");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 3);

        sc.close();
    }
}
