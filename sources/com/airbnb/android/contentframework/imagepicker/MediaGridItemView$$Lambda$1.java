package com.airbnb.android.contentframework.imagepicker;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class MediaGridItemView$$Lambda$1 implements OnClickListener {
    private final MediaGridItemView arg$1;

    private MediaGridItemView$$Lambda$1(MediaGridItemView mediaGridItemView) {
        this.arg$1 = mediaGridItemView;
    }

    public static OnClickListener lambdaFactory$(MediaGridItemView mediaGridItemView) {
        return new MediaGridItemView$$Lambda$1(mediaGridItemView);
    }

    public void onClick(View view) {
        MediaGridItemView.lambda$init$0(this.arg$1, view);
    }
}
