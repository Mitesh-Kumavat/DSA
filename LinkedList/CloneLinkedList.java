package LinkedList;

import java.util.HashMap;

public class CloneLinkedList {
    public static class Node {
        int data;
        Node next;
        Node random;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.random = null;
        }
    }

    /*
     * BRUTE FORCE APPROACH (USING HASHMAP)
     * 
     * INTUITION:
     * - We cannot directly copy nodes because random pointers
     * may point forward or backward to nodes not yet created.
     * - So first create ALL nodes, then connect them.
     * 
     * IDEA:
     * - Use a HashMap to store:
     * originalNode -> clonedNode
     * 
     * STEPS:
     * 1. Traverse original list and create a clone node
     * (only data) for each node.
     * 2. Store mapping in HashMap.
     * 3. Traverse again and assign next & random pointers
     * using the map.
     * 
     * TIME COMPLEXITY: O(N)
     * SPACE COMPLEXITY: O(N)
     */
    public static Node cloneLinkedList(Node head) {

        if (head == null)
            return null;

        HashMap<Node, Node> map = new HashMap<>();
        Node temp = head;

        // Step 1: Create clone nodes
        while (temp != null) {
            map.put(temp, new Node(temp.data));
            temp = temp.next;
        }

        temp = head;

        // Step 2: Assign next and random pointers
        while (temp != null) {
            Node clone = map.get(temp);
            clone.next = map.get(temp.next);
            clone.random = map.get(temp.random);
            temp = temp.next;
        }

        return map.get(head);
    }

    /*
     * OPTIMAL APPROACH
     * 
     * CORE IDEA:
     * - Instead of using HashMap, we temporarily MODIFY the list
     * - We insert cloned nodes in between original nodes
     * 
     * ORIGINAL:
     * A -> B -> C
     * 
     * AFTER STEP 1:
     * A -> A' -> B -> B' -> C -> C'
     * 
     * WHY THIS WORKS:
     * - Clone of any node X is always X.next
     * - So random pointer can be set easily:
     * X'.random = X.random.next
     * 
     * STEPS (3 PASSES):
     * 
     * PASS 1: Insert cloned nodes after each original node
     * PASS 2: Assign random pointers to cloned nodes
     * PASS 3: Separate original list and cloned list
     * 
     * TIME COMPLEXITY: O(3N)
     * SPACE COMPLEXITY: O(1)
     */
    public static Node cloneLinkedListOptimal(Node head) {

        if (head == null)
            return null;

        Node temp = head;

        /*
         * PASS 1
         * Create cloned nodes and insert them
         * just after original nodes
         * 
         * A -> B -> C
         * becomes:
         * A -> A' -> B -> B' -> C -> C'
         */
        while (temp != null) {
            Node copyNode = new Node(temp.data);
            copyNode.next = temp.next;
            temp.next = copyNode;
            temp = copyNode.next;
        }

        temp = head;

        /*
         * PASS 2
         * Assign random pointers to cloned nodes
         * 
         * Original:
         * X.random -> Y
         * 
         * Cloned:
         * X'.random -> Y'
         * i.e. X.random.next
         */
        while (temp != null) {
            Node copyNode = temp.next;

            if (temp.random != null) {
                copyNode.random = temp.random.next;
            } else {
                copyNode.random = null;
            }

            temp = copyNode.next;
        }

        /*
         * PASS 3
         * Separate the interleaved list into:
         * 1. Original list
         * 2. Cloned list
         */
        Node dummy = new Node(-1);
        Node res = dummy;
        temp = head;

        while (temp != null) {
            // Extract cloned node
            res.next = temp.next;
            res = res.next;

            // Restore original list
            temp.next = temp.next.next;
            temp = temp.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {

        /*
         * Original list:
         * 1 -> 2 -> 3
         * 
         * Random pointers:
         * 1.random -> 3
         * 2.random -> 1
         * 3.random -> 2
         */
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);

        head.random = head.next.next;
        head.next.random = head;
        head.next.next.random = head.next;

        Node clonedHead = cloneLinkedListOptimal(head);

        System.out.println("Original list:");
        Node temp = head;
        while (temp != null) {
            System.out.println("Node " + temp.data +
                    " random -> " + (temp.random != null ? temp.random.data : -1));
            temp = temp.next;
        }

        System.out.println("\nCloned list:");
        temp = clonedHead;
        while (temp != null) {
            System.out.println("Node " + temp.data +
                    " random -> " + (temp.random != null ? temp.random.data : -1));
            temp = temp.next;
        }
    }
}
