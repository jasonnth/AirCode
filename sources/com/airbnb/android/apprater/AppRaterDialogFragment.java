package com.airbnb.android.apprater;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.p002v7.app.AlertDialog;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.lib.AppRaterComponent.Builder;

public class AppRaterDialogFragment extends AirDialogFragment {
    AppRaterController appRaterController;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((AppRaterBindings) CoreApplication.instance(getContext()).componentProvider()).appRaterComponentProvider().get()).build().inject(this);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AppRaterAnalytics.trackDialogAction("impression");
        return new AlertDialog.Builder(getContext()).setTitle(C1683R.string.apprater_title).setMessage(C1683R.string.apprater_message).setPositiveButton(C1683R.string.apprater_rate_app_button, AppRaterDialogFragment$$Lambda$1.lambdaFactory$(this)).setNegativeButton(C1683R.string.apprater_remind_me_button, AppRaterDialogFragment$$Lambda$4.lambdaFactory$(this)).setNeutralButton(C1683R.string.apprater_no_thanks_button, AppRaterDialogFragment$$Lambda$5.lambdaFactory$(this)).setOnCancelListener(AppRaterDialogFragment$$Lambda$6.lambdaFactory$(this)).create();
    }

    static /* synthetic */ void lambda$onCreateDialog$0(AppRaterDialogFragment appRaterDialogFragment, DialogInterface dialog, int which) {
        AppRaterAnalytics.trackDialogAction(AppRaterAnalytics.RATE_APP);
        appRaterDialogFragment.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.airbnb.android")));
        appRaterDialogFragment.appRaterController.notifyRateAppClicked();
    }

    static /* synthetic */ void lambda$onCreateDialog$1(AppRaterDialogFragment appRaterDialogFragment, DialogInterface dialog, int which) {
        AppRaterAnalytics.trackDialogAction(AppRaterAnalytics.REMIND_LATER);
        appRaterDialogFragment.appRaterController.notifyRemindMeClicked();
        appRaterDialogFragment.dismiss();
    }

    static /* synthetic */ void lambda$onCreateDialog$2(AppRaterDialogFragment appRaterDialogFragment, DialogInterface dialog, int which) {
        AppRaterAnalytics.trackDialogAction(AppRaterAnalytics.REJECT);
        appRaterDialogFragment.appRaterController.notifyRatingRejected();
        appRaterDialogFragment.dismiss();
    }

    static /* synthetic */ void lambda$onCreateDialog$3(AppRaterDialogFragment appRaterDialogFragment, DialogInterface dialog) {
        AppRaterAnalytics.trackDialogAction(AppRaterAnalytics.DISMISS);
        appRaterDialogFragment.appRaterController.notifyRemindMeClicked();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.AppRaterDialog;
    }
}
