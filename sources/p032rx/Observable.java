package p032rx;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import p032rx.Emitter.BackpressureMode;
import p032rx.exceptions.Exceptions;
import p032rx.exceptions.OnErrorFailedException;
import p032rx.functions.Action0;
import p032rx.functions.Action1;
import p032rx.functions.Actions;
import p032rx.functions.Func0;
import p032rx.functions.Func1;
import p032rx.internal.operators.EmptyObservableHolder;
import p032rx.internal.operators.OnSubscribeConcatMap;
import p032rx.internal.operators.OnSubscribeCreate;
import p032rx.internal.operators.OnSubscribeDefer;
import p032rx.internal.operators.OnSubscribeDoOnEach;
import p032rx.internal.operators.OnSubscribeFilter;
import p032rx.internal.operators.OnSubscribeFromArray;
import p032rx.internal.operators.OnSubscribeFromCallable;
import p032rx.internal.operators.OnSubscribeFromIterable;
import p032rx.internal.operators.OnSubscribeLift;
import p032rx.internal.operators.OnSubscribeMap;
import p032rx.internal.operators.OnSubscribeSingle;
import p032rx.internal.operators.OnSubscribeTakeLastOne;
import p032rx.internal.operators.OnSubscribeThrow;
import p032rx.internal.operators.OnSubscribeTimerOnce;
import p032rx.internal.operators.OnSubscribeTimerPeriodically;
import p032rx.internal.operators.OperatorAsObservable;
import p032rx.internal.operators.OperatorDelay;
import p032rx.internal.operators.OperatorDoOnSubscribe;
import p032rx.internal.operators.OperatorDoOnUnsubscribe;
import p032rx.internal.operators.OperatorIgnoreElements;
import p032rx.internal.operators.OperatorMerge;
import p032rx.internal.operators.OperatorObserveOn;
import p032rx.internal.operators.OperatorOnErrorResumeNextViaFunction;
import p032rx.internal.operators.OperatorSubscribeOn;
import p032rx.internal.operators.OperatorSwitch;
import p032rx.internal.operators.OperatorTake;
import p032rx.internal.operators.OperatorTakeLast;
import p032rx.internal.operators.OperatorTakeWhile;
import p032rx.internal.operators.OperatorToObservableList;
import p032rx.internal.operators.OperatorUnsubscribeOn;
import p032rx.internal.util.ActionObserver;
import p032rx.internal.util.ActionSubscriber;
import p032rx.internal.util.InternalObservableUtils;
import p032rx.internal.util.ObserverSubscriber;
import p032rx.internal.util.RxRingBuffer;
import p032rx.internal.util.ScalarSynchronousObservable;
import p032rx.internal.util.UtilityFunctions;
import p032rx.observers.SafeSubscriber;
import p032rx.plugins.RxJavaHooks;
import p032rx.schedulers.Schedulers;

/* renamed from: rx.Observable */
public class Observable<T> {
    final OnSubscribe<T> onSubscribe;

    /* renamed from: rx.Observable$Transformer */
    public interface Transformer<T, R> extends Func1<Observable<T>, Observable<R>> {
    }

    /* renamed from: rx.Observable$OnSubscribe */
    public interface OnSubscribe<T> extends Action1<Subscriber<? super T>> {
    }

    /* renamed from: rx.Observable$Operator */
    public interface Operator<R, T> extends Func1<Subscriber<? super R>, Subscriber<? super T>> {
    }

    protected Observable(OnSubscribe<T> f) {
        this.onSubscribe = f;
    }

    @Deprecated
    public static <T> Observable<T> create(OnSubscribe<T> f) {
        return new Observable<>(RxJavaHooks.onCreate(f));
    }

    public static <T> Observable<T> create(Action1<Emitter<T>> emitter, BackpressureMode backpressure) {
        return unsafeCreate(new OnSubscribeCreate(emitter, backpressure));
    }

    public static <T> Observable<T> unsafeCreate(OnSubscribe<T> f) {
        return new Observable<>(RxJavaHooks.onCreate(f));
    }

