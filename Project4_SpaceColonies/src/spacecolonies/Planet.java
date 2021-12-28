// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package spacecolonies;

import java.util.Arrays;

/**
 * Class that reprensents Planet objects that can store a certain amount of
 * Person objects who qualifies
 * 
 * @author Cuong Ngo (ngoct)
 * @version 04/02/2020
 */
public class Planet implements Comparable<Planet> {

    private String name;
    private Skills skills;
    private Person[] population;
    private int populationSize;
    private final int capacity;


    /**
     * Constructor that creates a Planet objecting based on attributes specified
     * from user
     * 
     * @param planetName
     *            name of the planet
     * @param planetAgri
     *            the minimum Agriculture requirement
     * @param planetMedi
     *            the minimum Medicine requirement
     * @param planetTech
     *            the minimum Technology requirement
     * @param planetCap
     *            the maximum capacity of that planet
     */
    public Planet(
        String planetName,
        int planetAgri,
        int planetMedi,
        int planetTech,
        int planetCap) {
        name = planetName;
        skills = new Skills(planetAgri, planetMedi, planetTech);
        capacity = planetCap;
        population = new Person[capacity];
    }


    /**
     * Takes in a String object to set the name of the planet
     * 
     * @param name
     *            the name of the planet to change to
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Get the name of the planet
     * 
     * @return the String object containing planetName
     */
    public String getName() {
        return name;
    }


    /**
     * Get the Skills requirement for the planet as a Skills object
     * 
     * @return the Planet Skills as a Skills object
     */
    public Skills getSkills() {
        return skills;
    }


    /**
     * Get the details about the current population of the Planet in an array
     * containing Person objects
     * 
     * @return the array containing all Person objects on that planet
     */
    public Person[] getPopulation() {
        return population;
    }


    /**
     * Get the population size of that planet
     * 
     * @return an integer representing how many people are on that planet
     */
    public int getPopulationSize() {
        return populationSize;
    }


    /**
     * Get the maximum capacity of that planet
     * 
     * @return the maximum amount of Person objects the specified planet can
     *         store
     */
    public int getCapacity() {
        return capacity;
    }


    /**
     * Get the number of open space left on the Planet
     * 
     * @return the number of spaces available on the Planet
     */
    public int getAvailability() {
        return capacity - populationSize;
    }


    /**
     * Method that determines if Planet's capacity is full
     * 
     * @return True if the planet’s population has reached max capacity.
     */
    public boolean isFull() {
        return populationSize == capacity;
    }


    /**
     * Attempt to add a Person to the Planet. A Person will be added if the
     * colony has available space for this applicant and if the applicant is
     * qualified to live on the colony.
     * 
     * @param newbie
     *            the Person object to attempt to add to the Planet
     * @return whether or not that Person was added successfully
     */
    public boolean addPerson(Person newbie) {
        boolean added = false;

        // Add Person to planet if there is space on the planet and the person
        // is qualified
        if (!isFull() && isQualified(newbie)) {
            population[populationSize] = newbie;
            populationSize++;
            added = true;
        }

        return added;
    }


    /**
     * Determines if the Person meets the minimum Skills requirement to qualify
     * to be added to the Planet
     * 
     * @param person
     *            the Person to check for qualifications
     * @return whether or not that Person meets minimum Skills requirement
     */
    public boolean isQualified(Person person) {
        return skills.isBelow(person.getSkills());
    }


    /**
     * Returns all information about a Planet object in a String
     * 
     * @return a String containings all information about the Planet object
     */
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append(name);
        str.append(", population ");
        str.append(populationSize);
        str.append(" (cap: ");
        str.append(capacity);
        str.append("), Requires: A >= ");

        str.append(skills.getAgriculture());
        str.append(", M >= ");
        str.append(skills.getMedicine());
        str.append(", T >= ");
        str.append(skills.getTechnology());

        return str.toString();
    }


    /**
     * Compare 2 Planet objects to see if they are the same
     * 
     * @param obj
     *            the Object to compare to
     * @return True if all 5 input fields are equal and populationSize is equal.
     */
    public boolean equals(Object obj) {
        // Compare if they have the same reference
        if (obj == this) {
            return true;
        }

        // If the Object to compare to doesn't exist
        if (obj == null) {
            return false;
        }

        // If they are the same class
        if (this.getClass() == obj.getClass()) {
            Planet otherP = (Planet)obj;

            boolean samePop = Arrays.equals(getPopulation(), otherP
                .getPopulation());
            boolean sameSkills = skills.equals(otherP.getSkills());
            boolean sameName = name.equals(otherP.getName());
            boolean samePopSize = populationSize == otherP.populationSize;
            boolean sameCapacity = getCapacity() == otherP.getCapacity();

            // Determine if their attributes are equal
            return sameName && samePopSize && sameCapacity && sameSkills
                && samePop;
        }

        // If the objects are from different classes
        return false;
    }


    /**
     * Compare 2 planet objects based on availability
     * 
     * @param other
     *            the Other planet object to compare to
     * @return an integer depending on availability of the 2 Planets being
     *         compared
     */
    public int compareTo(Planet other) {

        // Planets are equal in availability
        int result = 0;

        // this Planet has more available space
        if (getAvailability() > other.getAvailability()) {
            result = 1;
        }

        // The other Planet has more available space
        if (getAvailability() < other.getAvailability()) {
            result = -1;
        }

        return result;

    }

}
