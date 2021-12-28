// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package spacecolonies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import bsh.ParseException;

/**
 * This class parses the input data from two text files. It generates the
 * planets and queue of applicants based on one file of comma separated values
 * about applicants and the other about each planet. Then it gives SpaceWindow
 * this queue in order to tie everything together.
 * 
 * @author Cuong Ngo (ngoct)
 * @version 04/02/2020
 */
public class ColonyReader {

    private Planet[] planets;
    private ArrayQueue<Person> queue;
    private ColonyCalculator newCol;
    private Scanner file;


    /**
     * Constructor that reads in the input files and passes them to their
     * appropriate methods
     * 
     * @param applicantFileName
     *            the applicant input file
     * @param planetFileName
     *            the planet input file
     * @throws SpaceColonyDataException
     *             if input files contain errors
     * @throws FileNotFoundException
     *             if File is not found
     * @throws ParseException
     *             if an error is encountered during parsing
     */
    public ColonyReader(String applicantFileName, String planetFileName)
        throws FileNotFoundException,
        SpaceColonyDataException,
        ParseException {

        // Send the applicant input file to readQueueFile to populate an
        // ArrayQueue<Person> for the colony
        readQueueFile(applicantFileName);

        // Send the planet input file to readPlanetFile to populate an array of
        // planets for the colony.
        readPlanetFile(planetFileName);

        // Create the new Colony
        newCol = new ColonyCalculator(queue, planets);

        // Instantiate a new SpaceWindow with the recently filled colony as its
        // parameter.
        SpaceWindow window = new SpaceWindow(newCol);
    }


    /**
     * Processes the input file of Planets and store them in a Planet array
     * 
     * @param fileName
     *            name of Planet input file
     * @return a Planet array containing all the Planets from input file
     * @throws FileNotFoundException
     *             if the input file cannot be found
     * @throws ParseException
     *             if encounters an error during parsing
     * @throws SpaceColonyDataException
     *             if there are less than 3 Planets in the input file
     */
    private Planet[] readPlanetFile(String fileName)
        throws FileNotFoundException,
        ParseException,
        SpaceColonyDataException {

        planets = new Planet[ColonyCalculator.NUM_PLANETS + 1];
        file = new Scanner(new File(fileName));
        String[] data;

        // For loop to get the first 3 planets from input file
        for (int i = 1; i <= ColonyCalculator.NUM_PLANETS; i++) {
            if (file.hasNextLine()) {
                String currentLine = file.nextLine();
                data = currentLine.split(", *");

                // If there are not 5 comma separated values on the line, then
                // the size of the array that stores the planet info will be
                // less than 5
                if (data.length != 5) {
                    throw new ParseException();
                }

                // If the skills are not between Skills range
                if (!isInSkillRange(Integer.valueOf(data[1]), Integer.valueOf(
                    data[2]), Integer.valueOf(data[3]))) {
                    throw new SpaceColonyDataException("Skill is not between "
                        + ColonyCalculator.MIN_SKILL_LEVEL + " and "
                        + ColonyCalculator.MAX_SKILL_LEVEL);
                }

                // Add new Planet to array of planets
                planets[i] = new Planet(data[0], Integer.valueOf(data[1]),
                    Integer.valueOf(data[2]), Integer.valueOf(data[3]), Integer
                        .valueOf(data[4]));
            }

            // If there are less than 3 planets,
            else {
                throw new SpaceColonyDataException(
                    "Invalid input, there are less than "
                        + ColonyCalculator.NUM_PLANETS + " planets");
            }
        }

        file.close(); // Close Scanner

        return planets;
    }


    /**
     * Processes the input file of applicants and store them in a ArrayQueue of
     * Persons
     * 
     * @param fileName
     *            name of the applicant input file
     * @return an ArrayQueue containing Person objects from the input file
     * @throws SpaceColonyDataException
     *             if a Person doesn't have valid Skills in the input file
     * @throws FileNotFoundException
     *             if the input file cannot be found
     */
    private ArrayQueue<Person> readQueueFile(String fileName)
        throws SpaceColonyDataException,
        FileNotFoundException {
        queue = new ArrayQueue<Person>();

        file = new Scanner(new File(fileName));
        String[] data;

        while (file.hasNextLine()) {
            String currentLine = file.nextLine();
            data = currentLine.split(", *");

            // If the skills are not between Skills range
            if (!isInSkillRange(Integer.valueOf(data[1]), Integer.valueOf(
                data[2]), Integer.valueOf(data[3]))) {
                throw new SpaceColonyDataException("Skill is not between "
                    + ColonyCalculator.MIN_SKILL_LEVEL + " and "
                    + ColonyCalculator.MAX_SKILL_LEVEL);
            }

            String planetPref = "";

            // Check if there is a preference in the array
            if (data.length == 5) {
                // If they don't have a planet Preference
                if (data[4].equals("Nowhere")) {
                    planetPref = "";
                }

                // If they do have a planet preference
                else {
                    planetPref = data[4];
                }

            }

            // Add new Person to person Queue
            queue.enqueue(new Person(data[0], Integer.valueOf(data[1]), Integer
                .valueOf(data[2]), Integer.valueOf(data[3]), planetPref));
        }
        file.close(); // Close Scanner
        return queue;
    }


    /**
     * Method that returns whether or not all of the integers it is
     * passed (num1, num2, and num3) are between the minimum and maximum
     * possible values for a Skill
     * 
     * @param num1
     *            the agriculture Skills of the object
     * @param num2
     *            the medicine Skills of the object
     * @param num3
     *            the technology Skills of the object
     * @return true if the 3 Skills are within the Skills range
     */
    private boolean isInSkillRange(int num1, int num2, int num3) {

        Skills min = new Skills(ColonyCalculator.MIN_SKILL_LEVEL,
            ColonyCalculator.MIN_SKILL_LEVEL, ColonyCalculator.MIN_SKILL_LEVEL);
        Skills max = new Skills(ColonyCalculator.MAX_SKILL_LEVEL,
            ColonyCalculator.MAX_SKILL_LEVEL, ColonyCalculator.MAX_SKILL_LEVEL);

        Skills currSkills = new Skills(num1, num2, num3);

        return currSkills.isBelow(max) && min.isBelow(currSkills);
    }
}
