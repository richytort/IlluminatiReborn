package ARK.Illuminati.exceptions;

@SuppressWarnings("serial")
public class DefenseGroupAttackException extends RuntimeException{

    public DefenseGroupAttackException() {
    }

    public DefenseGroupAttackException(String message) {
        super(message);
    }
}
