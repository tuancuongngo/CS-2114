// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package project1;

/**
 * This class is used to demonstrate the functionality
 * of both the ShapeWindow and DisplayCollection classes.
 * 
 * @author Cuong Ngo (ngoct)
 * @version 02/11/2020
 */
public class ProjectRunner {

    /**
     * Constructor, which is intentionally left blank
     */
    public ProjectRunner() {
        // No instructions specified so intentionally left blank
    }


    /**
     * The main method of ProjectRunner, which creates
     * a DisplayCollection and a ShapeWindow object
     * 
     * @param args
     */
    public static void main(String[] args) {
        DisplayCollection collection1 = new DisplayCollection();
        ShapeWindow shape1 = new ShapeWindow(collection1.getItemBag());
    }

}
