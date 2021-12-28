// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package towerofhanoi;

/**
 * The Tower objects that store Disks function as stacks, extending the
 * LinkedStack that was implemented
 * Towers offer a unique extension to a normal stack - they only allow smaller
 * disks to be placed on top of larger ones.
 * 
 * @author Cuong Ngo (ngoct)
 * @version 03/04/2020
 */
public class Tower extends LinkedStack<Disk> {

    private Position position;


    /**
     * Default constructor that creates a Tower at the specified position
     * 
     * @param position
     *            position where tower will be created, either left, right, or
     *            middle
     */
    public Tower(Position position) {
        super();
        this.position = position;
    }


    /**
     * Getter method for position of a tower
     * 
     * @return the position of a tower
     */
    public Position position() {
        return position;
    }


    /**
     * Method that push the disk provided into the tower after checking whether
     * it is valid to do so. Will throw IllegalStateException() if user is
     * attempting to put a larger disk on top
     * 
     * @param disk
     *            the disk to add
     * @throws IllegalArgumentException
     *             when user provides an null input
     * @throws IllegalStateException
     *             when user attempts to put a larger disk on top of a smaller
     *             one
     */
    @Override
    public void push(Disk disk) {

        // If the disk being passed in is null
        if (disk == null) {
            throw new IllegalArgumentException();
        }

        // Add disk to tower if the tower is empty or if the current top disk is
        // bigger than the disk being passed in
        if (isEmpty() || peek().compareTo(disk) > 0) {
            super.push(disk);
        }

        // If the push is invalid (Attempting to put a larger disk on top)
        else {
            throw new IllegalStateException();
        }

    }

}
