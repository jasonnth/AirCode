package com.airbnb.android.lib.businesstravel;

import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.ButtonBarEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel;

public class BusinessTravelInterstitialAdapter extends AirEpoxyAdapter {
    private final ButtonBarEpoxyModel_ buttonBar = new ButtonBarEpoxyModel_();
    private final BusinessTravelListener listener;
    private final DocumentMarqueeEpoxyModel_ marqueeModel = new DocumentMarqueeEpoxyModel_();

    public interface BusinessTravelListener {
        void setIsBusinessTravel(boolean z);
    }

    public BusinessTravelInterstitialAdapter(BusinessTravelListener listener2) {
        this.listener = listener2;
        setUpMarqueeModel();
        setUpButtonBar();
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.marqueeModel, this.buttonBar});
    }

    public void setUpButtonBar() {
        this.buttonBar.addButton(C0880R.string.main_trip_purpose_personal, C0880R.C0881drawable.icon_personal_travel, BusinessTravelInterstitialAdapter$$Lambda$1.lambdaFactory$(this));
        this.buttonBar.addButton(C0880R.string.main_trip_purpose_business_travel, C0880R.C0881drawable.icon_business_travel, BusinessTravelInterstitialAdapter$$Lambda$2.lambdaFactory$(this));
        this.buttonBar.showDivider(false);
    }

    public void setUpMarqueeModel() {
        this.marqueeModel.titleRes(C0880R.string.bt_interstitial_title).captionRes(C0880R.string.main_trip_purpose);
    }
}
