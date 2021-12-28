// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package spacecolonies;

import student.TestCase;

/**
 * Test the Skills class in various situations to make sure its working
 * correctly
 * 
 * @author Cuong Ngo (ngoct)
 * @version 04/02/2020
 */
public class SkillsTest extends TestCase {

    private Skills weak;
    private Skills strong;


    /**
     * Sets up the common attributes for all test cases
     */
    public void setUp() {
        weak = new Skills(1, 2, 3);
        strong = new Skills(1, 4, 4);
    }


    /**
     * Tests the isBelow() method, comparing weak to strong, and vice versa, and
     * strong to itself
     */
    public void testIsBelow() {
        // Comparing weak to strong, weak has smaller attributes
        assertTrue(weak.isBelow(strong));

        // Comparing strong to weak
        assertFalse(strong.isBelow(weak));

        // When agriculture is greater
        weak = new Skills(2, 2, 4);
        assertFalse(weak.isBelow(strong));

        // When technology is greater
        weak = new Skills(1, 1, 5);
        assertFalse(weak.isBelow(strong));

        // Comparing strong to itself
        assertTrue(strong.isBelow(strong));
    }


    /**
     * Tests the equals() method on various situations
     */
    public void testEquals() {
        // Comparing objects of the same reference
        Skills same = strong;
        assertTrue(strong.equals(same));
        assertEquals(same.toString(), strong.toString());

        // Comparing objects of different classes
        assertFalse(strong.equals(""));

        // Comparing 2 Skills objects that are different
        assertFalse(strong.equals(weak));

        // When agriculture is greater
        Skills diff = new Skills(2, 2, 2);
        assertFalse(strong.equals(diff));

        // When medicine is greater
        diff = new Skills(1, 5, 5);
        assertFalse(strong.equals(diff));

        // When technology is greater
        diff = new Skills(1, 4, 5);
        assertFalse(strong.equals(diff));

        // Comparing 2 Skills objects that are equal in attributes
        same = new Skills(1, 4, 4);
        assertTrue(strong.equals(same));
        assertEquals(same.toString(), strong.toString());
        
        // Comparing to a null object
        same = null;
        assertFalse(strong.equals(same));

    }


    /**
     * Test the getAgriculture() method
     */
    public void testGetAgriculture() {
        assertEquals(1, weak.getAgriculture());
    }


    /**
     * Test the getMedicine() method
     */
    public void testGetMedicine() {
        assertEquals(2, weak.getMedicine());
    }


    /**
     * Test the getTechnology() method
     */
    public void testGetTechnology() {
        assertEquals(3, weak.getTechnology());
    }

}
