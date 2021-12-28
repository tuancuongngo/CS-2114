// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package towerofhanoi;

/**
 * Class that creates an instance of the game for user
 * either making it based on user input, or make a
 * default game of 5 disks
 * 
 * @author Cuong Ngo (ngoct)
 * @version 03/04/2020
 */
public class ProjectRunner {

    /**
     * The main method of ProjectRunner, which creates
     * a PuzzleWindoaw, and pass it a new HanoiSolver, and pass the number of
     * disks to use
     * This is called when you run the program
     * 
     * @param args
     *            the number of disks for the puzzle game from user input
     */
    public static void main(String[] args) {
        int disks = 5;

        // If the user specified how many disks in their game
        if (args.length == 1) {
            disks = Integer.parseInt(args[0]);
        }

        // Otherwise, create a default puzzle containing 5 disks
        new PuzzleWindow(new HanoiSolver(disks));
    }

}
