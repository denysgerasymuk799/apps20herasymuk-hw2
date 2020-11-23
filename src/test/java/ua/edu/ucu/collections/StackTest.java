package ua.edu.ucu.collections;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StackTest {

    Stack emptySt;
    Stack bigSt;

    @Before
    public void init() {
        emptySt = new Stack();
        bigSt = new Stack();
        for (int i = 0; i < 6; i++){
            bigSt.push(String.valueOf(i + 1));
        }
    }

    @Test
    public void testConstructor() {
        Stack newSt = new Stack();
    }

    /** ========================= Tests for Push ========================= **/
    @Test
    public void testPush() {
        emptySt.push(0);
        assertEquals(0, emptySt.peek());
    }

    @Test
    public void testPushStr() {
        emptySt.push("1");
        assertEquals("1", emptySt.peek());
    }

    @Test
    public void testPushBig() {
        bigSt.push(0);
        assertEquals(0, bigSt.peek());
    }

    @Test(expected = NullPointerException.class)
    public void testPushNull() {
        emptySt.push(null);
        assertNull(emptySt.peek());
    }

    /** ========================= Tests for Peek ========================= **/
    @Test(expected = IndexOutOfBoundsException.class)
    public void testPeek() {
        assertEquals(0, emptySt.peek());
    }

    @Test
    public void testPeekBig() {
        assertEquals("6", bigSt.peek());
    }

    /** ========================= Tests for Pop ========================= **/
    @Test(expected = IndexOutOfBoundsException.class)
    public void testPop() {
        assertEquals(0, emptySt.pop());
    }

    @Test
    public void testPopStr() {
        emptySt.push("1");
        assertEquals("1", emptySt.pop());
        assertTrue(emptySt.isEmpty());
    }

    @Test
    public void testPopBig() {
        bigSt.push(0);
        assertEquals(0, bigSt.pop());
        assertEquals("6", bigSt.pop());
        assertFalse(bigSt.isEmpty());
    }
}
