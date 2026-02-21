package Stack;

import java.util.Map;
import java.util.HashMap;

/*
 * LRU CACHE (Least Recently Used Cache)
 *
 * Rules:
 * 1) When capacity is full → remove the Least Recently Used item.
 * 2) get(key) → return value if present, else -1.
 * 3) put(key, value) → insert/update key.
 *
 * We must achieve:
 * - O(1) get
 * - O(1) put
 *
 * So we use:
 * 1) HashMap  → O(1) access by key
 * 2) Doubly Linked List → O(1) insert & delete
 *
 * Why Doubly Linked List?
 * Because we need fast removal from middle.
 */

class Node {
    int key;
    int value;
    Node prev;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}

public class LRUCache {

    Map<Integer, Node> cache;
    int capacity;

    // Dummy head and tail nodes
    // head.next = Most Recently Used (MRU)
    // tail.prev = Least Recently Used (LRU)
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        cache = new HashMap<>();
        this.capacity = capacity;

        // Create dummy nodes to avoid null checks
        head = new Node(-1, -1);
        tail = new Node(-1, -1);

        head.next = tail;
        tail.prev = head;
    }

    /*
     * Removes a node from the doubly linked list.
     * Time: O(1)
     */
    public void deleteNode(Node node) {
        Node prev = node.prev;
        prev.next = node.next;
        node.next.prev = prev;
    }

    /*
     * Inserts node right after head.
     * This means it becomes the Most Recently Used.
     * Time: O(1)
     */
    public void insertAfterHead(Node node) {
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    /*
     * GET OPERATION
     *
     * If key exists:
     * - Move it to front (MRU position) why because it is accessed now so it
     * becomes most recently used.
     * - Return value
     *
     * Else:
     * - Return -1
     */
    public int get(int key) {

        if (!cache.containsKey(key)) {
            return -1;
        }

        Node node = cache.get(key);

        // Since it is accessed, it becomes Most Recently Used
        deleteNode(node);
        insertAfterHead(node);

        return node.value;
    }

    /*
     * PUT OPERATION
     *
     * Case 1: Key already exists
     * - Update value
     * - Move to front (MRU)
     *
     * Case 2: Key does not exist
     * - If capacity full:
     * Remove LRU (tail.prev)
     * - Insert new node at front
     */
    public void put(int key, int value) {

        if (cache.containsKey(key)) {

            // Update existing node
            Node node = cache.get(key);
            node.value = value;

            // Move to MRU position
            deleteNode(node);
            insertAfterHead(node);

        } else {

            // If capacity full → remove LRU
            if (cache.size() == capacity) {
                Node node = tail.prev; // LRU node
                cache.remove(node.key);
                deleteNode(node);
            }

            // Insert new node
            Node node = new Node(key, value);
            cache.put(key, node);
            insertAfterHead(node);
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // returns 1

        cache.put(3, 3); // evicts key 2
        System.out.println(cache.get(2)); // returns -1 (not found)

        cache.put(4, 4); // evicts key 3
        System.out.println(cache.get(3)); // returns -1 (not found)
        System.out.println(cache.get(4)); // returns 4
    }
}