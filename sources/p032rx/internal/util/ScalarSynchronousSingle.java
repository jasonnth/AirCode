package p032rx.internal.util;

import p032rx.Single;
import p032rx.Single.OnSubscribe;
import p032rx.SingleSubscriber;
import p032rx.functions.Func1;

/* renamed from: rx.internal.util.ScalarSynchronousSingle */
public final class ScalarSynchronousSingle<T> extends Single<T> {
    final T value;

    public static <T> ScalarSynchronousSingle<T> create(T t) {
        return new ScalarSynchronousSingle<>(t);
    }

    protected ScalarSynchronousSingle(final T t) {
        super(new OnSubscribe<T>() {
            public void call(SingleSubscriber<? super T> te) {
                te.onSuccess(t);
            }
        });
        this.value = t;
    }

    public <R> Single<R> scalarFlatMap(final Func1<? super T, ? extends Single<? extends R>> func) {
        return create(new OnSubscribe<R>() {
            public void call(final SingleSubscriber<? super R> child) {
                Single<? extends R> o = (Single) func.call(ScalarSynchronousSingle.this.value);
                if (o instanceof ScalarSynchronousSingle) {
                    child.onSuccess(((ScalarSynchronousSingle) o).value);
                    return;
                }
                SingleSubscriber<R> subscriber = new SingleSubscriber<R>() {
                    public void onError(Throwable e) {
                        child.onError(e);
                    }

                    public void onSuccess(R r) {
                        child.onSuccess(r);
                    }
                };
                child.add(subscriber);
                o.subscribe(subscriber);
            }
        });
    }
}
