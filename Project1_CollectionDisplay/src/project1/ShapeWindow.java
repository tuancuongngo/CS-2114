// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)
package project1;

import bag.BagInterface;
import CS2114.TextShape;
import java.awt.Color;
import CS2114.Window;
import CS2114.Button;
import CS2114.WindowSide;

/**
 * The class that contains the Graphical User Interface
 * (GUI) functionality for this project.
 * 
 * @author Cuong Ngo (ngoct)
 * @version 02/11/2020
 */
public class ShapeWindow {

    private Window window;
    private TextShape textShape;
    private Button quitButton;
    private Button chooseButton;
    private BagInterface<String> itemBag;


    /**
     * Initializes a ShapeWindow object
     * and its necessary fields and buttons
     * 
     * @param itemBag
     *            contains the information about the shapes
     *            stored in the bag
     */
    public ShapeWindow(BagInterface<String> itemBag) {
        window = new Window();
        window.setTitle("Project 1");

        this.itemBag = itemBag;

        // Adding the quit button and its function to Window
        quitButton = new Button("Quit");
        quitButton.onClick(this, "clickedQuit");
        window.addButton(quitButton, WindowSide.NORTH);

        // Adding the choose button and its function to Window
        chooseButton = new Button("Choose");
        chooseButton.onClick(this, "clickedChoose");
        window.addButton(chooseButton, WindowSide.SOUTH);

        // Add the textShape object to Window
        textShape = new TextShape(0, 0, "");
        window.addShape(textShape);

    }


    /**
     * Called when the Quit button is pressed
     * Close the window that belongs to the quit button
     * 
     * @param quitButton
     *            the button that signals Quit on a window
     */
    public void clickedQuit(Button quitButton) {
        System.exit(0);
    }


    /**
     * Called when the Choose button is pressed
     * Choose a textShape to display to the Window screen
     * 
     * @param chooseButton
     *            when pressed, will choose a textShape object
     */
    public void clickedChoose(Button chooseButton) {
        // Keep getting a random textShape from the Bag
        if (!itemBag.isEmpty()) {
            String toDisplay = itemBag.remove();
            textShape.setText(toDisplay);
            colorText();
            centerText();
        }

        // Displays to user that no items remain
        if (itemBag.isEmpty()) {
            String toDisplay = "No more items.";
            textShape.setText(toDisplay);
            colorText();
            centerText();
        }
    }


    /**
     * Changes the text color that will be displayed
     * to either blue or red or default black, depending
     * on the object being displayed
     */
    private void colorText() {
        // Get the information about the textShape and determine
        // color of the text
        String color = textShape.getText();

        if (color.contains("red")) {
            textShape.setForegroundColor(Color.RED);
        }

        else if (color.contains("blue")) {
            textShape.setForegroundColor(Color.BLUE);
        }

        else {
            textShape.setForegroundColor(Color.BLACK);
        }

    }


    /**
     * Find the coordinate to relocate the
     * textShape object to the center of Window
     */
    private void centerText() {
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
    }

}
