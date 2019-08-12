package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.utils.CallHelper;

final /* synthetic */ class SupportNumbersAdapter$$Lambda$3 implements OnClickListener {
    private final SupportNumbersAdapter arg$1;
    private final String arg$2;

    private SupportNumbersAdapter$$Lambda$3(SupportNumbersAdapter supportNumbersAdapter, String str) {
        this.arg$1 = supportNumbersAdapter;
        this.arg$2 = str;
    }

    public static OnClickListener lambdaFactory$(SupportNumbersAdapter supportNumbersAdapter, String str) {
        return new SupportNumbersAdapter$$Lambda$3(supportNumbersAdapter, str);
    }

    public void onClick(View view) {
        CallHelper.dial(this.arg$1.context, this.arg$2);
    }
}
