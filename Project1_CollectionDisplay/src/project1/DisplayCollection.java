// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cuong Ngo (ngoct)

package project1;

import bag.Bag;
import bag.BagInterface;
import java.util.Random;

/**
 * This class contains the functionality to create a
 * randomly generated Bag
 * The content of the Bag created will always be random
 *
 * @author Cuong Ngo (ngoct)
 * @version 02/11/2020
 */
public class DisplayCollection {

    /**
     * Instance Array field that contains all the possible Objects
     */
    public static final String[] STRINGS = { "red circle", "blue circle",
        "red square", "blue square" };
    private BagInterface<String> itemBag;


    /**
     * Initializes a DisplayCollection object
     * that will contain a random bag
     */
    public DisplayCollection() {
        this.itemBag = new Bag<>();

        // Fill a random Bag with a random number of items
        Random random = new Random();
        int count = random.nextInt(11) + 5;

        for (int i = 0; i < count; i++) {
            int randIndex = random.nextInt(4);
            String randObject = STRINGS[randIndex];
            itemBag.add(randObject);
        }
    }


    /**
     * Getter method for the itemBag object
     * 
     * @return itemBag the bag in this class
     */
    public BagInterface<String> getItemBag() {
        return itemBag;
    }

}
