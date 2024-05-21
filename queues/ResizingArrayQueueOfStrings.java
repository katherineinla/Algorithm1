/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.NoSuchElementException;

public class ResizingArrayQueueOfStrings {
    private String[] s;
    private int n = 0;

    public ResizingArrayQueueOfStrings(int capacity) {
        s = new String[capacity];
    }

    private void resize(int capacity) {
        String[] newArray = new String[capacity];
        for (int i = 0; i < n; i++) {
            newArray[i] = s[i];
        }
        s = newArray;
    }

    public void enqueue(String item) {
        if (n == s.length) {
            resize(2 * s.length);
        }
        s[n++] = item;
    }

    public String dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty");
        }
        String item = s[0];
        n--;
        for (int i = 0; i < n; i++) {
            s[i] = s[i + 1];
        }
        s[n] = null;
        if (n == s.length / 4) {
            resize(s.length / 2);
        }
        return item;
    }

    public boolean isEmpty() {
        return n == 0;
    }
}
