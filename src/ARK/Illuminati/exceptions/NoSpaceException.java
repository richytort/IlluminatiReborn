package ARK.Illuminati.exceptions;

@SuppressWarnings("serial")
/**
 * No space exception
 */
public class NoSpaceException extends RuntimeException{

    /**
     * no space exception
     */
    public NoSpaceException() {
    }

    /**
     * no space
     * @param arg0 of type string
     */
    public NoSpaceException(String arg0) {
        super(arg0);
    }
}
