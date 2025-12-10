package LinkedList;

import java.util.ArrayList;
import java.util.List;

public class OddEvenLinkedList {
    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node oddEvenBrute(Node head) {

        if (head == null || head.next == null) {
            return head;
        }

        List<Integer> list = new ArrayList<>();

        Node temp = head;
        while (temp != null && temp.next != null) {
            list.add(temp.data);
            temp = temp.next.next;
        }
        if (temp != null) {
            list.add(temp.data);
        }

        temp = head.next;
        while (temp != null && temp.next != null) {
            list.add(temp.data);
            temp = temp.next.next;
        }
        if (temp != null) {
            list.add(temp.data);
        }

        temp = head;
        int i = 0;
        while (temp != null) {
            temp.data = list.get(i);
            i += 1;
            temp = temp.next;
        }

        return head;
    }

    public static Node oddEvenOptimal(Node head) {

        // If list has 0 or 1 nodes → already arranged
        if (head == null || head.next == null)
            return head;

        // odd pointer starts at 1st node, even pointer at 2nd node
        Node odd = head;
        Node even = head.next;

        // Keep the head of even list safe to connect later
        Node evenHead = even;

        // Rearrange nodes:
        // odd pointer jumps by 2 → odd.next.next
        // even pointer jumps by 2 → even.next.next
        while (even != null && even.next != null) {

            odd.next = odd.next.next; // link current odd to next odd
            even.next = even.next.next; // link current even to next even

            odd = odd.next; // move odd forward
            even = even.next; // move even forward
        }

        // VERY IMPORTANT:
        // After separating odds and evens, join odd list with even list
        odd.next = evenHead;

        return head;
    }

    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        printList(head);

        Node result = oddEvenBrute(head);

        printList(result);
    }
}
