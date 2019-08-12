package android.support.p000v4.app;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.p000v4.app.RemoteInputCompatBase.RemoteInput;
import android.support.p000v4.app.RemoteInputCompatBase.RemoteInput.Factory;

@TargetApi(16)
/* renamed from: android.support.v4.app.RemoteInputCompatJellybean */
class RemoteInputCompatJellybean {
    static RemoteInput fromBundle(Bundle data, Factory factory) {
        return factory.build(data.getString("resultKey"), data.getCharSequence("label"), data.getCharSequenceArray("choices"), data.getBoolean("allowFreeFormInput"), data.getBundle("extras"));
    }

    static Bundle toBundle(RemoteInput remoteInput) {
        Bundle data = new Bundle();
        data.putString("resultKey", remoteInput.getResultKey());
        data.putCharSequence("label", remoteInput.getLabel());
        data.putCharSequenceArray("choices", remoteInput.getChoices());
        data.putBoolean("allowFreeFormInput", remoteInput.getAllowFreeFormInput());
        data.putBundle("extras", remoteInput.getExtras());
        return data;
    }

    static RemoteInput[] fromBundleArray(Bundle[] bundles, Factory factory) {
        if (bundles == null) {
            return null;
        }
        RemoteInput[] remoteInputs = factory.newArray(bundles.length);
        for (int i = 0; i < bundles.length; i++) {
            remoteInputs[i] = fromBundle(bundles[i], factory);
        }
        return remoteInputs;
    }

    static Bundle[] toBundleArray(RemoteInput[] remoteInputs) {
        if (remoteInputs == null) {
            return null;
        }
        Bundle[] bundles = new Bundle[remoteInputs.length];
        for (int i = 0; i < remoteInputs.length; i++) {
            bundles[i] = toBundle(remoteInputs[i]);
        }
        return bundles;
    }
}
