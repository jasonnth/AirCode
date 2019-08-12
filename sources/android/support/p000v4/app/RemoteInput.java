package android.support.p000v4.app;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.p000v4.app.RemoteInputCompatBase.RemoteInput.Factory;

/* renamed from: android.support.v4.app.RemoteInput */
public final class RemoteInput extends android.support.p000v4.app.RemoteInputCompatBase.RemoteInput {
    public static final Factory FACTORY = new Factory() {
        public RemoteInput build(String resultKey, CharSequence label, CharSequence[] choices, boolean allowFreeFormInput, Bundle extras) {
            return new RemoteInput(resultKey, label, choices, allowFreeFormInput, extras);
        }

        public RemoteInput[] newArray(int size) {
            return new RemoteInput[size];
        }
    };
    private static final Impl IMPL;
    private final boolean mAllowFreeFormInput;
    private final CharSequence[] mChoices;
    private final Bundle mExtras;
    private final CharSequence mLabel;
    private final String mResultKey;

    /* renamed from: android.support.v4.app.RemoteInput$Impl */
    interface Impl {
    }

    /* renamed from: android.support.v4.app.RemoteInput$ImplApi20 */
    static class ImplApi20 implements Impl {
        ImplApi20() {
        }
    }

    /* renamed from: android.support.v4.app.RemoteInput$ImplBase */
    static class ImplBase implements Impl {
        ImplBase() {
        }
    }

    /* renamed from: android.support.v4.app.RemoteInput$ImplJellybean */
    static class ImplJellybean implements Impl {
        ImplJellybean() {
        }
    }

    RemoteInput(String resultKey, CharSequence label, CharSequence[] choices, boolean allowFreeFormInput, Bundle extras) {
        this.mResultKey = resultKey;
        this.mLabel = label;
        this.mChoices = choices;
        this.mAllowFreeFormInput = allowFreeFormInput;
        this.mExtras = extras;
    }

    public String getResultKey() {
        return this.mResultKey;
    }

    public CharSequence getLabel() {
        return this.mLabel;
    }

    public CharSequence[] getChoices() {
        return this.mChoices;
    }

    public boolean getAllowFreeFormInput() {
        return this.mAllowFreeFormInput;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    static {
        if (VERSION.SDK_INT >= 20) {
            IMPL = new ImplApi20();
        } else if (VERSION.SDK_INT >= 16) {
            IMPL = new ImplJellybean();
        } else {
            IMPL = new ImplBase();
        }
    }
}
