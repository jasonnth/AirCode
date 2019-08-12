package com.airbnb.android.payout.create.controllers;

import com.airbnb.android.core.models.AirAddress;
import com.airbnb.p027n2.components.ToggleActionRow;
import com.airbnb.p027n2.components.ToggleActionRow.OnCheckedChangeListener;
import java.util.Map.Entry;

final /* synthetic */ class ChoosePayoutAddressEpoxyController$$Lambda$1 implements OnCheckedChangeListener {
    private final ChoosePayoutAddressEpoxyController arg$1;
    private final Entry arg$2;

    private ChoosePayoutAddressEpoxyController$$Lambda$1(ChoosePayoutAddressEpoxyController choosePayoutAddressEpoxyController, Entry entry) {
        this.arg$1 = choosePayoutAddressEpoxyController;
        this.arg$2 = entry;
    }

    public static OnCheckedChangeListener lambdaFactory$(ChoosePayoutAddressEpoxyController choosePayoutAddressEpoxyController, Entry entry) {
        return new ChoosePayoutAddressEpoxyController$$Lambda$1(choosePayoutAddressEpoxyController, entry);
    }

    public void onCheckedChanged(ToggleActionRow toggleActionRow, boolean z) {
        this.arg$1.listener.onAddressCheckChanged((AirAddress) this.arg$2.getKey(), z);
    }
}
