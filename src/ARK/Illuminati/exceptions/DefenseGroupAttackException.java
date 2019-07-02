package ARK.Illuminati.exceptions;

@SuppressWarnings("serial")
public class DefenseGroupAttackException extends RuntimeException{

    /**
     * Defenser exception for group attack
     */
    public DefenseGroupAttackException() {
    }

    public DefenseGroupAttackException(String message) {
        super(message);
    }
}
