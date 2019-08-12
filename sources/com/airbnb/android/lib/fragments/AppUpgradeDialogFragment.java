package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.p000v4.app.FragmentManager;
import android.text.TextUtils;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.GeneralAnalytics;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.ExternalAppUtils;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;

public final class AppUpgradeDialogFragment extends ZenDialog {
    private static final String KEY_CANCEL_COUNT = "cancel_count";
    private static final String KEY_LATEST_VERSION_CODE = "latest_version_code";
    private static final int UPGRADE_DIALOG_MAX_CANCEL_COUNT = 10;
    private static final long UPGRADE_DIALOG_REFRESH_MS = 86400000;
    AirbnbPreferences preferences;
    private final SharedPreferences sharedPrefs = this.preferences.getSharedPreferences();

    public AppUpgradeDialogFragment() {
        ((AirbnbGraph) AirbnbApplication.instance().component()).inject(this);
    }

    public static AppUpgradeDialogFragment newInstance(Context context) {
        String bodyText;
        String versionName = Trebuchet.launch("latest_version", "version_name", "");
        AirbnbEventLogger.track(GeneralAnalytics.AppOpen, "view_upgrade_dialog", "impressions");
        if (!TextUtils.isEmpty(versionName)) {
            bodyText = context.getString(C0880R.string.upgrade_dialog_message, new Object[]{versionName});
        } else {
            bodyText = context.getString(C0880R.string.upgrade_dialog_empty_message);
        }
        return (AppUpgradeDialogFragment) new ZenBuilder(new AppUpgradeDialogFragment()).withBodyText(bodyText).withTitle(C0880R.string.upgrade_dialog_title).setCancelable(true).withDualButton(C0880R.string.cancel, 1, C0880R.string.update, 2).create();
    }

    /* access modifiers changed from: protected */
    public void clickLeftButton(int requestCodeLeft) {
        onClickCancel();
    }

    /* access modifiers changed from: protected */
    public void clickRightButton(int requestCodeRight) {
        onClickUpgrade();
    }

    public static void showIfNeeded(Context context, FragmentManager fragmentManager) {
        SharedPreferences sharedPrefs2 = AirbnbApplication.instance().component().airbnbPreferences().getSharedPreferences();
        int latestVersionCode = getVersionCodeFromTrebuchet();
        String latestVersionKey = "latest_version_code:" + latestVersionCode;
        if (isUpgradeNeeded(latestVersionCode, sharedPrefs2.getLong(latestVersionKey, 0), sharedPrefs2.getInt("cancel_count:" + latestVersionCode, 0))) {
            sharedPrefs2.edit().putLong(latestVersionKey, System.currentTimeMillis()).apply();
            newInstance(context).show(fragmentManager, (String) null);
        }
    }

    private static int getVersionCodeFromTrebuchet() {
        return Trebuchet.launch("latest_version", "version_code", 0);
    }

    private static boolean isUpgradeNeeded(int latestVersionCode, long lastDialogDisplayMs, int cancelCount) {
        return BuildHelper.optEnabled("upgrade_dialog") || (latestVersionCode > BuildHelper.versionCode() && System.currentTimeMillis() - lastDialogDisplayMs > 86400000 && cancelCount < 10);
    }

    private void onClickUpgrade() {
        AirbnbEventLogger.track(GeneralAnalytics.AppOpen, "view_upgrade_dialog", "click_upgrade");
        getActivity().startActivity(new Intent("android.intent.action.VIEW", ExternalAppUtils.getAppStoreUri(getContext())));
    }

    private void onClickCancel() {
        AirbnbEventLogger.track(GeneralAnalytics.AppOpen, "view_upgrade_dialog", "click_cancel_upgrade");
        String cancelCountKey = "cancel_count:" + getVersionCodeFromTrebuchet();
        this.preferences.getSharedPreferences().edit().putInt(cancelCountKey, this.sharedPrefs.getInt(cancelCountKey, 0) + 1).apply();
    }

    public static void cleanupOldUpdateDialogPrefs() {
        SharedPreferences sharedPrefs2 = AirbnbApplication.instance().component().airbnbPreferences().getSharedPreferences();
        Editor editor = sharedPrefs2.edit();
        for (String key : sharedPrefs2.getAll().keySet()) {
            if (key.startsWith(KEY_LATEST_VERSION_CODE) || key.startsWith(KEY_CANCEL_COUNT)) {
                editor.remove(key);
            }
        }
        editor.apply();
    }
}
