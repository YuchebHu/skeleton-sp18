
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;

import org.junit.Test;

public class ArrayDequeTest {

    @Test
    public void testaddsizeempty() {
        ArrayDeque<String> dq = new ArrayDeque<>();
        boolean isEmpty = dq.isEmpty();
        assertTrue(dq.isEmpty() == true);

        dq.addFirst("first");
        assertTrue(1 == dq.size());

        dq.addLast("last");
        assertTrue(2 == dq.size());

        dq.printDeque();

        String first = dq.removeFirst();
        assertTrue("first".equals(first));

        String last = dq.removeLast();
        assertTrue("last".equals(last));

        assertTrue(0 == dq.size());
    }

    // public ArrayDeque<Integer> create(int[] array) {
    // ArrayDeque<Integer> dq = new ArrayDeque<>();
    // for (int x : array) {
    // dq.addLast(x);
    // }
    // return dq;
    // }

    @Test
    public void testgrowshrink() {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < 16; i++) {
            dq.addLast(i);
        }
        for (int i = -16; i < 0; i++) {
            dq.addFirst(i);
        }
        // {
        // ArrayDeque<Integer> newDq = new ArrayDeque<>(dq);
        // for (int i = 0; i < dq.size(); ++i) {
        // assertTrue(newDq.get(i) == dq.get(i));
        // }
        // }

        for (int i = 0; i < 16; i++) {
            assertTrue(-i - 1 == dq.get(i));
        }
        for (int i = 0; i < 30; i++) {
            dq.removeFirst();
        }
        assertTrue(2 == dq.size());
        dq.printDeque();

        // {
        // ArrayDeque<Integer> newDq = new ArrayDeque<>(dq);
        // for (int i = 0; i < dq.size(); ++i) {
        // assertTrue(newDq.get(i) == dq.get(i));
        // }
        // }
    }

}