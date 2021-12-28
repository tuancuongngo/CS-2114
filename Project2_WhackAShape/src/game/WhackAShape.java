// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package game;

import CS2114.Window;
import CS2114.WindowSide;
import bag.SimpleBagInterface;
import student.TestableRandom;
import java.awt.Color;
import CS2114.Button;
import CS2114.CircleShape;
import CS2114.Shape;
import CS2114.SquareShape;
import CS2114.TextShape;

/**
 * Class that will be the interface for the user,
 * displaying random shapes for users to click for the game
 * 
 * @author Cuong Ngo
 * @version 02/24/2020
 */
public class WhackAShape {

    /**
     * Instance Array field that contains all the possible Objects
     */
    public static final String[] STRINGS = { "red circle", "blue circle",
        "red square", "blue square" };

    private SimpleBagInterface<Shape> bag;
    private Window window;
    private TestableRandom randomGenerator;
    private Button quitButton;


    /**
     * Default constructor for WhackAShape
     */
    public WhackAShape() {
        // Initializes fields
        randomGenerator = new TestableRandom();
        window = new Window();
        bag = new SimpleLinkedBag<Shape>();

        // Adding the quit button and its function to Window
        quitButton = new Button("Quit");
        quitButton.onClick(this, "clickedQuit");
        window.addButton(quitButton, WindowSide.SOUTH);

        // Populate bag with a random size with random shapes
        int bagSize = randomGenerator.nextInt(6) + 7;
        int randomIndex = randomGenerator.nextInt(4);

        for (int i = 0; i < bagSize; i++) {
            // Get a random shape description
            String newShape = STRINGS[randomIndex];

            // Build the random shape, and adding it to bag
            bag.add(this.buildShape(newShape));
        }

        // Adding the first shape to the Window interface for user
        window.addShape(bag.pick());
    }


    /**
     * Constructor that populate bag with user specified Shapes
     * 
     * @param inputs
     *            a list of Shapes to create
     */
    public WhackAShape(String[] inputs) {
        // Initializes fields
        randomGenerator = new TestableRandom();
        window = new Window();
        bag = new SimpleLinkedBag<Shape>();

        // Adding the quit button and its function to Window
        quitButton = new Button("Quit");
        quitButton.onClick(this, "clickedQuit");
        window.addButton(quitButton, WindowSide.SOUTH);

        // Populate linked bag with Shapes, based on array input
        try {
            for (int i = 0; i < inputs.length; i++) {
                Shape newShape = this.buildShape(inputs[i]);
                bag.add(newShape);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Adding the first shape to the Window
        window.addShape(bag.pick());
    }


    /**
     * Build a Shape object based on a String definition
     * specified by the user
     * 
     * @param input
     *            Specifies what kind of shape to build
     * @return a newly created Shape object.
     * @throw IllegalArgumentException when user enters an invalid input
     */
    private Shape buildShape(String input) {
        int size = randomGenerator.nextInt(101) + 100;
        int x = randomGenerator.nextInt(window.getGraphPanelWidth() - size);
        int y = randomGenerator.nextInt(window.getGraphPanelHeight() - size);

        Shape currentShape = null;

        // Boolean variables to check if input is of the valid shape and color
        boolean isRed = input.contains("red");
        boolean isBlue = input.contains("blue");
        boolean isCircle = input.contains("circle");
        boolean isSquare = input.contains("square");

        // Check to see if the input has a valid color
        if (!isRed && !isBlue) {
            throw new IllegalArgumentException("Invalid color");
        }

        // Check to see if the input has a valid shape
        if (!isCircle && !isSquare) {
            throw new IllegalArgumentException("Invalid input");
        }

        // Determine if object is red
        if (isRed) {
            // Red circle
            if (isCircle) {
                currentShape = new CircleShape(x, y, size, Color.red);
                currentShape.onClick(this, "clickedShape");
            }

            // Red square
            if (isSquare) {
                currentShape = new SquareShape(x, y, size, Color.red);
                currentShape.onClick(this, "clickedShape");
            }
        }

        // Determine if object is blue
        if (isBlue) {
            // Blue circle
            if (isCircle) {
                currentShape = new CircleShape(x, y, size, Color.blue);
                currentShape.onClick(this, "clickedShape");
            }
            // Blue square
            if (isSquare) {
                currentShape = new SquareShape(x, y, size, Color.blue);
                currentShape.onClick(this, "clickedShape");
            }
        }
        return currentShape;
    }


    /**
     * Determine what happens when user clicked on a Shape
     * Remove a shape when an user clicks on it, and
     * add a new one from bag, if bag isn't empty
     * 
     * @param shape
     *            the shape that was clicked on
     */
    public void clickedShape(Shape shape) {
        // Remove the shape from the window and bag
        window.removeShape(shape);
        bag.remove(shape);

        // Get the next random object from the bag, if there is still one
        Shape nextShape = bag.pick();
        if (nextShape != null) {
            window.addShape(nextShape);
        }
        // If the bag is empty, user have won
        else {
            // Telling user that they have won using a Text display on screen
            TextShape textShape = new TextShape(0, 0, "You Win!");

            // Finding the height and width of the window and
            // the textShape object
            int wWidth = window.getGraphPanelWidth();
            int wHeight = window.getGraphPanelHeight();
            int tWidth = textShape.getWidth();
            int tHeight = textShape.getHeight();

            // Finding the center coordinates and relocate the textShape
            int centerWidth = (wWidth - tWidth) / 2;
            int centerHeight = (wHeight - tHeight) / 2;
            textShape.moveTo(centerWidth, centerHeight);

            // Add textShape to window, displaying to them that they have won
            window.addShape(textShape);
        }
    }


    /**
     * Called when the Quit button is pressed
     * Close the window that belongs to the quit button
     * 
     * @param button
     *            the button that signals Quit on a window
     */
    public void clickedQuit(Button button) {
        System.exit(0);
    }


    /**
     * Getter method for the Bag that is storing the Shapes
     * 
     * @return bag that contains Shapes
     */
    public SimpleBagInterface<Shape> getBag() {
        return bag;
    }


    /**
     * Getter method for the Window interface for the game
     * 
     * @return the Window object
     */
    public Window getWindow() {
        return window;
    }

}
