// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package towerofhanoi;

import java.util.Observable;

/**
 * This class represents a Tower of Hanoi puzzle, and will contains methods
 * that will solve the puzzle.
 * 
 * This class extends Observable, so that the PuzzleWindow
 * may observe HanoiSolver to update the display that animates the Disks.
 * 
 * @author Cuong Ngo (ngoct)
 * @version 03/04/2020
 *
 */
public class HanoiSolver extends Observable {

    private int numDisks;
    private Tower left;
    private Tower middle;
    private Tower right;


    /**
     * Default constructor
     * 
     * @param numDisks
     *            the number of disks present
     */
    public HanoiSolver(int numDisks) {
        this.numDisks = numDisks;
        left = new Tower(Position.LEFT);
        middle = new Tower(Position.MIDDLE);
        right = new Tower(Position.RIGHT);
    }


    /**
     * Getter method for the number of disks in the puzzle
     * 
     * @return the number of disks
     */
    public int disks() {
        return numDisks;
    }


    /**
     * Getter method that determines the tower based on its position
     * 
     * @param pos
     *            the position of specified tower
     * @return the tower in that position
     */
    public Tower getTower(Position pos) {
        switch (pos) {
            case LEFT:
                return left;
            case RIGHT:
                return right;
            case MIDDLE:
                return middle;
            default:
                return middle;
        }
    }


    /**
     * Return the widths of all the disks contained in each tower
     * in the order of
     * [widths of disks in left tower][widths of disks in middle tower][widths
     * of disks in right]
     * 
     * @return a string containing the widths of all the disks contained in the
     *         3 towers
     */
    @Override
    public String toString() {
        return left.toString() + middle.toString() + right.toString();
    }


    /**
     * This method moves disks from one tower to another by
     * popping the Disk from the "source" Tower, and push it onto the
     * "destination" Tower
     * 
     * @param source
     *            The source tower to remove disk from
     * @param destination
     *            The new tower to add disk to
     */
    private void move(Tower source, Tower destination) {
        destination.push(source.pop());
        setChanged();
        notifyObservers(destination.position());

    }


    /**
     * Recursion method that solves the puzzle that contains any number of disks
     * 
     * @param currentDisks
     *            number of disks in the puzzle
     * @param startPole
     *            the Tower at which the disks are originally at
     * @param tempPole
     *            the temporary Tower used to store disks to solve puzzle
     * @param endPole
     *            the Tower at which the disks will end up at when the puzzle is
     *            solved
     */
    private void solveTowers(
        int currentDisks,
        Tower startPole,
        Tower tempPole,
        Tower endPole) {

        // Base case, when there is one disk left on the original pole, move it
        // to the top of the end pole
        if (currentDisks == 1) {
            move(startPole, endPole);
        }

        // Recursive case
        else {
            // Solve the towers by moving disks from startPole to tempPole using
            // endPole as the temporary tower, leaving 1 disk at startPole
            solveTowers(currentDisks - 1, startPole, endPole, tempPole);
            // Moving the top disk from startPole to endPolse
            move(startPole, endPole);
            // Solve the towers by moving disks from tempPole to endPole using
            // startPole as the temporary tower
            solveTowers(currentDisks - 1, tempPole, startPole, endPole);
        }
    }


    /**
     * Method that initiates the problem solving process of the puzzle
     * This method makes the initial call to the private recursive solveTowers()
     * method
     */
    public void solve() {
        // Providing the solveTowers() method the correct parameters,
        // with right being the start, middle being the temp, and left being the
        // end
        solveTowers(numDisks, right, middle, left);
    }

}
