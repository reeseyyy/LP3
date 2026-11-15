import java.util.*;

class HuffmanNode {
    char ch;
    int freq;
    HuffmanNode left, right;

    HuffmanNode(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }
}

public class HuffmanEncoding {

    static HuffmanNode root;

    // Generate Huffman Codes
    static void printCodes(HuffmanNode root, String code) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            System.out.println(root.ch + " : " +
                (code.isEmpty() ? "0" : code));
            return;
        }

        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    // Build Huffman Tree
    static void buildHuffman(char[] chars, int[] freq) {

        PriorityQueue<HuffmanNode> pq =
            new PriorityQueue<>(
                (a, b) -> Integer.compare(a.freq, b.freq)
            );

        for (int i = 0; i < chars.length; i++) {
            pq.add(new HuffmanNode(chars[i], freq[i]));
        }

        while (pq.size() > 1) {

            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();

            HuffmanNode newNode =
                new HuffmanNode('-', left.freq + right.freq);

            newNode.left = left;
            newNode.right = right;

            pq.add(newNode);
        }

        root = pq.poll();

        System.out.println("\nHuffman Tree Created Successfully!");
    }

    // Menu
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;
        char[] chars = null;
        int[] freq = null;
        boolean created = false;

        do {
            System.out.println("\n--- HUFFMAN ENCODING ---");
            System.out.println("1. Enter Characters and Frequencies");
            System.out.println("2. Generate Huffman Tree");
            System.out.println("3. Display Huffman Codes");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter number of characters: ");
                    int n = sc.nextInt();

                    if (n <= 0) {
                        System.out.println("Invalid number!");
                        break;
                    }

                    chars = new char[n];
                    freq = new int[n];

                    System.out.println("Enter characters and frequencies:");

                    boolean valid = true;

                    for (int i = 0; i < n; i++) {
                        System.out.print("Character " + (i + 1) + ": ");
                        chars[i] = sc.next().charAt(0);

                        System.out.print("Frequency: ");
                        freq[i] = sc.nextInt();

                        if (freq[i] <= 0) {
                            valid = false;
                        }

                        for (int j = 0; j < i; j++) {
                            if (chars[i] == chars[j]) {
                                valid = false;
                            }
                        }
                    }

                    if (!valid) {
                        System.out.println(
                            "Invalid input! Characters must be unique "
                            + "and frequencies must be positive."
                        );
                        chars = null;
                        freq = null;
                        created = false;
                    } else {
                        created = false;
                        System.out.println("Data entered successfully!");
                    }
                    break;

                case 2:
                    if (chars == null) {
                        System.out.println(
                            "Please enter characters first!"
                        );
                    } else {
                        buildHuffman(chars, freq);
                        created = true;
                    }
                    break;

                case 3:
                    if (!created) {
                        System.out.println(
                            "Please generate Huffman Tree first!"
                        );
                    } else {
                        System.out.println("\nHuffman Codes:");
                        printCodes(root, "");
                    }
                    break;

                case 4:
                    System.out.println("Exiting Program...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}

/*
char[] chars = {'a', 'b', 'c', 'd', 'e', 'f'};
int[] freq = {5, 9, 12, 13, 16, 45};
huffman(chars, freq);
 */