package p032rx.exceptions;

/* renamed from: rx.exceptions.OnCompletedFailedException */
public final class OnCompletedFailedException extends RuntimeException {
    public OnCompletedFailedException(String message, Throwable throwable) {
        if (throwable == null) {
            throwable = new NullPointerException();
        }
        super(message, throwable);
    }
}
