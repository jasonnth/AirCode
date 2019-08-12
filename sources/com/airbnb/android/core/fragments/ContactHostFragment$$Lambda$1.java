package com.airbnb.android.core.fragments;

import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class ContactHostFragment$$Lambda$1 implements LinkOnClickListener {
    private final ContactHostFragment arg$1;

    private ContactHostFragment$$Lambda$1(ContactHostFragment contactHostFragment) {
        this.arg$1 = contactHostFragment;
    }

    public static LinkOnClickListener lambdaFactory$(ContactHostFragment contactHostFragment) {
        return new ContactHostFragment$$Lambda$1(contactHostFragment);
    }

    public void onClickLink(int i) {
        WebViewIntentBuilder.startMobileWebActivity(this.arg$1.getContext(), this.arg$1.getString(C0716R.string.contact_chinese_host_terms));
    }
}
