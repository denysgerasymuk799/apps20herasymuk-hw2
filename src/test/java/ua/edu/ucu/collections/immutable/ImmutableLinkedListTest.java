package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ImmutableLinkedListTest {
    private ImmutableList emptyImmutableLinked;
    private ImmutableList oneItemImmutableLinked;
    private ImmutableList threeItemsImmutableLinked;
    private ImmutableList eightItemsImmutableLinked;

    private ImmutableLinkedList threeImmutableLinkedList;


    @Before
    public void init() {
        ImmutableList newImmutableLinked = new ImmutableLinkedList();
        oneItemImmutableLinked = newImmutableLinked.add("12");
        ImmutableList newImmutableLinked3 = oneItemImmutableLinked.add("34");
        threeItemsImmutableLinked = newImmutableLinked3.add(56.7);

        Object[] inputArr = new Object[7];
        for (int i = 0; i < 7; i++){
            inputArr[i] = String.valueOf(i + 1);
        }
        eightItemsImmutableLinked = oneItemImmutableLinked.addAll(0, inputArr);

        emptyImmutableLinked = new ImmutableLinkedList();

        ImmutableLinkedList newImmutableLinkedList = new ImmutableLinkedList();
        ImmutableLinkedList newImmutableLinkedList2 = newImmutableLinkedList.add("12");
        ImmutableLinkedList newImmutableLinkedList3 = newImmutableLinkedList2.add("34");
        threeImmutableLinkedList = newImmutableLinkedList3.add(56.7);
    }

    @Test
    public void testLinkedConstructor() {
        ImmutableList newImmutableLinked = new ImmutableLinkedList();
    }

    @Test
    public void testNode() {
        Node newNode = new Node();
        assertNull(newNode.value);
    }

    /** ========================= Tests for Add ========================= **/
    @Test
    public void testLinkedAdd() {
        ImmutableList newImmutableLinked = threeItemsImmutableLinked.add("abc");
        assertArrayEquals(new Object[]{"12", "34", 56.7, "abc"}, newImmutableLinked.toArray());
    }

    @Test
    public void testLinkedAddOneItem() {
        ImmutableList newImmutableLinked = oneItemImmutableLinked.add("abc");
        assertArrayEquals(new Object[]{"12", "abc"}, newImmutableLinked.toArray());
    }

    @Test
    public void testLinkedAddEmpty() {
        ImmutableList newImmutableLinked = emptyImmutableLinked.add("abc");
        assertArrayEquals(new Object[]{"abc"}, newImmutableLinked.toArray());
    }

    @Test(expected = NullPointerException.class)
    public void testLinkedAddNull() {
        ImmutableList newImmutableLinked = eightItemsImmutableLinked.add( null);
    }


    /** ========================= Tests for AddIndex ========================= **/
    @Test
    public void testLinkedAddIndexPos0() {
        ImmutableList newImmutableLinked = threeItemsImmutableLinked.add(0, "abc");
        assertArrayEquals(new Object[]{"abc", "12", "34", 56.7}, newImmutableLinked.toArray());
    }

    @Test
    public void testLinkedAddIndexOneItem() {
        ImmutableList newImmutableLinked = oneItemImmutableLinked.add(0, "abc");
        assertArrayEquals(new Object[]{"abc", "12"}, newImmutableLinked.toArray());
    }

    @Test
    public void testLinkedAddIndexPos1() {
        ImmutableList newImmutableLinked = threeItemsImmutableLinked.add(1, "abc");
        assertArrayEquals(new Object[]{"12", "abc", "34", 56.7}, newImmutableLinked.toArray());
    }

    @Test
    public void testLinkedAddIndexPosLast() {
        ImmutableList newImmutableLinked = threeItemsImmutableLinked.add(2, "abc");
        assertArrayEquals(new Object[]{"12", "34", "abc", 56.7}, newImmutableLinked.toArray());
    }

    @Test(expected = NullPointerException.class)
    public void testLinkedAddIndexNull() {
        ImmutableList newImmutableLinked = eightItemsImmutableLinked.add(0, null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLinkedAddIndexLimit() {
        ImmutableList newImmutableLinked = eightItemsImmutableLinked.add(8, new Object[]{});
    }

    /** ========================= Tests for AddAll ========================= **/
    @Test
    public void testLinkedAddAll() {
        Object[] inputArr = new Object[6];
        for (int i = 0; i < 6; i++){
            inputArr[i] = String.valueOf(i + 1);
        }
        ImmutableList newImmutableLinked = threeItemsImmutableLinked.addAll(inputArr);
        assertArrayEquals(new Object[]{"12", "34", 56.7, "1", "2",
                "3", "4", "5", "6"}, newImmutableLinked.toArray());
    }

    @Test
    public void testLinkedAddAllBig() {
        Object[] inputArr = new Object[6];
        for (int i = 0; i < 6; i++){
            inputArr[i] = "1";
        }
        ImmutableList newImmutableLinked = eightItemsImmutableLinked.addAll(inputArr);

        assertArrayEquals(new Object[]{"1", "2", "3", "4", "5",
                        "6", "7", "12",
                        "1", "1", "1", "1", "1", "1",}, newImmutableLinked.toArray());
    }

    @Test
    public void testLinkedAddAllEmpty() {
        Object[] inputArr = new Object[6];
        for (int i = 0; i < 6; i++){
            inputArr[i] = "2";
        }
        ImmutableList newImmutableLinked = emptyImmutableLinked.addAll(inputArr);

        assertArrayEquals(new Object[]{"2", "2", "2", "2", "2", "2"},
                newImmutableLinked.toArray());
    }

    @Test
    public void testLinkedAddAllOneItem() {
        ImmutableList newImmutableLinked = oneItemImmutableLinked.addAll(new Object[]{"abc", 0});
        assertArrayEquals(new Object[]{"12", "abc", 0}, newImmutableLinked.toArray());
    }


    @Test(expected = NullPointerException.class)
    public void testLinkedAddAllNull() {
        ImmutableList newImmutableLinked = eightItemsImmutableLinked.addAll(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLinkedAddAllIllegal() {
        ImmutableList newImmutableLinked = eightItemsImmutableLinked.addAll(new Object[]{});
    }


    /** ========================= Tests for AddAllIndex ========================= **/
    @Test
    public void testLinkedAddIdxAll() {
        Object[] inputArr = new Object[6];
        for (int i = 0; i < 6; i++){
            inputArr[i] = String.valueOf(i + 1);
        }
        ImmutableList newImmutableLinked = threeItemsImmutableLinked.addAll(2, inputArr);
        assertArrayEquals(new Object[]{"12", "34", "1", "2",
                "3", "4", "5", "6", 56.7}, newImmutableLinked.toArray());
    }

    @Test
    public void testLinkedAddAllIdxBig() {
        Object[] inputArr = new Object[6];
        for (int i = 0; i < 6; i++){
            inputArr[i] = "1";
        }
        ImmutableList newImmutableLinked = eightItemsImmutableLinked.addAll(5, inputArr);

        assertArrayEquals(new Object[]{"1", "2", "3", "4", "5",
                        "1", "1", "1", "1", "1", "1",
                        "6", "7", "12"}, newImmutableLinked.toArray());
    }

    @Test
    public void testLinkedAddAllIdxPos1() {
        Object[] inputArr = new Object[6];
        for (int i = 0; i < 6; i++){
            inputArr[i] = "1";
        }
        ImmutableList newImmutableLinked = eightItemsImmutableLinked.addAll(1, inputArr);

        assertArrayEquals(new Object[]{"1", "1", "1", "1", "1", "1", "1",
                        "2", "3", "4", "5",
                        "6", "7", "12"}, newImmutableLinked.toArray());
    }

    @Test
    public void testLinkedAddAllIdxPos0() {
        Object[] inputArr = new Object[6];
        for (int i = 0; i < 6; i++){
            inputArr[i] = "2";
        }
        ImmutableList newImmutableLinked = eightItemsImmutableLinked.addAll(0, inputArr);

        assertArrayEquals(new Object[]{"2", "2", "2", "2", "2", "2", "1",
                        "2", "3", "4", "5",
                        "6", "7", "12"}, newImmutableLinked.toArray());
    }

    @Test
    public void testLinkedAddAllIdxOneElement() {
        Object[] inputArr = new Object[6];
        for (int i = 0; i < 6; i++){
            inputArr[i] = "2";
        }
        ImmutableList newImmutableLinked = oneItemImmutableLinked.addAll(0, inputArr);

        assertArrayEquals(new Object[]{"2", "2", "2", "2", "2", "2", "12"},
                newImmutableLinked.toArray());
    }

    @Test(expected = NullPointerException.class)
    public void testLinkedAddAllIdxNull() {
        ImmutableList newImmutableLinked = eightItemsImmutableLinked.addAll(0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLinkedAddAllIdxIllegal() {
        ImmutableList newImmutableLinked = eightItemsImmutableLinked.addAll(0, new Object[]{});
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLinkedAddAllIdxLimit() {
        ImmutableList newImmutableLinked = eightItemsImmutableLinked.addAll(8, new Object[]{});
    }


    /** ========================= Tests for Get ========================= **/
    @Test
    public void testLinkedGetPos0() {
        assertEquals("12", threeItemsImmutableLinked.get(0));
    }

    @Test
    public void testLinkedGetPos1() {
        assertEquals("34", threeItemsImmutableLinked.get(1));
    }

    @Test
    public void testLinkedGetPosLast() {
        assertEquals(56.7, threeItemsImmutableLinked.get(2));
    }

    @Test
    public void testLinkedGetOneItem() {
        assertEquals("12", oneItemImmutableLinked.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLinkedGetEmpty() {
        Object o = emptyImmutableLinked.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLinkedGetLimit() {
        Object o = eightItemsImmutableLinked.get(8);
    }


    /** ========================= Tests for Remove ========================= **/
    @Test
    public void testLinkedRemovePos0() {
        ImmutableList newImmutableLinked = threeItemsImmutableLinked.remove(0);
        assertArrayEquals(new Object[]{"34", 56.7}, newImmutableLinked.toArray());
    }

    @Test
    public void testLinkedRemovePosLast() {
        ImmutableList newImmutableLinked = eightItemsImmutableLinked.remove(7);

        assertArrayEquals(new Object[]{"1", "2", "3", "4",
                "5", "6", "7"}, newImmutableLinked.toArray());
    }

    @Test
    public void testLinkedRemovePosMiddle() {
        ImmutableList newImmutableLinked = eightItemsImmutableLinked.remove(1);

        assertArrayEquals(new Object[]{"1", "3", "4", "5",
                        "6", "7", "12"}, newImmutableLinked.toArray());
    }

    @Test
    public void testLinkedRemoveOneItem() {
        ImmutableList newImmutableLinked = oneItemImmutableLinked.remove(0);

        assertArrayEquals(new Object[]{}, newImmutableLinked.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLinkedRemoveEmpty() {
        ImmutableList newImmutableLinked = emptyImmutableLinked.remove(0);

        assertArrayEquals(new Object[]{}, newImmutableLinked.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLinkedRemoveLimit() {
        ImmutableList newImmutableLinked = eightItemsImmutableLinked.remove(8);
    }

    /** ========================= Tests for Set ========================= **/
    @Test
    public void testLinkedSetPos0() {
        ImmutableList newImmutableLinked = threeItemsImmutableLinked.set(0, 0);
        assertArrayEquals(new Object[]{0, "34", 56.7}, newImmutableLinked.toArray());
    }

    @Test
    public void testLinkedSetPosLast() {
        ImmutableList newImmutableLinked = eightItemsImmutableLinked.set(7, 0);

        assertArrayEquals(new Object[]{"1", "2", "3", "4",
                "5", "6", "7", 0}, newImmutableLinked.toArray());
    }

    @Test
    public void testLinkedSetPosMiddle() {
        ImmutableList newImmutableLinked = eightItemsImmutableLinked.set(1, 0);

        assertArrayEquals(new Object[]{"1", 0, "3", "4", "5",
                        "6", "7", "12"}, newImmutableLinked.toArray());
    }

    @Test
    public void testLinkedSetOneItem() {
        ImmutableList newImmutableLinked = oneItemImmutableLinked.set(0, '1');

        assertArrayEquals(new Object[]{'1'}, newImmutableLinked.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLinkedSetEmpty() {
        ImmutableList newImmutableLinked = emptyImmutableLinked.set(0, '1');
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLinkedSetLimit() {
        ImmutableList newImmutableLinked = eightItemsImmutableLinked.set(8, '1');
    }


    /** ========================= Tests for IndexOf ========================= **/
    @Test
    public void testLinkedIndexOfPos0() {
        assertEquals(0, threeItemsImmutableLinked.indexOf("12"));
    }

    @Test
    public void testLinkedIndexOfPosLast() {
        threeItemsImmutableLinked.indexOf(56.7);
        assertEquals(2, threeItemsImmutableLinked.indexOf(56.7));
    }

    @Test
    public void testLinkedIndexOfPosMiddle() {
        assertEquals(1, threeItemsImmutableLinked.indexOf("34"));
    }

    @Test
    public void testLinkedIndexOfOneItem() {
        assertEquals(0, oneItemImmutableLinked.indexOf("12"));
    }

    @Test(expected = NullPointerException.class)
    public void testLinkedIndexOfEmpty() {
        int index = eightItemsImmutableLinked.indexOf(null);
    }

    @Test
    public void testLinkedIndexOfNoItem() {
        assertEquals(-1, eightItemsImmutableLinked.indexOf('1'));
    }

    /** ========================= Tests for Size ========================= **/
    @Test
    public void testSize() {
        assertEquals(3, threeItemsImmutableLinked.size());
    }

    @Test
    public void testSize2() {
        assertEquals(0, emptyImmutableLinked.size());
    }

    /** ========================= Tests for Clear ========================= **/
    @Test
    public void testClear() {
        ImmutableList im = threeItemsImmutableLinked.clear();
        assertEquals(0, im.size());
        assertArrayEquals(new Object[]{}, im.toArray());
    }

    @Test
    public void testClear2() {
        ImmutableList im = emptyImmutableLinked.clear();
        assertEquals(0, im.size());
        assertArrayEquals(new Object[]{}, im.toArray());
    }

    /** ========================= Tests for IsEmpty ========================= **/
    @Test
    public void testIsEmpty() {
        assertTrue(emptyImmutableLinked.isEmpty());
    }

    @Test
    public void testIsEmpty2() {
        assertFalse(threeImmutableLinkedList.isEmpty());
    }


    /** ========================= Tests for ToString ========================= **/
    @Test
    public void testToString() {
        assertEquals("[12, 34, 56.7]", threeItemsImmutableLinked.toString());
        assertEquals("[]", emptyImmutableLinked.toString());
    }


    /** ========================= Tests for ToArray ========================= **/
    @Test
    public void testToArray() {
        Object[] arr = threeItemsImmutableLinked.toArray();
        assertArrayEquals(new Object[]{"12", "34", 56.7}, threeItemsImmutableLinked.toArray());
    }

    /** ========================= Tests for AddFirst ========================= **/
    @Test
    public void testAddFirst() {
        ImmutableLinkedList newImmutableLinked = threeImmutableLinkedList.addFirst(1);
        assertArrayEquals(new Object[]{1, "12", "34", 56.7}, newImmutableLinked.toArray());
    }

    /** ========================= Tests for AddLast ========================= **/
    @Test
    public void testAddLast() {
        ImmutableLinkedList newImmutableLinked = threeImmutableLinkedList.addLast(1);
        assertArrayEquals(new Object[]{"12", "34", 56.7, 1}, newImmutableLinked.toArray());
    }

    /** ========================= Tests for RemoveFirst ========================= **/
    @Test
    public void testRemoveFirst() {
        ImmutableLinkedList newImmutableLinked = threeImmutableLinkedList.removeFirst();
        assertArrayEquals(new Object[]{"34", 56.7}, newImmutableLinked.toArray());
    }

    /** ========================= Tests for RemoveLast ========================= **/
    @Test
    public void testRemoveLast() {
        ImmutableLinkedList newImmutableLinked = threeImmutableLinkedList.removeLast();
        assertArrayEquals(new Object[]{"12", "34"}, newImmutableLinked.toArray());
    }

    /** ========================= Tests for GetFirst ========================= **/
    @Test
    public void testGetFirst() {
        assertEquals("12", threeImmutableLinkedList.getFirst());
    }

    /** ========================= Tests for GetLast ========================= **/
    @Test
    public void testGetLast() {
        assertEquals(56.7, threeImmutableLinkedList.getLast());
    }
}
