package p032rx.exceptions;

/* renamed from: rx.exceptions.OnErrorNotImplementedException */
public class OnErrorNotImplementedException extends RuntimeException {
    public OnErrorNotImplementedException(String message, Throwable e) {
        if (e == null) {
            e = new NullPointerException();
        }
        super(message, e);
    }

    public OnErrorNotImplementedException(Throwable e) {
        String str = e != null ? e.getMessage() : null;
        if (e == null) {
            e = new NullPointerException();
        }
        super(str, e);
    }
}
