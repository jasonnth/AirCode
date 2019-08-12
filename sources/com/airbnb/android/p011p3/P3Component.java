package com.airbnb.android.p011p3;

import com.airbnb.android.core.explore.ChildScope;

@ChildScope
/* renamed from: com.airbnb.android.p3.P3Component */
public interface P3Component {

    /* renamed from: com.airbnb.android.p3.P3Component$Builder */
    public interface Builder {
        P3Component build();
    }

    void inject(P3Activity p3Activity);

    void inject(P3AdditionalPriceFragment p3AdditionalPriceFragment);

    void inject(P3Fragment p3Fragment);

    void inject(P3ReviewFragment p3ReviewFragment);

    void inject(P3ReviewSearchFragment p3ReviewSearchFragment);
}
