package com.airbnb.android.react;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.ActivityOptionsCompat;
import android.support.p000v4.util.Pair;
import android.util.SparseArray;
import com.airbnb.android.core.AirActivityFacade;
import com.airbnb.android.utils.AndroidVersion;
import com.facebook.react.bridge.Promise;
import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.Map;

public final class ReactInterfaceManager {
    private static int puuid = 1;
    private static final SparseArray<Promise> resultPromises = new SparseArray<>();
    private final AirActivityFacade activity;
    private final ReactInterface component;

    public ReactInterfaceManager(ReactInterface component2) {
        this.component = component2;
        this.activity = component2.getAirActivity();
    }

    public static void startActivityWithPromise(Activity activity2, Intent intent, Promise promise, Bundle options) {
        int requestCode = puuid;
        puuid = requestCode + 1;
        resultPromises.put(requestCode, promise);
        if (!AndroidVersion.isAtLeastLollipop() || !ReactNativeUtils.isReactNativeIntent(intent)) {
            activity2.startActivityForResult(intent, requestCode);
        } else {
            activity2.runOnUiThread(ReactInterfaceManager$$Lambda$1.lambdaFactory$(options, activity2, intent, requestCode));
        }
    }

    static /* synthetic */ void lambda$startActivityWithPromise$0(Bundle options, Activity activity2, Intent intent, int requestCode) {
        if (options != null) {
            activity2.startActivityForResult(intent, requestCode, options);
        } else {
            activity2.startActivityForResult(intent, requestCode, ActivityOptionsCompat.makeSceneTransitionAnimation(activity2, new Pair[0]).toBundle());
        }
    }

    public static int getPuuid() {
        return puuid;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        deliverPromise(requestCode, resultCode, data);
        if (isDismiss(data)) {
            this.activity.setResult(resultCode, getResultIntent(data));
            this.activity.finish();
        }
    }

    private Intent getResultIntent(Intent data) {
        return new Intent().putExtras(data.getExtras()).putExtra("isDismiss", this.component.isDismissible());
    }

    private void deliverPromise(int requestCode, int resultCode, Intent data) {
        Promise promise = getAndRemovePromise(requestCode);
        if (promise != null) {
            promise.resolve(ConversionUtil.toWritableMap(ImmutableMap.m1294of("code", Integer.valueOf(resultCode), "payload", getPayloadFromIntent(data))));
        }
    }

    private static boolean isDismiss(Intent data) {
        return data != null && data.getBooleanExtra("isDismiss", false);
    }

    private Promise getAndRemovePromise(int requestCode) {
        if (resultPromises.indexOfKey(requestCode) < 0) {
            return null;
        }
        Promise promise = (Promise) resultPromises.get(requestCode);
        resultPromises.remove(requestCode);
        return promise;
    }

    private static Map<String, Object> getPayloadFromIntent(Intent data) {
        if (data == null || !data.hasExtra("payload")) {
            return Collections.emptyMap();
        }
        return (Map) data.getSerializableExtra("payload");
    }
}
