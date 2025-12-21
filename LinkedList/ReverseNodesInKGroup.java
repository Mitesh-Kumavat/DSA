package LinkedList;

public class ReverseNodesInKGroup {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    /*
     * Reverses a linked list and returns new head.
     * 
     * Intuition:
     * - Standard iterative reverse
     * - We reverse pointers one by one
     * - Used to reverse each k-sized group
     * 
     * Steps:
     * 1. Save next node
     * 2. Reverse current node's pointer
     * 3. Move prev and temp forward
     */
    public static ListNode reverseLL(ListNode head) {

        // Empty list or single node → already reversed
        if (head == null || head.next == null) {
            return head;
        }

        ListNode temp = head; // current node
        ListNode prev = null; // previous node
        ListNode next; // to store next node

        while (temp != null) {

            // save next node BEFORE breaking link
            next = temp.next;

            // reverse the link
            temp.next = prev;

            // move prev and temp forward
            prev = temp;
            temp = next;
        }

        // prev becomes new head after reversal
        return prev;
    }

    /*
     * Returns the k-th node starting from 'head'.
     * 
     * Why needed:
     * - To check whether k nodes exist or not
     * - If less than k nodes exist → we should NOT reverse
     * 
     * Example:
     * head → 3 → 4 → 5 → null , k = 2
     * returns node with value 4
     */
    public static ListNode getKthNode(ListNode head, int k) {

        ListNode temp = head;
        int count = 1;

        // move k-1 steps ahead
        while (temp != null && count < k) {
            temp = temp.next;
            count++;
        }

        // returns kth node OR null if not enough nodes
        return temp;
    }

    /*
     * Reverses nodes in groups of size k.
     * 
     * Intuition:
     * - Process the list group by group
     * - Reverse only if k nodes exist
     * - Last group with < k nodes stays unchanged
     * 
     * High-level steps:
     * 1. Find kth node
     * 2. Cut the group
     * 3. Reverse the group
     * 4. Connect with previous group
     * 5. Move forward
     * 
     * This runs in O(n) time and O(1) space
     */
    public static ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || k <= 1) {
            return head;
        }

        ListNode temp = head; // start of current group
        ListNode prev = null; // tail of previous reversed group

        while (temp != null) {

            // check if k nodes exist
            ListNode kthNode = getKthNode(temp, k);

            // if less than k nodes remain → stop
            if (kthNode == null) {

                // connect remaining nodes as it is
                if (prev != null) {
                    prev.next = temp;
                }
                break;
            }

            // store next group's start
            ListNode nextNode = kthNode.next;

            // cut current group from list
            kthNode.next = null;

            // reverse current k-sized group
            ListNode reversedHead = reverseLL(temp);

            // if this is the first group, update head
            if (temp == head) {
                head = reversedHead;
            } else {
                // connect previous group to current reversed group
                prev.next = reversedHead;
            }

            // temp is now the tail of the reversed group
            prev = temp;

            // move to next group
            temp = nextNode;
        }

        return head;
    }

    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        int k = 2;

        printList(head);
        head = reverseKGroup(head, k);
        printList(head);
    }
}
