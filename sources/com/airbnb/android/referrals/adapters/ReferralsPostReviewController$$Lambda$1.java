package com.airbnb.android.referrals.adapters;

import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.referrals.C1532R;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class ReferralsPostReviewController$$Lambda$1 implements LinkOnClickListener {
    private final ReferralsPostReviewController arg$1;
    private final String arg$2;

    private ReferralsPostReviewController$$Lambda$1(ReferralsPostReviewController referralsPostReviewController, String str) {
        this.arg$1 = referralsPostReviewController;
        this.arg$2 = str;
    }

    public static LinkOnClickListener lambdaFactory$(ReferralsPostReviewController referralsPostReviewController, String str) {
        return new ReferralsPostReviewController$$Lambda$1(referralsPostReviewController, str);
    }

    public void onClickLink(int i) {
        this.arg$1.context.startActivity(WebViewIntentBuilder.newBuilder(this.arg$1.context, this.arg$1.context.getString(C1532R.string.tos_url_referrals)).title(this.arg$2).toIntent());
    }
}
