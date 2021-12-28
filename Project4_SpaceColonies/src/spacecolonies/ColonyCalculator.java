// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package spacecolonies;

import java.util.Arrays;
import list.AList;

/**
 * An object of this class handles all the major calculations and
 * decision-making for the program. It is in charge of handling accept and
 * reject instructions and checking that all requirements for a person are met
 * before they are added to a planet. It works together with SpaceWindow.
 * 
 * @author Cuong Ngo (ngoct)
 * @version 04/02/2020
 */
public class ColonyCalculator {

    private ArrayQueue<Person> applicantQueue;
    private AList<Person> rejectBus;

    /**
     * The number of Planets available
     */
    public static final int NUM_PLANETS = 3;

    /**
     * The minimum Skill requirement
     */
    public static final int MIN_SKILL_LEVEL = 1;

    /**
     * The maximum Skill requirement
     */
    public static final int MAX_SKILL_LEVEL = 5;

    /**
     * The array containing the Planets
     */
    private static Planet[] planets = new Planet[NUM_PLANETS + 1];


    /**
     * Constructor that initializes the calculation process
     * 
     * @param person
     *            the Queue containing all applicants of Person objects
     * @param planets
     *            the Planets available for the applicants
     * @throws IllegalArgumentException
     *             if the input ArrayQueue is null.
     */
    public ColonyCalculator(ArrayQueue<Person> person, Planet[] planets) {
        // If the input array is null
        if (person == null) {
            throw new IllegalArgumentException();
        }

        // ArrayList of all Persons that will be rejected
        rejectBus = new AList<Person>();

        // The queue of Person objects that need to be put in a planet
        applicantQueue = person;

        // The list of available planets
        ColonyCalculator.planets = planets;
    }


    /**
     * Attempt to accept an applicant to their desired/qualified Planet
     * 
     * @return True if applicant was added to the planet
     */
    public boolean accept() {

        // Make sure applicant line isn't empty
        if (!applicantQueue.isEmpty()) {
            Planet suggested = getPlanetForPerson(applicantQueue.getFront());

            // If Applicant doesn't qualify for any planets
            if (suggested == null) {
                return false;
            }

            // Add applicant to suggested planet and remove them from Queue
            suggested.addPerson(applicantQueue.dequeue());
            return true;
        }

        return false;
    }


    /**
     * This method removes the next applicant in line and add them to an
     * AList<Person>, the bus that takes them to skill training school.
     */
    public void reject() {
        rejectBus.add(applicantQueue.dequeue());
    }


    /**
     * Finds the suitable Planet for the Person object based on their Skills and
     * planet preference
     * 
     * @param nextPerson
     *            the Person to find the Planet for
     * @return the Planet that the Person qualifies for, or null if they don't
     *         qualify for any Planet
     */
    public Planet getPlanetForPerson(Person nextPerson) {
        // The object that will store the suitable Planet
        Planet pref = null;

        if (nextPerson == null) {
            return null;
        }

        // Make sure there are Persons in queue
        if (!applicantQueue.isEmpty()) {

            // If the Person doesn't have a planet Preference
            if (getPlanetIndex(nextPerson.getPlanetName()) == 0) {
                pref = getMostAvailablePlanet(nextPerson);
            }

            // If the Person has a planet Preference
            else {
                pref = getPreferredPlanet(nextPerson, getPlanetIndex(nextPerson
                    .getPlanetName()));
            }

        }

        // return the planet
        return pref;
    }


    /**
     * Find the Planet object for the given number (1, 2, or 3).
     * For any other number, return null.
     * 
     * @param planetNo
     *            the index of the Planet in array
     * @return the corresponding Planet object. Otherwise, return null
     */
    public Planet planetByNumber(int planetNo) {
        if (planetNo < 1 || planetNo > 3) {
            return null;
        }

        return planets[planetNo];
    }


    /**
     * Return the int representation for the given String (planet name).
     * If the given String is not a name for any of the three planets, return 0.
     * 
     * @param planetName
     *            name of the Planet to look for int representation
     * @return int representation if there is one. Otherwise, return 0.
     */
    public int getPlanetIndex(String planetName) {
        // Navigate through array to find int representation
        for (int i = 1; i <= NUM_PLANETS; i++) {
            if (planets[i].getName().equals(planetName)) {
                return i;
            }
        }
        return 0;
    }


    /**
     * Get the ArrayQueue that contains the list of applicants
     * 
     * @return the ArrayQueue objects that contains the list of applicants
     */
    public ArrayQueue<Person> getQueue() {
        return applicantQueue;
    }


    /**
     * Get the array containing all the available Planets
     * 
     * @return the array containing all available Planets
     */
    public static Planet[] getPlanets() {
        return planets;
    }


    /**
     * Private helper method that will determine whether or not the Person
     * object qualifies for their desired Planet
     * 
     * @param applicant
     *            the Person to find the Planet for
     * @param planetNo
     *            the position of the Planet that they want to be on, as a
     *            number in the Planet array
     * @return
     */
    private Planet getPreferredPlanet(Person applicant, int planetNo) {

        Planet pref = planets[planetNo];

        // Determine if there is space on preferred planet and if applicant
        // satisfies minimum Skills requirements
        if (pref.getAvailability() > 0 && pref.isQualified(applicant)) {

            return pref;

        }
        // If the person is not qualified or the planet is full
        return null;
    }


    /**
     * If the Person does not have a Planet preference, this method will attempt
     * to find a Planet that they most qualify for
     * 
     * @param applicant
     *            the Person to find a planet for
     * @return the most suitable Planet for that person
     */
    private Planet getMostAvailablePlanet(Person applicant) {

        // Make a copy of planets and sort it absed on availability
        Planet[] sortedPlanets = Arrays.copyOfRange(planets, 1, planets.length);

        Arrays.sort(sortedPlanets);

        Planet current;

        for (int i = sortedPlanets.length - 1; i >= 0; i--) {
            current = sortedPlanets[i];

            // Determine if there is space on preferred planet and if applicant
            // satisfies minimum Skills requirements
            if (current.getAvailability() > 0 && current.isQualified(
                applicant)) {
                return current;

            }
        }

        // If applicant does not qualify for any planets
        return null;
    }

}
