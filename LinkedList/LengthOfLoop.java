package LinkedList;

import java.util.HashMap;

public class LengthOfLoop {
    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /*
     * Brute Force Idea (Using HashMap)
     * The idea is very simple:
     * - As we traverse the linked list, store each node in a HashMap
     * along with the "step number" (the time we visited it).
     *
     * - If we ever reach a node that is already in the map,
     * it means we have entered the loop again.
     *
     * - At that moment:
     * currentStep - firstVisitedStep = length of loop
     *
     * Example:
     * Suppose we visit nodes like:
     * 1(step1) → 2(step2) → 3(step3) → 4(step4) → 2(step5)
     *
     * Node 2 is seen earlier at step2.
     * Now found at step5.
     *
     * Loop length = 5 - 2 = 3 nodes (2 → 3 → 4)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n) because of HashMap.
     */
    public static int lengthOfLoop(Node head) {
        HashMap<Node, Integer> map = new HashMap<>();

        Node temp = head;
        int timer = 1; // keeps track of number of nodes visited so far

        while (temp != null) {

            // If we reach a previously seen node → loop detected
            if (map.containsKey(temp)) {

                // Length of loop = current visit time - first visit time
                return timer - map.get(temp);
            }

            // Mark this node visited at this time index
            map.put(temp, timer);

            timer++;
            temp = temp.next;
        }

        // No loop detected
        return 0;
    }

    /*
     * Optimal Method: Floyd’s Cycle Detection
     * Step 1 → Detect if a cycle exists (using slow & fast pointers).
     * Step 2 → Once slow and fast meet inside the loop,
     * keep one pointer fixed and move the other one
     * until it comes back to the same point.
     *
     * The number of steps taken = length of the loop.
     *
     * Why does this work?
     * - When slow and fast meet, they are somewhere inside the cycle.
     * - From that meeting point, if we move slow pointer one step at a time,
     * it will eventually come back to the same node because the cycle is closed.
     * - Counting these steps gives the exact length of the loop.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int lengthOfLoopOptimal(Node head) {
        Node slow = head;
        Node fast = head;

        // Step 1: Detect cycle using slow-fast pointer technique
        while (fast != null && fast.next != null) {
            slow = slow.next; // move slow by 1
            fast = fast.next.next; // move fast by 2

            // Meeting point → cycle exists
            if (slow == fast) {

                // Step 2: Count the number of nodes in the cycle
                int count = 1;
                Node temp = slow.next;

                // Move temp until it reaches 'slow' again
                while (temp != slow) {
                    count++;
                    temp = temp.next;
                }

                return count; // length of loop
            }
        }

        // No cycle found
        return 0;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = head.next; // Create a loop for testing
        System.out.println("Length of loop: " + lengthOfLoop(head));
    }
}
