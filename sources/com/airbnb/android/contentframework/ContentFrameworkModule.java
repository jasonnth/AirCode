package com.airbnb.android.contentframework;

import android.content.Context;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.contentframework.controller.StoryPublishController;
import com.airbnb.android.core.explore.ChildScope;

public class ContentFrameworkModule {
    /* access modifiers changed from: 0000 */
    @ChildScope
    public StoryPublishController provideStoryPublishController(Context context, AirRequestInitializer airRequestInitializer) {
        return new StoryPublishController(context, airRequestInitializer);
    }
}
