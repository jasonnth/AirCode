package com.airbnb.android.core.fragments;

import android.os.Bundle;
import com.airbnb.android.core.fragments.EditTextFragment;
import com.airbnb.android.core.models.User;
import com.airbnb.android.utils.ReactNativeIntentUtils;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class EditTextFragment$$Icepick<T extends EditTextFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8450H = new Helper("com.airbnb.android.core.fragments.EditTextFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.headerTitle = f8450H.getString(state, "headerTitle");
            target.headerSubtitle = f8450H.getString(state, "headerSubtitle");
            target.hint = f8450H.getString(state, "hint");
            target.user = (User) f8450H.getParcelable(state, "user");
            target.menuButtonText = f8450H.getInt(state, "menuButtonText");
            target.message = f8450H.getString(state, "message");
            target.showHeader = f8450H.getBoolean(state, "showHeader");
            target.navigationTag = (NavigationTag) f8450H.getSerializable(state, ReactNativeIntentUtils.REACT_NAVIGATION_TAG);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8450H.putString(state, "headerTitle", target.headerTitle);
        f8450H.putString(state, "headerSubtitle", target.headerSubtitle);
        f8450H.putString(state, "hint", target.hint);
        f8450H.putParcelable(state, "user", target.user);
        f8450H.putInt(state, "menuButtonText", target.menuButtonText);
        f8450H.putString(state, "message", target.message);
        f8450H.putBoolean(state, "showHeader", target.showHeader);
        f8450H.putSerializable(state, ReactNativeIntentUtils.REACT_NAVIGATION_TAG, target.navigationTag);
    }
}
