package com.airbnb.android.insights.fragments.details;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.utils.ExternalAppUtils;

final /* synthetic */ class InsightsNightlyPriceFragment$1$$Lambda$2 implements OnClickListener {
    private final C65721 arg$1;

    private InsightsNightlyPriceFragment$1$$Lambda$2(C65721 r1) {
        this.arg$1 = r1;
    }

    public static OnClickListener lambdaFactory$(C65721 r1) {
        return new InsightsNightlyPriceFragment$1$$Lambda$2(r1);
    }

    public void onClick(View view) {
        InsightsNightlyPriceFragment.this.getActivity().startActivity(new Intent("android.intent.action.VIEW", ExternalAppUtils.getAppStoreUri(InsightsNightlyPriceFragment.this.getContext())));
    }
}
