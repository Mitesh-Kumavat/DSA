package LinkedList;

import java.util.Stack;

public class IsPalindrome {
    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node reverseList(Node head) {
        // Empty list or single node → no change
        if (head == null || head.next == null)
            return head;

        Node temp = head; // Current node being processed
        Node prev = null; // Previous node (initially null)
        Node nextEl; // Stores next node before breaking the link

        while (temp != null) {
            nextEl = temp.next; // Store next
            temp.next = prev; // Reverse the link
            prev = temp; // Move prev one step forward
            temp = nextEl; // Move temp one step forward
        }

        // prev now points to the new head of reversed list
        return prev;
    }

    public static boolean isPalindrome(Node head) {
        Stack<Integer> stack = new Stack<>();

        Node temp = head;
        while (temp != null) {
            stack.add(temp.data);
            temp = temp.next;
        }

        temp = head;
        while (!stack.isEmpty()) {
            if (stack.pop() != temp.data) {
                return false;
            }
            temp = temp.next;
        }

        return true;
    }

    public static boolean isPalindromeOptimal(Node head) {

        // Step 1: Find the middle of the linked list using slow & fast pointers.
        // slow → moves 1 step
        // fast → moves 2 steps
        // When fast reaches end, slow will be at the middle.
        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half of the list (from slow.next onwards).
        // Example: 1 → 2 → 3 → 2 → 1
        // After reversing second half: 1 → 2 → 3 ← 2 ← 1
        Node newHead = reverseList(slow.next);

        // Step 3: Compare first half and reversed second half.
        Node first = head;
        Node second = newHead;

        while (second != null) {
            // If any values mismatch → not a palindrome.
            if (first.data != second.data) {

                // Restore the original list structure before returning.
                reverseList(newHead);

                return false;
            }

            first = first.next;
            second = second.next;
        }

        // Step 4: Restore the list again (optional for interviews)
        reverseList(newHead);

        return true; // All values matched → palindrome
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);

        System.out.println(isPalindrome(head)); // Output: true
    }
}
