/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class LinkedQueueOfStrings {
    private class Node {
        String item;
        Node next;
    }

    private Node first = null;
    private Node last = null;

    public void enqueue(String item) {
        Node newLast = new Node();
        newLast.item = item;
        newLast.next = null;
        if (isEmpty()) {
            first = newLast;
            last = newLast;
        }
        else {
            last.next = newLast;
            last = newLast;
        }
    }

    public String dequeue() {
        if (isEmpty()) {
            return null;
        }
        Node oldFirst = first;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return oldFirst.item;
    }

    public boolean isEmpty() {
        return first == null;
    }
}
