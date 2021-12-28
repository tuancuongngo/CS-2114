// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package spacecolonies;

/**
 * Objects that represent Persons with names, attributes and their planet
 * preferences
 * 
 * @author Cuong Ngo (ngoct)
 * @version 04/02/2020
 */
public class Person {

    private String name;
    private Skills skills;
    private String planetPreference;


    /**
     * Constructor that sets up a Person object, which has a name, Skills set,
     * and planet preference
     * 
     * @param name
     *            the Person's name
     * @param agri
     *            their agriculture attribute
     * @param medi
     *            their Medicine attribute
     * @param tech
     *            their Technology attribute
     * @param planet
     *            their planet preference
     */
    public Person(String name, int agri, int medi, int tech, String planet) {
        this.name = name;
        skills = new Skills(agri, medi, tech);
        planetPreference = planet;
    }


    /**
     * Get the name of the Person object
     * 
     * @return the name as a String
     */
    public String getName() {
        return name;
    }


    /**
     * Get the Skills of the Person object
     * 
     * @return the Skills of that Person
     */
    public Skills getSkills() {
        return skills;
    }


    /**
     * Get the Person's planet preference
     * 
     * @return the planet reference of that Person
     */
    public String getPlanetName() {
        return planetPreference;
    }


    /**
     * Get the details of a Person object as a String object
     * 
     * @return String representation of details of a Person
     */
    public String toString() {
        StringBuilder str = new StringBuilder();

        // If the Person doesn't have a planetPreference
        if (planetPreference.length() < 1) {
            str.append("No-Planet ");
        }

        str.append(name);
        str.append(" ");
        str.append(skills.toString());

        // If the Person has a planetPreference
        if (planetPreference.length() >= 1) {
            str.append(" Wants: ");
            str.append(planetPreference);
        }

        return str.toString();
    }


    /**
     * Compare 2 Person objects to see whether they have equal attributes
     * 
     * @param obj
     *            the object to compare to
     * @return True if 2 Persons name, skills, and planet preference values are
     *         the same.
     */
    public boolean equals(Object obj) {

        // Compare if they have the same reference
        if (obj == this) {
            return true;
        }

        // If the Person to compare to doesn't exist
        if (obj == null) {
            return false;
        }

        // If they are the same class
        if (this.getClass() == obj.getClass()) {
            Person other = (Person)obj;

            // Compare their attributes
            return this.name.equals(other.name) && this.skills.equals(
                other.skills) && this.planetPreference.equals(
                    other.planetPreference);
        }

        // If objects are different classes
        return false;

    }

}
