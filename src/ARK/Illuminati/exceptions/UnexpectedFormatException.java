package ARK.Illuminati.exceptions;

@SuppressWarnings("serial")
/**
 * Unexpected format exceptions
 */
public class UnexpectedFormatException extends Exception {

    String sourceFile;
    int sourceLine;

    /**
     * empty- deault
     */
    public UnexpectedFormatException() {
    }

    /**
     * overloaded string exception
     * @param arg0 string
     */
    public UnexpectedFormatException(String arg0) {
        super(arg0);
    }

    /**
     * unexpected format exceptionn
     * @param sFile file read in
     * @param sLine line
     */
    public UnexpectedFormatException(String sFile, int sLine) {
        this.sourceFile = sFile;
        this.sourceLine = sLine;
    }

    /**
     * unexpected format exceptionn
     * @param sFile file read in
     * @param sLine line
     */
    public UnexpectedFormatException(String message, String sFile, int sLine) {
        super(message);
        this.sourceFile = sFile;
        this.sourceLine = sLine;
    }

    /**
     * getter for source file
     * @return source file
     */

    public String getSourceFile() {
        return sourceFile;
    }

    /**
     * setter for source file
     * @param sourceFile
     */
    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    /**
     * getter for source line
     * @return sourceLine
     */
    public int getSourceLine() {
        return sourceLine;
    }

    /**
     * setterfor Source line
     * @param sourceLine
     */
    public void setSourceLine(int sourceLine) {
        this.sourceLine = sourceLine;
    }
}
