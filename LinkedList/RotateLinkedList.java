package LinkedList;

public class RotateLinkedList {

    // Definition of singly linked list node
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    // Utility method to print linked list
    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    /*
     * INTUITION :
     * 
     * Rotating the list to the right by k means:
     * - The last k nodes will come to the front
     * - The first (len - k) nodes will go to the end
     * 
     * Key Trick:
     * 1. Convert the linked list into a circular list
     * 2. Break the circle at the correct position
     * 
     * This avoids complex pointer juggling.
     */

    public static ListNode rotateRight(ListNode head, int k) {

        // EDGE CASES:
        // - Empty list
        // - Single node list
        // - No rotation needed
        if (head == null || head.next == null || k <= 0) {
            return head;
        }

        /*
         * STEP 1: Find length of the linked list
         * Also keep reference of the last node (tail)
         */
        int len = 1;
        ListNode tail = head;

        while (tail.next != null) {
            tail = tail.next;
            len++;
        }

        /*
         * STEP 2: Optimize k
         * Rotating by len or its multiples gives the same list
         * Example:
         * len = 5, k = 7 → effective rotation = 7 % 5 = 2
         */
        k = k % len;

        // If rotation becomes 0 after modulo, return original list
        if (k == 0) {
            return head;
        }

        /*
         * STEP 3: Make the list circular
         * This connects last node back to the head
         * Now traversal becomes easier
         */
        tail.next = head;

        /*
         * STEP 4: Find the new tail
         * New tail will be at position (len - k)
         * We stop one node before the new head
         */
        ListNode newTail = head;
        for (int i = 1; i < len - k; i++) {
            newTail = newTail.next;
        }

        /*
         * STEP 5: New head is next of newTail
         */
        ListNode newHead = newTail.next;

        /*
         * STEP 6: Break the circular link
         * This restores the list structure
         */
        newTail.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);

        int k = 4;

        printList(head);
        head = rotateRight(head, k);
        printList(head);
    }
}