    public final <R> Observable<R> lift(Operator<? extends R, ? super T> operator) {
        return unsafeCreate(new OnSubscribeLift(this.onSubscribe, operator));
    }

    public <R> Observable<R> compose(Transformer<? super T, ? extends R> transformer) {
        return (Observable) transformer.call(this);
    }

    public Single<T> toSingle() {
        return new Single<>(OnSubscribeSingle.create(this));
    }

    public static <T> Observable<T> concat(Observable<? extends Observable<? extends T>> observables) {
        return observables.concatMap(UtilityFunctions.identity());
    }

    public static <T> Observable<T> concat(Observable<? extends T> t1, Observable<? extends T> t2) {
        return concat(just(t1, t2));
    }

    public static <T> Observable<T> defer(Func0<Observable<T>> observableFactory) {
        return unsafeCreate(new OnSubscribeDefer(observableFactory));
    }

    public static <T> Observable<T> empty() {
        return EmptyObservableHolder.instance();
    }

    public static <T> Observable<T> error(Throwable exception) {
        return unsafeCreate(new OnSubscribeThrow(exception));
    }

    public static <T> Observable<T> from(Iterable<? extends T> iterable) {
        return unsafeCreate(new OnSubscribeFromIterable(iterable));
    }

    public static <T> Observable<T> from(T[] array) {
        int n = array.length;
        if (n == 0) {
            return empty();
        }
        if (n == 1) {
            return just(array[0]);
        }
        return unsafeCreate(new OnSubscribeFromArray(array));
    }

    public static <T> Observable<T> fromCallable(Callable<? extends T> func) {
        return unsafeCreate(new OnSubscribeFromCallable(func));
    }

    public static Observable<Long> interval(long interval, TimeUnit unit, Scheduler scheduler) {
        return interval(interval, interval, unit, scheduler);
    }

    public static Observable<Long> interval(long initialDelay, long period, TimeUnit unit, Scheduler scheduler) {
        return unsafeCreate(new OnSubscribeTimerPeriodically(initialDelay, period, unit, scheduler));
    }

    public static <T> Observable<T> just(T value) {
        return ScalarSynchronousObservable.create(value);
    }

    public static <T> Observable<T> just(T t1, T t2) {
        return from((T[]) new Object[]{t1, t2});
    }

    public static <T> Observable<T> merge(Iterable<? extends Observable<? extends T>> sequences) {
        return merge(from(sequences));
    }

    public static <T> Observable<T> merge(Observable<? extends Observable<? extends T>> source) {
        if (source.getClass() == ScalarSynchronousObservable.class) {
            return ((ScalarSynchronousObservable) source).scalarFlatMap(UtilityFunctions.identity());
        }
        return source.lift(OperatorMerge.instance(false));
    }

    public static <T> Observable<T> switchOnNext(Observable<? extends Observable<? extends T>> sequenceOfSequences) {
        return sequenceOfSequences.lift(OperatorSwitch.instance(false));
    }

    public static Observable<Long> timer(long delay, TimeUnit unit, Scheduler scheduler) {
        return unsafeCreate(new OnSubscribeTimerOnce(delay, unit, scheduler));
    }

    public final Observable<T> asObservable() {
        return lift(OperatorAsObservable.instance());
    }

