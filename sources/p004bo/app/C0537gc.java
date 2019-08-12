package p004bo.app;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build.VERSION;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: bo.app.gc */
public class C0537gc {

    /* renamed from: bo.app.gc$a */
    static class C0538a implements ThreadFactory {

        /* renamed from: a */
        private static final AtomicInteger f573a = new AtomicInteger(1);

        /* renamed from: b */
        private final ThreadGroup f574b;

        /* renamed from: c */
        private final AtomicInteger f575c = new AtomicInteger(1);

        /* renamed from: d */
        private final String f576d;

        /* renamed from: e */
        private final int f577e;

        C0538a(int i, String str) {
            this.f577e = i;
            this.f574b = Thread.currentThread().getThreadGroup();
            this.f576d = str + f573a.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread thread = new Thread(this.f574b, r, this.f576d + this.f575c.getAndIncrement(), 0);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            thread.setPriority(this.f577e);
            return thread;
        }
    }

    /* renamed from: a */
    public static Executor m785a(int i, int i2, C0565gr grVar) {
        return new ThreadPoolExecutor(i, i, 0, TimeUnit.MILLISECONDS, grVar == C0565gr.LIFO ? new C0570gv() : new LinkedBlockingQueue(), m786a(i2, "uil-pool-"));
    }

    /* renamed from: a */
    public static Executor m784a() {
        return Executors.newCachedThreadPool(m786a(5, "uil-pool-d-"));
    }

    /* renamed from: b */
    public static C0530fw m787b() {
        return new C0531fx();
    }

    /* renamed from: a */
    public static C0516fp m780a(Context context, C0530fw fwVar, long j, int i) {
        File b = m788b(context);
        if (j > 0 || i > 0) {
            try {
                return new C0526ft(C0602hp.m1072b(context), b, fwVar, j, i);
            } catch (IOException e) {
                C0599hn.m1061a((Throwable) e);
            }
        }
        return new C0518fr(C0602hp.m1069a(context), b, fwVar);
    }

    /* renamed from: b */
    private static File m788b(Context context) {
        File a = C0602hp.m1071a(context, false);
        File file = new File(a, "uil-images");
        if (file.exists() || file.mkdir()) {
            return file;
        }
        return a;
    }

    /* renamed from: a */
    public static C0533fz m781a(Context context, int i) {
        int i2;
        if (i == 0) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            int memoryClass = activityManager.getMemoryClass();
            if (!m791d() || !m790c(context)) {
                i2 = memoryClass;
            } else {
                i2 = m779a(activityManager);
            }
            i = (i2 * 1048576) / 8;
        }
        return new C0536gb(i);
    }

    /* renamed from: d */
    private static boolean m791d() {
        return VERSION.SDK_INT >= 11;
    }

    @TargetApi(11)
    /* renamed from: c */
    private static boolean m790c(Context context) {
        return (context.getApplicationInfo().flags & 1048576) != 0;
    }

    @TargetApi(11)
    /* renamed from: a */
    private static int m779a(ActivityManager activityManager) {
        return activityManager.getLargeMemoryClass();
    }

    /* renamed from: a */
    public static C0586hd m783a(Context context) {
        return new C0584hc(context);
    }

    /* renamed from: a */
    public static C0579gy m782a(boolean z) {
        return new C0576gx(z);
    }

    /* renamed from: c */
    public static C0582ha m789c() {
        return new C0583hb();
    }

    /* renamed from: a */
    private static ThreadFactory m786a(int i, String str) {
        return new C0538a(i, str);
    }
}
