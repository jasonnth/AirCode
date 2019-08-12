package com.airbnb.android.lib.paidamenities.fragments.create;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.PaidAmenityCategory;
import com.airbnb.android.lib.paidamenities.fragments.create.SelectAmenityTypeFragment.SelectAmenityTypeAdapter;

final /* synthetic */ class SelectAmenityTypeFragment$SelectAmenityTypeAdapter$$Lambda$1 implements OnClickListener {
    private final SelectAmenityTypeAdapter arg$1;
    private final PaidAmenityCategory arg$2;

    private SelectAmenityTypeFragment$SelectAmenityTypeAdapter$$Lambda$1(SelectAmenityTypeAdapter selectAmenityTypeAdapter, PaidAmenityCategory paidAmenityCategory) {
        this.arg$1 = selectAmenityTypeAdapter;
        this.arg$2 = paidAmenityCategory;
    }

    public static OnClickListener lambdaFactory$(SelectAmenityTypeAdapter selectAmenityTypeAdapter, PaidAmenityCategory paidAmenityCategory) {
        return new SelectAmenityTypeFragment$SelectAmenityTypeAdapter$$Lambda$1(selectAmenityTypeAdapter, paidAmenityCategory);
    }

    public void onClick(View view) {
        SelectAmenityTypeFragment.this.listener.onSelectAmenityType(this.arg$2);
    }
}
