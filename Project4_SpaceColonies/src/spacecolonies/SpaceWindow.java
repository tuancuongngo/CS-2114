// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package spacecolonies;

import java.awt.Color;
import java.util.Arrays;
import CS2114.Button;
import CS2114.Shape;
import CS2114.TextShape;
import CS2114.Window;
import CS2114.WindowSide;
import CS2114.CircleShape;
import list.AList;

/**
 * The GUI class that the user interacts with
 * 
 * This class is responsible for the visualization of the applicants moving
 * through the line and being added to different Planets
 * 
 * @author Cuong Ngo (ngoct)
 * @version 04/02/2020
 */
public class SpaceWindow {

    /**
     * The starting x coordinate of the first Planet
     */
    public static final int START_PLANET_X = 100;

    /**
     * The starting x coordinate of the first Planet
     */
    public static final int START_PLANET_Y = 250;

    /**
     * The size of all Planets
     */
    public static final int PLANET_SIZE = 80;

    /**
     * The
     */
    public static final int PLANET_GAP = 50 + PLANET_SIZE;

    /**
     * The x coordinate for the CircleShape at the front of the Queue
     */
    public static final int CIRCLE_START_X = 100;

    /**
     * The y coordinate for all CircleShapes
     */
    public static final int CIRCLE_START_Y = 80;

    /**
     * The size of all CircleShapes that represent a Person object
     */
    public static final int CIRCLE_SIZE = 50;

    /**
     * The gap between 2 CircleShapes
     */
    public static final int CIRCLE_GAP = 5;

    /**
     * The x coordinate of the TextShape displaying information about the next
     * applicant in line
     */
    public static final int INFO_X = 10;

    /**
     * The y coordinate of the TextShape displaying information about the next
     * applicant in line
     */
    public static final int INFO_Y = 10;

    /**
     * Array that stores the Colors of Planets and Persons
     * in the order of No Preference, first Planet color, second Planet color,
     * third Planet color, and Planet filler color
     */
    private static Color[] shapeColor = { new Color(165, 209, 232), new Color(
        173, 147, 189), new Color(102, 176, 174), new Color(112, 148, 180),
        Color.YELLOW };

    /**
     * Array that stores the Colors of filled Planets
     */
    private static Color[] fillColors = { new Color(127, 96, 147), new Color(58,
        124, 122), new Color(49, 86, 119) };

    private ColonyCalculator colonyCalculator; // Colony Calculator object that
                                               // SpaceWindow will use
    private Window window;
    private CS2114.Button accept;
    private Button reject;
    private AList<CircleShape> personCircles;

    private Person[] personQ; // Array that stores all Person in queue
    private Planet[] planets; // Array that stores all available Planets
    private TextShape applicantInfo; // Displays the information about an
                                     // Applicant on screen of GUI


    /**
     * Default constructor that initializes GUI with Shapes and Buttons
     * 
     * @param calc
     *            the ColonyCalculator object to use for the GUI
     */
    public SpaceWindow(ColonyCalculator calc) {
        colonyCalculator = calc;

        planets = ColonyCalculator.getPlanets(); // Get the planets

        // Get the list of applicants and store in an Array
        Object[] persons = colonyCalculator.getQueue().toArray();
        personQ = Arrays.copyOf(persons, persons.length, Person[].class);

        // Make a new AList to store all the CircleShapes objects representing
        // Persons
        personCircles = new AList<CircleShape>();

        // Make a new Window
        window = new Window("Space Colony Placement");

        // Make the 'Accept' button
        accept = new Button("Accept");
        accept.onClick(this, "clickedAccept");
        window.addButton(accept, WindowSide.SOUTH);
        // Check the status of the "Accept" button, it will be disabled if first
        // Person doesn't qualify for any Planet
        buttonStatus();

        // Make the 'Reject' button
        reject = new Button("Reject");
        reject.onClick(this, "clickedReject");
        window.addButton(reject, WindowSide.SOUTH);

        // Populate GUI with Queue of CircleShapes and Planets
        populatePerson();
        createPlanets();
    }


    /**
     * Method that populate the GUI with CircleShape objects that represent
     * Persons based on person Queue
     */
    private void populatePerson() {

        // Clear array storing CircleShapes so that queue information can be
        // updated
        personCircles.clear();

        // Determine how many Persons are in Queue
        Object[] persons = colonyCalculator.getQueue().toArray();
        personQ = Arrays.copyOf(persons, persons.length, Person[].class);

        int numberOfShapes = personQ.length;
        String planetPref = "";

        int currentX = CIRCLE_START_X; // X coordinate at the start of Queue

        // For loop to create CircleShapes
        for (int i = 0; i < numberOfShapes; i++) {
            // Make a new CircleShape
            CircleShape current = new CircleShape(currentX, CIRCLE_START_Y
                - CIRCLE_SIZE, CIRCLE_SIZE);

            // Determine Color based on planetPreference
            planetPref = personQ[i].getPlanetName();

            boolean colored = false;
            for (int j = 1; j < planets.length; j++) {
                if (planetPref.equals(planets[j].getName())) {
                    current.setForegroundColor(shapeColor[j]);
                    colored = true;
                }
            }

            // No planet preference
            if (colored == false) {
                current.setForegroundColor(shapeColor[0]);
            }

            // Add current CircleShape to list
            personCircles.add(current);

            // Update x coordinate for Queue and add Circle to GUI
            currentX = currentX + CIRCLE_SIZE + CIRCLE_GAP;
            window.addShape(current);
        }

        // Call method to display information about the first person in the
        // Queue
        displayPerson();
    }


