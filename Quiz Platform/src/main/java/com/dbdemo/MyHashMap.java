package com.dbdemo;

import java.util.LinkedList;

public class MyHashMap<K, V> {
    // Node to store key-value pairs
    @SuppressWarnings("hiding")
    class Node<K, V> {
        K key;
        V value;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int SIZE = 16;  // Initial size of the bucket array
    private LinkedList<Node<K, V>>[] buckets;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = new LinkedList[SIZE];  // Initialize the bucket array
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new LinkedList<>();  // Initialize each bucket as a LinkedList
        }
    }

    // Hash function to calculate the index
    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode()) % SIZE;  // Ensure the index is within bounds
    }

    // Put method to insert or update key-value pairs
    public void put(K key, V value) {
        int index = getBucketIndex(key);
        LinkedList<Node<K, V>> bucket = buckets[index];

        // Check if the key already exists in the bucket
        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                // If key exists, update the value
                node.value = value;
                return;
            }
        }

        // If key does not exist, add a new node to the bucket
        bucket.add(new Node<>(key, value));
    }

    // Get method to retrieve the value for a given key
    public V get(K key) {
        int index = getBucketIndex(key);
        LinkedList<Node<K, V>> bucket = buckets[index];

        // Search for the key in the bucket
        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                return node.value;  // Return the value if found
            }
        }

        return null;  // Return null if the key is not found
    }

    // Method to remove a key-value pair
    public void remove(K key) {
        int index = getBucketIndex(key);
        LinkedList<Node<K, V>> bucket = buckets[index];

        // Find and remove the key-value pair in the bucket
        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                bucket.remove(node);
                return;
            }
        }
    }
}
