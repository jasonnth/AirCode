package com.airbnb.android.core.intents;

import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.Fragments;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.utils.BundleBuilder;

public class ShakeFeedbackDialogIntents {
    private static final String EMAIL_GUEST = "feedback_android@airbnb.com";
    private static final String EMAIL_HOST = "host_feedback_android@airbnb.com";
    public static final String KEY_EMAIL = "KEY_EMAIL";

    public static ZenDialog newInstanceForHostFeedback() {
        return newInstance(EMAIL_HOST);
    }

    public static ZenDialog newInstanceForGuestFeedback() {
        return newInstance(EMAIL_GUEST);
    }

    public static ZenDialog newInstanceForShakeFeedback() {
        return newInstanceForGuestFeedback();
    }

    private static ZenDialog newInstance(String recipient) {
        return new ZenBuilder(Fragments.shakeFeedback()).withTitle(C0716R.string.feedback).setCustomLayout(C0716R.layout.shake_feedback_dialog).withDualButton(17039360, 1, C0716R.string.feedback_dialog_send_feedback, 2).withArguments(((BundleBuilder) new BundleBuilder().putString(KEY_EMAIL, recipient)).toBundle()).create();
    }
}
