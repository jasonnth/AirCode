package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.utils.MiscUtils;

final /* synthetic */ class ImageFragment$$Lambda$1 implements OnLongClickListener {
    private final ImageFragment arg$1;
    private final String arg$2;

    private ImageFragment$$Lambda$1(ImageFragment imageFragment, String str) {
        this.arg$1 = imageFragment;
        this.arg$2 = str;
    }

    public static OnLongClickListener lambdaFactory$(ImageFragment imageFragment, String str) {
        return new ImageFragment$$Lambda$1(imageFragment, str);
    }

    public boolean onLongClick(View view) {
        return MiscUtils.copyToClipboard(this.arg$1.getActivity(), this.arg$2);
    }
}
