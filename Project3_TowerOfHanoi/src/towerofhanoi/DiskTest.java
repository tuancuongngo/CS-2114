// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package towerofhanoi;

/**
 * Test class for the Disk class, ensuring all methods function properly
 * 
 * @author Cuong Ngo (ngoct)
 * @version 03/04/2020
 */
public class DiskTest extends student.TestCase {

    private Disk d1;
    private Disk d2;

    /**
     * Sets up common conditions used in every test cases
     */
    public void setUp() {
        d1 = new Disk(10);
        d2 = new Disk(10);
    }


    /**
     * Test the compareTo() method where the disks are the same size,
     * which will return 0
     */
    public void testCompareTo0() {
        assertEquals(0, d1.compareTo(d2));
    }


    /**
     * Test the compareTo() method where the current disk has a smaller size
     * than disk being compared to, which will return a negative number
     */
    public void testCompareTo1() {
        d1 = new Disk(10);
        d2 = new Disk(20);

        assertTrue(d1.compareTo(d2) < 0);

    }


    /**
     * Test the compareTo() method where the current disk has a bigger size than
     * the disk being compared to, which will return a positive number
     */
    public void testCompareTo2() {

        d1 = new Disk(20);
        d2 = new Disk(10);
        assertTrue(d1.compareTo(d2) > 0);

    }


    /**
     * Test the compareTo() method where the disk in the parameter is null
     * which will return IllegalArgumentException()
     */
    public void testCompareTo3() {
        
        d2 = null;
        
        Exception thrown = null;
        try {
            d1.compareTo(d2);
        }
        catch (Exception exception) {
            thrown = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown instanceof IllegalArgumentException);
    }
    
    
    /**
     * Test the toString() method
     */
    public void testToString() {
        assertEquals("10", d1.toString());

        d2 = new Disk(100);
        assertEquals("100", d2.toString());
    }


    /**
     * Test the equals() method
     * comparing to itself
     * (SAME REFERENCE)
     */
    public void testEquals0() {
        d2 = d1;
        assertTrue(d2.equals(d1));
    }


    /**
     * Test the equals() method
     * comparing to a null parameter
     */
    public void testEquals1() {
        d2 = null;
        assertFalse(d1.equals(d2));
    }


    /**
     * Test the equals() method
     * comparing to an object of a different class
     */
    public void testEquals2() {
        String bb = "";
        assertFalse(d1.equals(bb));
    }


    /**
     * Test the equals() method
     * when d1 is less than d2 in width
     */
    public void testEquals3() {
        d1 = new Disk(2);
        assertFalse(d1.equals(d2));
    }


    /**
     * Test the equals() method
     * when d1 is more than d2 in width
     */
    public void testEquals4() {

        d1 = new Disk(100);
        assertFalse(d1.equals(d2));

    }


    /**
     * Test the equals() method
     * when d1 and d2 share the same width
     */
    public void testEquals5() {

        assertTrue(d1.equals(d2));
    }

}
