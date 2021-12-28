// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package game;

import bag.SimpleBagInterface;
import student.TestableRandom;

/**
 * ArrayBag class that stores a list of data inputs using arrays.
 * In this project, it will store items of type Shape
 * It is an implementation of SimpleBagInterface<T>
 * 
 * @author Cuong Ngo
 * @version 02/24/2020
 * 
 * @param <T>
 *            Type of data to store
 */
public class SimpleArrayBag<T> implements SimpleBagInterface<T> {

    private T[] bag;
    private static final int MAX = 25;
    private int numberOfEntries;


    /**
     * Default constructor for the SimpleArrayBag object
     * Initializes an array of type T to store input,
     * and a variable to keep count of how many entries
     * are in the bag
     */
    @SuppressWarnings("unchecked")
    public SimpleArrayBag() {
        T[] tempbag = (T[])new Object[MAX];
        bag = tempbag;
        numberOfEntries = 0;
    }


    /**
     * Adds the specified element to the bag.
     * 
     * @param anEntry
     *            to be added
     * @return Returns true if the item was successfully added.
     */
    @Override
    public boolean add(T anEntry) {
        // Making sure there is an entry to add
        if (anEntry != null && numberOfEntries < MAX) {
            bag[numberOfEntries] = anEntry;
            numberOfEntries++;

            return true;
        }
        return false;
    }


    /**
     * A basic getter method for the private numberOfEntries variable.
     * Determines the number of elements currently in the bag.
     *
     * @return the number of elements in the bag
     */
    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }


    /**
     * Determines if the bag contains no elements.
     *
     * @return true if collection is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }


    /**
     * Getter method that return a random item in the bag to user,
     * if the bag is not empty
     * 
     * @return a random item in the bag
     */
    @Override
    public T pick() {
        TestableRandom generator = new TestableRandom();

        // Return a random item in the bag to user if bag is not empty
        if (!isEmpty()) {
            int index = generator.nextInt(numberOfEntries);
            return bag[index];
        }
        // If bag is empty
        return null;
    }


    /**
     * Removes the specified element from the bag. If multiple
     * copies of the same element appear in the bag, the first one is removed.
     *
     * @param anEntry
     *            item to be removed
     * @return true if the item was removed successfully. Otherwise false
     * @postcondition target item is removed
     */
    @Override
    public boolean remove(T anEntry) {
        int entryIndex = getIndexOf(anEntry);

        // If the entry is found, remove it and return true
        if (entryIndex != -1) {
            bag[entryIndex] = bag[numberOfEntries - 1];
            bag[numberOfEntries - 1] = null;
            numberOfEntries--;
            return true;
        }

        // Otherwise, return false
        return false;
    }


    /**
     * Helper method for remove()
     * Checks if an item exists in the bag
     * 
     * @param anEntry
     *            the item to look for in the bag
     * @return the index of the item in the bag. If not found, return -1
     */
    private int getIndexOf(T anEntry) {
        // Loop through every entry in the bag, if a match is found, loop
        // terminates and the index is returned.
        for (int i = 0; i < numberOfEntries; i++) {
            if (bag[i].equals(anEntry)) {
                return i;
            }
        }

        // If the entry is not found
        return -1;
    }

}
