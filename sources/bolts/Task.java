package bolts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class Task<TResult> {
    public static final ExecutorService BACKGROUND_EXECUTOR = BoltsExecutors.background();
    private static final Executor IMMEDIATE_EXECUTOR = BoltsExecutors.immediate();
    private static Task<?> TASK_CANCELLED = new Task<>(true);
    private static Task<Boolean> TASK_FALSE = new Task<>((TResult) Boolean.valueOf(false));
    private static Task<?> TASK_NULL = new Task<>((TResult) null);
    private static Task<Boolean> TASK_TRUE = new Task<>((TResult) Boolean.valueOf(true));
    public static final Executor UI_THREAD_EXECUTOR = AndroidExecutors.uiThread();
    private static volatile UnobservedExceptionHandler unobservedExceptionHandler;
    private boolean cancelled;
    private boolean complete;
    private List<Continuation<TResult, Void>> continuations = new ArrayList();
    private Exception error;
    private boolean errorHasBeenObserved;
    private final Object lock = new Object();
    private TResult result;
    private UnobservedErrorNotifier unobservedErrorNotifier;

    public class TaskCompletionSource extends TaskCompletionSource<TResult> {
        TaskCompletionSource() {
        }
    }

    public interface UnobservedExceptionHandler {
        void unobservedException(Task<?> task, UnobservedTaskException unobservedTaskException);
    }

    public static UnobservedExceptionHandler getUnobservedExceptionHandler() {
        return unobservedExceptionHandler;
    }

    Task() {
    }

    private Task(TResult result2) {
        trySetResult(result2);
    }

    private Task(boolean cancelled2) {
        if (cancelled2) {
            trySetCancelled();
        } else {
            trySetResult(null);
        }
    }

    public static <TResult> TaskCompletionSource create() {
        Task<TResult> task = new Task<>();
        task.getClass();
        return new TaskCompletionSource<>();
    }

    public boolean isCompleted() {
        boolean z;
        synchronized (this.lock) {
            z = this.complete;
        }
        return z;
    }

    public boolean isCancelled() {
        boolean z;
        synchronized (this.lock) {
            z = this.cancelled;
        }
        return z;
    }

    public boolean isFaulted() {
        boolean z;
        synchronized (this.lock) {
            z = getError() != null;
        }
        return z;
    }

    public TResult getResult() {
        TResult tresult;
        synchronized (this.lock) {
            tresult = this.result;
        }
        return tresult;
    }

    public Exception getError() {
        Exception exc;
        synchronized (this.lock) {
            if (this.error != null) {
                this.errorHasBeenObserved = true;
                if (this.unobservedErrorNotifier != null) {
                    this.unobservedErrorNotifier.setObserved();
                    this.unobservedErrorNotifier = null;
                }
            }
            exc = this.error;
        }
        return exc;
    }

    public static <TResult> Task<TResult> forResult(TResult value) {
        if (value == null) {
            return TASK_NULL;
        }
        if (value instanceof Boolean) {
            return ((Boolean) value).booleanValue() ? TASK_TRUE : TASK_FALSE;
        }
        TaskCompletionSource<TResult> tcs = new TaskCompletionSource<>();
        tcs.setResult(value);
        return tcs.getTask();
    }

    public static <TResult> Task<TResult> forError(Exception error2) {
        TaskCompletionSource<TResult> tcs = new TaskCompletionSource<>();
        tcs.setError(error2);
        return tcs.getTask();
    }

    public static <TResult> Task<TResult> cancelled() {
        return TASK_CANCELLED;
    }

    public static <TResult> Task<TResult> call(Callable<TResult> callable, Executor executor) {
        return call(callable, executor, null);
    }

    public static <TResult> Task<TResult> call(final Callable<TResult> callable, Executor executor, final CancellationToken ct) {
        final TaskCompletionSource<TResult> tcs = new TaskCompletionSource<>();
        try {
            executor.execute(new Runnable() {
                public void run() {
                    if (ct == null || !ct.isCancellationRequested()) {
                        try {
                            tcs.setResult(callable.call());
                        } catch (CancellationException e) {
                            tcs.setCancelled();
                        } catch (Exception e2) {
                            tcs.setError(e2);
                        }
                    } else {
                        tcs.setCancelled();
                    }
                }
            });
        } catch (Exception e) {
            tcs.setError(new ExecutorException(e));
        }
        return tcs.getTask();
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation, Executor executor, CancellationToken ct) {
        boolean completed;
        final TaskCompletionSource<TContinuationResult> tcs = new TaskCompletionSource<>();
        synchronized (this.lock) {
            completed = isCompleted();
            if (!completed) {
                final Continuation<TResult, TContinuationResult> continuation2 = continuation;
                final Executor executor2 = executor;
                final CancellationToken cancellationToken = ct;
                this.continuations.add(new Continuation<TResult, Void>() {
                    public Void then(Task<TResult> task) {
                        Task.completeImmediately(tcs, continuation2, task, executor2, cancellationToken);
                        return null;
                    }
                });
            }
        }
        if (completed) {
            completeImmediately(tcs, continuation, this, executor, ct);
        }
        return tcs.getTask();
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation) {
        return continueWith(continuation, IMMEDIATE_EXECUTOR, null);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor) {
        return continueWithTask(continuation, executor, null);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor, CancellationToken ct) {
        boolean completed;
        final TaskCompletionSource<TContinuationResult> tcs = new TaskCompletionSource<>();
        synchronized (this.lock) {
            completed = isCompleted();
            if (!completed) {
                final Continuation<TResult, Task<TContinuationResult>> continuation2 = continuation;
                final Executor executor2 = executor;
                final CancellationToken cancellationToken = ct;
                this.continuations.add(new Continuation<TResult, Void>() {
                    public Void then(Task<TResult> task) {
                        Task.completeAfterTask(tcs, continuation2, task, executor2, cancellationToken);
                        return null;
                    }
                });
            }
        }
        if (completed) {
            completeAfterTask(tcs, continuation, this, executor, ct);
        }
        return tcs.getTask();
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation) {
        return continueWithTask(continuation, IMMEDIATE_EXECUTOR, null);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(final Continuation<TResult, TContinuationResult> continuation, Executor executor, final CancellationToken ct) {
        return continueWithTask(new Continuation<TResult, Task<TContinuationResult>>() {
            public Task<TContinuationResult> then(Task<TResult> task) {
                if (ct != null && ct.isCancellationRequested()) {
                    return Task.cancelled();
                }
                if (task.isFaulted()) {
                    return Task.forError(task.getError());
                }
                if (task.isCancelled()) {
                    return Task.cancelled();
                }
                return task.continueWith(continuation);
            }
        }, executor);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(Continuation<TResult, TContinuationResult> continuation) {
        return onSuccess(continuation, IMMEDIATE_EXECUTOR, null);
    }

    /* access modifiers changed from: private */
    public static <TContinuationResult, TResult> void completeImmediately(final TaskCompletionSource<TContinuationResult> tcs, final Continuation<TResult, TContinuationResult> continuation, final Task<TResult> task, Executor executor, final CancellationToken ct) {
        try {
            executor.execute(new Runnable() {
                public void run() {
                    if (ct == null || !ct.isCancellationRequested()) {
                        try {
                            tcs.setResult(continuation.then(task));
                        } catch (CancellationException e) {
                            tcs.setCancelled();
                        } catch (Exception e2) {
                            tcs.setError(e2);
                        }
                    } else {
                        tcs.setCancelled();
                    }
                }
            });
        } catch (Exception e) {
            tcs.setError(new ExecutorException(e));
        }
    }

    /* access modifiers changed from: private */
    public static <TContinuationResult, TResult> void completeAfterTask(final TaskCompletionSource<TContinuationResult> tcs, final Continuation<TResult, Task<TContinuationResult>> continuation, final Task<TResult> task, Executor executor, final CancellationToken ct) {
        try {
            executor.execute(new Runnable() {
                public void run() {
                    if (ct == null || !ct.isCancellationRequested()) {
                        try {
                            Task<TContinuationResult> result = (Task) continuation.then(task);
                            if (result == null) {
                                tcs.setResult(null);
                            } else {
                                result.continueWith(new Continuation<TContinuationResult, Void>() {
                                    public Void then(Task<TContinuationResult> task) {
                                        if (ct != null && ct.isCancellationRequested()) {
                                            tcs.setCancelled();
                                        } else if (task.isCancelled()) {
                                            tcs.setCancelled();
                                        } else if (task.isFaulted()) {
                                            tcs.setError(task.getError());
                                        } else {
                                            tcs.setResult(task.getResult());
                                        }
                                        return null;
                                    }
                                });
                            }
                        } catch (CancellationException e) {
                            tcs.setCancelled();
                        } catch (Exception e2) {
                            tcs.setError(e2);
                        }
                    } else {
                        tcs.setCancelled();
                    }
                }
            });
        } catch (Exception e) {
            tcs.setError(new ExecutorException(e));
        }
    }

    private void runContinuations() {
        synchronized (this.lock) {
            for (Continuation<TResult, ?> continuation : this.continuations) {
                try {
                    continuation.then(this);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            this.continuations = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean trySetCancelled() {
        boolean z = true;
        synchronized (this.lock) {
            if (this.complete) {
                z = false;
            } else {
                this.complete = true;
                this.cancelled = true;
                this.lock.notifyAll();
                runContinuations();
            }
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public boolean trySetResult(TResult result2) {
        boolean z = true;
        synchronized (this.lock) {
            if (this.complete) {
                z = false;
            } else {
                this.complete = true;
                this.result = result2;
                this.lock.notifyAll();
                runContinuations();
            }
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean trySetError(java.lang.Exception r5) {
        /*
            r4 = this;
            r1 = 1
            r0 = 0
            java.lang.Object r2 = r4.lock
            monitor-enter(r2)
            boolean r3 = r4.complete     // Catch:{ all -> 0x002f }
            if (r3 == 0) goto L_0x000b
            monitor-exit(r2)     // Catch:{ all -> 0x002f }
        L_0x000a:
            return r0
        L_0x000b:
            r0 = 1
            r4.complete = r0     // Catch:{ all -> 0x002f }
            r4.error = r5     // Catch:{ all -> 0x002f }
            r0 = 0
            r4.errorHasBeenObserved = r0     // Catch:{ all -> 0x002f }
            java.lang.Object r0 = r4.lock     // Catch:{ all -> 0x002f }
            r0.notifyAll()     // Catch:{ all -> 0x002f }
            r4.runContinuations()     // Catch:{ all -> 0x002f }
            boolean r0 = r4.errorHasBeenObserved     // Catch:{ all -> 0x002f }
            if (r0 != 0) goto L_0x002c
            bolts.Task$UnobservedExceptionHandler r0 = getUnobservedExceptionHandler()     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x002c
            bolts.UnobservedErrorNotifier r0 = new bolts.UnobservedErrorNotifier     // Catch:{ all -> 0x002f }
            r0.<init>(r4)     // Catch:{ all -> 0x002f }
            r4.unobservedErrorNotifier = r0     // Catch:{ all -> 0x002f }
        L_0x002c:
            monitor-exit(r2)     // Catch:{ all -> 0x002f }
            r0 = r1
            goto L_0x000a
        L_0x002f:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x002f }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: bolts.Task.trySetError(java.lang.Exception):boolean");
    }
}
