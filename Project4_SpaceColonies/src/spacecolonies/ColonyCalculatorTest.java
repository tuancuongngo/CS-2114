// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package spacecolonies;

import student.TestCase;

/**
 * Test the ColonyCalculator() class in various situations to make sure it's
 * working correctly
 * 
 * @author Cuong Ngo (ngoct)
 * @version 04/02/2020
 */
public class ColonyCalculatorTest extends TestCase {

    private ColonyCalculator col;
    private ArrayQueue<Person> applicants;
    private Planet[] planets;
    private Person king;
    private Person low;
    private Planet hard;
    private Planet medium;
    private Planet ez;


    /**
     * Sets up the common attributes for all test cases
     */
    public void setUp() {
        // Make new Person objects
        low = new Person("Marva Buel", 1, 2, 1, "hard");
        king = new Person("king", 5, 5, 5, "");

        // Initializes applicant queue and planet array
        applicants = new ArrayQueue<Person>();
        planets = new Planet[ColonyCalculator.NUM_PLANETS + 1];

        // Adding Person to queue
        applicants.enqueue(low);
        applicants.enqueue(king);

        // Make new planets
        hard = new Planet("hard", 5, 2, 2, 4);
        medium = new Planet("medium", 3, 3, 3, 3);
        ez = new Planet("ez", 1, 1, 1, 2);

        // Add planets to planet array
        planets[1] = hard;
        planets[2] = medium;
        planets[3] = ez;

        // New ColonyCalculator object
        col = new ColonyCalculator(applicants, planets);
    }


    /**
     * Test the default constructor to see if it throws IllegalArgumentException
     * when it's supposed to
     */
    public void testColonyCalculator() {
        applicants = null;
        Exception exception = null;
        try {
            // Passing in an empty Person list
            col = new ColonyCalculator(applicants, planets);
        }
        catch (Exception e) {
            exception = e;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(exception);

        assertTrue(exception instanceof IllegalArgumentException);
        assertEquals(planets, col.getPlanets());
    }


    /**
     * Test the accept method in various situations
     */
    public void testAccept() {
        // When the applicant doesn't qualify for any planet because of lower
        // Skills
        assertFalse(col.accept());
        applicants.dequeue();

        // When applicant (king) qualifies for his desired planet
        assertTrue(col.accept());

        // When the applicants list is empty
        applicants.clear();
        assertFalse(col.accept());
    }


    /**
     * Test the getPlanetForPerson() method in various situations
     */
    public void testGetPlanetForPerson() {
        // When no person is passed in as parameter
        assertNull(col.getPlanetForPerson(null));

        // When the person doesn't qualify for their desired planet
        assertNull(col.getPlanetForPerson(low));

        // When a person does qualify for a planet
        assertEquals(hard, col.getPlanetForPerson(king));

        // Empty applicant queue
        applicants.clear();
        assertEquals(null, col.getPlanetForPerson(king));

        // When a person is qualified for their desired planet
        Person queen = new Person("queen", 5, 5, 5, "hard");
        applicants.enqueue(queen);
        assertEquals(hard, col.getPlanetForPerson(queen));

        // When a person does qualify for a planet but its full
        ez.addPerson(new Person("bot1", 2, 2, 2, "ez"));
        ez.addPerson(new Person("bot2", 2, 2, 2, "ez"));

        queen = new Person("queen", 2, 2, 2, "ez");
        applicants.enqueue(queen);

        assertEquals(null, col.getPlanetForPerson(queen));

        // When a person doesn't have a preference and doesn't qualify for any
        // planet
        queen = new Person("queen", 0, 0, 0, "Nowhere");
        applicants.enqueue(queen);
        assertEquals(null, col.getPlanetForPerson(queen));
    }


    /**
     * Test the reject method to ensure it's functions properly
     */
    public void testReject() {
        // Empty the queue by calling reject
        col.reject();
        col.reject();

        assertTrue(col.getQueue().isEmpty());
    }


    /**
     * Test the planetByNumber() method in various situations
     */
    public void testPlanetByNumber() {
        // When an invalid planet number is passed in
        assertNull(col.planetByNumber(11));
        assertNull(col.planetByNumber(-1));

        // When a valid planet number is passed in
        assertEquals(hard, col.planetByNumber(1));
        assertEquals(medium, col.planetByNumber(2));
    }


    /**
     * Test the getPlanetIndex method in various situations
     */
    public void testGetPlanetIndex() {
        // When name is not found in Planet list
        assertEquals(0, col.getPlanetIndex(""));

        // Getting the PLanet named medium
        assertEquals(2, col.getPlanetIndex("medium"));
    }

}
