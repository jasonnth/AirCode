package com.facebook.accountkit.p029ui;

import com.facebook.accountkit.internal.AccountKitController.Logger;

/* renamed from: com.facebook.accountkit.ui.EmailSentCodeContentController */
final class EmailSentCodeContentController extends SentCodeContentController {
    EmailSentCodeContentController(AccountKitConfiguration configuration) {
        super(configuration);
    }

    /* access modifiers changed from: protected */
    public void logImpression() {
        Logger.logUISentCode(true, LoginType.EMAIL);
    }
}
