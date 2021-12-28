// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package towerofhanoi;

import java.util.EmptyStackException;
import stack.StackInterface;

/**
 * A class of stacks whose entries are stored in a chain of nodes.
 * This class that implements the StackInterface<T> and will store
 * objects of type <T>
 * 
 * @author Cuong Ngo (ngoct)
 * @version 03/04/2020
 * @param <T>
 *            the generic object type that will be replaced
 */
public class LinkedStack<T> implements StackInterface<T> {

    private Node<T> topNode;
    private int size;


    /**
     * Default constructor that sets up the original size of the LinkedStack,
     * and initializes a node
     */
    public LinkedStack() {
        topNode = null;
        size = 0;
    }


    /**
     * Getter method for the number of objects
     * currently stored in the LinkedStack
     * 
     * @return the number of objects
     *         currently stored in the LinkedStack
     */
    public int size() {
        return size;
    }


    /**
     * Determines if the LinkedStack contains no elements.
     *
     * @return true if it is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * This method shows what’s on the top of the stack, without modifying the
     * stack in any way.
     * 
     * @return topNode’s data (the element at the top of the stack)
     * @throws EmptyStackException
     *             if the stack is empty
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return topNode.getData();
    }


    /**
     * Adds the specified element to the top of the LinkedStack
     * 
     * @param anEntry
     *            to be added
     * @precondition the entry is a valid entry
     **/
    @Override
    public void push(T anEntry) {

        Node<T> newNode = new Node<T>(anEntry);
        newNode.setNextNode(topNode);
        topNode = newNode;
        size++;

    }


    /**
     * This method takes away the Node from the top of the stack, and return its
     * data.
     * 
     * @return the data that is retrieved from topNode
     * @throws EmptyStackException
     *             if the stack is empty
     */
    @Override
    public T pop() {

        // If the stack empty
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        // Object to store the object that will be removed
        T toReturn = topNode.getData();

        // Remove the object and decrements size
        topNode = topNode.getNextNode();
        size--;

        return toReturn;
    }


    /**
     * Return a String that contains all elements in the LinkedStack ordered
     * from top to bottom
     * 
     * @return String in that contains all elements in the Stack in the order of
     *         "[lastPush, secondLastPush, ...., firstPush]"
     */
    @Override
    public String toString() {

        String returnString = "";

        Node<T> currentNode = topNode;

        while (currentNode != null) {
            returnString += currentNode.getData().toString();

            // Add commas between items only if it's not the last item (last
            // item = null)
            if (currentNode.getNextNode() != null) {
                returnString += ", ";
            }
            currentNode = currentNode.getNextNode();
        }
        return "[" + returnString + "]";
    }


    /**
     * Clear all data in the LinkedStack
     */
    @Override
    public void clear() {
        topNode = null;
        size = 0;
    }


    /**
     * Class for singly linked Nodes, which will be used in LinkedStack<T> to
     * store and retrieve data
     * 
     * @author Cuong Ngo (ngoct)
     * @version 03/04/2020
     * 
     * @param <T>
     *            the generic Type of object.
     */
    @SuppressWarnings("hiding")
    private class Node<T> {
        private Node<T> next; // Link to next node
        private T data; // Entry in the bag


        /**
         * Default constructor for Node, which sets its data to be the provided
         * dataPortion.
         * 
         * @param dataPortion
         *            data to put inside this Node
         */
        public Node(T dataPortion) {
            this(dataPortion, null);
        }


        /**
         * Node constructor which sets its data to be dataPortion
         * and its following node to be nextNode.
         * 
         * @param dataPortion
         *            data to put inside this Node
         * @param nextNode
         *            This Node's next Node
         */
        public Node(T dataPortion, Node<T> nextNode) {
            data = dataPortion;
            next = nextNode;
        }


        /**
         * Getter method for the next Node
         * 
         * @return next Node holding a T object
         */
        public Node<T> getNextNode() {
            return next;
        }


        /**
         * Getter for data in the specified Node
         * 
         * @return the T object inside of this Node
         */
        public T getData() {
            return data;
        }


        /**
         * Setter for next Node
         * 
         * @param newNode
         *            the new next node
         */
        public void setNextNode(Node<T> newNode) {
            next = newNode;
        }

    }
}
