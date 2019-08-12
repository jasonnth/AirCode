package android.support.p000v4.p001os;

import android.annotation.TargetApi;
import android.os.Trace;

@TargetApi(18)
/* renamed from: android.support.v4.os.TraceJellybeanMR2 */
class TraceJellybeanMR2 {
    public static void beginSection(String section) {
        Trace.beginSection(section);
    }

    public static void endSection() {
        Trace.endSection();
    }
}
