package com.airbnb.android.lib.views.messages;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.lib.views.messages.MessageThreadInputView;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.View;
import java.util.HashMap;
import java.util.Map;

public class MessageThreadInputView$$Icepick<T extends MessageThreadInputView> extends View<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9671H = new Helper("com.airbnb.android.lib.views.messages.MessageThreadInputView$$Icepick.", BUNDLERS);

    public Parcelable restore(T target, Parcelable p) {
        Bundle state = (Bundle) p;
        target.imagePath = f9671H.getString(state, "imagePath");
        return super.restore(target, f9671H.getParent(state));
    }

    public Parcelable save(T target, Parcelable p) {
        Bundle state = f9671H.putParent(super.save(target, p));
        f9671H.putString(state, "imagePath", target.imagePath);
        return state;
    }
}
