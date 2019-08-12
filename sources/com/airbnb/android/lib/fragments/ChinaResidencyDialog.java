package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.lib.C0880R;

public final class ChinaResidencyDialog extends ZenDialog {
    private static final int LEARN_MORE = 2;

    /* renamed from: OK */
    private static final int f9524OK = 1;

    public static ChinaResidencyDialog newInstance() {
        return (ChinaResidencyDialog) new ZenBuilder(new ChinaResidencyDialog()).withTitle(C0880R.string.update_country_to_china_title).withBodyText(C0880R.string.update_country_to_china_body).withDualButton(C0880R.string.okay, 1, C0880R.string.radical_transparency_learn_more, 2).create();
    }

    /* access modifiers changed from: protected */
    public void clickLeftButton(int requestCodeLeft) {
    }

    /* access modifiers changed from: protected */
    public void clickRightButton(int requestCodeRight) {
        WebViewIntentBuilder.startMobileWebActivity(getContext(), getString(C0880R.string.tos_url_china_terms));
    }
}
