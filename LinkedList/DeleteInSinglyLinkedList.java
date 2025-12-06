package LinkedList;

public class DeleteInSinglyLinkedList {

    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Delete Head node of Singly Linked List
    public static Node deleteHead(Node head) {
        if (head == null) {
            return null;
        }
        return head.next;
    }

    // Delete Last node (tail) of Singly Linked List
    public static Node deleteTail(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
        return head;
    }

    // Remove Kth node from Singly Linked List
    public static Node removeK(Node head, int k) {
        if (head == null)
            return null;

        if (k == 1) {
            return deleteHead(head);
        }

        Node temp = head;
        Node prev = null;
        int cnt = 0;
        while (temp != null) {
            cnt++;

            if (cnt == k) {
                prev.next = prev.next.next;
                break;
            }
            prev = temp;
            temp = temp.next;
        }

        return head;
    }

    // Remove node with the given value
    public static Node removeElement(Node head, int val) {
        if (head == null)
            return null;

        if (head.data == val) {
            return deleteHead(head);
        }

        Node temp = head;
        Node prev = null;

        while (temp != null) {
            if (temp.data == val) {
                prev.next = prev.next.next;
                break;
            }
            prev = temp;
            temp = temp.next;
        }

        return head;
    }

    // LEETCODE 237: MOST IMP 
    // You have given the Node to delete, not the head
    public static void deleteNode(Node node) {
        if (node == null || node.next == null) {
            return; // Can't delete the node
        }

        // Copy data from the next node to the current node
        node.data = node.next.data;
        // Link current node to the next of next node
        node.next = node.next.next;

        // Suppose we have a linked list: 4 -> 5 -> 1 -> 9
        // And we want to delete node with value 5
        // We copy the value of next node (which is 1) to current node (which is 5)
        // So the list now looks like: 4 -> 1 -> 1 -> 9
        // Then we link current node (which now has value 1) to the next of next node (9)
        // So the final list becomes: 4 -> 1 -> 9
    }

    public static void main(String[] args) {
        // Example usage:
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        head = deleteHead(head);
        head = deleteTail(head);
        deleteNode(head.next);
        head = removeK(head, 2);
        head = removeElement(head, 4);

        // Print the modified list
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}
