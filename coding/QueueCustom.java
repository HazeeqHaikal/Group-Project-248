public class QueueCustom extends Circular {
    public QueueCustom() {
        super();
    }

    // i. Add data at the start of the list (enqueue).
    public void enqueue(Object data) {
        insertAtFront(data);
    }

    // ii. Removes data at the end of a list (dequeue) and return the removed data.
    public Object dequeue() {
        return removeFromBack();
    }

    // iii. Determine whether the list is empty.
    public boolean isEmpty() {
        return super.isEmpty();
    }

    // iv. Determine the size of the list.
    public int getSize() {
        return super.size();
    }
}