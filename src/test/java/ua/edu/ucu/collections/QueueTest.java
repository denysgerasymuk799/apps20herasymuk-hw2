package ua.edu.ucu.collections;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {
    Queue emptyQ;
    Queue bigQ;

    @Before
    public void init() {
        emptyQ = new Queue();
        bigQ = new Queue();
        for (int i = 0; i < 6; i++){
            bigQ.enqueue(String.valueOf(i + 1));
        }
    }

    @Test
    public void testConstructor() {
        Queue newQ = new Queue();
    }

    /** ========================= Tests for Enqueue ========================= **/
    @Test
    public void testEnqueue() {
        emptyQ.enqueue(0);
        assertEquals(0, emptyQ.peek());
    }

    @Test
    public void testEnqueueStr() {
        emptyQ.enqueue("1");
        assertEquals("1", emptyQ.peek());
    }

    @Test
    public void testEnqueueBig() {
        bigQ.enqueue(0);
        assertEquals("1", bigQ.peek());
    }

    @Test(expected = NullPointerException.class)
    public void testEnqueueNull() {
        emptyQ.enqueue(null);
        assertNull(emptyQ.peek());
    }

    /** ========================= Tests for Peek ========================= **/
    @Test(expected = IndexOutOfBoundsException.class)
    public void testPeek() {
        assertEquals(0, emptyQ.peek());
    }

    @Test
    public void testPeekBig() {
        assertEquals("1", bigQ.peek());
    }

    /** ========================= Tests for Dequeue ========================= **/
    @Test(expected = IndexOutOfBoundsException.class)
    public void testDequeue() {
        assertEquals(0, emptyQ.dequeue());
    }

    @Test
    public void testDequeueStr() {
        emptyQ.enqueue("1");
        assertEquals("1", emptyQ.dequeue());
        assertTrue(emptyQ.isEmpty());
    }

    @Test
    public void testDequeueBig() {
        bigQ.enqueue(0);
        assertEquals("1", bigQ.dequeue());
        assertFalse(bigQ.isEmpty());
    }
}
