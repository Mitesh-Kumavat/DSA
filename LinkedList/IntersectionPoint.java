package LinkedList;

import java.util.HashMap;

public class IntersectionPoint {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /*
     * APPROACH 1: HASHMAP (BRUTE FORCE)
     * 
     * INTUITION:
     * ----------
     * If two linked lists intersect, they will share at least
     * one common node (same reference).
     * 
     * IDEA:
     * -----
     * - Store all nodes of first list in HashMap
     * - Traverse second list
     * - First node found in map is the intersection
     * 
     * WHY IT WORKS:
     * -------------
     * HashMap checks reference equality, not value equality.
     * 
     * TIME : O(n + m)
     * SPACE : O(n)
     */

    public static Node getIntersectionUsingHashMap(Node l1, Node l2) {

        // If either list is null, no intersection possible
        if (l1 == null || l2 == null) {
            return null;
        }

        HashMap<Node, Integer> map = new HashMap<>();

        Node temp = l1;

        // Step 1: Store all nodes of first list
        while (temp != null) {
            map.put(temp, 1); // key is node reference
            temp = temp.next;
        }

        // Step 2: Traverse second list and check presence
        temp = l2;
        while (temp != null) {
            if (map.containsKey(temp)) {
                // First common node found
                return temp;
            }
            temp = temp.next;
        }

        // No intersection
        return null;
    }

    /*
     * APPROACH 2: TWO POINTER SWITCHING
     * 
     * INTUITION:
     * ----------
     * Let:
     * Length of list1 = a + c
     * Length of list2 = b + c
     * (c = common part)
     * 
     * If:
     * - Pointer1 traverses list1 then list2
     * - Pointer2 traverses list2 then list1
     * 
     * Both pointers will travel:
     * a + b + c steps → they meet at intersection
     * 
     * KEY IDEA:
     * ---------
     * Switching heads equalizes the distance automatically.
     * No length calculation needed.
     * 
     * TIME : O(n + m)
     * SPACE : O(1)
     */

    public static Node getIntersectionTwoPointer(Node l1, Node l2) {

        if (l1 == null || l2 == null) {
            return null;
        }

        Node ptr1 = l1;
        Node ptr2 = l2;

        /*
         * Traverse until both pointers meet
         * --------------------------------
         * - Move both pointers one step
         * - If pointer reaches null, switch to other list head
         */
        while (ptr1 != ptr2) {

            ptr1 = (ptr1 == null) ? l2 : ptr1.next;
            ptr2 = (ptr2 == null) ? l1 : ptr2.next;
        }

        /*
         * ptr1 == ptr2
         * - Either intersection node
         * - OR both null (no intersection)
         */
        return ptr1;
    }

    /*
     * APPROACH 3: LENGTH / DISTANCE METHOD
     * 
     * INTUITION:
     * ----------
     * If one list is longer,
     * move its pointer ahead by length difference.
     * Then move both pointers together.
     * 
     * WHY IT WORKS:
     * -------------
     * After alignment, both pointers are equally far
     * from intersection.
     * 
     * STEPS:
     * ------
     * 1. Calculate length of both lists
     * 2. Find difference
     * 3. Move longer list pointer ahead
     * 4. Traverse both together
     * 
     * TIME : O(n + m)
     * SPACE : O(1)
     */

    public static Node getIntersectionByLength(Node l1, Node l2) {

        if (l1 == null || l2 == null) {
            return null;
        }

        // Step 1: Calculate lengths
        int len1 = 0, len2 = 0;
        Node temp1 = l1;
        Node temp2 = l2;

        while (temp1 != null) {
            len1++;
            temp1 = temp1.next;
        }

        while (temp2 != null) {
            len2++;
            temp2 = temp2.next;
        }

        // Step 2: Align starting points
        temp1 = l1;
        temp2 = l2;
        int diff = Math.abs(len1 - len2);

        if (len1 > len2) {
            while (diff-- > 0) {
                temp1 = temp1.next;
            }
        } else {
            while (diff-- > 0) {
                temp2 = temp2.next;
            }
        }

        // Step 3: Traverse both lists together
        while (temp1 != null && temp2 != null) {
            if (temp1 == temp2) {
                return temp1;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        return null;
    }

    public static void main(String[] args) {

        Node head1 = new Node(3);
        head1.next = new Node(6);
        head1.next.next = new Node(9);
        head1.next.next.next = new Node(15);
        head1.next.next.next.next = new Node(30);

        Node head2 = new Node(10);
        head2.next = head1.next.next.next; // intersection at 15

        Node ans1 = getIntersectionUsingHashMap(head1, head2);
        Node ans2 = getIntersectionTwoPointer(head1, head2);
        Node ans3 = getIntersectionByLength(head1, head2);

        System.out.println("HashMap Approach: " +
                (ans1 != null ? ans1.data : "No Intersection"));

        System.out.println("Two Pointer Approach: " +
                (ans2 != null ? ans2.data : "No Intersection"));

        System.out.println("Length Approach: " +
                (ans3 != null ? ans3.data : "No Intersection"));
    }
}
