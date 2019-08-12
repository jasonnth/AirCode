package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.p027n2.components.GiftCardPromo;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class GiftCardPromoEpoxyModel extends AirEpoxyModel<GiftCardPromo> {
    DisplayOptions displayOptions;
    String imageUrl;
    boolean isTablet;
    OnClickListener onClickListener;

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        if (this.isTablet) {
            return C0716R.layout.view_holder_gift_card_promo_grid;
        }
        return C0716R.layout.n2_view_holder_gift_card_promo;
    }

    public void bind(GiftCardPromo view) {
        super.bind(view);
        view.setImageUrl(this.imageUrl);
        view.setOnClickListener(this.onClickListener);
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return this.displayOptions.getSpanSize(totalSpanCount);
    }
}
