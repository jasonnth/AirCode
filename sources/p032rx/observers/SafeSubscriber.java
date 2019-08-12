package p032rx.observers;

import java.util.Arrays;
import java.util.Collection;
import p032rx.Observer;
import p032rx.Subscriber;
import p032rx.exceptions.CompositeException;
import p032rx.exceptions.Exceptions;
import p032rx.exceptions.OnErrorFailedException;
import p032rx.exceptions.OnErrorNotImplementedException;
import p032rx.exceptions.UnsubscribeFailedException;
import p032rx.plugins.RxJavaHooks;
import p032rx.plugins.RxJavaPlugins;

/* renamed from: rx.observers.SafeSubscriber */
public class SafeSubscriber<T> extends Subscriber<T> {
    private final Subscriber<? super T> actual;
    boolean done;

    public SafeSubscriber(Subscriber<? super T> actual2) {
        super(actual2);
        this.actual = actual2;
    }

    public void onCompleted() {
        if (!this.done) {
            this.done = true;
            try {
                this.actual.onCompleted();
                try {
                    unsubscribe();
                } catch (Throwable e) {
                    RxJavaHooks.onError(e);
                    throw new UnsubscribeFailedException(e.getMessage(), e);
                }
            } catch (Throwable th) {
                try {
                    unsubscribe();
                    throw th;
                } catch (Throwable e2) {
                    RxJavaHooks.onError(e2);
                    throw new UnsubscribeFailedException(e2.getMessage(), e2);
                }
            }
        }
    }

    public void onError(Throwable e) {
        Exceptions.throwIfFatal(e);
        if (!this.done) {
            this.done = true;
            _onError(e);
        }
    }

    public void onNext(T t) {
        try {
            if (!this.done) {
                this.actual.onNext(t);
            }
        } catch (Throwable e) {
            Exceptions.throwOrReport(e, (Observer<?>) this);
        }
    }

    /* access modifiers changed from: protected */
    public void _onError(Throwable e) {
        RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
        try {
            this.actual.onError(e);
            try {
                unsubscribe();
            } catch (Throwable unsubscribeException) {
                RxJavaHooks.onError(unsubscribeException);
                throw new OnErrorFailedException(unsubscribeException);
            }
        } catch (OnErrorNotImplementedException e2) {
            unsubscribe();
            throw e2;
        } catch (Throwable unsubscribeException2) {
            RxJavaHooks.onError(unsubscribeException2);
            throw new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError and during unsubscription.", new CompositeException((Collection<? extends Throwable>) Arrays.asList(new Throwable[]{e, e2, unsubscribeException2})));
        }
    }
}
