package com.airbnb.android.listing.adapters;

import android.text.TextUtils;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.google.common.base.Predicate;

final /* synthetic */ class EditAddressAdapter$$Lambda$9 implements Predicate {
    private static final EditAddressAdapter$$Lambda$9 instance = new EditAddressAdapter$$Lambda$9();

    private EditAddressAdapter$$Lambda$9() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return TextUtils.isEmpty(((InlineInputRowEpoxyModel_) obj).input());
    }
}
