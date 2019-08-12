package com.nytimes.android.external.cache;

import com.nytimes.android.external.cache.AbstractFuture.TrustedFuture;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Futures {

    private static abstract class AbstractChainingFuture<I, O, F> extends TrustedFuture<O> implements Runnable {
        F function;
        ListenableFuture<? extends I> inputFuture;

        /* access modifiers changed from: 0000 */
        public abstract void doTransform(F f, I i) throws Exception;

        AbstractChainingFuture(ListenableFuture<? extends I> inputFuture2, F function2) {
            this.inputFuture = (ListenableFuture) Preconditions.checkNotNull(inputFuture2);
            this.function = Preconditions.checkNotNull(function2);
        }

        public final void run() {
            boolean z = true;
            try {
                ListenableFuture<? extends I> localInputFuture = this.inputFuture;
                F localFunction = this.function;
                boolean isCancelled = (localInputFuture == null) | isCancelled();
                if (localFunction != null) {
                    z = false;
                }
                if (!z && !isCancelled) {
                    this.inputFuture = null;
                    this.function = null;
                    try {
                        doTransform(localFunction, Uninterruptibles.getUninterruptibly(localInputFuture));
                    } catch (CancellationException e) {
                        cancel(false);
                    } catch (ExecutionException e2) {
                        setException(e2.getCause());
                    }
                }
            } catch (UndeclaredThrowableException e3) {
                setException(e3.getCause());
            } catch (Throwable t) {
                setException(t);
            }
        }

        /* access modifiers changed from: 0000 */
        public final void done() {
            maybePropagateCancellation(this.inputFuture);
            this.inputFuture = null;
        }
    }

    private static final class ChainingFuture<I, O> extends AbstractChainingFuture<I, O, Function<? super I, ? extends O>> {
        ChainingFuture(ListenableFuture<? extends I> inputFuture, Function<? super I, ? extends O> function) {
            super(inputFuture, function);
        }

        /* access modifiers changed from: 0000 */
        public void doTransform(Function<? super I, ? extends O> function, I input) {
            set(function.apply(input));
        }
    }

    private static class ImmediateFailedFuture<V> extends ImmediateFuture<V> {
        private final Throwable thrown;

        ImmediateFailedFuture(Throwable thrown2) {
            super();
            this.thrown = thrown2;
        }

        public V get() throws ExecutionException {
            throw new ExecutionException(this.thrown);
        }
    }

    private static abstract class ImmediateFuture<V> implements ListenableFuture<V> {
        private static final Logger log = Logger.getLogger(ImmediateFuture.class.getName());

        public abstract V get() throws ExecutionException;

        private ImmediateFuture() {
        }

        public void addListener(Runnable listener, Executor executor) {
            Preconditions.checkNotNull(listener, "Runnable was null.");
            Preconditions.checkNotNull(executor, "Executor was null.");
            try {
                executor.execute(listener);
            } catch (RuntimeException var4) {
                log.log(Level.SEVERE, "RuntimeException while executing runnable " + listener + " with executor " + executor, var4);
            }
        }

        public boolean cancel(boolean mayInterruptIfRunning) {
            return false;
        }

        public V get(long timeout, TimeUnit unit) throws ExecutionException {
            Preconditions.checkNotNull(unit);
            return get();
        }

        public boolean isCancelled() {
            return false;
        }

        public boolean isDone() {
            return true;
        }
    }

    private static class ImmediateSuccessfulFuture<V> extends ImmediateFuture<V> {
        static final ImmediateSuccessfulFuture<Object> NULL = new ImmediateSuccessfulFuture<>(null);
        private final V value;

        ImmediateSuccessfulFuture(V value2) {
            super();
            this.value = value2;
        }

        public V get() {
            return this.value;
        }
    }

    public static <V> ListenableFuture<V> immediateFuture(V value) {
        if (value == null) {
            return ImmediateSuccessfulFuture.NULL;
        }
        return new ImmediateSuccessfulFuture(value);
    }

    public static <V> ListenableFuture<V> immediateFailedFuture(Throwable throwable) {
        Preconditions.checkNotNull(throwable);
        return new ImmediateFailedFuture(throwable);
    }

    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> input, Function<? super I, ? extends O> function) {
        Preconditions.checkNotNull(function);
        ChainingFuture<I, O> output = new ChainingFuture<>(input, function);
        input.addListener(output, DirectExecutor.INSTANCE);
        return output;
    }
}
