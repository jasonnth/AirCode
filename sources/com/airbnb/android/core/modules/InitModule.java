package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.JPushInitializer;
import com.airbnb.android.core.PostApplicationCreatedInitializer;

public class InitModule {

    public interface Declarations {
        PostApplicationCreatedInitializer bindJPushInitializer(JPushInitializer jPushInitializer);
    }

    /* access modifiers changed from: 0000 */
    public JPushInitializer provideJPushInitializer(Context context) {
        return _provideJPushInitializer(context);
    }

    /* access modifiers changed from: 0000 */
    public JPushInitializer _provideJPushInitializer(Context context) {
        return new JPushInitializer(context);
    }
}
