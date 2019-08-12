package com.airbnb.android.internal.bugreporter;

import android.os.Bundle;
import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import com.airbnb.android.internal.bugreporter.InternalBugReportAdapter;
import com.facebook.places.model.PlaceFields;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;
import org.spongycastle.i18n.ErrorBundle;

public class InternalBugReportAdapter$$Icepick<T extends InternalBugReportAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8208H = new Helper("com.airbnb.android.internal.bugreporter.InternalBugReportAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.includeUserInfo = f8208H.getBoolean(state, "includeUserInfo");
            target.subject = f8208H.getCharSequence(state, ErfExperimentsModel.SUBJECT);
            target.details = f8208H.getCharSequence(state, ErrorBundle.DETAIL_ENTRY);
            target.recipient = f8208H.getCharSequence(state, "recipient");
            target.photos = f8208H.getStringArrayList(state, PlaceFields.PHOTOS_PROFILE);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8208H.putBoolean(state, "includeUserInfo", target.includeUserInfo);
        f8208H.putCharSequence(state, ErfExperimentsModel.SUBJECT, target.subject);
        f8208H.putCharSequence(state, ErrorBundle.DETAIL_ENTRY, target.details);
        f8208H.putCharSequence(state, "recipient", target.recipient);
        f8208H.putStringArrayList(state, PlaceFields.PHOTOS_PROFILE, target.photos);
    }
}
