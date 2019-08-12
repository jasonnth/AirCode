package p032rx;

import p032rx.exceptions.Exceptions;
import p032rx.functions.Action1;
import p032rx.functions.Actions;
import p032rx.functions.Func1;
import p032rx.internal.operators.SingleDoOnEvent;
import p032rx.internal.operators.SingleOnSubscribeMap;
import p032rx.internal.util.ScalarSynchronousSingle;
import p032rx.internal.util.UtilityFunctions;
import p032rx.plugins.RxJavaHooks;

/* renamed from: rx.Single */
public class Single<T> {
    final OnSubscribe<T> onSubscribe;

    /* renamed from: rx.Single$OnSubscribe */
    public interface OnSubscribe<T> extends Action1<SingleSubscriber<? super T>> {
    }

    protected Single(OnSubscribe<T> f) {
        this.onSubscribe = RxJavaHooks.onCreate(f);
    }

    public static <T> Single<T> create(OnSubscribe<T> f) {
        return new Single<>(f);
    }

    public static <T> Single<T> error(final Throwable exception) {
        return create(new OnSubscribe<T>() {
            public void call(SingleSubscriber<? super T> te) {
                te.onError(exception);
            }
        });
    }

    public static <T> Single<T> just(T value) {
        return ScalarSynchronousSingle.create(value);
    }

    public static <T> Single<T> merge(Single<? extends Single<? extends T>> source) {
        if (source instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle) source).scalarFlatMap(UtilityFunctions.identity());
        }
        return create(new OnSubscribe<T>(source) {
            final /* synthetic */ Single val$source;

            {
                this.val$source = r1;
            }

            public void call(final SingleSubscriber<? super T> child) {
                SingleSubscriber<Single<? extends T>> parent = new SingleSubscriber<Single<? extends T>>() {
                    public void onSuccess(Single<? extends T> innerSingle) {
                        innerSingle.subscribe(child);
                    }

                    public void onError(Throwable error) {
                        child.onError(error);
                    }
                };
                child.add(parent);
                this.val$source.subscribe(parent);
            }
        });
    }

    public final <R> Single<R> flatMap(Func1<? super T, ? extends Single<? extends R>> func) {
        if (this instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle) this).scalarFlatMap(func);
        }
        return merge(map(func));
    }

    public final <R> Single<R> map(Func1<? super T, ? extends R> func) {
        return create(new SingleOnSubscribeMap(this, func));
    }

    public final Subscription subscribe(final Observer<? super T> observer) {
        if (observer != null) {
            return subscribe((SingleSubscriber<? super T>) new SingleSubscriber<T>() {
                public void onSuccess(T value) {
                    observer.onNext(value);
                    observer.onCompleted();
                }

                public void onError(Throwable error) {
                    observer.onError(error);
                }
            });
        }
        throw new NullPointerException("observer is null");
    }

    public final Subscription subscribe(SingleSubscriber<? super T> te) {
        if (te == null) {
            throw new IllegalArgumentException("te is null");
        }
        try {
            RxJavaHooks.onSingleStart(this, this.onSubscribe).call(te);
            return RxJavaHooks.onSingleReturn(te);
        } catch (Throwable e2) {
            Exceptions.throwIfFatal(e2);
            RuntimeException r = new RuntimeException("Error occurred attempting to subscribe [" + ex.getMessage() + "] and then again while trying to pass to onError.", e2);
            RxJavaHooks.onSingleError(r);
            throw r;
        }
    }

    public final Single<T> doOnError(final Action1<Throwable> onError) {
        if (onError != null) {
            return create(new SingleDoOnEvent(this, Actions.empty(), new Action1<Throwable>() {
                public void call(Throwable throwable) {
                    onError.call(throwable);
                }
            }));
        }
        throw new IllegalArgumentException("onError is null");
    }
}
