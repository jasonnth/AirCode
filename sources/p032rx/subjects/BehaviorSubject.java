package p032rx.subjects;

import java.util.ArrayList;
import java.util.List;
import p032rx.Observable.OnSubscribe;
import p032rx.exceptions.Exceptions;
import p032rx.functions.Action1;
import p032rx.internal.operators.NotificationLite;

/* renamed from: rx.subjects.BehaviorSubject */
public final class BehaviorSubject<T> extends Subject<T, T> {
    private static final Object[] EMPTY_ARRAY = new Object[0];
    private final SubjectSubscriptionManager<T> state;

    public static <T> BehaviorSubject<T> create() {
        return create(null, false);
    }

    public static <T> BehaviorSubject<T> create(T defaultValue) {
        return create(defaultValue, true);
    }

    private static <T> BehaviorSubject<T> create(T defaultValue, boolean hasDefault) {
        final SubjectSubscriptionManager<T> state2 = new SubjectSubscriptionManager<>();
        if (hasDefault) {
            state2.setLatest(NotificationLite.next(defaultValue));
        }
        state2.onAdded = new Action1<SubjectObserver<T>>() {
            public void call(SubjectObserver<T> o) {
                o.emitFirst(state2.getLatest());
            }
        };
        state2.onTerminated = state2.onAdded;
        return new BehaviorSubject<>(state2, state2);
    }

    protected BehaviorSubject(OnSubscribe<T> onSubscribe, SubjectSubscriptionManager<T> state2) {
        super(onSubscribe);
        this.state = state2;
    }

    public void onCompleted() {
        if (this.state.getLatest() == null || this.state.active) {
            Object n = NotificationLite.completed();
            for (SubjectObserver<T> bo : this.state.terminate(n)) {
                bo.emitNext(n);
            }
        }
    }

    public void onError(Throwable e) {
        if (this.state.getLatest() == null || this.state.active) {
            Object n = NotificationLite.error(e);
            List<Throwable> errors = null;
            for (SubjectObserver<T> bo : this.state.terminate(n)) {
                try {
                    bo.emitNext(n);
                } catch (Throwable e2) {
                    if (errors == null) {
                        errors = new ArrayList<>();
                    }
                    errors.add(e2);
                }
            }
            Exceptions.throwIfAny(errors);
        }
    }

    public void onNext(T v) {
        if (this.state.getLatest() == null || this.state.active) {
            Object n = NotificationLite.next(v);
            for (SubjectObserver<T> bo : this.state.next(n)) {
                bo.emitNext(n);
            }
        }
    }

    public boolean hasCompleted() {
        return NotificationLite.isCompleted(this.state.getLatest());
    }
}
