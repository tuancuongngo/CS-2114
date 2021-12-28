// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package towerofhanoi;

import student.TestCase;
import student.TestableRandom;

/**
 * Test class for HanoiSolver() class, ensuring proper functionings of methods
 * 
 * @author Cuong Ngo (ngoct)
 * @version 03/04/2020
 */
public class HanoiSolverTest extends TestCase {

    private HanoiSolver han;


    /**
     * Sets up common conditions used in every test cases
     */
    public void setUp() {
        han = new HanoiSolver(4);
    }


    /**
     * Test the disks() method
     */
    public void testDisks() {
        assertEquals(4, han.disks());
    }


    /**
     * Test the getTower() method on the left tower
     */
    public void testGetTowerL() {
        // Getting the left tower, which has 0 disks
        assertEquals(0, han.getTower(Position.LEFT).size());

        // Adding a disk to the left tower
        han.getTower(Position.LEFT).push(new Disk(1));
        assertEquals(1, han.getTower(Position.LEFT).size());

        // Removing a disk from the left tower
        han.getTower(Position.LEFT).pop();
        assertEquals(0, han.getTower(Position.LEFT).size());
    }


    /**
     * Test the getTower() method on the middle tower
     */
    public void testGetTowerM() {
        // Getting the middle tower, which has 0 disks
        assertEquals(0, han.getTower(Position.MIDDLE).size());

        // Adding a disk to the middle tower
        han.getTower(Position.MIDDLE).push(new Disk(1));
        assertEquals(1, han.getTower(Position.MIDDLE).size());

        // Removing a disk from the middle tower
        han.getTower(Position.MIDDLE).pop();
        assertEquals(0, han.getTower(Position.MIDDLE).size());
    }


    /**
     * Test the getTower() method on the right tower
     */
    public void testGetTowerR() {
        // Getting the right tower, which has 0 disks
        assertEquals(0, han.getTower(Position.RIGHT).size());

        // Adding a disk to the right tower
        han.getTower(Position.RIGHT).push(new Disk(1));
        assertEquals(1, han.getTower(Position.RIGHT).size());

        // Removing a disk from the right tower
        han.getTower(Position.RIGHT).pop();
        assertEquals(0, han.getTower(Position.RIGHT).size());
    }


    /**
     * Test the getTower() method on the default tower, which is the middle
     */
    public void testGetTowerD() {
        // Getting the default, which is the middle tower, which has 0 disks
        assertEquals(0, han.getTower(Position.DEFAULT).size());

        // Adding a disk to the middle tower, which also is the DEFAULT tower
        han.getTower(Position.MIDDLE).push(new Disk(1));
        assertEquals(1, han.getTower(Position.DEFAULT).size());

        // Removing a disk from the middle tower, which also is the DEFAULT
        // tower
        han.getTower(Position.MIDDLE).pop();
        assertEquals(0, han.getTower(Position.DEFAULT).size());
    }


    /**
     * Test the toString() method on empty towers
     */
    public void testToString0() {
        assertEquals("[][][]", han.toString());
    }


    /**
     * Test the toString() method on valid towers each containing 1 disk
     */
    public void testToString1() {
        han.getTower(Position.LEFT).push(new Disk(1));
        han.getTower(Position.MIDDLE).push(new Disk(2));
        han.getTower(Position.RIGHT).push(new Disk(3));

        assertEquals("[1][2][3]", han.toString());
    }


    /**
     * Test the toString() method on valid towers each containing multiple disks
     */
    public void testToString2() {
        han.getTower(Position.LEFT).push(new Disk(400));
        han.getTower(Position.LEFT).push(new Disk(20));

        han.getTower(Position.MIDDLE).push(new Disk(20));
        han.getTower(Position.MIDDLE).push(new Disk(19));
        han.getTower(Position.MIDDLE).push(new Disk(18));

        han.getTower(Position.RIGHT).push(new Disk(3));

        assertEquals("[20, 400][18, 19, 20][3]", han.toString());
    }


    /**
     * Test the solve method on a game containing 1 disks
     */
    public void testSolve0() {
        han = new HanoiSolver(1);
        han.getTower(Position.RIGHT).push(new Disk(1));
        han.solve();

        // The disk will be moved to the left pole
        assertEquals(1, han.getTower(Position.LEFT).size());
        assertEquals(0, han.getTower(Position.RIGHT).size());
    }


    /**
     * Test the solve method on a game containing multiple disks
     */
    public void testSolve1() {
        // Randomly generates a number of disks, ranging from 2-6 disks
        TestableRandom rand = new TestableRandom();
        int noDisk = rand.nextInt(5) + 2;

        // Pushing the disks to the right pole manually
        han = new HanoiSolver(noDisk);
        for (int i = noDisk; i > 0; i--) {
            han.getTower(Position.RIGHT).push(new Disk(i));
        }

        han.solve();

        // All the disks is now in the left pole
        assertEquals(noDisk, han.getTower(Position.LEFT).size());
        assertEquals(0, han.getTower(Position.RIGHT).size());
    }

}
