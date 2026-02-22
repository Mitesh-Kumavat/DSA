package Stack;

import java.util.HashMap;
import java.util.Map;

/*
 * LFU Cache (Least Frequently Used)
 *
 * Removal Priority:
 * 1) Lowest frequency
 * 2) If tie → Least Recently Used
 *
 * Time Complexity:
 * get()  → O(1)
 * put()  → O(1)
 */

class Node {
    int key;
    int value;
    int freq;
    Node prev;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.freq = 1; // new node frequency starts at 1
    }
}

/* Doubly Linked List for each frequency */
class DoublyLinkedList {
    Node head;
    Node tail;
    int size;

    public DoublyLinkedList() {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    // Insert at front (Most Recently Used inside same freq)
    public void insert(Node node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
        size++;
    }

    // Remove node
    public void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    // Remove Least Recently Used (tail.prev)
    public Node removeLRU() {
        if (size > 0) {
            Node node = tail.prev;
            remove(node);
            return node;
        }
        return null;
    }
}

public class LFUCache {

    int capacity;
    int minFreq;

    Map<Integer, Node> keyMap; // key → node
    Map<Integer, DoublyLinkedList> freqMap; // freq → DLL

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        keyMap = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {

        if (!keyMap.containsKey(key)) {
            return -1;
        }

        Node node = keyMap.get(key);
        updateFrequency(node);

        return node.value;
    }

    public void put(int key, int value) {

        if (capacity == 0)
            return;

        if (keyMap.containsKey(key)) {

            Node node = keyMap.get(key);
            node.value = value;
            updateFrequency(node);

        } else {

            if (keyMap.size() == capacity) {

                // Remove LFU node
                DoublyLinkedList minList = freqMap.get(minFreq);
                Node nodeToRemove = minList.removeLRU();
                keyMap.remove(nodeToRemove.key);
            }

            Node newNode = new Node(key, value);

            minFreq = 1;

            DoublyLinkedList list = freqMap.getOrDefault(1, new DoublyLinkedList());

            list.insert(newNode);
            freqMap.put(1, list);

            keyMap.put(key, newNode);
        }
    }

    private void updateFrequency(Node node) {

        int currFreq = node.freq;
        DoublyLinkedList currList = freqMap.get(currFreq);

        currList.remove(node);

        // Update minFreq if needed
        if (currFreq == minFreq && currList.size == 0) {
            minFreq++;
        }

        node.freq++;

        DoublyLinkedList newList = freqMap.getOrDefault(node.freq, new DoublyLinkedList());

        newList.insert(node);
        freqMap.put(node.freq, newList);
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);

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