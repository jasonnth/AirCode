package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.utils.MiscUtils;

final /* synthetic */ class SupportNumbersAdapter$$Lambda$4 implements OnLongClickListener {
    private final SupportNumbersAdapter arg$1;
    private final String arg$2;

    private SupportNumbersAdapter$$Lambda$4(SupportNumbersAdapter supportNumbersAdapter, String str) {
        this.arg$1 = supportNumbersAdapter;
        this.arg$2 = str;
    }

    public static OnLongClickListener lambdaFactory$(SupportNumbersAdapter supportNumbersAdapter, String str) {
        return new SupportNumbersAdapter$$Lambda$4(supportNumbersAdapter, str);
    }

    public boolean onLongClick(View view) {
        return MiscUtils.copyToClipboard(this.arg$1.context, this.arg$2);
    }
}
