package com.spoons.core;

/**
 * A class that holds the information of a Node within the Doubly Linked List.
 * 
 * @author Alex Schmierbach
 * @date 2-8-2025
 */
public class Node {
    int data; // Data stored within the node
    Node next; // Pointer to the next node
    Node previous; // Pointer to the previous node

    // Constructor
    public Node(int data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    // Getter for the data within the Node
    public int getData() {
        return this.data;
    }

    // Getter for the Node that comes after this one
    public Node getNext() {
        return this.next;
    }

    // Getter for the Node that comes before this one
    public Node getPrevious() {
        return this.previous;
    }

    // Setter for the data within the node
    public void setData(int newData) {
        this.data = newData;
    }

    // Setter for the Node that comes after this one
    public void setNext(Node newNode) {
        this.next = newNode;
    }

    // Setter for the Node that comes before this one
    public void setPrevious(Node newNode) {
        this.previous = newNode;
    }
}
