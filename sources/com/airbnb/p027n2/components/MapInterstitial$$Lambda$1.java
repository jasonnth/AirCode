package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.airbnb.p027n2.utils.MiscUtils;

/* renamed from: com.airbnb.n2.components.MapInterstitial$$Lambda$1 */
final /* synthetic */ class MapInterstitial$$Lambda$1 implements OnLongClickListener {
    private final MapInterstitial arg$1;

    private MapInterstitial$$Lambda$1(MapInterstitial mapInterstitial) {
        this.arg$1 = mapInterstitial;
    }

    public static OnLongClickListener lambdaFactory$(MapInterstitial mapInterstitial) {
        return new MapInterstitial$$Lambda$1(mapInterstitial);
    }

    public boolean onLongClick(View view) {
        return MiscUtils.copyToClipboard(this.arg$1.getContext(), this.arg$1.title.getText().toString());
    }
}
