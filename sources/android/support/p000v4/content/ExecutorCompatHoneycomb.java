package android.support.p000v4.content;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import java.util.concurrent.Executor;

@TargetApi(11)
/* renamed from: android.support.v4.content.ExecutorCompatHoneycomb */
class ExecutorCompatHoneycomb {
    public static Executor getParallelExecutor() {
        return AsyncTask.THREAD_POOL_EXECUTOR;
    }
}
