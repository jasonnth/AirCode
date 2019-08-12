package com.airbnb.android.lib.utils;

import android.content.Context;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.lib.C0880R;

public class VRUtils {
    public static final int REQUEST_CODE_VR = 10;
    public static final String TAG_VR_DIALOG = "vr_dialog";

    public static void handleOnActivityResult(Context context) {
        context.startActivity(HomeActivityIntents.intentForHostHome(context));
    }

    public static void showUseVRPLatformDialogIfNeeded(AirbnbAccountManager manager, Fragment f) {
        if (manager.isVRPlatformPoweredHost()) {
            ZenDialog.builder().withTitle(C0880R.string.error).withBodyText(C0880R.string.error_text_use_vr_platform).withSingleButton(C0880R.string.okay, 10, f).withResultOnCancel(10).create().show(f.getFragmentManager(), TAG_VR_DIALOG);
        }
    }
}
