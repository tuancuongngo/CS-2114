// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package towerofhanoi;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import CS2114.Button;
import CS2114.Shape;
import CS2114.Window;
import CS2114.WindowSide;

/**
 * Front end class that creates the Window in which we view the puzzle, and
 * observes HanoiSolver
 * given to it by the main method present in ProjectRunner
 * 
 * @author Cuong Ngo (ngoct)
 * @version 03/04/2020
 */
public class PuzzleWindow implements Observer {

    private HanoiSolver game;
    private Shape left;
    private Shape middle;
    private Shape right;
    private Button solve;

    public static final int WIDTH_FACTOR = 15;
    public static final int DISK_GAP = 0;
    public static final int DISK_HEIGHT = 5;


    /**
     * Default constructor that sets up the GUI by adding towers, appropriate
     * disks and buttons
     * 
     * @param game
     *            the puzzle to make the GUI for
     */
    public PuzzleWindow(HanoiSolver game) {
        this.game = game;
        game.addObserver(this);
        Window win = new Window("Tower of Hanoi");

        // Getting the dimensions of the Window object
        int x = win.getWidth() - 1;
        int y = win.getHeight() - 1;

        // Making the 3 Towers/Poles of identical sizes and adding them to the
        // map equidistant apart
        left = new Shape(x - (x * 2 / 3), y / 2, 3, 80, Color.RED);
        middle = new Shape(x - (x / 2), y / 2, 3, 80, Color.RED);
        right = new Shape(x - (x * 1 / 3), y / 2, 3, 80, Color.RED);

        // Adding disks to the RIGHT tower, which is the initial tower that
        // stores disks at the start of every game
        for (int i = game.disks(); i > 0; i--) {

            int width = WIDTH_FACTOR * i; // Finding widths of
                                          // the disks based on
                                          // its
            // index
            Disk newDisk = new Disk(width);
            win.addShape(newDisk);

            // Adding the newly created disk to the RIGHT tower
            game.getTower(Position.RIGHT).push(newDisk);
            moveDisk(Position.RIGHT);
        }

        // Adding the poles to the window
        win.addShape(left);
        win.addShape(middle);
        win.addShape(right);

        // Adding the solve button and its function of solving the puzzle to
        // Window
        solve = new Button("Solve");
        solve.onClick(this, "clickedSolve");
        win.addButton(solve, WindowSide.SOUTH);
    }


    /**
     * Method that is called automatically when the game’s move method calls
     * notifyObservers
     * Showing disks movement from the front end
     * 
     * @param o
     *            the observable object.
     * @param arg
     *            the Position parameter that the disk is moved to
     */
    public void update(Observable o, Object arg) {
        if (arg.getClass().equals(Position.class)) {
            Position newP = (Position)arg;
            moveDisk(newP);
            sleep();
        }
    }


    /**
     * Method that allows a pause between Disk movements.
     * Without a pause, we wouldn’t be able to see the algorithm in action.
     */
    private void sleep() {
        try {
            Thread.sleep(500);
        }
        catch (Exception e) {
        }
    }


    /**
     * This method supports the Solve button
     * 
     * @param button
     *            the button that will solve the puzzle
     */
    public void clickedSolve(Button button) {
        button.disable();

        // Thread is needed so that when it calls game’s solve method,
        // the display is updated when the back-end changes.
        new Thread() {
            public void run() {
                game.solve();
            }
        }.start();
    }


    /**
     * Moves the disks, updates the front-end, after the back-end has been
     * changed
     * 
     * @param position
     *            the position of the tower in the move that the disk was added
     *            to
     */
    private void moveDisk(Position position) {

        // Determine the Tower and Disk involved in the movement
        Tower currentTower = game.getTower(position);
        Disk currentDisk = currentTower.peek();
        Shape currentPole;

        // Finding the correct position for the Tower that the disk was added to
        switch (position) {
            case LEFT:
                currentPole = left;
                break;
            case MIDDLE:
                currentPole = middle;
                break;
            case RIGHT:
                currentPole = right;
                break;
            default:
                currentPole = middle;
                break;
        }

        // Get the coordinates for the current pole that disks are being added
        // to determine where to place the disks
        int poleX = currentPole.getX();
        int poleY = currentPole.getY();

        // Find the x middle point of the pole to add the disk to
        int poleWidth = currentPole.getWidth() / 2;
        int diskMiddleX = currentDisk.getWidth() / 2 - poleWidth;
        int xPosition = poleX - diskMiddleX;

        // How far up the pole each disk should be is determined by the total
        // disks currently on pole * HEIGHT of one disk (Finding the y
        // coordinate of disk)
        int poleHeight = currentPole.getHeight();
        int gapDistance = currentTower.size() * DISK_HEIGHT;
        int yPosition = poleY + poleHeight - gapDistance;

        // Place the disk into the tower
        currentDisk.moveTo(xPosition, yPosition);

    }

}
