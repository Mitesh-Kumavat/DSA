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

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        ListNode sortedHead = sortListBrute(head);
        printList(sortedHead);
    }
}
