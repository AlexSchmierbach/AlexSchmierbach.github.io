package com.spoons;

import java.util.Scanner;

import com.spoons.core.Node;
import com.spoons.ui.Menu;

/**
 * A simple command line tool to create, delete, and edit the nodes within a Doubly Linked List data structure.
 * 
 * @author Alex Schmierbach
 * @date 2-8-2025
 */
public final class App {
    private App() {
    }

    /**
     * Allows for the insertion of a Node at the head.
     * @param head - The head of the list to find the Node that will be inserted.
     * @param data - The data that will reside within the new Node.
     * @reurn - Returns the newNode which is the new head.
     */
    static Node insertAtBegin(Node head, int data) {
        Node newNode = new Node(data);

        // Make next of the new node as the head
        newNode.setNext(head);

        // Set previous of the head as the new node
        if (head != null) {
            head.setPrevious(newNode);
        }

        // Returns the new node as the new head
        return newNode;
    }

    /**
     * Allows for the insertion of a Node at the tail.
     * @param head - The head of the list to find the Node that will be inserted.
     * @param data - The data that will reside within the new Node.
     * @return - Returns the new head of the list.
     */
    static Node insertAtEnd(Node head, int data) {
        Node newNode = new Node(data);

        // If the linked list is empty, set the new node as the head
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;

            while (current.getNext() != null) {
                current = current.getNext();
            }

            // Set the next of last node to the new node
            current.setNext(newNode);
            // Set the previous of new node to the last node
            newNode.setPrevious(current);
        }

