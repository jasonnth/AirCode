package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.PaymentOptionRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class PaymentOptionEpoxyModel extends AirEpoxyModel<PaymentOptionRow> {
    int drawableRes;
    boolean isChecked;
    OnClickListener onClickListener;
    String subtitle;
    String title;

    public void bind(PaymentOptionRow view) {
        super.bind(view);
        view.setTitle(this.title);
        view.setSubtitleText(this.subtitle);
        view.setChecked(this.isChecked);
        view.setImage(this.drawableRes);
        view.setOnClickListener(this.onClickListener);
        view.showDivider(true);
    }

    public int getDividerViewType() {
        return 0;
    }
}
