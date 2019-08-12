package com.airbnb.android.lib.controller;

import com.bumptech.glide.ListPreloader.PreloadSizeProvider;

final /* synthetic */ class PropertyImagesPagerPreloader$$Lambda$1 implements PreloadSizeProvider {
    private final int[] arg$1;

    private PropertyImagesPagerPreloader$$Lambda$1(int[] iArr) {
        this.arg$1 = iArr;
    }

    public static PreloadSizeProvider lambdaFactory$(int[] iArr) {
        return new PropertyImagesPagerPreloader$$Lambda$1(iArr);
    }

    public int[] getPreloadSize(Object obj, int i, int i2) {
        return PropertyImagesPagerPreloader.lambda$new$0(this.arg$1, (String) obj, i, i2);
    }
}
