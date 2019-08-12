package com.airbnb.android.cohosting.adapters;

import com.airbnb.android.cohosting.utils.CohostingUtil;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class ListingManagerDetailsAdapter$$Lambda$1 implements LinkOnClickListener {
    private final ListingManagerDetailsAdapter arg$1;

    private ListingManagerDetailsAdapter$$Lambda$1(ListingManagerDetailsAdapter listingManagerDetailsAdapter) {
        this.arg$1 = listingManagerDetailsAdapter;
    }

    public static LinkOnClickListener lambdaFactory$(ListingManagerDetailsAdapter listingManagerDetailsAdapter) {
        return new ListingManagerDetailsAdapter$$Lambda$1(listingManagerDetailsAdapter);
    }

    public void onClickLink(int i) {
        CohostingUtil.goToHelpCenterLink(this.arg$1.context, HelpCenterArticle.COHOSTING_DIFFERENCE_BETWEEN_COHOST_AND_PRIMARY_HOST);
    }
}
