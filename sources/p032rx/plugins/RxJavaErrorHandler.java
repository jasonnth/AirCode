package p032rx.plugins;

import p032rx.exceptions.Exceptions;

/* renamed from: rx.plugins.RxJavaErrorHandler */
public abstract class RxJavaErrorHandler {
    @Deprecated
    public void handleError(Throwable e) {
    }

    public final String handleOnNextValueRendering(Object item) {
        try {
            return render(item);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Throwable t) {
            Exceptions.throwIfFatal(t);
        }
        return item.getClass().getName() + ".errorRendering";
    }

    /* access modifiers changed from: protected */
    public String render(Object item) throws InterruptedException {
        return null;
    }
}
