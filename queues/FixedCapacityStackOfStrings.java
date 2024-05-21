/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class FixedCapacityStackOfStrings {
    private String[] s;
    private int n = 0;

    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    public void push(String item) {
        s[n++] = item;
    }

    public String pop() {
        String item = s[--n];
        s[n] = null;
        return item;
    }

    public boolean isEmpty() {
        return n == 0;
    }
}
