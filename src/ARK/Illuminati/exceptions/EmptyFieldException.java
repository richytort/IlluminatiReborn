package ARK.Illuminati.exceptions;

@SuppressWarnings("serial")
/**
 * this class looks at empty field
 */
public class EmptyFieldException extends UnexpectedFormatException{
    int sourceField;

    /**
     * default exception
     */
    public EmptyFieldException() {
    }

    /**
     * overloaded exception
     * @param arg0 string value
     */
    public EmptyFieldException(String arg0) {
        super(arg0);
    }

    /**
     * Exception for empty field
     */
    public EmptyFieldException(String sFile, int sLine, int sField) {
        super(sFile, sLine);
        this.sourceField = sField;
    }
    /**
     * Exception for empty field
     */
    public EmptyFieldException(String message, String sFile, int sLine, int sField) {
        super(message, sFile, sLine);
        this.sourceField = sField;
    }

    /**
     * getter for source field
     * @return sourceField
     */
    public int getSourceField() {
        return sourceField;
    }

    /**
     * setter for Source field
     * @param sourceField int
     */
    public void setSourceField(int sourceField) {
        this.sourceField = sourceField;
    }
}
