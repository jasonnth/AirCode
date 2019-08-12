package android.support.test.espresso.idling;

import android.os.SystemClock;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.IdlingResource.ResourceCallback;
import android.text.TextUtils;
import android.util.Log;
import java.util.concurrent.atomic.AtomicInteger;

public final class CountingIdlingResource implements IdlingResource {
    private volatile long becameBusyAt;
    private volatile long becameIdleAt;
    private final AtomicInteger counter;
    private final boolean debugCounting;
    private volatile ResourceCallback resourceCallback;
    private final String resourceName;

    public CountingIdlingResource(String resourceName2) {
        this(resourceName2, false);
    }

    public CountingIdlingResource(String resourceName2, boolean debugCounting2) {
        this.counter = new AtomicInteger(0);
        this.becameBusyAt = 0;
        this.becameIdleAt = 0;
        if (TextUtils.isEmpty(resourceName2)) {
            throw new IllegalArgumentException("resourceName cannot be empty or null!");
        }
        this.resourceName = resourceName2;
        this.debugCounting = debugCounting2;
    }

    public void increment() {
        int counterVal = this.counter.getAndIncrement();
        if (counterVal == 0) {
            this.becameBusyAt = SystemClock.uptimeMillis();
        }
        if (this.debugCounting) {
            Log.i("CountingIdlingResource", "Resource: " + this.resourceName + " in-use-count incremented to: " + (counterVal + 1));
        }
    }

    public void decrement() {
        int counterVal = this.counter.decrementAndGet();
        if (counterVal == 0) {
            if (this.resourceCallback != null) {
                this.resourceCallback.onTransitionToIdle();
            }
            this.becameIdleAt = SystemClock.uptimeMillis();
        }
        if (this.debugCounting) {
            if (counterVal == 0) {
                Log.i("CountingIdlingResource", "Resource: " + this.resourceName + " went idle! (Time spent not idle: " + (this.becameIdleAt - this.becameBusyAt) + ")");
            } else {
                Log.i("CountingIdlingResource", "Resource: " + this.resourceName + " in-use-count decremented to: " + counterVal);
            }
        }
        if (counterVal <= -1) {
            throw new IllegalStateException("Counter has been corrupted! counterVal=" + counterVal);
        }
    }
}
