// also need to have ADT and node class that would be able to do the following processing:
// i. Insert node at the front and the back of the list.
// ii. remove the node anywhere in the list.
// iii. provide traversal from the head until the last node in the list. (getHead() and getNext()).
// iv. determine the size of the list.
// v. status of whether the list is empty or has an element(s).
// vi. a method to display details of all elements in the list.

public class LinkedListCustom {
    // atrributes
    private Node head;
    private int size;

    // default constructor
    public LinkedListCustom() {
        head = null;
        size = 0;
    }

    // normal constructor
    public LinkedListCustom(Node head, int size) {
        this.head = head;
        this.size = size;
    }

    // getter

    // get the head of the list
    public Node getHead() {
        return head;
    }

    // show the size of the list
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

    // insert node at the front of the list
    public void insertAtFront(Food data) {
        Node newNode = new Node(data);
        newNode.setNext(head);
        head = newNode;
        size++;
    }

    // insert node at the back of the list
    public void insertAtBack(Food data) {
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

    // remove node anywhere in the list
    public void removeNode(Food data) {
        Node temp = head;
        Node prev = null;
        if (temp != null && temp.getData().equals(data)) {
            head = temp.getNext();
            return;
        }
        while (temp != null && !temp.getData().equals(data)) {
            prev = temp;
            temp = temp.getNext();
        }
        if (temp == null) {
            return;
        }
        prev.setNext(temp.getNext());
        size--;
    }

    // status of whether the list is empty or has an element(s)
    public boolean isEmpty() {
        return head == null;
    }

    // a method to display details of all elements in the list
    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }
    }

}

class Node {
    private Food data;
    private Node next;

    // default constructor
    public Node() {
        data = null;
        next = null;
    }

    // default constructor
    public Node(Food data) {
        this.data = data;
        next = null;
    }

    // normal constructor
    public Node(Food data, Node next) {
        this.data = data;
        this.next = next;
    }

    // getter
    public Food getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    // setter
    public void setData(Food data) {
        this.data = data;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public String toString() {
        return data.toString();
    }

}