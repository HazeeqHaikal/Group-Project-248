// circular linked list class
// the head and tail of the list are connected
// the tail points to the head

public class Circular {
    private Node head;
    private Node tail;
    private int size;

    public Circular() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(Object data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            tail.setNext(head);
        } else {
            tail.setNext(newNode);
            tail = newNode;
            tail.setNext(head);
        }
        size++;
    }

    public void add(Object data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node newNode = new Node(data);
        // if the list is empty
        if (head == null) {
            head = newNode;
            tail = newNode;
            tail.setNext(head);
        } else if (index == 0) {
            newNode.setNext(head);
            head = newNode;
            tail.setNext(head);
        } else if (index == size) {
            tail.setNext(newNode);
            tail = newNode;
            tail.setNext(head);
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        size++;
    }

    // insert a node at the end of the list
    public void insertAtBack(Object data) {
        add(data, size);
    }

    // insert a node at the front of the list
    public void insertAtFront(Object data) {
        add(data, 0);
    }

    // insert a node at middle of the list
    public void insertAtMiddle(Object data) {
        add(data, size / 2);
    }

    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Object removedData = null;
        if (index == 0) {
            removedData = head.getData();
            head = head.getNext();
            tail.setNext(head);
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            removedData = current.getNext().getData();
            current.setNext(current.getNext().getNext());
        }
        size--;
        return removedData;
    }

    // remove from front of the list
    public Object removeFromFront() {
        return remove(0);
    }

    // remove from back of the list
    public Object removeFromBack() {
        return remove(size - 1);
    }

    // remove from middle of the list
    public Object removeFromMiddle() {
        return remove(size / 2);
    }

    // remove all nodes from the list
    public void removeAll() {
        while (!isEmpty()) {
            removeFromFront();
        }
    }

    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    public int size() {
        return size;
    }

    // getSize() is the same as size()
    public int getSize() {
        return size;
    }

    // check if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // swap the data of two nodes
    public void swap(int index1, int index2) {
        if (index1 < 0 || index1 >= size || index2 < 0 || index2 >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node current1 = head;
        for (int i = 0; i < index1; i++) {
            current1 = current1.getNext();
        }
        Node current2 = head;
        for (int i = 0; i < index2; i++) {
            current2 = current2.getNext();
        }
        Object temp = current1.getData();
        current1.setData(current2.getData());
        current2.setData(temp);
    }

    public String toString() {
        String output = "";
        Node current = head;
        for (int i = 0; i < size; i++) {
            output += "[" + current.getData() + "] -> ";
            current = current.getNext();
        }
        return output;

    }
}
