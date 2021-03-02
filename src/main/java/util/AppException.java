package util;

/**
 * exception class to catch Connection troubles and warnings
 */
public class AppException extends RuntimeException {

    public AppException() {
    }

    public AppException(String message) {
        super("Custom Exception" + message);
    }

    public AppException(String message , Throwable cause) {
        super(message , cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message , Throwable cause , boolean enableSuppression , boolean writableStackTrace) {
        super(message , cause , enableSuppression , writableStackTrace);
    }
}
