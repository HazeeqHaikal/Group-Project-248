// Define a queue (ADT) data structure with all the necessary methods.
// i. Add data at the end of the list (enqueue).
// ii. Removes data at the beginning of a list (dequeue).
// iii. Determine the size of the list.
// iv. Determine whether the list is empty.
// d) Define TWO application classes:
// i. First is to implement the 5 processing in the Linked List data structure.
// ii. The second is to implement the 5 processing in Queue data structure.
// *The data for processing should be stored and read from an input File (.txt)

import java.io.*;

public class QueueCustom {
    private Node head;
    private int size;

    // default constructor
    public QueueCustom() {
        head = null;
        size = 0;
    }

    // normal constructor
    public QueueCustom(Node head, int size) {
        this.head = head;
        this.size = size;
    }

    // getter
    public Node getHead() {
        return head;
    }

    // iii. Determine the size of the list.
    public int getSize() {
        return size;
    }

    // setter
    public void setHead(Node head) {
        this.head = head;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // i. Add data at the end of the list (enqueue).
    public void enqueue(Food data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
        }
        size++;
    }

    // ii. Removes data at the beginning of a list (dequeue).
    public void dequeue() {
        if (head == null) {
            System.out.println("Queue is empty");
        } else {
            head = head.getNext();
            size--;
        }
    }

    // iv. Determine whether the list is empty.
    public boolean isEmpty() {
        return head == null;
    }

    // display all the data in the list
    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }
    }

    // store all the data in the list into a txt file
    public void storeToFile() {
        Node temp = head;
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));
            while (temp != null) {
                pw.println(temp.getData());
                temp = temp.getNext();
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("File " + e.getMessage() + " not found.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\nAt line: " + e.getStackTrace()[0].getLineNumber());
        }
    }

}
