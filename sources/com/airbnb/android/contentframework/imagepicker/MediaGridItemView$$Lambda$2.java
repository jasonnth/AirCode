package com.airbnb.android.contentframework.imagepicker;

import android.view.View;
import android.view.View.OnLongClickListener;

final /* synthetic */ class MediaGridItemView$$Lambda$2 implements OnLongClickListener {
    private final MediaGridItemView arg$1;

    private MediaGridItemView$$Lambda$2(MediaGridItemView mediaGridItemView) {
        this.arg$1 = mediaGridItemView;
    }

    public static OnLongClickListener lambdaFactory$(MediaGridItemView mediaGridItemView) {
        return new MediaGridItemView$$Lambda$2(mediaGridItemView);
    }

    public boolean onLongClick(View view) {
        return MediaGridItemView.lambda$init$1(this.arg$1, view);
    }
}