    public final <R> Observable<R> concatMap(Func1<? super T, ? extends Observable<? extends R>> func) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable) this).scalarFlatMap(func);
        }
        return unsafeCreate(new OnSubscribeConcatMap(this, func, 2, 0));
    }

    public final Observable<T> delay(long delay, TimeUnit unit) {
        return delay(delay, unit, Schedulers.computation());
    }

    public final Observable<T> delay(long delay, TimeUnit unit, Scheduler scheduler) {
        return lift(new OperatorDelay(delay, unit, scheduler));
    }

    public final Observable<T> doOnCompleted(Action0 onCompleted) {
        return unsafeCreate(new OnSubscribeDoOnEach(this, new ActionObserver<>(Actions.empty(), Actions.empty(), onCompleted)));
    }

    public final Observable<T> doOnError(Action1<? super Throwable> onError) {
        return unsafeCreate(new OnSubscribeDoOnEach(this, new ActionObserver<>(Actions.empty(), onError, Actions.empty())));
    }

    public final Observable<T> doOnNext(Action1<? super T> onNext) {
        return unsafeCreate(new OnSubscribeDoOnEach(this, new ActionObserver<>(onNext, Actions.empty(), Actions.empty())));
    }

    public final Observable<T> doOnSubscribe(Action0 subscribe) {
        return lift(new OperatorDoOnSubscribe(subscribe));
    }

    public final Observable<T> doOnTerminate(Action0 onTerminate) {
        return unsafeCreate(new OnSubscribeDoOnEach(this, new ActionObserver<>(Actions.empty(), Actions.toAction1(onTerminate), onTerminate)));
    }

    public final Observable<T> doOnUnsubscribe(Action0 unsubscribe) {
        return lift(new OperatorDoOnUnsubscribe(unsubscribe));
    }

    public final Observable<T> filter(Func1<? super T, Boolean> predicate) {
        return unsafeCreate(new OnSubscribeFilter(this, predicate));
    }

    public final <R> Observable<R> flatMap(Func1<? super T, ? extends Observable<? extends R>> func) {
        if (getClass() == ScalarSynchronousObservable.class) {
            return ((ScalarSynchronousObservable) this).scalarFlatMap(func);
        }
        return merge(map(func));
    }

    public final Observable<T> ignoreElements() {
        return lift(OperatorIgnoreElements.instance());
    }

    public final <R> Observable<R> map(Func1<? super T, ? extends R> func) {
        return unsafeCreate(new OnSubscribeMap(this, func));
    }

    public final Observable<T> observeOn(Scheduler scheduler) {
        return observeOn(scheduler, RxRingBuffer.SIZE);
    }

    public final Observable<T> observeOn(Scheduler scheduler, int bufferSize) {
        return observeOn(scheduler, false, bufferSize);
    }

    public final Observable<T> observeOn(Scheduler scheduler, boolean delayError, int bufferSize) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable) this).scalarScheduleOn(scheduler);
        }
        return lift(new OperatorObserveOn(scheduler, delayError, bufferSize));
    }

    public final Observable<T> onErrorResumeNext(Func1<? super Throwable, ? extends Observable<? extends T>> resumeFunction) {
        return lift(new OperatorOnErrorResumeNextViaFunction(resumeFunction));
    }

    public final Observable<T> onErrorResumeNext(Observable<? extends T> resumeSequence) {
        return lift(OperatorOnErrorResumeNextViaFunction.withOther(resumeSequence));
    }

    public final Observable<T> onErrorReturn(Func1<? super Throwable, ? extends T> resumeFunction) {
        return lift(OperatorOnErrorResumeNextViaFunction.withSingle(resumeFunction));
    }

    public final Subscription subscribe() {
        return subscribe((Subscriber<? super T>) new ActionSubscriber<Object>(Actions.empty(), InternalObservableUtils.ERROR_NOT_IMPLEMENTED, Actions.empty()));
    }

    public final Subscription subscribe(Action1<? super T> onNext) {
        if (onNext != null) {
            return subscribe((Subscriber<? super T>) new ActionSubscriber<Object>(onNext, InternalObservableUtils.ERROR_NOT_IMPLEMENTED, Actions.empty()));
        }
        throw new IllegalArgumentException("onNext can not be null");
    }

    public final Subscription subscribe(Action1<? super T> onNext, Action1<Throwable> onError) {
        if (onNext == null) {
            throw new IllegalArgumentException("onNext can not be null");
        } else if (onError != null) {
            return subscribe((Subscriber<? super T>) new ActionSubscriber<Object>(onNext, onError, Actions.empty()));
        } else {
            throw new IllegalArgumentException("onError can not be null");
        }
    }

    public final Subscription subscribe(Action1<? super T> onNext, Action1<Throwable> onError, Action0 onCompleted) {
        if (onNext == null) {
            throw new IllegalArgumentException("onNext can not be null");
        } else if (onError == null) {
            throw new IllegalArgumentException("onError can not be null");
        } else if (onCompleted != null) {
            return subscribe((Subscriber<? super T>) new ActionSubscriber<Object>(onNext, onError, onCompleted));
        } else {
            throw new IllegalArgumentException("onComplete can not be null");
        }
    }

    public final Subscription subscribe(Observer<? super T> observer) {
        if (observer instanceof Subscriber) {
            return subscribe((Subscriber) observer);
        }
        if (observer != null) {
            return subscribe((Subscriber<? super T>) new ObserverSubscriber<Object>(observer));
        }
        throw new NullPointerException("observer is null");
    }

    public final Subscription unsafeSubscribe(Subscriber<? super T> subscriber) {
        try {
            subscriber.onStart();
            RxJavaHooks.onObservableStart(this, this.onSubscribe).call(subscriber);
            return RxJavaHooks.onObservableReturn(subscriber);
        } catch (Throwable e2) {
            Exceptions.throwIfFatal(e2);
            RuntimeException r = new OnErrorFailedException("Error occurred attempting to subscribe [" + e.getMessage() + "] and then again while trying to pass to onError.", e2);
            RxJavaHooks.onObservableError(r);
            throw r;
        }
    }

    public final Subscription subscribe(Subscriber<? super T> subscriber) {
        return subscribe(subscriber, this);
    }

    static <T> Subscription subscribe(Subscriber<? super T> subscriber, Observable<T> observable) {
        if (subscriber == null) {
            throw new IllegalArgumentException("subscriber can not be null");
        } else if (observable.onSubscribe == null) {
            throw new IllegalStateException("onSubscribe function can not be null.");
        } else {
            subscriber.onStart();
            if (!(subscriber instanceof SafeSubscriber)) {
                subscriber = new SafeSubscriber<>(subscriber);
            }
            try {
                RxJavaHooks.onObservableStart(observable, observable.onSubscribe).call(subscriber);
                return RxJavaHooks.onObservableReturn(subscriber);
            } catch (Throwable e2) {
                Exceptions.throwIfFatal(e2);
                RuntimeException r = new OnErrorFailedException("Error occurred attempting to subscribe [" + e.getMessage() + "] and then again while trying to pass to onError.", e2);
                RxJavaHooks.onObservableError(r);
                throw r;
            }
        }
    }

    public final Observable<T> subscribeOn(Scheduler scheduler) {
        return subscribeOn(scheduler, !(this.onSubscribe instanceof OnSubscribeCreate));
    }

    public final Observable<T> subscribeOn(Scheduler scheduler, boolean requestOn) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable) this).scalarScheduleOn(scheduler);
        }
        return unsafeCreate(new OperatorSubscribeOn(this, scheduler, requestOn));
    }

    public final <R> Observable<R> switchMap(Func1<? super T, ? extends Observable<? extends R>> func) {
        return switchOnNext(map(func));
    }

    public final Observable<T> take(int count) {
        return lift(new OperatorTake(count));
    }

    public final Observable<T> takeFirst(Func1<? super T, Boolean> predicate) {
        return filter(predicate).take(1);
    }

    public final Observable<T> takeLast(int count) {
        if (count == 0) {
            return ignoreElements();
        }
        if (count == 1) {
            return unsafeCreate(new OnSubscribeTakeLastOne(this));
        }
        return lift(new OperatorTakeLast(count));
    }

    public final Observable<T> takeWhile(Func1<? super T, Boolean> predicate) {
        return lift(new OperatorTakeWhile(predicate));
    }

    public final Observable<List<T>> toList() {
        return lift(OperatorToObservableList.instance());
    }

    public final Observable<T> unsubscribeOn(Scheduler scheduler) {
        return lift(new OperatorUnsubscribeOn(scheduler));
    }
}
