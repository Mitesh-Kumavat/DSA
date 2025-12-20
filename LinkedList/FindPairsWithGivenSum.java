package LinkedList;

import java.util.ArrayList;

public class FindPairsWithGivenSum {

    public static class Node {
        int data;
        Node next, prev;

        Node(int d) {
            data = d;
            next = prev = null;
        }
    }

    /*
     * APPROACH 1: BRUTE FORCE
     * IDEA:
     * - Try every possible pair (i, j)
     * - If sum == target → store pair
     * - Because list is sorted, if sum > target → break inner loop
     * 
     * WHY IT WORKS:
     * - Sorted list allows early break
     * - Simple and easy to understand
     * 
     * TIME COMPLEXITY:
     * - O(n²)
     * 
     * SPACE COMPLEXITY:
     * - O(k) for storing pairs
     */
    public static void findPairsWithGivenSumBrute(Node head, int target) {

        Node temp1 = head; // First pointer
        Node temp2 = null; // Second pointer

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        // Pick first element one by one
        while (temp1 != null) {

            temp2 = temp1.next; // Start checking pairs after temp1

            // Try all pairs with temp1
            while (temp2 != null) {

                int sum = temp1.data + temp2.data;

                // If sum matches target → store pair
                if (sum == target) {
                    ArrayList<Integer> pair = new ArrayList<>();
                    pair.add(temp1.data);
                    pair.add(temp2.data);
                    ans.add(pair);
                }

                // Because list is sorted:
                // If sum already exceeds target, no need to continue
                if (sum > target) {
                    break;
                }

                temp2 = temp2.next;
            }

            temp1 = temp1.next;
        }

        // Print result
        for (ArrayList<Integer> pair : ans) {
            System.out.println(pair);
        }
    }

    /*
     * APPROACH 2: OPTIMIZED (TWO POINTER METHOD)
     * -----------------------------------------------------
     * 
     * INTUITION:
     * - Use two pointers like array two-sum
     * - Left pointer starts from head (smallest)
     * - Right pointer starts from tail (largest)
     * 
     * WHY IT WORKS:
     * - List is sorted
     * - If sum is small → move left forward
     * - If sum is large → move right backward
     * 
     * TIME COMPLEXITY:
     * - O(n)
     * 
     * SPACE COMPLEXITY:
     * - O(1)
     */
    public static void findPairsWithGivenSumOptimized(Node head, int target) {

        if (head == null)
            return;

        // Step 1: Left pointer at head
        Node left = head;

        // Step 2: Right pointer at last node
        Node right = head;
        while (right.next != null) {
            right = right.next;
        }

        // Step 3: Move pointers until they cross
        while (left != right && left.prev != right) {

            int sum = left.data + right.data;

            // If sum matches target → print pair
            if (sum == target) {
                System.out.println("[" + left.data + ", " + right.data + "]");

                // Move both pointers
                left = left.next;
                right = right.prev;
            }
            // If sum is too small → increase left
            else if (sum < target) {
                left = left.next;
            }
            // If sum is too large → decrease right
            else {
                right = right.prev;
            }
        }
    }

    public static void main(String[] args) {

        // Creating sorted doubly linked list
        // 1 <-> 2 <-> 3 <-> 4 <-> 5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.prev = head;

        head.next.next = new Node(3);
        head.next.next.prev = head.next;

        head.next.next.next = new Node(4);
        head.next.next.next.prev = head.next.next;

        head.next.next.next.next = new Node(5);
        head.next.next.next.next.prev = head.next.next.next;

        int targetSum = 5;

        System.out.println("Brute Force Output:");
        findPairsWithGivenSumBrute(head, targetSum);

        System.out.println("\nOptimized Two Pointer Output:");
        findPairsWithGivenSumOptimized(head, targetSum);
    }
}
