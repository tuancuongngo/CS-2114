// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package game;

/**
 * Class that creates an instance of the game for user
 * either making it based on user input, or make a
 * random game
 * 
 * @author Cuong Ngo
 * @version 02/24/2020
 */
public class ProjectRunner {

    /**
     * The main method of ProjectRunner, which creates
     * a DisplayCollection and a ShapeWindow object
     * This is called when you run the program
     * 
     * @param args
     *            the String array from user containing Shape objects being
     *            passed from the command line when the program runs
     */
    public static void main(String[] args) {

        // If the user passes in a list of object,
        // create a new game with the given objects
        if (args.length > 0) {
            new WhackAShape(args);
        }

        // Otherwise, make a game with a random object
        // by calling the default constructor
        else {
            new WhackAShape();
        }

    }

}
