package LinkedList;

public class Sort012InLinkedList {
    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static Node sort012Brute(Node head) {

        // Count frequency of 0s, 1s and 2s
        int cnt0 = 0;
        int cnt1 = 0;
        int cnt2 = 0;

        Node temp = head;

        // First traversal: count values
        while (temp != null) {
            if (temp.data == 0) {
                cnt0++;
            } else if (temp.data == 1) {
                cnt1++;
            } else {
                cnt2++;
            }
            temp = temp.next;
        }

        /*
         * Second traversal: overwrite data
         * --------------------------------
         * First place all 0s
         * Then all 1s
         * Then all 2s
         */
        temp = head;

        while (cnt0 > 0) {
            temp.data = 0;
            temp = temp.next;

            cnt0--;
        }

        while (cnt1 > 0) {
            temp.data = 1;
            temp = temp.next;
            cnt1--;
        }

        while (cnt2 > 0) {
            temp.data = 2;
            temp = temp.next;
            cnt2--;
        }

        return head;
    }

    public static Node sort012Optimal(Node head) {

        // Dummy heads for 0, 1, 2 lists
        Node zeroDummy = new Node(-1);
        Node oneDummy = new Node(-1);
        Node twoDummy = new Node(-1);

        // Tails to build lists
        Node zero = zeroDummy;
        Node one = oneDummy;
        Node two = twoDummy;

        Node temp = head;

        /*
         * Distribute nodes into 3 lists
         * ------------------------------
         * 0 → zero list
         * 1 → one list
         * 2 → two list
         */
        while (temp != null) {
            if (temp.data == 0) {
                zero.next = temp;
                zero = zero.next;
            } else if (temp.data == 1) {
                one.next = temp;
                one = one.next;
            } else {
                two.next = temp;
                two = two.next;
            }
            temp = temp.next;
        }

        /*
         * Connect lists:
         * --------------
         * zero → one → two
         * Handle empty lists safely
         */
        zero.next = (oneDummy.next != null) ? oneDummy.next : twoDummy.next;
        one.next = twoDummy.next;
        two.next = null; // important to avoid cycle

        // New head is start of zero list
        return zeroDummy.next;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(0);
        head.next.next.next = new Node(1);
        head.next.next.next.next = new Node(2);
        head.next.next.next.next.next = new Node(0);

        head = sort012Brute(head);
        printList(head);
    }

}
