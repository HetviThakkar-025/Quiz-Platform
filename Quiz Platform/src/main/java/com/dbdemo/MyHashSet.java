package com.dbdemo;

import java.util.LinkedList;

public class MyHashSet<T> {
    // Node class to represent an element in the set
    class Node<T> {
        T value;
        Node(T value) {
            this.value = value;
        }
    }

    private final int SIZE = 16;  // Initial size of the bucket array
    private LinkedList<Node<T>>[] buckets;

    @SuppressWarnings("unchecked")
    public MyHashSet() {
        buckets = new LinkedList[SIZE];  // Initialize the bucket array
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new LinkedList<>();  // Initialize each bucket as a LinkedList
        }
    }

    // Hash function to calculate the index
    private int getBucketIndex(T value) {
        return Math.abs(value.hashCode()) % SIZE;  // Ensure the index is within bounds
    }

    // Add method to insert a value into the set
    public void add(T value) {
        int index = getBucketIndex(value);
        LinkedList<Node<T>> bucket = buckets[index];

        // Check if the value already exists in the set
        for (Node<T> node : bucket) {
            if (node.value.equals(value)) {
                // Value already exists, do nothing
                return;
            }
        }

        // Value does not exist, add a new node to the bucket
        bucket.add(new Node<>(value));
    }

    // Contains method to check if the set contains a specific value
    public boolean contains(T value) {
        int index = getBucketIndex(value);
        LinkedList<Node<T>> bucket = buckets[index];

        // Search for the value in the bucket
        for (Node<T> node : bucket) {
            if (node.value.equals(value)) {
                return true;  // Value found in the set
            }
        }

        return false;  // Value not found
    }

    // Remove method to delete a value from the set
    public void remove(T value) {
        int index = getBucketIndex(value);
        LinkedList<Node<T>> bucket = buckets[index];

        // Find and remove the value in the bucket
        for (Node<T> node : bucket) {
            if (node.value.equals(value)) {
                bucket.remove(node);
                return;
            }
        }
    }
}


