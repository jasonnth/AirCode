package com.airbnb.android.sharing.shareables;

import android.graphics.Bitmap;
import p032rx.functions.Action1;

final /* synthetic */ class StoryShareable$$Lambda$2 implements Action1 {
    private final StoryShareable arg$1;

    private StoryShareable$$Lambda$2(StoryShareable storyShareable) {
        this.arg$1 = storyShareable;
    }

    public static Action1 lambdaFactory$(StoryShareable storyShareable) {
        return new StoryShareable$$Lambda$2(storyShareable);
    }

    public void call(Object obj) {
        this.arg$1.thumbnailBitmap = (Bitmap) obj;
    }
}
