// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package spacecolonies;

import student.TestCase;

/**
 * Test the Person() class in various situations to make sure its working
 * correctly
 * 
 * @author Cuong Ngo (ngoct)
 * @version 04/02/2020
 */
public class PersonTest extends TestCase {

    private Person bad;
    private Person good;
    private Person copy;


    /**
     * Sets up the common attributes for all test cases
     */
    public void setUp() {
        bad = new Person("bad", 1, 1, 1, ""); // No planet preference
        good = new Person("good", 5, 4, 1, "Mars"); // Has a planetPreference
        copy = new Person("good", 5, 4, 1, "Mars"); // Copy of good

    }


    /**
     * Test the getName() method
     */
    public void testGetName() {
        assertEquals("bad", bad.getName());
        assertEquals("good", good.getName());
        assertEquals(good.getName(), copy.getName());
    }


    /**
     * Test the getSkills() method
     */
    public void testGetSkills() {
        assertEquals(good.getSkills(), copy.getSkills());
        assertEquals(good.getSkills().toString(), copy.getSkills().toString());

        assertFalse(good.getSkills().equals(bad.getSkills()));
    }


    /**
     * Test the getPlanetName() method
     */
    public void testGetPlanetName() {
        assertEquals("Mars", good.getPlanetName());
        assertEquals(good.getPlanetName(), copy.getPlanetName());
    }


    /**
     * Test the toString() method
     */
    public void testToString() {
        String goodDetails = "good A:5 M:4 T:1 Wants: Mars";
        String badDetails = "No-Planet bad A:1 M:1 T:1";

        assertEquals(good.toString(), copy.toString());
        assertEquals(goodDetails, good.toString());

        assertEquals(badDetails, bad.toString());

    }


    /**
     * Test the equals() method on various situations to ensure full code
     * coverage
     */
    public void testEquals() {

        // Comparing objects of different classes
        assertFalse(good.equals(""));

        // Comparing 2 Persons objects that are different
        assertFalse(good.equals(bad));

        // Comparing 2 Persons objects that are equal in attributes
        assertTrue(good.equals(copy));
        assertEquals(good.toString(), copy.toString());

        // When everything is the same except planetPreference
        copy = new Person("good", 5, 4, 1, "Venus");
        assertFalse(good.equals(copy));

        // When everything is the same except Skills
        copy = new Person("good", 0, 0, 0, "Mars");
        assertFalse(good.equals(copy));

        // Compare the objects with the same reference
        copy = good;
        assertTrue(good.equals(copy));
        assertEquals(good.toString(), copy.toString());
        
        // Compare to a null Object
        copy = null;
        assertFalse(good.equals(copy));


    }

}
