package com.facebook.accountkit.p029ui;

import com.facebook.accountkit.internal.AccountKitController.Logger;

/* renamed from: com.facebook.accountkit.ui.PhoneSentCodeContentController */
final class PhoneSentCodeContentController extends SentCodeContentController {
    PhoneSentCodeContentController(AccountKitConfiguration configuration) {
        super(configuration);
    }

    /* access modifiers changed from: protected */
    public void logImpression() {
        Logger.logUISentCode(true, LoginType.PHONE);
    }
}
