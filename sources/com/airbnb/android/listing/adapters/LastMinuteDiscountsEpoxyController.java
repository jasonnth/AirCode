package com.airbnb.android.listing.adapters;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class LastMinuteDiscountsEpoxyController extends AirEpoxyController {
    private final Context context;
    private final Listener listener;
    DocumentMarqueeEpoxyModel_ marqueeModel;

    public interface Listener {
        void showLastMinuteDiscountLearnMore();
    }

    public LastMinuteDiscountsEpoxyController(Context context2, Listener listener2, Bundle savedState) {
        this.context = context2;
        this.listener = listener2;
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.marqueeModel.titleRes(C7213R.string.manage_listings_last_minute_discount_title).captionRes(C7213R.string.manage_listings_last_minute_discount_subtitle).linkRes(C7213R.string.learn_more_info_text).linkClickListener(LastMinuteDiscountsEpoxyController$$Lambda$1.lambdaFactory$(this));
    }
}
