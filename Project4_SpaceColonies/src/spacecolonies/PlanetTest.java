// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package spacecolonies;

import student.TestCase;

/**
 * Test the Planet() class in various situations to make sure its working
 * correctly
 * 
 * @author Cuong Ngo (ngoct)
 * @version 04/02/2020
 */
public class PlanetTest extends TestCase {

    private Planet mars;
    private Planet earth;
    private Planet copy;

    private Person king;
    private Person e1;
    private Person m1;
    private Person m2;


    /**
     * Sets up the common attributes for all test cases
     */
    public void setUp() {
        // Sets up the requirements for each Planet
        mars = new Planet("mars", 3, 3, 3, 3);
        earth = new Planet("earth", 1, 1, 1, 2);
        copy = new Planet("mars", 3, 3, 3, 3);

        // Sets up each Person's attributes
        king = new Person("king", 5, 5, 5, "mars");
        e1 = new Person("e1", 1, 1, 1, "earth");
        m1 = new Person("m1", 3, 4, 5, "mars");
        m2 = new Person("m2", 4, 4, 5, "mars");

        // Add people to Planets
        earth.addPerson(m1);
        earth.addPerson(m2);
    }


    /**
     * Test the addPerson() method
     */
    public void testaddPerson() {
        // Adding unqualified individual
        assertFalse(mars.addPerson(e1));

        // Adding qualified individual to empty Planet
        assertTrue(mars.addPerson(king));
        assertTrue(mars.addPerson(m1));
        assertTrue(mars.addPerson(m2));
        assertEquals(3, mars.getPopulationSize());

        // Adding qualified individual to full Planet
        assertFalse(mars.addPerson(new Person("new", 4, 4, 4, "mars")));
    }


    /**
     * Test the toString() method
     */
    public void testToString() {
        String earthInfo =
            "earth, population 2 (cap: 2), Requires: A >= 1, M >= 1, T >= 1";
        String marsInfo =
            "mars, population 0 (cap: 3), Requires: A >= 3, M >= 3, T >= 3";

        assertEquals(earthInfo, earth.toString());
        assertEquals(marsInfo, mars.toString());
    }


    /**
     * Test the equals() method on various situations
     */
    public void testEquals() {

        // Comparing objects of different classes
        assertFalse(earth.equals(1));

        // Comparing 2 objects that are different
        assertFalse(earth.equals(mars));

        // Comparing 2 objects that are equal
        assertTrue(mars.equals(copy));
        assertEquals(mars.toString(), copy.toString());

        // Compare the objects with the same reference
        copy = mars;
        assertTrue(mars.equals(copy));
        assertEquals(mars.toString(), copy.toString());

        // Compare to a null Object
        copy = null;
        assertFalse(earth.equals(copy));
    }


    /**
     * Test the equals() method on Objects that are different by certain
     * attributes
     */
    public void testEqualsAtt() {
        // Comparing 2 objects that are different only in capacity
        mars = new Planet("mars", 3, 3, 3, 5);
        assertFalse(mars.equals(copy));

        mars = new Planet("mars", 3, 3, 3, 3);
        // Comparing 2 objects that are different only in populationSize
        mars.addPerson(king);
        assertFalse(mars.equals(copy));

        // Comparing 2 objects that are different only by Person
        copy.addPerson(m1);
        assertFalse(mars.equals(copy));

        // Comparing 2 objects that are different only by Name
        mars = new Planet("hi", 3, 3, 3, 3);
        copy = new Planet("copy", 3, 3, 3, 3);
        assertFalse(mars.equals(copy));

        // Comparing 2 objects that are different only by Skills requirements
        copy.setName("mars");
        mars = new Planet("mars", 4, 5, 5, 3);
        assertFalse(mars.equals(copy));
    }


    /**
     * Test the compareTo() method
     */
    public void testCompareTo() {
        // Same availability
        assertEquals(0, mars.compareTo(copy));

        // Different availability, with earth less available than mars
        assertEquals(-1, earth.compareTo(mars));
        assertEquals(1, mars.compareTo(earth));
    }

}
