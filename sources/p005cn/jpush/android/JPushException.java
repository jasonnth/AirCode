package p005cn.jpush.android;

/* renamed from: cn.jpush.android.JPushException */
public class JPushException extends Exception {
    private static final long serialVersionUID = 1;

    public JPushException() {
    }

    public JPushException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public JPushException(String detailMessage) {
        super(detailMessage);
    }

    public JPushException(Throwable throwable) {
        super(throwable);
    }
}
