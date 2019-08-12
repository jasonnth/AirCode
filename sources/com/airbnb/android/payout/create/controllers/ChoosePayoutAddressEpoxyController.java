package com.airbnb.android.payout.create.controllers;

import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.android.payout.C7552R;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import java.util.Map;
import java.util.Map.Entry;

public class ChoosePayoutAddressEpoxyController extends AirEpoxyController {
    private Map<AirAddress, Boolean> addresses;
    DocumentMarqueeEpoxyModel_ documentMarqueeModel;
    LinkActionRowEpoxyModel_ linkActionRowModel;
    private final Listener listener;
    EpoxyControllerLoadingModel_ loadingModel;

    public interface Listener {
        void onAddressCheckChanged(AirAddress airAddress, boolean z);

        void onClickAddNewAddress();
    }

    public ChoosePayoutAddressEpoxyController(Listener listener2) {
        this.listener = listener2;
    }

    public void setAddresses(Map<AirAddress, Boolean> addresses2) {
        this.addresses = addresses2;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.documentMarqueeModel.titleRes(C7552R.string.add_payout_method_choose_address_marquee_title);
        if (this.addresses == null) {
            add((EpoxyModel<?>) this.loadingModel);
            return;
        }
        int modelId = 0;
        for (Entry<AirAddress, Boolean> addressEntry : this.addresses.entrySet()) {
            new ToggleActionRowEpoxyModel_().m5698id((long) modelId).title(((AirAddress) addressEntry.getKey()).streetAddressOne()).checked(((Boolean) addressEntry.getValue()).booleanValue()).checkedChangedlistener(ChoosePayoutAddressEpoxyController$$Lambda$1.lambdaFactory$(this, addressEntry)).addTo(this);
            modelId++;
        }
        this.linkActionRowModel.textRes(C7552R.string.add_payout_address_row_title).clickListener(ChoosePayoutAddressEpoxyController$$Lambda$2.lambdaFactory$(this));
    }
}
