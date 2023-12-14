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

    // default constructor
    public LinkedListCustom() {
        head = null;
    }

    // normal constructor
    public LinkedListCustom(Node head, int size) {
        this.head = head;
    }

    // getter

    // get the head of the list
    public Node getHead() {
        return head;
    }

    // setter
    public void setHead(Node head) {
        this.head = head;
    }

    // insert node at the front of the list
    public void insertAtFront(Food data) {
        Node newNode = new Node(data);
        newNode.setNext(head);
        head = newNode;
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
    }

    // insert node at any position in the list
    public void insertAtPosition(Food data, int position) {
        Node newNode = new Node(data);
        if (position == 1) {
            newNode.setNext(head);
            head = newNode;
        } else {
            Node temp = head;
            for (int i = 1; i < position - 1; i++) {
                temp = temp.getNext();
            }
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
        }
    }

    // insert node at middle of the list
    public void insertAtMiddle(Food data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            // int mid = (size % 2 == 0) ? (size / 2) : ((size + 1) / 2);
            int mid = 0;
            while (temp.getNext() != null) {
                temp = temp.getNext();
                mid++;
            }

            mid = (mid % 2 == 0) ? (mid / 2) : ((mid + 1) / 2);

            temp = head;

            for (int i = 1; i < mid; i++) {
                temp = temp.getNext();
            }
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
        }
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