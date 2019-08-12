package com.airbnb.android.react;

import android.app.Activity;
import android.support.test.espresso.idling.CountingIdlingResource;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ReactFirstRenderIdlingResource {
    private final CountingIdlingResource idlingResource = new CountingIdlingResource("ReactFirstRender");
    private final List<WeakReference<Activity>> waitingActivities = new ArrayList(1);

    ReactFirstRenderIdlingResource() {
    }

    /* access modifiers changed from: 0000 */
    public void signalWaitingForFirstRender(Activity activity) {
        boolean found = false;
        for (WeakReference<Activity> activityRef : this.waitingActivities) {
            if (((Activity) activityRef.get()) == activity) {
                found = true;
            }
        }
        if (!found) {
            this.waitingActivities.add(new WeakReference(activity));
            this.idlingResource.increment();
        }
    }

    /* access modifiers changed from: 0000 */
    public void signalFirstRenderComplete(Activity activity) {
        Iterator<WeakReference<Activity>> it = this.waitingActivities.iterator();
        while (it.hasNext()) {
            Activity a = (Activity) ((WeakReference) it.next()).get();
            if (a == null || a == activity) {
                it.remove();
                this.idlingResource.decrement();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public CountingIdlingResource getIdlingResource() {
        return this.idlingResource;
    }
}
