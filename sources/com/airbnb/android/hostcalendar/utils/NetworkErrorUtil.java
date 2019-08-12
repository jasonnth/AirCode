package com.airbnb.android.hostcalendar.utils;

import android.content.Context;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.text.TextUtils;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.hostcalendar.C6418R;

public final class NetworkErrorUtil {
    public static void promptNetworkError(Context context, String errorMessage, int titleRes, int defaultErrorMessageRes, FragmentManager fragmentManager) {
        ZenBuilder builder = ZenDialog.builder().withTitle(NetworkUtil.getErrorTitle(context, titleRes));
        if (TextUtils.isEmpty(errorMessage)) {
            builder.withBodyText(defaultErrorMessageRes);
        } else {
            builder.withBodyText(errorMessage);
        }
        builder.withSingleButton(C6418R.string.okay, 0, (Fragment) null).create().showAllowingStateLoss(fragmentManager, null);
    }
}
