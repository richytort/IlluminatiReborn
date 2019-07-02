package ARK.Illuminati.exceptions;

@SuppressWarnings("serial")
public class MissingFieldException extends UnexpectedFormatException{

    /**
     * default
     */
    public MissingFieldException() {
    }

    /**'
     * overloaded exception
     * @param arg0 string
     */
    public MissingFieldException(String arg0) {
        super(arg0);
    }

    /**
     * Missing field exception
     * @param sFile file parameter
     * @param sLine int line amount
     */
    public MissingFieldException(String sFile, int sLine) {
        super(sFile, sLine);
    }

    /**
     * Missing Field exception
     * @param message message display
     * @param sFile file input
     * @param sLine line int
     */
    public MissingFieldException(String message, String sFile, int sLine) {
        super(message, sFile, sLine);
    }
}
