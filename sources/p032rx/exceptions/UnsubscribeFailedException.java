package p032rx.exceptions;

/* renamed from: rx.exceptions.UnsubscribeFailedException */
public final class UnsubscribeFailedException extends RuntimeException {
    public UnsubscribeFailedException(String message, Throwable throwable) {
        if (throwable == null) {
            throwable = new NullPointerException();
        }
        super(message, throwable);
    }
}
