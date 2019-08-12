package com.airbnb.android.react;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.os.Handler;
import android.os.Looper;
import android.support.p000v4.util.ArrayMap;
import android.support.p000v4.util.Pair;
import com.airbnb.android.core.events.LocaleChangedEvent;
import com.airbnb.android.react.C7663R.string;
import com.facebook.react.bridge.ReactApplicationContext;
import com.google.common.base.Predicates;
import com.google.common.collect.FluentIterable;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class I18nModule extends VersionedReactModuleBase {
    private static final String KEY_PREFIX = "react_native_";
    private static final int VERSION = 1;
    private final String packageName;
    private final Resources resources;

    I18nModule(ReactApplicationContext reactContext, Bus bus) {
        super(reactContext, 1);
        Context context = getReactApplicationContext();
        this.resources = context.getResources();
        this.packageName = context.getPackageName();
        new Handler(Looper.getMainLooper()).post(I18nModule$$Lambda$1.lambdaFactory$(this, bus));
    }

    public String getName() {
        return "I18nBridge";
    }

    @Subscribe
    public void onLocaleChanged(LocaleChangedEvent e) {
        ReactNativeUtils.maybeEmitEvent(getReactApplicationContext(), "BBI18nBridgeUpdatedPhrasesNotificationName", ConversionUtil.toWritableMap(Collections.unmodifiableMap(getPhrases())));
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = super.getConstants();
        constants.put("initialPhrases", getPhrases());
        return constants;
    }

    private Map<String, String> getPhrases() {
        return pairListToMap(FluentIterable.m1283of(string.class.getDeclaredFields()).transform(I18nModule$$Lambda$2.lambdaFactory$()).filter(I18nModule$$Lambda$3.lambdaFactory$()).transform(I18nModule$$Lambda$4.lambdaFactory$(this)).filter(Predicates.notNull()).toList());
    }

    private static Map<String, String> pairListToMap(List<Pair<String, String>> strings) {
        Map<String, String> phrases = new ArrayMap<>(strings.size());
        for (Pair<String, String> pair : strings) {
            phrases.put(pair.first, pair.second);
        }
        return phrases;
    }

    /* access modifiers changed from: private */
    public Pair<String, String> fieldNameToString(String name) {
        try {
            return Pair.create(name, this.resources.getString(this.resources.getIdentifier(name, "string", this.packageName)));
        } catch (NotFoundException e) {
            return null;
        }
    }
}
