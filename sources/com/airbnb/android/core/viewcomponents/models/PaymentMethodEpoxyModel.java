package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.PaymentMethodRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class PaymentMethodEpoxyModel extends AirEpoxyModel<PaymentMethodRow> {
    int drawableRes;
    String imageUrl;
    OnClickListener onClickListener;
    String title;
    int titleRes;

    public void bind(PaymentMethodRow view) {
        super.bind(view);
        if (this.title != null) {
            view.setTitle((CharSequence) this.title);
        } else {
            view.setTitle(this.titleRes);
        }
        if (this.imageUrl != null) {
            view.setImageUrl(this.imageUrl);
        } else {
            view.setImageDrawable(this.drawableRes);
        }
        view.showDivider(true);
        view.setOnClickListener(this.onClickListener);
        view.setRowDrawable(C0716R.C0717drawable.n2_standard_row_right_caret_gray);
    }

    public int getDividerViewType() {
        return 0;
    }
}
