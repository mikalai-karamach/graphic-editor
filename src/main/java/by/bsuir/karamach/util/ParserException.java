package by.bsuir.karamach.util;

public class ParserException extends Exception {
    private static final long serialVersionUID = 543238034721058516L;

    public ParserException() {
        super();
    }

    public ParserException(String message) {
        super(message);
    }

    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParserException(Throwable cause) {
        super(cause);
    }
}
