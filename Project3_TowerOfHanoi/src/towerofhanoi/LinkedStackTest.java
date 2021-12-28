// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package towerofhanoi;

import java.util.EmptyStackException;

/**
 * Class that tests all the methods of LinkedStack<T>
 * 
 * @author Cuong Ngo (ngoct)
 * @version 03/04/2020 
 */
public class LinkedStackTest extends student.TestCase {

    private LinkedStack<Integer> link;

    /**
     * Sets up common conditions used in every test cases
     */
    public void setUp() {
        link = new LinkedStack<Integer>();
    }


    /**
     * Test the size() method by adding and removing elements
     */
    public void testSize() {
        assertEquals(0, link.size());

        link.push(1); // Increase size by 1
        assertEquals(1, link.size());

        link.pop(); // Decrease size by 1
        assertEquals(0, link.size());
    }


    /**
     * Test the isEmpty() method
     */
    public void testIsEmpty() {
        assertTrue(link.isEmpty());

        link.push(1); // Increase size by 1, no longer empty
        assertFalse(link.isEmpty());
    }


    /**
     * Test the peek() method on an empty LinkedStack, which will throw
     * EmptyStackException
     */
    public void testPeek0() {
        Exception thrown = null;
        try {
            link.peek();
        }
        catch (Exception exception) {
            thrown = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown instanceof EmptyStackException);
    }


    /**
     * Test the peek() method on a valid LinkedStack
     */
    public void testPeek1() {

        // push a new element
        link.push(1);
        assertEquals(1, (int)link.peek());

        link.push(2); // push a new element to the top
        assertEquals(2, (int)link.peek());
    }


    /**
     * Test the push() method
     */
    public void testPush() {
        assertEquals(0, link.size());

        link.push(1); // Increase size by 1
        assertEquals(1, link.size());

        link.pop(); // Decrease size by 1
        assertEquals(0, link.size());
    }


    /**
     * Test the pop() method on an empty LinkedStack, which will throw
     * EmptyStackException
     */
    public void testPop0() {
        Exception thrown = null;
        try {
            link.pop();
        }
        catch (Exception exception) {
            thrown = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown instanceof EmptyStackException);
    }


    /**
     * Test the pop() method on a valid LinkedStack
     */
    public void testPop1() {
        link.push(1);
        link.push(2);

        // Last in, First out, so 2 will be removed before 1
        assertEquals(2, (int)link.pop());
        assertEquals(1, link.size());

        assertEquals(1, (int)link.pop());
        assertEquals(0, link.size());
    }


    /**
     * Test the toString() method on a null LinkedStack
     */
    public void testToString0() {
        assertEquals("[]", link.toString());

    }


    /**
     * Test the toString() method on valid inputs
     */
    public void testToString1() {
        link.push(1);
        link.push(2);
        link.push(3);
        assertEquals("[3, 2, 1]", link.toString());
    }


    /**
     * Test the clear() method
     */
    public void testClear() {

        link.push(1);
        link.push(2);
        link.push(3);
        assertEquals(3, link.size());

        link.clear();
        assertEquals(0, link.size());
    }

}
