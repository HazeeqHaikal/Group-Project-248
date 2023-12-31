public class Node {
    private Object data;
    private Node next;

    // default constructor
    public Node() {
        data = null;
        next = null;
    }

    // normal constructor
    public Node(Object data) {
        this.data = data;
        next = null;
    }

    // getter
    public Object getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    // setter
    public void setData(Object data) {
        this.data = data;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public String toString() {
        return data + "";
    }
}
