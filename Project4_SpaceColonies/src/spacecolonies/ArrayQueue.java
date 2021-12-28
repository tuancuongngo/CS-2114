// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package spacecolonies;

import queue.EmptyQueueException;
import queue.QueueInterface;

/**
 * This data structure implements QueueInterface with a circular array
 * implementation.
 * 
 * It provides default queue behavior, such as enqueue, dequeue,
 * getFront, and isEmpty.
 * 
 * @author Cuong Ngo (ngoct)
 * @version 04/02/2020
 * @param <T>
 *            the Type of object the ArrayQueue will store
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = 100;
    private int size;
    private int enqueueIndex; // Back Index
    private int dequeueIndex; // Front Index
    private T[] queue;


    /**
     * Default constructor that initializes all variables for a new ArrayQueue
     */
    public ArrayQueue() {
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[])new Object[DEFAULT_CAPACITY + 1];
        queue = tempQueue;
        dequeueIndex = 1;
        enqueueIndex = 0;
        size = 0;
    }


    /**
     * Check to see whether queue is full
     * 
     * @return True if queue is considered full using Circular Array logic
     */
    private boolean isFull() {
        return dequeueIndex == ((enqueueIndex + 2) % queue.length);
    }


    /**
     * Get the length of the queue
     * 
     * @return the integer representation of the queue length
     */
    public int getLength() {
        return queue.length;
    }


    /**
     * Get the size of the queue, which is how many positions are occupied
     * within the queue
     * 
     * @return the integer of the number of occupied positions in the queue
     */
    public int getSize() {
        return size;
    }


    /**
     * Doubles the size of an array queue if it is full
     * until it gets to MAX_CAPACITY
     */
    private void ensureCapacity() {

        // If size exceeds max capacity, throw an exception
        if (size + 1 > MAX_CAPACITY) {
            throw new IllegalStateException();
        }

        // Check to see if the queue is full
        // If it is full currently, the size will double until it gets to max
        // capacity
        if (isFull()) {
            T[] oldQueue = queue;
            int oldSize = oldQueue.length;
            int newSize = (2 * oldSize) - 1;

            // If doubling the size of array exceeds maximum capacity,
            // set expanded size to be max capacity
            if (newSize > MAX_CAPACITY) {
                newSize = MAX_CAPACITY + 1;
            }

            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[])new Object[newSize];
            queue = tempQueue;

            // Copies items from original Queue to new Queue
            for (int index = 0; index < oldSize - 1; index++) {
                queue[index] = oldQueue[dequeueIndex];
                dequeueIndex = incrementIndex(dequeueIndex);
            }

            // Reset the front and back indexes
            dequeueIndex = 0;
            enqueueIndex = oldSize - 2;
        }
    }


    /**
     * Helper method that increments an index in the queue using circular queue
     * wrapping logic.
     * 
     * @param index
     *            the index to increment
     * @return the incremented index
     */
    private int incrementIndex(int index) {
        return ((index + 1) % queue.length);
    }


    /**
     * Empties the queue and resets all variables to default state
     */
    @Override
    public void clear() {
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[])new Object[DEFAULT_CAPACITY + 1];
        queue = tempQueue;
        dequeueIndex = 0;
        enqueueIndex = DEFAULT_CAPACITY;
        size = 0;
    }


    /**
     * Removes an element at the front of the queue
     * 
     * @return the removed element
     */
    @Override
    public T dequeue() {

        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        T front = queue[dequeueIndex];
        queue[dequeueIndex] = null;
        dequeueIndex = incrementIndex(dequeueIndex);
        size--;
        return front;

    }


    /**
     * Adds an element to the back of the queue
     */
    @Override
    public void enqueue(T newEntry) {

        // Expand array if it's full
        ensureCapacity();

        // Add newEntry to array
        enqueueIndex = incrementIndex(enqueueIndex);
        queue[enqueueIndex] = newEntry;
        size++;
    }


    /**
     * Get the element that's currently at the front of the queue without
     * removing it
     * 
     * @return the element at the front of the queue
     */
    @Override
    public T getFront() {

        // Throws an exception if the queue is empty
        if (isEmpty()) {
            throw new EmptyQueueException("There is no element at the front");
        }

        return queue[dequeueIndex];
    }


    /**
     * Determine if the queue is empty using circular wrapping logic
     * 
     * @return True if the queue is empty (No elements left in the queue)
     */
    @Override
    public boolean isEmpty() {
        return dequeueIndex == ((enqueueIndex + 1) % queue.length);
    }


    /**
     * Convert the queue to an array to be returned to the user
     * 
     * @return the converted array containing elements from the queue
     */
    public Object[] toArray() {

        // Check if the array is empty
        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        // Make an array object to return
        Object[] returnArray = new Object[size];

        // Iterate through all elements and add to the return array

        for (int i = 0; i < getSize(); i++) {
            returnArray[i] = queue[(dequeueIndex + i) % queue.length];
        }

        return returnArray;

    }


    /**
     * Convert all elements in the queue to a String to return to the user
     * 
     * @return a String containing all elements in the queue
     */
    public String toString() {

        // Check to see if Queue is empty
        if (isEmpty()) {
            return "[]";
        }

        // Create a StringBuilder for String concatenation efficiency
        StringBuilder str = new StringBuilder();
        str.append("[");

        // Iterate through the elements and adding its details to StringBuilder
        for (int i = 0; i < getSize(); i++) {

            // Get the details of the current Object in the Queue
            T currentObject = queue[(dequeueIndex + i) % queue.length];
            str.append(currentObject.toString());

            // Adding appropriate commas and spaces
            if ((dequeueIndex + i) % queue.length != enqueueIndex) {
                str.append(", ");
            }
        }

        str.append("]");

        return str.toString();
    }


    /**
     * Determine if 2 ArrayQueues are equal in content by performing various
     * checks
     * 
     * For two ArrayQueues to be equal, they have to contain the same elements
     * in the same order.
     * 
     * @return true if the 2 objects are equal
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {

        // Compare if they have the same reference
        if (obj == this) {
            return true;
        }

        // If the Object to compare to doesn't exist
        if (obj == null) {
            return false;
        }

        // If they are the same class
        if (this.getClass() == obj.getClass()) {
            ArrayQueue<T> otherQ = (ArrayQueue<T>)obj;

            // If the objects are different length or size
            if (getSize() != otherQ.getSize()) {
                return false;
            }

            for (int i = 0; i < getSize(); i++) {
                // Get the element from each object 1 by 1
                T myElement = queue[(dequeueIndex + i) % queue.length];
                T otherElement = otherQ.queue[(otherQ.dequeueIndex + i)
                    % otherQ.queue.length];

                // Compare them to each other
                if (!myElement.equals(otherElement)) {
                    return false;
                }
            }
            // If the 2 objects are equal
            return true;
        }

        // If the objects are from different classes
        return false;
    }

}
