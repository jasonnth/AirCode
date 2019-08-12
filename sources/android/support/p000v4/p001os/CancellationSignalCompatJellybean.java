package android.support.p000v4.p001os;

import android.annotation.TargetApi;
import android.os.CancellationSignal;

@TargetApi(16)
/* renamed from: android.support.v4.os.CancellationSignalCompatJellybean */
class CancellationSignalCompatJellybean {
    public static Object create() {
        return new CancellationSignal();
    }

    public static void cancel(Object cancellationSignalObj) {
        ((CancellationSignal) cancellationSignalObj).cancel();
    }
}
