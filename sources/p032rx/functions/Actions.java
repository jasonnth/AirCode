package p032rx.functions;

/* renamed from: rx.functions.Actions */
public final class Actions {
    private static final EmptyAction EMPTY_ACTION = new EmptyAction();

    /* renamed from: rx.functions.Actions$Action1CallsAction0 */
    static final class Action1CallsAction0<T> implements Action1<T> {
        final Action0 action;

        public Action1CallsAction0(Action0 action2) {
            this.action = action2;
        }

        public void call(T t) {
            this.action.call();
        }
    }

    /* renamed from: rx.functions.Actions$EmptyAction */
    static final class EmptyAction<T0, T1, T2, T3, T4, T5, T6, T7, T8> implements Action0, Action1<T0>, Action2<T0, T1> {
        EmptyAction() {
        }

        public void call() {
        }

        public void call(T0 t0) {
        }

        public void call(T0 t0, T1 t1) {
        }
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8> EmptyAction<T0, T1, T2, T3, T4, T5, T6, T7, T8> empty() {
        return EMPTY_ACTION;
    }

    public static <T> Action1<T> toAction1(Action0 action) {
        return new Action1CallsAction0(action);
    }
}
