package android.support.p000v4.content;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build.VERSION;

/* renamed from: android.support.v4.content.IntentCompat */
public final class IntentCompat {
    private static final IntentCompatImpl IMPL;

    /* renamed from: android.support.v4.content.IntentCompat$IntentCompatImpl */
    interface IntentCompatImpl {
        Intent makeMainActivity(ComponentName componentName);
    }

    /* renamed from: android.support.v4.content.IntentCompat$IntentCompatImplBase */
    static class IntentCompatImplBase implements IntentCompatImpl {
        IntentCompatImplBase() {
        }

        public Intent makeMainActivity(ComponentName componentName) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setComponent(componentName);
            intent.addCategory("android.intent.category.LAUNCHER");
            return intent;
        }
    }

    /* renamed from: android.support.v4.content.IntentCompat$IntentCompatImplHC */
    static class IntentCompatImplHC extends IntentCompatImplBase {
        IntentCompatImplHC() {
        }

        public Intent makeMainActivity(ComponentName componentName) {
            return IntentCompatHoneycomb.makeMainActivity(componentName);
        }
    }

    /* renamed from: android.support.v4.content.IntentCompat$IntentCompatImplIcsMr1 */
    static class IntentCompatImplIcsMr1 extends IntentCompatImplHC {
        IntentCompatImplIcsMr1() {
        }
    }

    static {
        int version = VERSION.SDK_INT;
        if (version >= 15) {
            IMPL = new IntentCompatImplIcsMr1();
        } else if (version >= 11) {
            IMPL = new IntentCompatImplHC();
        } else {
            IMPL = new IntentCompatImplBase();
        }
    }

    public static Intent makeMainActivity(ComponentName mainActivity) {
        return IMPL.makeMainActivity(mainActivity);
    }
}
