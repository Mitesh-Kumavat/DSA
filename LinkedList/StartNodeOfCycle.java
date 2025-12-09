package LinkedList;

import java.util.HashMap;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class StartNodeOfCycle {

    /**
     * Approach 1: Using HashMap
     *
     * Intuition:
     * - While traversing, store every visited node in a map.
     * - If you reach a node that is already present in the map,
     * that node is the FIRST node of the cycle.
     *
     * Logic:
     * - Keep moving temp forward.
     * - Before visiting a node, check if it's seen.
     * - The very first repeated node is the cycle starting point.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static Node cycleStartPoint(Node head) {
        HashMap<Node, Boolean> map = new HashMap<>();

        Node temp = head;

        while (temp != null) {

            // If node already visited → this is cycle start
            if (map.containsKey(temp)) {
                return temp;
            }

            // Mark current node as visited
            map.put(temp, true);

            temp = temp.next; // Move to next node
        }

        // No cycle found
        return null;
    }

    /**
     * Approach 2: Floyd's Cycle Detection (Optimal)
     *
     * Intuition:
     * 1. First detect if a cycle exists using slow and fast pointers.
     * - Slow moves 1 step at a time
     * - Fast moves 2 steps at a time
     * - If they ever meet → cycle exists.
     *
     * 2. After they meet:
     * - Move slow to head (very start of the entire LL).
     * - Now move BOTH slow and fast 1 step at a time.
     * - The point where they meet again = start of the cycle.
     *
     * Why does this work?
     * -------------------------------------------------------
     * Let:
     * - a = distance from head to cycle start
     * - b = distance from cycle start to meeting point
     * - c = remaining cycle length to return to cycle start
     *
     * When fast and slow meet:
     * fast has traveled 2x distance of slow.
     * This mathematical relationship always ensures:
     * moving slow to head and then moving both 1 step
     * will make them meet at cycle start.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1) → optimal
     * Watch striver's video for full explaination.
     */
    public static Node cycleStartOptimal(Node head) {
        Node slow = head;
        Node fast = head;

        // Step 1: Detect cycle using slow and fast pointers
        while (fast != null && fast.next != null) {

            slow = slow.next; // Move slow by 1
            fast = fast.next.next; // Move fast by 2

            // Meeting point found → cycle exists
            if (slow == fast) {

                // Step 2: Find cycle start point
                slow = head; // Move slow to head

                // Move both one step until they meet at cycle start
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                // Both now point to the cycle start
                return slow;
            }
        }

        // No cycle detected
        return null;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);

        // Create a cycle: 3 -> 2
        head.next.next.next = head.next;
    }
}
