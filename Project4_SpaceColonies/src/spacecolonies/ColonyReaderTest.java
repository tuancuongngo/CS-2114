// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package spacecolonies;

import student.TestCase;

/**
 * Test class to make a ColonyReader object that tests the
 * SpaceColonyDataExceptions
 * 
 * @author Cuong Ngo (ngoct)
 * @version 04/02/2020
 */
public class ColonyReaderTest extends TestCase {

    /**
     * Sets up the common attributes
     */
    public void setUp() {
        // Intentionally left blank
    }


    /**
     * Test whether SpaceColonyDataException is thrown when it is supposed to
     */
    public void testException() {

        Exception exception = null;
        try {
            // Passing in a Planet not in Skills range, so
            // SpaceColonyDataException is thrown
            new ColonyReader("shortInput.txt", "planetsHighSkill.txt");
        }
        catch (Exception e) {
            exception = e;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(exception);

        assertTrue(exception instanceof SpaceColonyDataException);

    }
}