    /**
     * Displays information about the first person in the Queue
     */
    private void displayPerson() {
        // Will only update if the Person is in valid Range of Queue
        if (personCircles.getLength() != 0) {
            applicantInfo = new TextShape(INFO_X, INFO_Y, personQ[0]
                .toString());
            applicantInfo.setBackgroundColor(Color.WHITE);
            window.addShape(applicantInfo);
        }

    }


    /**
     * Creates the Planets and displays its fill status as well as the planet
     * information
     */
    public void createPlanets() {

        int numberOfPlanets = ColonyCalculator.NUM_PLANETS;
        int currX = START_PLANET_X; // Current x coordinate

        Shape currPlan;
        for (int i = 0; i < numberOfPlanets; i++) {
            // Current planet object
            currPlan = new Shape(currX, START_PLANET_Y - PLANET_SIZE,
                PLANET_SIZE, shapeColor[i + 1]);

            // Get information to create fillerPlanet to display Planet's
            // capacity visually
            Planet planetInfo = planets[i + 1];
            int fillStatus = (int)((1.0 * planetInfo.getPopulationSize()
                / planetInfo.getCapacity()) * PLANET_SIZE);

            // Set the dimensions of fillPlanet
            Shape fillerPlanet = new Shape(currX, START_PLANET_Y - fillStatus,
                PLANET_SIZE, fillStatus, fillColors[i]);

            // Display information about the Planet as texts
            displayPlanetInfo(currX, planetInfo);

            // Update x coordinate
            currX = currX + PLANET_GAP;

            // Add planets to window GUI
            window.addShape(currPlan);
            window.addShape(fillerPlanet);
            window.moveToFront(fillerPlanet);

        }

    }


    /**
     * Helper method to display information about a Planet underneath the Planet
     * on the GUI
     * 
     * @param xCoor
     *            the x coordinate of the TextShape
     * @param current
     *            the Planet to display information
     */
    private void displayPlanetInfo(int xCoor, Planet current) {

        StringBuilder str = new StringBuilder();

        // First line of Planet information
        str.append(current.getName());
        str.append(", ");
        str.append(current.getPopulationSize());
        str.append("/");
        str.append(current.getCapacity());

        // Adding the first line to window
        TextShape currInfo = new TextShape(xCoor, START_PLANET_Y, str
            .toString());
        currInfo.setBackgroundColor(Color.WHITE);
        window.addShape(currInfo);

        // Second line of Planet information
        str = new StringBuilder();
        str.append("A: ");
        str.append(current.getSkills().getAgriculture());
        str.append(", M: ");
        str.append(current.getSkills().getMedicine());
        str.append(", T: ");
        str.append(current.getSkills().getTechnology());

        // Adding the second line to window
        TextShape currInfo2 = new TextShape(xCoor, START_PLANET_Y + currInfo
            .getHeight(), str.toString());
        currInfo2.setBackgroundColor(Color.WHITE);
        window.addShape(currInfo2);
    }


    /**
     * Method that updates the status of the GUI
     * It is called whenever the "Accept" or "Reject" button is pressed
     */
    private void updateGUI() {

        // Remove all shapes to update GUI with new ones
        window.removeAllShapes();

        // If there are still Persons left in Queue
        if (personCircles.getLength() != 0) {
            populatePerson();
            createPlanets();
        }

        // Otherwise, tell user they are finished
        else {
            displayFinish();
        }

        buttonStatus(); // Update the status of the Buttons
    }


    /**
     * Checks the status of the Accept Button
     */
    private void buttonStatus() {
        // If there are still applicants in the Queue
        if (personCircles.getLength() != 0) {
            // Disable the "Accept" button if next Person can't be accepted into
            // any Planets
            if (colonyCalculator.getPlanetForPerson(personQ[0]) == null) {
                accept.disable();
            }

            // Enable "Accept" otherwise
            else {
                accept.enable();
            }
        }
    }


    /**
     * Display to user they have finished the simulation
     */
    private void displayFinish() {
        window.removeAllShapes();

        // No elements left in the queue
        // Disable both buttons
        accept.disable();
        reject.disable();

        // CHANGE TO NOT HARDCORDE
        TextShape fin = new TextShape(CIRCLE_START_X + 50, CIRCLE_START_Y + 50,
            "All Applicants Processed - Good Work!");
        fin.setBackgroundColor(Color.WHITE);

        // Display finished message to user
        window.addShape(fin);
    }


    /**
     * Determine what happens when user clicked on Accept
     * Add the person in the front of the Queue to their desired Planet
     * and remove them from the Queue
     * 
     * @param accept
     *            the button that was clicked on (Accept button)
     */
    public void clickedAccept(Button accept) {
        // If the Person has been accepted, remove that Person from Window
        colonyCalculator.accept();
        window.removeShape(personCircles.remove(0));
        updateGUI();

    }


    /**
     * Determine what happens when user clicked on Reject
     * Add the person in the front of the Queue to the rejected Queue
     * and remove them from the Queue
     * 
     * @param reject
     *            the button that was clicked on (Reject button)
     */
    public void clickedReject(Button reject) {
        if (!personCircles.isEmpty()) {
            colonyCalculator.reject();
            window.removeShape(personCircles.remove(0));
            updateGUI();
        }
    }
}
