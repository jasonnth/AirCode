package com.airbnb.android.core.fragments;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.utils.MiscUtils;

final /* synthetic */ class LocalAttractionDetailFragment$$Lambda$6 implements OnLongClickListener {
    private final LocalAttractionDetailFragment arg$1;
    private final String arg$2;

    private LocalAttractionDetailFragment$$Lambda$6(LocalAttractionDetailFragment localAttractionDetailFragment, String str) {
        this.arg$1 = localAttractionDetailFragment;
        this.arg$2 = str;
    }

    public static OnLongClickListener lambdaFactory$(LocalAttractionDetailFragment localAttractionDetailFragment, String str) {
        return new LocalAttractionDetailFragment$$Lambda$6(localAttractionDetailFragment, str);
    }

    public boolean onLongClick(View view) {
        return MiscUtils.copyToClipboard(this.arg$1.getActivity(), this.arg$2);
    }
}
