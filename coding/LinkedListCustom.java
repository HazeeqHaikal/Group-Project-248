// also need to have ADT and node class that would be able to do the following processing:
// i. Insert node at the front and the back of the list.
// ii. remove the node anywhere in the list.
// iii. provide traversal from the head until the last node in the list. (getHead() and getNext()).
// iv. determine the size of the list.
// v. status of whether the list is empty or has an element(s).
// vi. a method to display details of all elements in the list.

// Define a queue (ADT) data structure with all the necessary methods.
// i. Add data at the end of the list (enqueue).
// ii. Removes data at the beginning of a list (dequeue).
// iii. Determine the size of the list.
// iv. Determine whether the list is empty.
// d) Define TWO application classes:
// i. First is to implement the 5 processing in the Linked List data structure.
// ii. The second is to implement the 5 processing in Queue data structure.
// *The data for processing should be stored and read from an input File (.txt)

public class LinkedListCustom {
    private Node head;
    private int size;

}

class Node {
    private Food data;
    private Node next;
}