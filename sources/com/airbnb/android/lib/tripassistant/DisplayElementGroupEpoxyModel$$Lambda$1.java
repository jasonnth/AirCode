package com.airbnb.android.lib.tripassistant;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.utils.MapUtil;

final /* synthetic */ class DisplayElementGroupEpoxyModel$$Lambda$1 implements OnClickListener {
    private final String arg$1;

    private DisplayElementGroupEpoxyModel$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static OnClickListener lambdaFactory$(String str) {
        return new DisplayElementGroupEpoxyModel$$Lambda$1(str);
    }

    public void onClick(View view) {
        view.getContext().startActivity(MapUtil.getMapIntent(view.getContext(), this.arg$1));
    }
}
