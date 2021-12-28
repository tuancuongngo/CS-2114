// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package project1;

import bag.BagInterface;
import student.TestCase;

/**
 * This class will test all of the methods in DisplayCollection
 * to make sure that they run and perform as expected
 * 
 * @author Cuong Ngo (ngoct)
 * @version 02/11/2020
 */
public class DisplayCollectionTest extends TestCase {

    /**
     * Constructor for this class
     */
    public DisplayCollectionTest() {
        // Method intentionally left empty
        // because it is not defined in the instructions
    }


    /**
     * Sets up initial conditions
     * Intentionally left empty
     */
    public void setUp() {
        // Method intentionally left empty
    }


    /**
     * Test the contents of a bag, ensuring that
     * every String in the bag contains one of the
     * 4 legal strings:
     * "blue square", "red square", "blue circle",
     * or "red circle"
     */
    public void testBagContents() {
        DisplayCollection collection1 = new DisplayCollection();
        BagInterface<String> refBag = collection1.getItemBag();

        // Remove every item in the bag 1 by 1 and check
        // if each item is valid
        int bagSize = refBag.getCurrentSize();
        for (int i = 0; i < bagSize; i++) {
            String currentItem = refBag.remove();
            assertTrue(currentItem.equals("blue square") || currentItem.equals(
                "red square") || currentItem.equals("blue circle")
                || currentItem.equals("red circle"));
        }
    }


    /**
     * Test the contents of a bag, ensuring that once all elements
     * are removed, the Bag is empty
     */
    public void testBagContentsEmpty() {
        DisplayCollection collection1 = new DisplayCollection();
        BagInterface<String> refBag = collection1.getItemBag();

        // Remove every item in the bag
        refBag.clear();

        // After every item is removed, Bag is Empty
        assertTrue(refBag.isEmpty());
    }


    /**
     * Test the size of the bag, making sure that
     * the size is between 5 and 15
     */
    public void testBagSize() {
        // Create 20 new DisplayCollection objects to test
        // if they have the appropriate size
        for (int i = 0; i < 20; i++) {
            DisplayCollection collection1 = new DisplayCollection();
            BagInterface<String> refBag = collection1.getItemBag();

            assertTrue(refBag.getCurrentSize() >= 5);
            assertTrue(refBag.getCurrentSize() <= 15);
        }
    }

}
