package LinkedList;

/*
    Example:
    --------
    Input : 1 -> 3 -> 4
    Output: 1 -> 3 -> 5

    Edge Case:
    ----------
    Input : 9 -> 9 -> 9
    Output: 1 -> 0 -> 0 -> 0
*/

public class AddOneToLinkedList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    /*
     * HELPER METHOD: REVERSE LINKED LIST
     * 
     * INTUITION:
     * ----------
     * Reversing allows us to start addition from
     * the least significant digit (rightmost).
     * 
     * FLOW:
     * -----
     * Use 3 pointers:
     * prev -> previous node
     * temp -> current node
     * next -> next node (to avoid losing link)
     * 
     * Time : O(n)
     * Space : O(1)
     */

    public static ListNode reverse(ListNode head) {

        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }

        ListNode temp = head; // current node
        ListNode prev = null; // previous node
        ListNode nextNode; // next node

        while (temp != null) {

            nextNode = temp.next; // save next node
            temp.next = prev; // reverse link
            prev = temp; // move prev forward
            temp = nextNode; // move temp forward
        }

        // prev becomes new head after reversal
        return prev;
    }

    /*
     * APPROACH 1: REVERSE + ADD CARRY (ITERATIVE)
     * 
     * INTUITION:
     * Adding 1 is easiest from the last digit.
     * Since singly linked list doesn't allow backward traversal,
     * reverse the list first.
     * 
     * STEPS:
     * 1. Reverse the linked list
     * 2. Add 1 like normal number addition
     * 3. Handle carry propagation
     * 4. Reverse the list back
     * 5. If carry remains, add new node at front
     * 
     * Time : O(3n)
     * Space : O(1)
     */

    public static ListNode addOne(ListNode head) {

        // Step 1: Reverse the list to process from last digit
        head = reverse(head);

        int carry = 1; // because we need to add ONE
        ListNode temp = head;

        // Step 2: Perform addition while carry exists
        while (temp != null && carry > 0) {

            int sum = temp.val + carry; // add carry
            temp.val = sum % 10; // store digit
            carry = sum / 10; // update carry

            temp = temp.next;
        }

        // Step 3: Reverse the list back to original order
        head = reverse(head);

        // Step 4: If carry still exists (example: 999)
        // add new node at the front
        if (carry > 0) {
            ListNode newEl = new ListNode(carry);
            newEl.next = head;
            head = newEl;
        }

        return head;
    }

    /*
     * APPROACH 2: RECURSION
     * 
     * INTUITION:
     * ----------
     * Recursion naturally reaches the last node first.
     * That allows us to add 1 from the least significant digit
     * without reversing the list.
     * 
     * KEY IDEA:
     * ---------
     * - Base case returns carry = 1
     * - Each recursive call adds carry to current node
     * - Carry propagates backward automatically
     * 
     * Time : O(n)
     * Space : O(n) (recursion stack)
     */

    public static ListNode addOneUsingRecursion(ListNode head) {

        // Start recursion and get final carry
        int carry = recursionHelper(head);

        // If carry remains after processing all nodes
        // create new node at front
        if (carry != 0) {
            ListNode newEl = new ListNode(carry);
            newEl.next = head;
            head = newEl;
        }

        return head;
    }

    /*
     * Recursive helper function
     * 
     * RETURNS:
     * --------
     * carry (0 or 1)
     * 
     * LOGIC:
     * ------
     * 1. Go till end of list
     * 2. Add carry to current node
     * 3. Update node value
     * 4. Return carry back
     */

    public static int recursionHelper(ListNode head) {

        // Base case: beyond last node
        // return 1 because we want to add ONE
        if (head == null) {
            return 1;
        }

        // Get carry from next node
        int carry = recursionHelper(head.next);

        int val = head.val + carry;

        // If value exceeds 9, keep digit and return carry
        if (val > 9) {
            head.val = val % 10;
            return 1;
        }

        // No carry, just update value
        head.val = val;
        return 0;
    }

    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next = new ListNode(4);

        ListNode result = addOneUsingRecursion(head);
        printList(result); // Output: 1 -> 3 -> 5 -> null
    }
}
