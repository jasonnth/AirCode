package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.listing.adapters.HouseRulesSettingsAdapter.Listener;

final /* synthetic */ class HouseRulesSettingsAdapter$$Lambda$2 implements OnClickListener {
    private final Listener arg$1;

    private HouseRulesSettingsAdapter$$Lambda$2(Listener listener) {
        this.arg$1 = listener;
    }

    public static OnClickListener lambdaFactory$(Listener listener) {
        return new HouseRulesSettingsAdapter$$Lambda$2(listener);
    }

    public void onClick(View view) {
        this.arg$1.onAdditionalHouseRulesClicked();
    }
}