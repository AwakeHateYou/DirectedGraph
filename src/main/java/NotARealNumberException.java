/**
 * Created by etere on 13.12.2015.
 */
public class NotARealNumberException extends Exception {
    /** String with error message. */
    private static final String ERROR_MESSAGE = "Must be positive value of all numbers.";

    /**
     * Default constructor.
     * Sets exception message.
     */
    public NotARealNumberException(){
        super(ERROR_MESSAGE);
    }
}
