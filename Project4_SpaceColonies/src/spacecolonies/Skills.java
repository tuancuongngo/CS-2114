// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package spacecolonies;

/**
 * Class that contains attributes for a Person object, such as their
 * agriculture, medicine, and technology skills
 * 
 * @author Cuong Ngo (ngoct)
 * @version 04/02/2020
 */
public class Skills {

    private int agriculture;
    private int medicine;
    private int technology;


    /**
     * Constructor that takes in the integer for each Skills,
     * ranging from 1 to 5
     * 
     * @param ag
     *            the agriculture Skill
     * @param med
     *            the medicine Skill
     * @param tech
     *            the technology Skill
     */
    public Skills(int ag, int med, int tech) {
        agriculture = ag;
        medicine = med;
        technology = tech;
    }


    /**
     * Compare a given Skill "other" to "this" Skill
     * Used to check if a person's skill set is accepted based on a planet's
     * minimum required skillset.
     * 
     * @param other
     *            the other Skills object to compare to
     * 
     * @return True only if "this.agriculture" is less or equal to
     *         "other.agriculture" AND "this.medicine" is less or equal to
     *         "other.medicine" AND "this.technology" is less or equal to
     *         "other.technology".
     */
    public boolean isBelow(Skills other) {
        return ((this.agriculture <= other.agriculture)
            && (this.medicine <= other.medicine)
            && (this.technology <= other.technology));
    }


    /**
     * Compare 2 Skills objects to see whether they have equal attributes
     * 
     * @param obj
     *            the other object to compare to
     * @return True if for both objects, all three fields: agriculture,
     *         medicine, and technology, are equal.
     */
    public boolean equals(Object obj) {

        // Compare if they have the same reference
        if (obj == this) {
            return true;
        }

        // If the Skills to compare to doesn't exist
        if (obj == null) {
            return false;
        }

        // If they are the same class
        if (this.getClass() == obj.getClass()) {
            Skills other = (Skills)obj;

            // Compare their attributes
            return (this.agriculture == other.agriculture)
                && (this.medicine == other.medicine)
                && (this.technology == other.technology);
        }

        // If objects are different classes
        return false;

    }


    /**
     * Get the Agriculture attribute
     * 
     * @return the integer for Agriculture attribute
     */
    public int getAgriculture() {
        return agriculture;
    }


    /**
     * Get the Medicine attribute
     * 
     * @return the integer for Medicine attribute
     */
    public int getMedicine() {
        return medicine;
    }


    /**
     * Get the Technology attribute
     * 
     * @return the integer for Technology attribute
     */
    public int getTechnology() {
        return technology;
    }


    /**
     * Get the String representation of the 3 attributes of Skills
     * 
     * @return a String containing the agriculture, medicine, and technology
     *         attributes
     */
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("A:");
        str.append(agriculture);
        str.append(" M:");
        str.append(medicine);
        str.append(" T:");
        str.append(technology);

        return str.toString();
    }
}
