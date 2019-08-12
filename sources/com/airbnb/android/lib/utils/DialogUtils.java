package com.airbnb.android.lib.utils;

import android.content.Context;
import android.support.p000v4.app.DialogFragment;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import com.airbnb.android.core.analytics.SecurityCheckAnalytics;
import com.airbnb.android.core.fragments.ProgressDialogFragment;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.identity.C6533R;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.TextUtil;

public final class DialogUtils {
    public static final String CONTACT_DIALOG_TAG = "contact_dialog";
    private static final String PROGRESS_DIALOG_TAG = "photo_upload_progress_dialog_tag";

    private DialogUtils() {
    }

    public static void showProgressDialog(Context context, FragmentManager fragmentManager, int titleRes, int subtitleRes) {
        showProgressDialog(context, fragmentManager, titleRes, subtitleRes, true);
    }

    public static void showProgressDialog(Context context, FragmentManager fragmentManager, int titleRes, int subtitleRes, boolean cancelable) {
        if (((ProgressDialogFragment) fragmentManager.findFragmentByTag(PROGRESS_DIALOG_TAG)) == null) {
            ProgressDialogFragment progressDialog = ProgressDialogFragment.newInstance(context, titleRes, subtitleRes);
            progressDialog.setCancelable(cancelable);
            progressDialog.show(fragmentManager, PROGRESS_DIALOG_TAG);
        }
    }

    public static void hideProgressDialog(FragmentManager fragmentManager) {
        ProgressDialogFragment progressDialog = (ProgressDialogFragment) fragmentManager.findFragmentByTag(PROGRESS_DIALOG_TAG);
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public static void showContactDialog(Fragment fragment, int requestCode, Fragment f, int bodyText) {
        FragmentManager fragmentManager = fragment.getFragmentManager();
        Context context = fragment.getContext();
        if (fragmentManager.findFragmentByTag(CONTACT_DIALOG_TAG) == null) {
            SecurityCheckAnalytics.trackContactImpression(null);
            DialogFragment dialog = ZenDialog.builder().withTitle(C0880R.string.security_check_contact_title).withBodyText(TextUtil.fromHtmlSafe(context.getString(bodyText, new Object[]{context.getString(C6533R.string.account_verification_contact_email)})).toString()).withSingleButton(C6533R.string.security_check_contact, requestCode, f).create();
            dialog.setCancelable(false);
            dialog.show(fragmentManager, CONTACT_DIALOG_TAG);
        }
    }
}
