/**
 * @author Robert Simionescu
 * @version Milestone 4
 *
 * Get the String representations of the "Jail" and "Go to Jail" square
 */
public class LocalizationHandler
{
    private static final String JAIL = "Jail";
    private static final String GO_TO_JAIL = "Go to Jail";

    /**
     * @author Robert Simionescu
     * Get the name for "Go to Jail" in the appropriate language
     *
     * @return      a String representation of the "Go To Jail" square
     */
    public static String getGoToJailName()
    {
        return GO_TO_JAIL;
    }

    /**
     * @author Robert Simionescu
     * Get the name for "Jail" in the appropriate language
     *
     * @return      a String representation of the "Jail" square
     */
    public static String getJailName()
    {
        return JAIL;
    }
}
