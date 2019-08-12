package com.airbnb.android.p011p3;

import android.os.Bundle;
import com.airbnb.android.p011p3.PostInquiryIBUpsellFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.airbnb.android.p3.PostInquiryIBUpsellFragment$$Icepick */
public class PostInquiryIBUpsellFragment$$Icepick<T extends PostInquiryIBUpsellFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10538H = new Helper("com.airbnb.android.p3.PostInquiryIBUpsellFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.isRTB = f10538H.getBoolean(state, "isRTB");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10538H.putBoolean(state, "isRTB", target.isRTB);
    }
}
