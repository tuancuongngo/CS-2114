// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package spacecolonies;

import queue.EmptyQueueException;
import student.TestCase;

/**
 * Test the ArrayQueue<T>() class in various situations to make sure it's
 * working correctly
 * 
 * @author Cuong Ngo (ngoct)
 * @version 04/02/2020
 */
public class ArrayQueueTest extends TestCase {

    private ArrayQueue<String> str1;
    private ArrayQueue<String> str2;
    private ArrayQueue<String> copy;


    /**
     * Sets up the common attributes for all test cases
     */
    public void setUp() {

        str1 = new ArrayQueue<String>();
        str2 = new ArrayQueue<String>();
        copy = new ArrayQueue<String>();

        // Adding elements to each queue

        str1.enqueue("1");
        str1.enqueue("2");
        str1.enqueue("3");

        str2.enqueue("a");
        str2.enqueue("b");
        str2.enqueue("c");

        copy.enqueue("1");
        copy.enqueue("2");
        copy.enqueue("3");
    }


    /**
     * Test the getLength() method
     */
    public void testGetLength() {
        assertEquals(11, str1.getLength());

        // Forcing the array to expand once
        for (int i = 0; i < 11; i++) {
            str1.enqueue("");
        }
        assertEquals(21, str1.getLength());
    }


    /**
     * Test the getSize() method
     */
    public void testGetSize() {
        assertEquals(3, str1.getSize());

        // Adding 1 element
        str1.enqueue("");
        assertEquals(4, str1.getSize());

        // Removing 2 elements
        str1.dequeue();
        str1.dequeue();
        assertEquals(2, str1.getSize());
    }


    /**
     * Test the clear() method
     */
    public void testClear() {
        str1.clear();
        assertTrue(str1.isEmpty());
        assertEquals(0, str1.getSize());
        assertEquals(11, str1.getLength());
        assertEquals("[]", str1.toString());
    }


