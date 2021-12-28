// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package spacecolonies;

import java.io.FileNotFoundException;
import bsh.ParseException;

/**
 * This class begins the program by creating a QueueReader and telling it which
 * file to look at
 * 
 * @author Cuong Ngo (ngoct)
 * @version 04/02/2020
 * 
 */
public class ProjectRunner {

    /**
     * The main method of ProjectRunner, which creates a ColonyReader object
     * 
     * @param args
     *            the array containing the names of input files for applicants
     *            and planets
     * @throws FileNotFoundException
     *             if an input file is not found
     * @throws ParseException
     *             if an error is encountered during parsing
     * @throws SpaceColonyException
     *             if there is an invalid input in any input files
     */
    public static void main(String[] args)
        throws FileNotFoundException,
        ParseException,
        SpaceColonyDataException {

        // If the user has specified input files
        if (args.length == 2) {
            new ColonyReader(args[0], args[1]);
        }

        // Default input files
        else {
            new ColonyReader("input.txt", "planets.txt");
        }
    }

}
