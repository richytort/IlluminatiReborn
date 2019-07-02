package ARK.Illuminati.exceptions;

@SuppressWarnings("serial")
/**
 * For unknown cards exceptions
 */
public class UnknownCardTypeException extends UnexpectedFormatException {

    String unknownType;

    /**
     * default
     */
    public UnknownCardTypeException() {
    }

    /**
     * unknown type exception
     * @param arg0 string
     */
    public UnknownCardTypeException(String arg0) {
        super(arg0);
    }

    /**
     * unknown card type
     * @param sFile file read in
     * @param sLine line specified
     * @param type string
     */
    public UnknownCardTypeException(String sFile, int sLine, String type) {
        super(sFile, sLine);
        this.unknownType = type;
    }

    /**
     * unknown card type
     * @param sFile file read in
     * @param sLine line specified
     * @param type string
     * @param message string
     */
    public UnknownCardTypeException(String message, String sFile, int sLine,
                                    String type) {
        super(message, sFile, sLine);
        this.unknownType = type;
    }

    /**
     * getter for unknown type
     * @return unknown type
     */
    public String getUnknownType() {
        return unknownType;
    }

}
