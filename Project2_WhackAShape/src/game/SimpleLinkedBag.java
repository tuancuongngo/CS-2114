// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package game;

import bag.Node;
import bag.SimpleBagInterface;
import student.TestableRandom;

/**
 * LinkedBag class that stores a list of data inputs using linked chains.
 * In this project, it will store items of type Shape.
 * It is an implementation of SimpleBagInterface<T>
 * 
 * @author Cuong Ngo
 * @version 02/24/2020
 *
 * @param <T>
 *            Type of data to store
 */
public class SimpleLinkedBag<T> implements SimpleBagInterface<T> {

    private Node<T> firstNode;
    private int numberOfEntries;


    /**
     * Default constructor for a SimpleLinkedBag object
     */
    public SimpleLinkedBag() {
        firstNode = null;
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
        if (anEntry != null) {
            Node<T> newNode = new Node<T>(anEntry);
            newNode.setNext(firstNode);
            firstNode = newNode;
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

        // If there are elements in the bag
        if (!isEmpty()) {
            int index = generator.nextInt(numberOfEntries);

            Node<T> pointerNode = firstNode;

            // Get a random element to return, based on the random number
            // generated
            for (int i = 0; i < index; i++) {
                pointerNode = pointerNode.next();
            }
            // Return selected element to user
            return pointerNode.data();
        }
        // If there is no element, return null
        return null;
    }


    /**
     * Helper method for remove()
     * Checks if an item exists in the bag
     * 
     * @param anEntry
     *            the item to look for in the bag
     * @return reference to the Node that contains the item, otherwise return
     *         null
     */
    private Node<T> getReferenceTo(T anEntry) {
        boolean found = false;
        Node<T> currentNode = firstNode;
        // Navigating through the linked chains,
        // attempting to find a match
        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data())) {
                found = true;
            }

            else {
                currentNode = currentNode.next();
            }
        }
        return currentNode;
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

        boolean result = false;
        Node<T> toRemove = getReferenceTo(anEntry);
        if (toRemove != null) {
            toRemove.setData(firstNode.data());
            firstNode = firstNode.next();
            numberOfEntries--;
            result = true;
        }
        return result;
    }

}
