// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package towerofhanoi;

import java.awt.Color;
import CS2114.Shape;
import student.TestableRandom;

/**
 * Disks are the main objects that will be moved around in this game
 * from one pole to another
 * The Disk class extends Shape, because they are simply rectangles of various
 * widths
 * It also implements the Comparable<Disk> interface to allow a Disk to be
 * compared to one another
 * 
 * @author Cuong Ngo (ngoct)
 * @version 03/04/2020
 */
public class Disk extends Shape implements Comparable<Disk> {

    /**
     * Default constructor, that will create disks of a user specified width,
     * and a random color
     * 
     * @param width
     *            the width to set for the disk
     */
    public Disk(int width) {
        super(0, 0, width, PuzzleWindow.DISK_HEIGHT);

        // Set disk to a randomly generated color
        TestableRandom random = new TestableRandom();
        Color color = new Color(random.nextInt(256), random.nextInt(256), random
            .nextInt(256));
        this.setBackgroundColor(color);
    }


    /**
     * Used to determine the relative size of disks
     * Compare width of current disk to another disk
     * 
     * @param otherDisk
     *            the disk that will be compared to current disk
     * @throws IllegalArgumentException
     *             if there is no disk to compare to
     */
    @Override
    public int compareTo(Disk otherDisk) {

        // If there is no disk to compare to, throw a IllegalArgumentException
        if (otherDisk == null) {
            throw new IllegalArgumentException();
        }

        // Will return a negative number if the current disk's width is less
        // than otherDisk's width
        // Will return a positive number if the current disk's width is more
        // than otherDisk's width
        // Will return 0 if the disks have equal width
        return this.getWidth() - otherDisk.getWidth();

    }


    /**
     * Return the width of this Disk as a string.
     * 
     * @return the width of the Disk as a string
     */
    @Override
    public String toString() {
        return String.valueOf(this.getWidth());
    }


    /**
     * Test whether 2 Disk objects are equal to each other
     * 
     * @return True if they are equal. False otherwise
     */
    @Override
    public boolean equals(Object obj) {
        // Compare if they have the same reference
        if (obj == this) {
            return true;
        }

        // If the disk to compare to doesn't exist
        if (obj == null) {
            return false;
        }

        // If the disks are the same class
        if (this.getClass() == obj.getClass()) {
            Disk otherDisk = (Disk)obj;

            // If width is different/non-zero they are not equal
            return this.compareTo(otherDisk) == 0;

        }

        // Otherwise, return false because object is not of the same class
        return false;
    }
}
