package com.airbnb.p027n2.epoxy;

import android.view.View;
import butterknife.ButterKnife;
import com.airbnb.epoxy.EpoxyHolder;

/* renamed from: com.airbnb.n2.epoxy.AirViewHolder */
public class AirViewHolder extends EpoxyHolder {
    /* access modifiers changed from: protected */
    public void bindView(View itemView) {
        ButterKnife.bind(this, itemView);
    }

    /* access modifiers changed from: protected */
    public void showDivider(boolean showDivider) {
    }
}
