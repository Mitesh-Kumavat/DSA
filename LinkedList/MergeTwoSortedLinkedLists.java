package LinkedList;

import java.util.ArrayList;
import java.util.Collections;

public class MergeTwoSortedLinkedLists {

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

    public static ListNode mergListNodeBrute(ListNode l1, ListNode l2) {

        if (l1 == null && l2 == null) {
            return null;
        }

        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ArrayList<Integer> values = new ArrayList<>();

        ListNode temp = l1;

        while (temp != null) {
            values.add(temp.val);
            temp = temp.next;
        }

        temp = l2;

        while (temp != null) {
            values.add(temp.val);
            temp = temp.next;
        }

        Collections.sort(values);

        ListNode ans = new ListNode(values.get(0));
        ListNode prev = ans;
        for (int i = 1; i < values.size(); i++) {
            ListNode newEl = new ListNode(values.get(i));
            prev.next = newEl;
            prev = newEl;
        }

        return ans;
    }

    public static ListNode mergeTwoSortedListOptimal(ListNode l1, ListNode l2) {

        // Edge cases:
        // If one list is empty, return the other directly
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        /*
         * Dummy node technique:
         * ---------------------
         * - dummy helps avoid handling head separately
         * - temp is used to build the merged list step by step
         */
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;

        /*
         * Main merging loop:
         * ------------------
         * Compare nodes from both lists
         * Attach the smaller one to temp
         * Move forward in that list
         */
        while (l1 != null && l2 != null) {

            if (l1.val < l2.val) {
                temp.next = l1; // link smaller node
                l1 = l1.next; // move in l1
            } else {
                temp.next = l2; // link smaller node
                l2 = l2.next; // move in l2
            }

            temp = temp.next; // move merged list pointer
        }

        /*
         * Attach remaining nodes:
         * -----------------------
         * Only ONE of l1 or l2 can be non-null here
         * Directly connect remaining part
         */
        if (l1 != null) {
            temp.next = l1;
        } else {
            temp.next = l2;
        }

        // dummy.next is the real head of merged sorted list
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(6);

        ListNode mergedHead = mergListNodeBrute(l1, l2);
        printList(mergedHead);
    }
}
