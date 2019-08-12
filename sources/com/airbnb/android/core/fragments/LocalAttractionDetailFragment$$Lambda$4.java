package com.airbnb.android.core.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.utils.CallHelper;

final /* synthetic */ class LocalAttractionDetailFragment$$Lambda$4 implements OnClickListener {
    private final LocalAttractionDetailFragment arg$1;
    private final String arg$2;

    private LocalAttractionDetailFragment$$Lambda$4(LocalAttractionDetailFragment localAttractionDetailFragment, String str) {
        this.arg$1 = localAttractionDetailFragment;
        this.arg$2 = str;
    }

    public static OnClickListener lambdaFactory$(LocalAttractionDetailFragment localAttractionDetailFragment, String str) {
        return new LocalAttractionDetailFragment$$Lambda$4(localAttractionDetailFragment, str);
    }

    public void onClick(View view) {
        CallHelper.dial(this.arg$1.getActivity(), this.arg$2);
    }
}
