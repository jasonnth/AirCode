package p032rx.android.schedulers;

import android.os.Looper;
import java.util.concurrent.atomic.AtomicReference;
import p032rx.Scheduler;
import p032rx.android.plugins.RxAndroidPlugins;

/* renamed from: rx.android.schedulers.AndroidSchedulers */
public final class AndroidSchedulers {
    private static final AtomicReference<AndroidSchedulers> INSTANCE = new AtomicReference<>();
    private final Scheduler mainThreadScheduler;

    private static AndroidSchedulers getInstance() {
        AndroidSchedulers current;
        do {
            current = (AndroidSchedulers) INSTANCE.get();
            if (current != null) {
                break;
            }
            current = new AndroidSchedulers();
        } while (!INSTANCE.compareAndSet(null, current));
        return current;
    }

    private AndroidSchedulers() {
        Scheduler main = RxAndroidPlugins.getInstance().getSchedulersHook().getMainThreadScheduler();
        if (main != null) {
            this.mainThreadScheduler = main;
        } else {
            this.mainThreadScheduler = new LooperScheduler(Looper.getMainLooper());
        }
    }

    public static Scheduler mainThread() {
        return getInstance().mainThreadScheduler;
    }
}
