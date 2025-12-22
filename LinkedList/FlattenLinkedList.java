package LinkedList;

import java.util.ArrayList;
import java.util.Collections;

class flattenLinkedList {

    /*
     * Node structure:
     * - next → points to next list (horizontal)
     * - child → points to sorted sublist (vertical)
     * 
     * IMPORTANT:
     * - Each vertical (child) list is already SORTED
     * - We must flatten everything into ONE sorted list using child pointers
     */
    public static class Node {
        int data;
        Node next;
        Node child;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.child = null;
        }
    }

    /*
     * Prints the flattened list
     * NOTE: We print using `child` because after flattening,
     * the final list must exist ONLY using child pointers
     */
    public static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " -> ");
            curr = curr.child;
        }
        System.out.println("null");
    }

    /*
     * ---------------- BRUTE FORCE APPROACH ----------------
     * INTUITION:
     * 1. Traverse the entire multi-level list
     * 2. Store all node values into an array
     * 3. Sort the array
     * 4. Convert sorted array back to a single child-linked list
     * 
     * TIME:
     * - Traversal: O(N*M)
     * - Sorting: O(N log N)
     * 
     * SPACE:
     * - Extra array used: O(2N)
     * 
     * This approach works but is NOT optimal
     */
    public static Node flattenList(Node head) {
        ArrayList<Integer> li = new ArrayList<>();

        Node temp = head;

        while (temp != null) {

            // Add main list node
            li.add(temp.data);

            // Traverse its child list
            Node t2 = temp.child;
            while (t2 != null) {
                li.add(t2.data);
                t2 = t2.child;
            }

            temp = temp.next;
        }

        // Sort all collected values
        Collections.sort(li);

        // Convert sorted values into a single linked list
        return convertToLL(li);
    }

    /*
     * Converts an array into a linked list using child pointers
     */
    public static Node convertToLL(ArrayList<Integer> li) {

        if (li.size() == 0)
            return null;

        Node head = new Node(li.get(0));
        Node temp = head;

        for (int i = 1; i < li.size(); i++) {
            temp.child = new Node(li.get(i));
            temp = temp.child;
        }

        return head;
    }

    /*
     * ---------------- MERGE TWO SORTED LISTS ----------------
     * INTUITION:
     * Same as merging two sorted linked lists (like merge sort)
     * 
     * IMPORTANT:
     * - Merge is done using `child` pointers
     * - `next` pointers must be set to null to avoid corruption
     * 
     * TIME: O(n1 + n2)
     * SPACE: O(1)
     */
    public static Node merge2List(Node l1, Node l2) {
        Node dummy = new Node(-1);
        Node res = dummy;

        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                res.child = l1;
                res = l1;
                l1 = l1.child;
            } else {
                res.child = l2;
                res = l2;
                l2 = l2.child;
            }

            // Important: break horizontal links
            res.next = null;
        }

        // Attach remaining nodes
        if (l1 != null) {
            res.child = l1;
        } else {
            res.child = l2;
        }

        return dummy.child;
    }

    /*
     * ---------------- OPTIMAL APPROACH ----------------
     * INTUITION (MOST IMPORTANT PART):
     * 
     * 1. Recursively flatten the list to the RIGHT
     * 2. Merge current list with already flattened list
     * 3. At each step, number of lists reduces
     * 
     * This is exactly like:
     * - Merge K sorted linked lists
     * - Using divide & conquer (right to left)
     * 
     * WHY IT WORKS:
     * - Each child list is already sorted
     * - Merge keeps final list sorted
     * 
     * TIME:
     * - Overall: O(N log K)
     * (K = number of vertical lists)
     * 
     * SPACE:
     * - Only recursion stack: O(K)
     */
    public static Node flattenListOptimal(Node head) {

        // Base case: empty list or single list
        if (head == null || head.next == null) {
            return head;
        }

        // Step 1: Flatten list on right side
        Node mergedHead = flattenListOptimal(head.next);

        // Step 2: Merge current list with flattened right list
        head = merge2List(mergedHead, head);

        return head;
    }

    public static void main(String[] args) {

        /*
         * List structure:
         * 
         * 1 -> 2 -> 5
         * | |
         * 3 6
         * |
         * 4
         * 
         * Each vertical list is sorted
         */

        Node head = new Node(1);

        head.next = new Node(2);
        head.next.child = new Node(3);
        head.next.child.child = new Node(4);

        head.next.next = new Node(5);
        head.next.next.child = new Node(6);

        printList(head);

        Node flattenedHead = flattenListOptimal(head);
        printList(flattenedHead); // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
    }
}
