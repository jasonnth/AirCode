package p032rx.schedulers;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import p032rx.Scheduler;
import p032rx.internal.schedulers.ExecutorScheduler;
import p032rx.internal.schedulers.SchedulerLifecycle;
import p032rx.plugins.RxJavaHooks;
import p032rx.plugins.RxJavaPlugins;
import p032rx.plugins.RxJavaSchedulersHook;

/* renamed from: rx.schedulers.Schedulers */
public final class Schedulers {
    private static final AtomicReference<Schedulers> INSTANCE = new AtomicReference<>();
    private final Scheduler computationScheduler;
    private final Scheduler ioScheduler;
    private final Scheduler newThreadScheduler;

    private static Schedulers getInstance() {
        Schedulers current;
        while (true) {
            current = (Schedulers) INSTANCE.get();
            if (current == null) {
                current = new Schedulers();
                if (INSTANCE.compareAndSet(null, current)) {
                    break;
                }
                current.shutdownInstance();
            } else {
                break;
            }
        }
        return current;
    }

    private Schedulers() {
        RxJavaSchedulersHook hook = RxJavaPlugins.getInstance().getSchedulersHook();
        Scheduler c = hook.getComputationScheduler();
        if (c != null) {
            this.computationScheduler = c;
        } else {
            this.computationScheduler = RxJavaSchedulersHook.createComputationScheduler();
        }
        Scheduler io = hook.getIOScheduler();
        if (io != null) {
            this.ioScheduler = io;
        } else {
            this.ioScheduler = RxJavaSchedulersHook.createIoScheduler();
        }
        Scheduler nt = hook.getNewThreadScheduler();
        if (nt != null) {
            this.newThreadScheduler = nt;
        } else {
            this.newThreadScheduler = RxJavaSchedulersHook.createNewThreadScheduler();
        }
    }

    public static Scheduler computation() {
        return RxJavaHooks.onComputationScheduler(getInstance().computationScheduler);
    }

    /* renamed from: io */
    public static Scheduler m4048io() {
        return RxJavaHooks.onIOScheduler(getInstance().ioScheduler);
    }

    public static Scheduler from(Executor executor) {
        return new ExecutorScheduler(executor);
    }

    /* access modifiers changed from: 0000 */
    public synchronized void shutdownInstance() {
        if (this.computationScheduler instanceof SchedulerLifecycle) {
            ((SchedulerLifecycle) this.computationScheduler).shutdown();
        }
        if (this.ioScheduler instanceof SchedulerLifecycle) {
            ((SchedulerLifecycle) this.ioScheduler).shutdown();
        }
        if (this.newThreadScheduler instanceof SchedulerLifecycle) {
            ((SchedulerLifecycle) this.newThreadScheduler).shutdown();
        }
    }
}
