// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package game;

import student.TestCase;

/**
 * This class will test all of the methods in SimpleLinkedBag
 * to make sure that they run and perform as expected
 * 
 * @author Cuong Ngo
 * @version 02/24/2020
 */
public class SimpleLinkedBagTest extends TestCase {

    private SimpleLinkedBag<String> bag1;


    /**
     * Set up common variables for all test methods. Runs before every test.
     */
    public void setUp() {
        bag1 = new SimpleLinkedBag<String>();
        bag1.add("red circle");
        bag1.add("blue circle");
        bag1.add("red square");
        bag1.add("blue square");
        bag1.add("red circle");
    }


    /**
     * Test the add() method, adding a null element
     */
    public void testAdd0() {
        assertFalse(bag1.add(null));
    }


    /**
     * Test the add() method, adding a valid element
     */
    public void testAdd1() {
        assertTrue(bag1.add("valid"));
        assertEquals(6, bag1.getCurrentSize());
    }


    /**
     * Test the getCurrentSize() method after adding an element
     */
    public void testGetCurrentSize0() {
        // Original size
        assertEquals(5, bag1.getCurrentSize());

        bag1.add("new");

        // New size
        assertEquals(6, bag1.getCurrentSize());
    }


    /**
     * Test the getCurrentSize() method after removing an element
     */
    public void testGetCurrentSize1() {
        // Original size
        assertEquals(5, bag1.getCurrentSize());

        bag1.remove("red circle");

        // New size
        assertEquals(4, bag1.getCurrentSize());
    }


    /**
     * Test the isEmpty() method on a bag with elements
     */
    public void testIsEmpty0() {
        assertFalse(bag1.isEmpty());
    }


    /**
     * Test the isEmpty() method on an empty bag
     */
    public void testIsEmpty1() {
        bag1 = new SimpleLinkedBag<String>();
        assertTrue(bag1.isEmpty());
    }


    /**
     * Test the pick() method on an empty bag
     */
    public void testPick0() {
        bag1 = new SimpleLinkedBag<String>();
        assertEquals(null, bag1.pick());
    }


    /**
     * Testing the pick() method on bag with items,
     * ensuring that every String in the bag contains
     * one of the 4 legal strings:
     * "blue square", "red square", "blue circle",
     * or "red circle"
     */
    public void testPick1() {
        // Remove every item in the bag 1 by 1 and check
        // if each item is valid
        int bagSize = bag1.getCurrentSize();
        for (int i = 0; i < bagSize; i++) {
            String currentItem = bag1.pick();

            // Making sure that every item in bag is valid
            assertTrue(currentItem.equals("blue square") || currentItem.equals(
                "red square") || currentItem.equals("blue circle")
                || currentItem.equals("red circle"));
            // Remove the item that was checked from list
            bag1.remove(currentItem);
        }
    }


    /**
     * Test the remove() method
     * removing a null element
     * This method also tests the private getReferenceTo() method
     */
    public void testRemove0() {
        assertFalse(bag1.remove("nullEntry"));
        assertEquals(5, bag1.getCurrentSize());
    }


    /**
     * Test the remove() method, removing all items
     * from bag one by one
     * This method also tests the private getReferenceTo() method
     */
    public void testRemove1() {
        // Remove items one by one, checkin size after each removal
        assertTrue(bag1.remove("red circle"));
        assertEquals(4, bag1.getCurrentSize());

        assertTrue(bag1.remove("blue circle"));
        assertEquals(3, bag1.getCurrentSize());

        assertTrue(bag1.remove("red square"));
        assertEquals(2, bag1.getCurrentSize());

        assertTrue(bag1.remove("blue square"));
        assertEquals(1, bag1.getCurrentSize());

        assertTrue(bag1.remove("red circle"));
        assertEquals(0, bag1.getCurrentSize());
    }

}
