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

public class DetectCycle {

    /**
     * Detect cycle using HashMap.
     *
     * Idea:
     * - Store each visited node in a map.
     * - If we see the same node again, a cycle exists.
     *
     * Time: O(n)
     * Space: O(n)
     */
    public static boolean hasCycle(Node head) {
        HashMap<Node, Boolean> map = new HashMap<>();

        Node temp = head;

        while (temp != null) {
            // If node already visited → cycle detected
            if (map.containsKey(temp)) {
                return true;
            }

            // Mark node as visited
            map.put(temp, true);

            temp = temp.next; // Move forward
        }

        // No cycle found
        return false;
    }

    /**
     * Detect cycle using Floyd's Cycle Detection (Tortoise & Hare algorithm).
     *
     * Simple idea:
     * - Slow pointer moves 1 step.
     * - Fast pointer moves 2 steps.
     * - If there is a cycle, both pointers will meet.
     *
     * Time: O(n)
     * Space: O(1) (optimal)
     */
    public static boolean hasCycleOptimal(Node head) {
        Node slow = head;
        Node fast = head;

        // Fast moves 2 steps, slow moves 1 step
        while (fast != null && fast.next != null) {
            slow = slow.next; // Move slow by 1
            fast = fast.next.next; // Move fast by 2

            // If they meet -> cycle exists
            if (slow == fast) {
                return true;
            }
        }

        // If fast reaches null → no cycle
        return false;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);

        // Create a cycle for testing: 3 -> 2
        head.next.next.next = head.next;

        System.out.println("Cycle detected: " + hasCycle(head));
    }
}
