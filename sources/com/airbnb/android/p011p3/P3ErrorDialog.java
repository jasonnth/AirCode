package com.airbnb.android.p011p3;

import android.content.DialogInterface;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;

/* renamed from: com.airbnb.android.p3.P3ErrorDialog */
public class P3ErrorDialog extends ZenDialog {
    public static P3ErrorDialog newInstance() {
        return (P3ErrorDialog) new ZenBuilder(new P3ErrorDialog()).withTitle(C7532R.string.error).withBodyText(C7532R.string.listing_details_error).withSingleButton(C7532R.string.okay, 0, (Fragment) null).create();
    }

    public void onDismiss(DialogInterface dialog) {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }
}
