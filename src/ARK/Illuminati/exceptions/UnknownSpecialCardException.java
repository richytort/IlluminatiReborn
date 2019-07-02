package ARK.Illuminati.exceptions;

@SuppressWarnings("serial")
/**
 * This class deals with exceptions unknown special cards.
 */
public class UnknownSpecialCardException extends UnexpectedFormatException{
    String unknownSpecial ;

    /**
     * Unknown special card exception
     */
    public UnknownSpecialCardException() {
    }

    /**
     * Unknown special card exception
     * @param arg0 string
     */
    public UnknownSpecialCardException(String arg0) {
        super(arg0);
    }

    /**
     * Unknown special card exception
     * @param sFile file
     * @param sLine line
     */
    public UnknownSpecialCardException(String sFile, int sLine, String spell) {
        super(sFile, sLine);
        this.unknownSpecial = spell;
    }

    /**
     * Unknown special card exception
     * @param sFile file
     * @param sLine line
     */
    public UnknownSpecialCardException(String message, String sFile, int sLine, String spell) {
        super(message, sFile, sLine);
        this.unknownSpecial = spell;
    }

    /**
     * @return unknown special
     */
    public String getUnknownSpell() {
        return unknownSpecial;
    }

    /**
     * @param unknownSpell setter for unknown special
     */
    public void setUnknownSpell(String unknownSpell) {
        this.unknownSpecial = unknownSpell;
    }

}
