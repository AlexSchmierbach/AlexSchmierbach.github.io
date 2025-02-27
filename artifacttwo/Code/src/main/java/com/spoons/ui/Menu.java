package com.spoons.ui;

import com.spoons.core.Node;


/**
 * A class that holds repsonsibility for printing information to the console.
 * 
 * @author Alex Schmierbach
 * @date 2-8-2025
 */
public class Menu {
    public Menu() {}

    // Decoration for the user input
    public void printInputMarker() {
        System.out.print("> ");
    }

    // Shows the user the list of options that can be done
    public void printMainMenu() {
        System.out.println("+-----------------------------------------------+");
        System.out.println("|         Weclome to Doubly Manipulate!         |");
        System.out.println("| 0. Print the current doubly linked list.      |");
        System.out.println("| 1. Add to the doubly linked list.             |");
        System.out.println("| 2. Remove from the doubly linked list.        |");
        System.out.println("| 3. Update node within the doubly linked list. |");
        System.out.println("| 4. Exit.                                      |");
        System.out.println("+-----------------------------------------------+");
    }

    // Shows the user the sub options when they want to add a node to the Doubly Linked List
    public void printAddNodeMenu() {
        System.out.println("+-------------------------------------------------------------+");
        System.out.println("| Do you want to insert at the head, tail, or in the middle?  |");
        System.out.println("| 1. Head                                                     |");
        System.out.println("| 2. Tail                                                     |");
        System.out.println("| 3. Middle                                                   |");
        System.out.println("+-------------------------------------------------------------+");
    }

    // Shows the user the sub options when they want to remove a node from the Doubly Linked List
    public void printRemoveNodeMenu() {
        System.out.println("+-------------------------------------------------------------+");
        System.out.println("| Do you want to remove the head, tail, or within the middle? |");
        System.out.println("| 1. Head                                                     |");
        System.out.println("| 2. Tail                                                     |");
        System.out.println("| 3. Middle                                                   |");
        System.out.println("+-------------------------------------------------------------+");
    }

    // Show the user the current state of the Doubly Linked List
    public void printList(Node head) {
        Node current = head;
        
        System.out.println("+-----------------------------------------------+");
        System.out.println("|         Current Doubly Linked List!           |");
        System.out.println("+-----------------------------------------------+");
        if (current == null) {
            System.out.print("Empty List or No List Found");
        }
        while (current != null) {
            System.out.print("<-(" + current.getData() + ")->");
            
            current = current.getNext();
        }
        System.out.println();
        System.out.println("+-----------------------------------------------+");
    }
}
