package by.bsuir.karamach.app.loader;

public class LoaderException extends Exception {
    private static final long serialVersionUID = -108668838340290506L;

    public LoaderException() {
        super();
    }

    public LoaderException(String message) {
        super(message);
    }

    public LoaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoaderException(Throwable cause) {
        super(cause);
    }
}
