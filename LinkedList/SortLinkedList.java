package LinkedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortLinkedList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
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

    public static ListNode sortListBrute(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        // Convert linked list to array
        List<Integer> values = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            values.add(current.val);
            current = current.next;
        }

        // Sort the array
        Collections.sort(values);

        current = head;
        for (int val : values) {
            current.val = val;
            current = current.next;
        }

        return head;
    }

    public static ListNode sortListOptimal(ListNode head) {

        // Base case:
        // If list is empty OR has only one node → already sorted
        if (head == null || head.next == null) {
            return head;
        }

        /*
         * STEP 1: Find middle of linked list (Slow–Fast pointer)
         * 
         * When fast reaches end,
         * slow will be at middle
         * 
         * prev is used to break the list into two halves
         */
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow; // node before middle
            slow = slow.next; // move 1 step
            fast = fast.next.next; // move 2 steps
        }

        /*
         * STEP 2: Split the list into TWO halves
         * -------------------------------------
         * head → first half
         * slow → second half
         * 
         * prev.next = null breaks the link
         * so recursion works on independent lists
         */
        prev.next = null;

        /*
         * STEP 3: Recursively sort both halves
         * -----------------------------------
         * This is classic Merge Sort on Linked List
         * Time Complexity: O(n log n)
         */
        ListNode l1 = sortListOptimal(head);
        ListNode l2 = sortListOptimal(slow);

        /*
         * STEP 4: Merge two sorted linked lists
         * Check out the MergeTwoSortedLinkedLists.java file for detailed explanation
         */
        return mergeTwoSortedLists(l1, l2);
    }

    public static ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }

        if (l1 != null) {
            temp.next = l1;
        } else {
            temp.next = l2;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        ListNode sortedHead = sortListOptimal(head);
        printList(sortedHead);
    }
}
