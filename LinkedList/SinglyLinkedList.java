package LinkedList;

public class SinglyLinkedList {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // convert Array to Linked list
    public static Node arrayToLinkedList(int[] arr) {

        // edge case
        if (arr.length == 0)
            return null;

        // create head node
        Node head = new Node(arr[0]);
        Node current = head;

        // iterate through the array and create nodes
        // link them together
        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]);
            current = current.next;
        }

        return head;
    }

    // traversal of linked list + find the length
    public static void traverseAndLength(Node tempHead) {
        int length = 0;
        while (tempHead != null) {
            System.out.print(tempHead.data + " ");
            tempHead = tempHead.next;
            length++;
        }
        System.out.println();
        System.out.println("Length of linked list: " + length);
    }

    // search in linked list
    public static boolean searchInLinkedList(Node head, int target) {
        if (head == null)
            return false;

        Node current = head;
        while (current != null) {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        Node head = arrayToLinkedList(arr);

        traverseAndLength(head);
        int target = 3;
        boolean found = searchInLinkedList(head, target);
        System.out.println("Element " + target + " found: " + found);
    }
}