package com.airbnb.android.sharing.p033ui;

import android.support.p002v7.graphics.Palette;
import android.support.p002v7.graphics.Palette.PaletteAsyncListener;

/* renamed from: com.airbnb.android.sharing.ui.SharePreview$$Lambda$1 */
final /* synthetic */ class SharePreview$$Lambda$1 implements PaletteAsyncListener {
    private final SharePreview arg$1;

    private SharePreview$$Lambda$1(SharePreview sharePreview) {
        this.arg$1 = sharePreview;
    }

    public static PaletteAsyncListener lambdaFactory$(SharePreview sharePreview) {
        return new SharePreview$$Lambda$1(sharePreview);
    }

    public void onGenerated(Palette palette) {
        SharePreview.lambda$setBackgroundColors$0(this.arg$1, palette);
    }
}
