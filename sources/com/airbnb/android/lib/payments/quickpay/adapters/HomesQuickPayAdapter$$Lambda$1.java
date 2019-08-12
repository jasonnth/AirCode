package com.airbnb.android.lib.payments.quickpay.adapters;

import com.airbnb.android.core.payments.models.BillPriceQuote.Link;
import com.google.common.base.Function;

final /* synthetic */ class HomesQuickPayAdapter$$Lambda$1 implements Function {
    private final HomesQuickPayAdapter arg$1;

    private HomesQuickPayAdapter$$Lambda$1(HomesQuickPayAdapter homesQuickPayAdapter) {
        this.arg$1 = homesQuickPayAdapter;
    }

    public static Function lambdaFactory$(HomesQuickPayAdapter homesQuickPayAdapter) {
        return new HomesQuickPayAdapter$$Lambda$1(homesQuickPayAdapter);
    }

    public Object apply(Object obj) {
        return HomesQuickPayAdapter.lambda$convertLinksToUrlTexts$0(this.arg$1, (Link) obj);
    }
}