        return head;
    }

    // Find the length of the Doubly Linked List
    /**
     * Finds the entire length of the list based from a specific head Node.
     * @param head - The head of the list to find the entire length.
     * @return - Returns the length of the entire list.
     */
    static int findLength(Node head) {
        int count = 0;
        for (Node current = head; current != null; current = current.getNext()) {
            count++;
        }

        return count;
    }

    /**
     * Allows for the insertion of a Node at the specified position.
     * @param head - The head of the list to find the Node that will be inserted.
     * @param position - The position of the Node that will be inserted.
     * @param data - The data that will reside within the new Node.
     * @return - Returns the head of the list after insertion.
     */
    static Node insertAtPosition(Node head, int position, int data) {
        Node newNode = new Node(data);

        // Insert at the beginning
        if (position == 1) {
            newNode.setNext(head);

            // If the linked list is not empty, set the previous node of the head to the new node
            if (head != null) {
                head.setPrevious(newNode);
            }

            // Set the new node as the head of the linked list
            head = newNode;
            return head;
        }

        Node current = head;

        // Tarverse the list to find the node before the insertion point
        for (int i = 1; ((i < position - 1) && (current != null)); ++i) {
            current = current.getNext();
        }

        // If the position is out of bounds
        if (current == null) {
            System.out.println("Position is out of bounds.");
            return head;
        }

        // Set the previous node of the new node to the current node
        newNode.setPrevious(current);

        // Set the next node of the new node to the next of the current node
        newNode.setNext(current.getNext());

        // update the next node of the current node to the new node
        current.setNext(newNode);

        // If the new node is not the last node, update the previous node of the next node to the new node
        if (newNode.getNext() != null) {
            newNode.getNext().setPrevious(newNode);
        }

        return head;
    }

    /**
     * Allows for the deletion of the head Node.
     * @param head - The head of the list to find the Node that will be deleted.
     * @return - Returns the head of the list that doesn't include the deleted Node.
     */
    static Node deleteHead(Node head) {
        if (head == null) {
            return null;
        }

        // Store in temp for deletion later due to local scope and hava automatically cleaing up
        Node temp = head;

        // Move the head tot he next node of the head
        head = head.getNext();

        // Set the previous node of the new head node
        if (head != null) {
            head.setPrevious(null);
        }

        return head;
    }

    /**
     * Allows for the deletion of the tail Node.
     * @param head - The head of the list to find the Node that will be deleted.
     * @return - Returns the head of the list that doesn't include the deleted Node.
     */
    static Node deleteTail(Node head) {
        if (head == null) {
            return null;
        }

        if (head.getNext() == null) {
            return null;
        }

        // Traverse to the last node
        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }

        if (current.getPrevious() != null) {
            current.getPrevious().setNext(null);
        }

        return head;
    }

    /**
     * Allows for the deletion of a Node at the specified position.
     * @param head - The head of the list to find the Node that will be deleted.
     * @param position - The position of the Node that will be deleted.
     * @return - Returns the head of the list that doesn't include the deleted Node.
     */
    static Node deleteAtPosition(Node head, int position) {
        if (head == null) {
            return head;
        }

        Node current = head;

        // Traverse to the node at the specific position
        for (int i = 1; ((current != null) && (i < position)); ++i) {
            current = current.getNext();
        }

        // If the position is out of range
        if (current == null) {
            return head;
        }

        if (current.getPrevious() != null) {
            current.getPrevious().setNext(current.getNext());
        }

        if (current.getNext() != null) {
            current.getNext().setPrevious(current.getPrevious());
        }

        if (head == current) {
            head = current.getNext();
        }

        return head;
    }

    /**
     * Allows for editing the data within the node at the specified position.
     * @param head - The head of the list to find the Node that will be edited.
     * @param position - The position of the Node that will have their data edited.
     * @param data = The new data that will replace the old data within the edited Node.
     * @return - Returns the head of the list that has the Node with new data that was updated.
     */
    static Node editNodeAtPosition(Node head, int position, int data) {
        if (head == null) {
            return head;
        }

        Node current = head;

        // Traverse of the node at the specific position
        for (int i = 1; ((current != null) && (i < position)); ++i) {
            current = current.getNext();
        }

        // If the position is out of range
        if (current == null) {
            return head;
        }

        current.setData(data);

        return head;
    }

    /**
     * Displays options the user can use to manipulate a Doubly Linked list.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Menu menu = new Menu();
        //Node head = initTestList();
        Node head = new Node(0);
        int inputValue = 0;

        while (inputValue != 4) {
            switch (inputValue) {
                case 0:
                    menu.printList(head);
                    break;
                case 1: // Adds nodes to the Doubly Linked List
                    int newNodeData = -1;
                    menu.printAddNodeMenu();
                    menu.printInputMarker();
                    int addInputValue = input.nextInt();
                    switch (addInputValue) {
                        case 1: // Adds a Node at the head of the list
                            System.out.println("Please specifiy data within the node.");
                            menu.printInputMarker();
                            newNodeData = input.nextInt();
                            head = insertAtBegin(head, newNodeData);
                            System.out.println("+-------------------------------------------------+");
                            System.out.println(String.format(" Added a new node with data %d at the head.", newNodeData));
                            System.out.println("+-------------------------------------------------+");
                            break;
                        case 2: // Adds a Node at the tail of the list
                            System.out.println("Please specifiy the data within the node.");
                            menu.printInputMarker();
                            newNodeData = input.nextInt();
                            head = insertAtEnd(head, newNodeData);
                            System.out.println("+-----------------------------------------------+");
                            System.out.println(String.format(" Added a new node with data %d at the tail.", newNodeData));
                            System.out.println("+-----------------------------------------------+");
                            break;
                        case 3: // Adds a Node at a specific position within the list
                            System.out.println("Please specifiy the data within the node.");
                            menu.printInputMarker();
                            newNodeData = input.nextInt();
                            System.out.println(String.format("Please specifiy the position of the node. 1-Head %d-Tail", findLength(head)));
                            menu.printInputMarker();
                            int newNodePosition = input.nextInt();
                            head = insertAtPosition(head, newNodePosition, newNodeData);
                            System.out.println("+-----------------------------------------------+");
                            System.out.println(String.format(" Added a new node with data %d at position %d within the list.", newNodeData));
                            System.out.println("+-----------------------------------------------+");
                            break;
                    }
                    break;
                case 2: // Removes nodes from the Doubly Linked List
                    menu.printRemoveNodeMenu();
                    menu.printInputMarker();
                    int removeInputValue = input.nextInt();
                    switch (removeInputValue) {
                        case 1: // Deletes the head of the list
                            head = deleteHead(head);
                            System.out.println("+-------------------------------------------------+");
                            System.out.println("Removed the head node.");
                            System.out.println("+-------------------------------------------------+");
                            break;
                        case 2: // Deletes the tail of the list
                            head = deleteTail(head);
                            System.out.println("+-----------------------------------------------+");
                            System.out.println("Removed the tail node.");
                            System.out.println("+-----------------------------------------------+");
                            break;
                        case 3: // Deletes the Node at the specific position within the list
                            System.out.println("Please specifiy the position of the node.");
                            menu.printInputMarker();
                            int removeNodePosition = input.nextInt();
                            head = deleteAtPosition(head, removeNodePosition);
                            System.out.println("+-----------------------------------------------+");
                            System.out.println(String.format("Removed a node at position %d within the list.", removeNodePosition));
                            System.out.println("+-----------------------------------------------+");
                            break;
                        }
                    break;
                case 3: // Edits a specific node
                    System.out.println(String.format("Please specifiy the position of the node you wish to edit. 1-Head %d-Tail", findLength(head)));
                    menu.printInputMarker();
                    int positionOfNodeToBeEdited = input.nextInt();
                    System.out.println("Please specify the new data you would like to update within the node,");
                    menu.printInputMarker();
                    newNodeData = input.nextInt();
                    head = editNodeAtPosition(head, positionOfNodeToBeEdited, newNodeData);
                    System.out.println("+-----------------------------------------------+");
                    System.out.println(String.format("Edited node at position %d with new data %d.", positionOfNodeToBeEdited, newNodeData));
                    System.out.println("+-----------------------------------------------+");
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid input please choose a valid option.");
            }
            menu.printList(head);
            menu.printMainMenu();
            menu.printInputMarker();
            inputValue = input.nextInt();
        }

        System.out.println("Exiting...");
        input.close();
    }
}
