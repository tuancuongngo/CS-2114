// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package towerofhanoi;

import student.TestCase;

/**
 * Class that tests all the methods of Tower class, ensuring proper functioning
 * 
 * @author Cuong Ngo (ngoct)
 * @version 03/04/2020
 */
public class TowerTest extends TestCase {

    private Tower left; // LEFT tower
    private Tower right; // RIGHT tower
    private Tower middle; // MIDDLE tower


    /**
     * Sets up common conditions used in every test cases
     */
    public void setUp() {
        left = new Tower(Position.LEFT);
        middle = new Tower(Position.MIDDLE);
        right = new Tower(Position.RIGHT);
    }


    /**
     * Test the position() method
     */
    public void testPosition() {
        assertEquals(Position.LEFT, left.position());
        assertEquals(Position.RIGHT, right.position());
        assertEquals(Position.MIDDLE, middle.position());

        Tower defaultT = new Tower(Position.DEFAULT);
        assertEquals(Position.DEFAULT, defaultT.position());
    }


    /**
     * Test the push() method where the disk being passed in is null,
     * which will throw a IllegalArgumentException()
     */
    public void testPush0() {
        Exception thrown = null;
        try {
            left.push(null);
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
     * Test the push() method where we add a disk to a Tower that is empty
     */
    public void testPush1() {
        middle.push(new Disk(2));
        assertEquals(1, middle.size());
    }


    /**
     * Test the push() method where we add a smaller disk to a bigger disk
     * (Valid push)
     */
    public void testPush2() {
        right.push(new Disk(100));

        // Adding a smaller disk on top
        right.push(new Disk(1));
        assertEquals(2, right.size());
    }


    /**
     * Test the push() method where we add a bigger disk to a smaller disk
     * (Invalid push) so an IllegalStateException() will be thrown
     */
    public void testPush3() {

        // Add a smaller disk to the bottom
        left.push(new Disk(1));

        Exception thrown = null;
        try {
            // Attempt to add a bigger disk on top
            left.push(new Disk(100));
        }
        catch (Exception exception) {
            thrown = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown instanceof IllegalStateException);

    }

}
