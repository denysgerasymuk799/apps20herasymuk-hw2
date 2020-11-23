package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;

import java.lang.NullPointerException;


import static org.junit.Assert.*;

public class ImmutableArrayListTest {
    private ImmutableList littleImmutableArray;
    private ImmutableList emptyImmutableArray;
    private ImmutableList threeElemImmutableArray;
    private ImmutableList fourElemImmutableArray;
    private Object[] expectedLittle;
    private Object[] expectedBig;


    @Before
    public void init() {
        ImmutableList newImmutableArray = new ImmutableArrayList();
        ImmutableList newImmutableArray2 = newImmutableArray.add("123");
        littleImmutableArray = newImmutableArray2.add("45");

        threeElemImmutableArray = littleImmutableArray.add(56);
        fourElemImmutableArray = threeElemImmutableArray.add(78);

        emptyImmutableArray = new ImmutableArrayList();
        
        expectedLittle = new Object[littleImmutableArray.size() + 3];
        System.arraycopy(littleImmutableArray.toArray(), 0,
                expectedLittle, 0, littleImmutableArray.size());

        expectedBig = new Object[littleImmutableArray.size() + 1000];
        System.arraycopy(littleImmutableArray.toArray(), 0,
                expectedBig, 0, littleImmutableArray.size());
    }

    @Test
    public void testArrayConstructor() {
        ImmutableList newImmutableArray = new ImmutableArrayList();
    }

    /** ========================= Tests for Add ========================= **/
    @Test
    public void testArrayAdd() {
        ImmutableList newImmutableArray = littleImmutableArray.add("abc");

        assertNotEquals(newImmutableArray.toArray(), littleImmutableArray.toArray());
        assertEquals(littleImmutableArray.toArray(), littleImmutableArray.toArray());

        ImmutableList newImmutableArray2 = newImmutableArray.add(1.8);
        ImmutableList newImmutableArray3 = newImmutableArray2.add("3");

        assertNotEquals(newImmutableArray3.toArray(), newImmutableArray2.toArray());

        expectedLittle[littleImmutableArray.size()] = "abc";
        expectedLittle[littleImmutableArray.size() + 1] = 1.8;
        expectedLittle[littleImmutableArray.size() + 2] = "3";

        assertEquals(newImmutableArray3.toArray(), expectedLittle);
    }

    @Test(expected = NullPointerException.class)
    public void testArrayAddNull() {
        ImmutableList newImmutableArray = littleImmutableArray.add(null);
    }


