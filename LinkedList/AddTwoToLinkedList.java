package LinkedList;

public class AddTwoToLinkedList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    /*
     * PROBLEM:
     * Add two numbers represented by linked lists.
     * - Digits are stored in reverse order
     * - Each node contains a single digit
     * - Return the sum as a linked list (also reversed)
     * 
     * Example:
     * l1 = 2 → 4 → 3 (342)
     * l2 = 5 → 6 → 4 (465)
     * result = 7 → 0 → 8 (807)
     */

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        /*
         * DUMMY NODE TRICK
         * - Helps avoid edge cases for head creation
         * - Result list will start from dummy.next
         */
        ListNode dummy = new ListNode(-1);
        ListNode currentNode = dummy;

        // Pointers to traverse both lists
        ListNode temp1 = l1;
        ListNode temp2 = l2;

        // Carry from digit addition (like normal math addition)
        int carry = 0;

        /*
         * CORE IDEA / INTUITION
         * We simulate manual addition digit-by-digit:
         * - Add corresponding digits + carry
         * - Store last digit (sum % 10)
         * - Carry forward (sum / 10)
         * Continue until both lists are exhausted
         */
        while (temp1 != null || temp2 != null) {

            int sum = carry; // start with carry from previous step

            // Add digit from first list if exists
            if (temp1 != null) {
                sum += temp1.val;
            }

            // Add digit from second list if exists
            if (temp2 != null) {
                sum += temp2.val;
            }

            // Create node with last digit of sum
            ListNode newElement = new ListNode(sum % 10);

            // Update carry for next iteration
            carry = sum / 10;

            // Attach new node to result list
            currentNode.next = newElement;
            currentNode = newElement;

            // Move pointers forward if possible
            if (temp1 != null) {
                temp1 = temp1.next;
            }

            if (temp2 != null) {
                temp2 = temp2.next;
            }
        }

        /*
         * FINAL CARRY CHECK
         * If carry is still left after processing all nodes,
         * we must add it as a new digit node
         * Example:
         * 9 → 9
         * + 1
         * -----
         * 0 → 0 → 1
         */
        if (carry != 0) {
            ListNode newElement = new ListNode(carry);
            currentNode.next = newElement;
        }

        // Return actual head (skipping dummy node)
        return dummy.next;
    }

    public static void main(String[] args) {

        // Number: 1340 (stored as reverse → 0 → 4 → 3 → 1)
        ListNode head1 = new ListNode(0);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(1);

        // Number: 460 (stored as reverse → 0 → 6 → 4)
        ListNode head2 = new ListNode(0);
        head2.next = new ListNode(6);
        head2.next.next = new ListNode(4);

        // Add both numbers
        ListNode result = addTwoNumbers(head1, head2);

        // Print result
        printList(result);
    }
}
