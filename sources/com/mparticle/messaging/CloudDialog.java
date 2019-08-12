package com.mparticle.messaging;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.p000v4.app.DialogFragment;
import android.view.ContextThemeWrapper;
import com.airbnb.android.core.responses.OfficialIdStatusResponse;
import com.facebook.internal.AnalyticsEvents;

public class CloudDialog extends DialogFragment implements OnClickListener {
    private static final String DARK_THEME = "mp.dark";
    private static final String LIGHT_THEME = "mp.light";
    public static String TAG = "mp_dialog";
    private OnClickListener mListener;

    public static CloudDialog newInstance(MPCloudNotificationMessage message) {
        CloudDialog cloudDialog = new CloudDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable(MPMessagingAPI.CLOUD_MESSAGE_EXTRA, message);
        cloudDialog.setArguments(bundle);
        return cloudDialog;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int i;
        Builder builder;
        int i2 = 0;
        MPCloudNotificationMessage mPCloudNotificationMessage = (MPCloudNotificationMessage) getArguments().getParcelable(MPMessagingAPI.CLOUD_MESSAGE_EXTRA);
        int i3 = 17301543;
        try {
            i3 = getActivity().getPackageManager().getApplicationInfo(getActivity().getPackageName(), 0).icon;
        } catch (NameNotFoundException e) {
        }
        String inAppTheme = mPCloudNotificationMessage.getInAppTheme();
        if (inAppTheme != null && !inAppTheme.equals(DARK_THEME) && !inAppTheme.equals(LIGHT_THEME)) {
            try {
                i = getResources().getIdentifier(inAppTheme, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, getActivity().getPackageName());
            } catch (Exception e2) {
                i = i2;
            }
        } else if (VERSION.SDK_INT < 11) {
            i = i2;
        } else if (DARK_THEME.equals(inAppTheme)) {
            i = 2;
        } else {
            i = 3;
        }
        if (i <= 0) {
            builder = new Builder(getActivity());
        } else if (VERSION.SDK_INT >= 11) {
            builder = new Builder(getActivity(), i);
        } else {
            builder = new Builder(new ContextThemeWrapper(getActivity(), i));
        }
        builder.setIcon(i3);
        builder.setTitle(mPCloudNotificationMessage.getContentTitle(getActivity()));
        String primaryMessage = mPCloudNotificationMessage.getPrimaryMessage(getActivity());
        String bigText = mPCloudNotificationMessage.getBigText();
        if (bigText != null) {
            builder.setMessage(bigText);
        } else {
            builder.setMessage(primaryMessage);
        }
        builder.setPositiveButton(OfficialIdStatusResponse.f1090OK, this);
        return builder.create();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.mListener = listener;
    }

    public final void onClick(DialogInterface dialog, int which) {
        if (this.mListener != null) {
            this.mListener.onClick(dialog, which);
        }
        dialog.dismiss();
    }
}
