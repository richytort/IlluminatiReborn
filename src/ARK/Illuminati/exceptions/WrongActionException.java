package ARK.Illuminati.exceptions;

@SuppressWarnings("serial")
public class WrongActionException extends RuntimeException{

    public WrongActionException() {
    }

    public WrongActionException(String message) {
        super(message);
    }

}
