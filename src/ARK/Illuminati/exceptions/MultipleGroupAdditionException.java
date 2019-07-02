package ARK.Illuminati.exceptions;

/**
 * for multiple group exceptions
 */
public class MultipleGroupAdditionException extends RuntimeException {

    /**
     * empty exception
     */
    public MultipleGroupAdditionException(){
        }
        public MultipleGroupAdditionException(String message) {
            super(message);
        }

    }

