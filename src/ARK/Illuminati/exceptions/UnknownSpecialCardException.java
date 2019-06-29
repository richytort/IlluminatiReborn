package ARK.Illuminati.exceptions;

@SuppressWarnings("serial")
public class UnknownSpecialCardException extends UnexpectedFormatException{
    String unknownSpecial ;

    public UnknownSpecialCardException() {
    }

    public UnknownSpecialCardException(String arg0) {
        super(arg0);
    }

    public UnknownSpecialCardException(String sFile, int sLine, String spell) {
        super(sFile, sLine);
        this.unknownSpecial = spell;
    }

    public UnknownSpecialCardException(String message, String sFile, int sLine, String spell) {
        super(message, sFile, sLine);
        this.unknownSpecial = spell;
    }

    public String getUnknownSpell() {
        return unknownSpecial;
    }

    public void setUnknownSpell(String unknownSpell) {
        this.unknownSpecial = unknownSpell;
    }

}