    /** ========================= Tests for Add with index ========================= **/
    @Test(expected = NullPointerException.class)
    public void testArrayAddIndexNull() {
        ImmutableList newImmutableArray = littleImmutableArray.add(0, null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testArrayAddIndexSizeLimit() {
        ImmutableList newImmutableArray = littleImmutableArray.add(
                littleImmutableArray.size() + 1, "1");
    }

    @Test
    public void testArrayAddIndex() {
        ImmutableList newImmutableArray = littleImmutableArray.add(
                1, "1");

        assertNotEquals(new Object[]{"123", "1", "45"}, newImmutableArray.toArray());

        newImmutableArray = newImmutableArray.add(
                1, "2");

        assertNotEquals(new Object[]{"123", "2", "1", "45"}, newImmutableArray.toArray());
    }

    /** ========================= Tests for AddAll ========================= **/
    @Test
    public void testArrayAddAll() {
        Object[] newItem = new Object[1000];
        for (int i = 0; i < 1000; i++){
            newItem[i] = i;
            expectedBig[littleImmutableArray.size() + i] = i;
        }

        ImmutableList newImmutableArray = littleImmutableArray.addAll(newItem);

        assertEquals(newImmutableArray.toArray(), expectedBig);

        // other test
        Object[] expectedBig2000 = new Object[littleImmutableArray.size() + 2000];
        System.arraycopy(expectedBig, 0, expectedBig2000, 0, expectedBig.length);
        for (int i = 0; i < 1000; i++){
            newItem[i] = "1";
            expectedBig2000[expectedBig.length + i] = "1";
        }

        ImmutableList newImmutableArray2 = newImmutableArray.addAll(newItem);

        assertEquals(newImmutableArray2.toArray(), expectedBig2000);
    }

    @Test(expected = NullPointerException.class)
    public void testArrayAddAllNull() {
        ImmutableList newImmutableArray = littleImmutableArray.addAll(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArrayAddAllEmpty() {
        ImmutableList newImmutableArray = littleImmutableArray.addAll(new Object[]{});
    }

    /** ========================= Tests for AddAll with index ========================= **/
    @Test
    public void testArrayAddAllWithIdx() {
        Object[] newItem = new Object[1000];
        int inputPosition = 1;

        for (int i = 0; i < 1000; i++){
            newItem[i] = i;
            expectedBig[inputPosition + i] = i;
        }
        System.arraycopy(littleImmutableArray.toArray(), inputPosition,
                expectedBig, inputPosition + 1000,
                littleImmutableArray.size() - inputPosition);


        ImmutableList newImmutableArray = littleImmutableArray.addAll(inputPosition, newItem);

        assertEquals(expectedBig, newImmutableArray.toArray());


        // other test
        inputPosition = 5;

        Object[] expected = new Object[2000 + littleImmutableArray.size()];
        System.arraycopy(expectedBig, 0,
                expected, 0, inputPosition);

        for (int i = 0; i < 1000; i++){
            newItem[i] = "1";
            expected[inputPosition + i] = "1";
        }

        System.arraycopy(expectedBig, inputPosition,
                expected, inputPosition + 1000, expectedBig.length - inputPosition);

        ImmutableList newImmutableArray2 = newImmutableArray.addAll(inputPosition, newItem);

        assertEquals(expected, newImmutableArray2.toArray());
    }

    @Test
    public void testArrayAddAllWithIdx2() {
        int inputPosition = 2;

        ImmutableList newImmutableArray2 = threeElemImmutableArray.addAll(inputPosition,
                new Object[]{"22", 33});

        assertEquals(new Object[]{"123", "45", "22", 33, 56},
                newImmutableArray2.toArray());
    }

    @Test(expected = NullPointerException.class)
    public void testArrayAddAllNullWithIdx() {
        ImmutableList newImmutableArray = littleImmutableArray.addAll(0, null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testArrayAddAllNullWithIdxLimit() {
        Object[] newItem = new Object[1000];
        for (int i = 0; i < 1000; i++){
            newItem[i] = i;
        }
        ImmutableList newImmutableArray2 = littleImmutableArray.addAll(5, newItem);
    }


    /** ========================= Tests for Get ========================= **/
    @Test(expected = IndexOutOfBoundsException.class)
    public void testArrayGetExceed() {
        littleImmutableArray.get(5);
    }

    @Test
    public void testArrayGet() {
        assertEquals("45", littleImmutableArray.get(1));
    }


    /** ========================= Tests for Remove ========================= **/
    @Test(expected = IndexOutOfBoundsException.class)
    public void testArrayRemoveExceed() {
        ImmutableList newImmutableArray2 = littleImmutableArray.remove(5);
    }

    @Test
    public void testArrayRemove() {
        ImmutableList newImmutableArray2 = fourElemImmutableArray.remove(1);
        assertEquals(new Object[]{"123", 56, 78}, newImmutableArray2.toArray());

        newImmutableArray2 = newImmutableArray2.remove(1);
        assertEquals(new Object[]{"123", 78}, newImmutableArray2.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testArrayRemoveEmpty() {
        ImmutableList newImmutableArray2 = emptyImmutableArray.remove(0);
    }


    /** ========================= Tests for Set ========================= **/
    @Test(expected = IndexOutOfBoundsException.class)
    public void testArraySetExceed() {
        ImmutableList newImmutableArray2 = littleImmutableArray.set(5, "1");
    }

    @Test
    public void testArraySet() {
        ImmutableList newImmutableArray2 = fourElemImmutableArray.set(1, "hello");
        assertEquals(new Object[]{"123", "hello", 56, 78},
                newImmutableArray2.toArray());

        newImmutableArray2 = newImmutableArray2.set(2, 45.5);
        assertEquals(new Object[]{"123", "hello", 45.5, 78},
                newImmutableArray2.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testArraySetEmpty() {
        ImmutableList newImmutableArray2 = emptyImmutableArray.set(0, "1");
    }


    /** ========================= Tests for IndexOf ========================= **/
    @Test
    public void testArrayIndexOfNoItem() {
        assertEquals(-1, littleImmutableArray.indexOf(45.5));
    }

    @Test
    public void testArrayIndexOf() {
        assertEquals(0,
                fourElemImmutableArray.indexOf("123"));

        assertEquals(2,
                fourElemImmutableArray.indexOf(56));
    }


    /** ========================= Tests for Size ========================= **/
    @Test
    public void testArraySize() {
        assertEquals(2, littleImmutableArray.size());
        assertEquals(0, emptyImmutableArray.size());
    }

    /** ========================= Tests for Clear ========================= **/
    @Test
    public void testArrayClear() {
        ImmutableList newImmutableArray = new ImmutableArrayList();
        assertEquals(newImmutableArray.size(), littleImmutableArray.clear().size());
        assertEquals(newImmutableArray.toArray(), littleImmutableArray.clear().toArray());
    }


    @Test
    public void testArrayClearEmpty() {
        ImmutableList newImmutableArray = new ImmutableArrayList();
        assertEquals(newImmutableArray.size(), emptyImmutableArray.clear().size());
        assertEquals(newImmutableArray.toArray(), emptyImmutableArray.clear().toArray());
    }


    /** ========================= Tests for IsEmpty ========================= **/
    @Test
    public void testArrayIsEmpty() {
        assertFalse(littleImmutableArray.isEmpty());
    }


    @Test
    public void testArrayIsEmptyForEmpty() {
        assertTrue(emptyImmutableArray.isEmpty());
    }


    /** ========================= Tests for ToArray ========================= **/
    @Test
    public void testArrayToArray() {
        assertEquals(new Object[]{"123", "45"}, littleImmutableArray.toArray());
    }


    @Test
    public void testArrayToArrayForEmpty() {
        assertEquals(new Object[]{}, emptyImmutableArray.toArray());
    }


    /** ========================= Tests for ToString ========================= **/
    @Test
    public void testArrayToString() {
        assertEquals("[123, 45]", littleImmutableArray.toString());
        assertEquals("[123, 45, 56]", threeElemImmutableArray.toString());
    }


    @Test
    public void testArrayToStringForEmpty() {
        assertEquals("[]", emptyImmutableArray.toString());
    }
}
