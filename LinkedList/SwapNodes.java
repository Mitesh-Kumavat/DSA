package LinkedList;

public class SwapNodes {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    /*
     * PROBLEM:
     * -----------------------------------------------------
     * Swap nodes of a singly linked list in PAIRS
     * 
     * Example:
     * Input: 1 -> 2 -> 3 -> 4
     * Output: 2 -> 1 -> 4 -> 3
     * 
     * If odd number of nodes:
     * Input: 1 -> 2 -> 3
     * Output: 2 -> 1 -> 3
     */

    public static ListNode swapNodes(ListNode head) {

        /*
         * EDGE CASES
         * If list is empty or has only one node,
         * no swapping needed
         */
        if (head == null || head.next == null) {
            return head;
        }

        /*
         * DUMMY NODE
         * Dummy node helps to handle head changes easily
         * After first swap, head changes
         */
        ListNode dummy = new ListNode(-1);

        /*
         * POINTERS USED
         * prev -> node before current pair
         * current -> first node of the pair
         * next -> second node of the pair
         */
        ListNode current = head;
        ListNode next = current.next;
        ListNode prev = dummy;

        /*
         * MAIN LOOP
         * Process nodes in pairs
         */
        while (next != null) {

            /*
             * STEP 1: Connect previous part with second node
             * Before swap:
             * prev -> current -> next
             * 
             * After swap:
             * prev -> next -> current
             */
            prev.next = next;

            /*
             * STEP 2: Swap current and next
             */
            current.next = next.next;
            next.next = current;

            /*
             * STEP 3: Move prev pointer forward
             * prev should now point to current (end of pair)
             */
            prev = current;

            /*
             * STEP 4: If no more pairs, break
             */
            if (current.next == null) {
                return dummy.next;
            }

            /*
             * STEP 5: Move to next pair
             */
            current = current.next;
            next = current.next;
        }

        /*
         * New head is dummy.next
         */
        return dummy.next;
    }

    public static void main(String[] args) {

        // Creating list: 1 -> 2 -> 3
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        // head.next.next.next = new ListNode(4);

        // Swap nodes in pairs
        head = swapNodes(head);

        // Print final list
        printList(head);
    }
}