    /**
     * Test the dequeue() method on an empty Queue
     */
    public void testDequeueEmpty() {
        str2.clear();

        Exception exception = null;
        try {
            // Calling dequeue() on empty Queue
            str2.dequeue();
        }
        catch (Exception e) {
            exception = e;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(exception);

        assertTrue(exception instanceof EmptyQueueException);
    }


    /**
     * Test the dequeue() method on a regular Queue
     */
    public void testDequeue() {

        assertEquals("a", str2.dequeue());
        assertEquals("b", str2.dequeue());
        assertEquals("c", str2.dequeue());
    }


    /**
     * Test the enqueue() method when max capacity is reached,
     * which will throw an exception
     */
    public void testEnqueueMax() {
        // Make the queue FULL CAPACITY (100 elements)
        str1.clear();
        for (int i = 0; i < 100; i++) {
            str1.enqueue("");
        }

        assertEquals(100, str1.getSize());

        // Trying to add another element to maxed out queue
        Exception exception = null;
        try {
            str1.enqueue("");
        }
        catch (Exception e) {
            exception = e;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(exception);

        assertTrue(exception instanceof IllegalStateException);
    }


    /**
     * Test the enqueue() method regularly
     */
    public void testEnqueue() {
        str1.enqueue("");
        assertEquals(4, str1.getSize());
    }


    /**
     * Test the getFront() method regularly
     */
    public void testGetFront() {
        assertEquals("1", str1.getFront());

        // removing 1 element
        str1.dequeue();
        assertEquals("2", str1.getFront());
    }


    /**
     * Test the getFront() method on a empty Queue,
     * which will throw an exception
     */
    public void testGetFrontEmpty() {
        str1.clear();
        Exception exception = null;
        try {
            // Calling getFront() on empty Queue
            str1.getFront();
        }
        catch (Exception e) {
            exception = e;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(exception);

        assertTrue(exception instanceof EmptyQueueException);
    }


    /**
     * Test the isEmpty() method on a empty and non-empty object
     */
    public void testIsEmpty() {
        assertFalse(str1.isEmpty());

        str1.clear();
        assertTrue(str1.isEmpty());
    }


    /**
     * Test the toArray() method when array is Empty
     */
    public void testToArrayEmpty() {
        str1.clear();

        Exception exception = null;
        try {
            // Calling toArray() on empty Queue
            str1.toArray();
        }
        catch (Exception e) {
            exception = e;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(exception);

        assertTrue(exception instanceof EmptyQueueException);
    }


    /**
     * Test the toArray method
     */
    public void testToArray() {
        String[] expectedA = { "1", "2", "3" };
        Object[] actualA = str1.toArray();

        // Comparing contents of arrays
        for (int i = 0; i < actualA.length; i++) {
            assertEquals(expectedA[i], actualA[i]);
        }

        assertEquals("[1, 2, 3]", str1.toString());
    }


    /**
     * Test the toString method
     */
    public void testToString() {
        // When the ArrayQueue is empty
        str1.clear();
        assertEquals("[]", str1.toString());

        // Regular String queue
        assertEquals("[a, b, c]", str2.toString());

        // After removing one element
        str2.dequeue(); // Position 0 is empty
        assertEquals("[b, c]", str2.toString());

        // Test Circular array logic
        // Make queue has a size 9
        for (int i = 0; i < 7; i++) {
            str2.enqueue("z");
        }

        // Element 10 will be at position 0
        str2.enqueue("last");
        assertEquals("[b, c, z, z, z, z, z, z, z, last]", str2.toString());

        // Removing an element
        str2.dequeue();
        assertEquals("[c, z, z, z, z, z, z, z, last]", str2.toString());

    }


    /**
     * Test the equals() method on various situations
     */
    public void testEquals() {

        // Comparing objects of different classes
        assertFalse(str1.equals(1));

        // Comparing 2 objects that are different
        assertFalse(str1.equals(str2));

        // Comparing 2 objects that are equal
        assertTrue(str1.equals(copy));
        assertEquals(str1.toString(), copy.toString());

        // Comparing 2 objects of different length
        for (int i = 0; i < 11; i++) {
            copy.enqueue("");
        }
        assertFalse(str1.equals(copy));

        // Comparing 2 objects that are different by 1 element
        copy.clear();
        copy.enqueue("1");
        copy.enqueue("different");
        copy.enqueue("3");
        assertFalse(str1.equals(copy));

        // Comparing objects of different size
        copy.enqueue("");
        assertFalse(str1.equals(copy));

        // Compare the objects with the same reference
        copy = str1;
        assertTrue(str1.equals(copy));
        assertEquals(str1.toString(), copy.toString());

        // Compare to a null Object
        str2 = null;
        assertFalse(str1.equals(str2));
    }


    /**
     * Test the equal method for cases that require circular array wrapping
     * logic
     */
    public void testEqualsWrap() {
        ArrayQueue<Integer> int1 = new ArrayQueue<Integer>();
        ArrayQueue<Integer> intCopy = new ArrayQueue<Integer>();

        // Content of int1 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        // Content of copyInt [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        for (int i = 0; i < 10; i++) {
            int1.enqueue(i);
            intCopy.enqueue(i);
        }

        int1.dequeue();
        int1.dequeue();
        int1.dequeue();
        // Content of int1 [3, 4, 5, 6, 7, 8, 9] with dequeueIndex = 3 and
        // enqueueIndex = 0

        intCopy.clear();

        // Content of intCopy [3, 4, 5, 6, 7, 8, 9] with dequeueIndex = 0 and
        // enqueueIndex = 7
        for (int i = 3; i < 10; i++) {
            intCopy.enqueue(i);
        }

        // Even though int1 has been wrapped around, copyInt and int1 are still
        // equal in content
        assertEquals(int1, intCopy);

        // Fill up arrays to max size again
        for (int i = 0; i < 3; i++) {
            int1.enqueue(0);
            intCopy.enqueue(0);
        }

        // Still equals in content
        assertEquals(int1, intCopy);
    }

}
