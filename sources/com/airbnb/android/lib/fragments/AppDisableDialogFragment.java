package com.airbnb.android.lib.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.app.FragmentManager;
import android.widget.Toast;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.ExternalAppUtils;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.lib.C0880R;

public class AppDisableDialogFragment extends ZenDialog {
    private static final int KILL_DELAY = 500;
    private final Handler handler = new Handler(Looper.getMainLooper());

    public static AppDisableDialogFragment newInstance() {
        return (AppDisableDialogFragment) new ZenBuilder(new AppDisableDialogFragment()).withTitle(C0880R.string.error).withBodyText(C0880R.string.app_disabled_message).withSingleButton(C0880R.string.upgrade, 1, (Fragment) null).create();
    }

    public void onDismiss(DialogInterface dialog) {
        FragmentActivity activity = getActivity();
        Toast.makeText(activity, C0880R.string.app_disabled_toast_message, 0).show();
        activity.finish();
        this.handler.postDelayed(AppDisableDialogFragment$$Lambda$1.lambdaFactory$(), 500);
    }

    /* access modifiers changed from: protected */
    public void clickSingleButton(int requestCodeSingle) {
        startActivity(new Intent("android.intent.action.VIEW", ExternalAppUtils.getAppStoreUri(getContext())));
    }

    public static void showIfNeeded(FragmentManager fragmentManager) {
        if (isAppVersionDisabled()) {
            AirbnbEventLogger.event().name("android_eng").mo8238kv("type", "app_killed").mo8236kv("build_code", BuildHelper.versionCode()).track();
            newInstance().show(fragmentManager, (String) null);
        }
    }

    private static boolean isAppVersionDisabled() {
        if (!BuildHelper.optEnabled("kill_app_dialog")) {
            if (!Trebuchet.launch(Trebuchet.KEY_KILL_SWITCH, String.format("android-app-kill-%s", new Object[]{Integer.valueOf(BuildHelper.versionCode())}), false)) {
                return false;
            }
        }
        return true;
    }
}
