package ARK.Illuminati.exceptions;

@SuppressWarnings("serial")
/**
 * This class creates an exception for wrong actions
 */
public class WrongActionException extends RuntimeException{

    /**
     * Wrong Action Exception
     */
    public WrongActionException() {
    }

    /**
     * Wrong Action Exception
     * @param message string
     */
    public WrongActionException(String message) {
        super(message);
    }

}
