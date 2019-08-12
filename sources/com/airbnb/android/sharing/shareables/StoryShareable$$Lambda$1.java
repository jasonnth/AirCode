package com.airbnb.android.sharing.shareables;

import android.content.Context;
import com.airbnb.android.core.utils.WeChatHelper;
import java.util.concurrent.Callable;

final /* synthetic */ class StoryShareable$$Lambda$1 implements Callable {
    private final StoryShareable arg$1;
    private final Context arg$2;

    private StoryShareable$$Lambda$1(StoryShareable storyShareable, Context context) {
        this.arg$1 = storyShareable;
        this.arg$2 = context;
    }

    public static Callable lambdaFactory$(StoryShareable storyShareable, Context context) {
        return new StoryShareable$$Lambda$1(storyShareable, context);
    }

    public Object call() {
        return WeChatHelper.fetchThumbnailForWeChatSharing(this.arg$2, this.arg$1.coverImageUrl);
    }
}
