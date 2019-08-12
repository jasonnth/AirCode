package com.airbnb.android.utils;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

public class ConcurrentUtil {

    public static class MainThreadExecutor implements Executor {
        private static final Handler HANDLER = new Handler(Looper.getMainLooper());

        public void execute(Runnable runnable) {
            HANDLER.post(runnable);
        }
    }

    public static class SimpleAsyncTask extends AsyncTask<Void, Void, Void> {
        private final Runnable runnable;

        public SimpleAsyncTask(Runnable runnable2) {
            this.runnable = runnable2;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... params) {
            this.runnable.run();
            return null;
        }
    }

    public static class SynchronousExecutor implements Executor {
        public void execute(Runnable runnable) {
            runnable.run();
        }
    }

    public static void defer(Runnable runnable) {
        new SimpleAsyncTask(runnable).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    public static void deferParallel(Runnable runnable) {
        new SimpleAsyncTask(runnable).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public static void deferOnExecutor(Runnable runnable, Executor executor) {
        new SimpleAsyncTask(runnable).executeOnExecutor(executor, new Void[0]);
    }
}
