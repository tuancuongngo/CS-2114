// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package spacecolonies;

/**
 * Exception class that will be thrown when certain errors are encoutered
 * 
 * @author Cuong Ngo (ngoct)
 * @version 04/02/2020
 */
public class SpaceColonyDataException extends Exception {

    /**
     * Create an exception based on string error message
     * 
     * @param string
     *            to create an exception of
     */
    public SpaceColonyDataException(String string) {
        super(string);
    }
}
