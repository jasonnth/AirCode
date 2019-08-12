package com.airbnb.android.p011p3;

import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

/* renamed from: com.airbnb.android.p3.ContactHostV2Fragment$$Lambda$2 */
final /* synthetic */ class ContactHostV2Fragment$$Lambda$2 implements LinkOnClickListener {
    private final ContactHostV2Fragment arg$1;

    private ContactHostV2Fragment$$Lambda$2(ContactHostV2Fragment contactHostV2Fragment) {
        this.arg$1 = contactHostV2Fragment;
    }

    public static LinkOnClickListener lambdaFactory$(ContactHostV2Fragment contactHostV2Fragment) {
        return new ContactHostV2Fragment$$Lambda$2(contactHostV2Fragment);
    }

    public void onClickLink(int i) {
        WebViewIntentBuilder.startMobileWebActivity(this.arg$1.getContext(), this.arg$1.getString(C7532R.string.contact_chinese_host_terms));
    }
}
