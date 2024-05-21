/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
public class ResizingArrayStackOfStrings {
    private String[] s;
    private int n = 0;

    public ResizingArrayStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    private void resize(int capacity) {
        String[] newArray = new String[capacity];
        for (int i = 0; i < n; i++) {
            newArray[i] = s[i];
        }
        s = newArray;
    }

    public void push(String item) {
        if (n == s.length) {
            resize(2 * s.length);
        }
        s[n++] = item;
    }

    public String pop() {
        String item = s[--n];
        s[n] = null;
        if (n > 0 && n == s.length / 4) {
            resize(s.length / 2);
        }
        return item;
    }

    public boolean isEmpty() {
        return n == 0;
    }
}
