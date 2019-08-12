package com.jumio.sdk.exception;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.analytics.MetaInfo;
import com.jumio.analytics.MobileEvents;
import com.jumio.analytics.Screen;
import com.jumio.analytics.UserAction;
import com.jumio.gui.Colors;

public class JumioError {

    public interface ErrorInterface {
        void getAction();

        String getCaption();

        int getColorID();
    }

    public static AlertDialog getAlertDialogBuilder(Context context, JumioException error, final ErrorInterface positiveAction, final ErrorInterface negativeAction) {
        boolean z;
        final JumioErrorCase errorCase = error.getErrorCase();
        MetaInfo metaInfo = new MetaInfo();
        metaInfo.put("errorCode", Integer.valueOf(errorCase.code()));
        metaInfo.put("detailedErrorCode", Integer.valueOf(error.getDetailedErrorCase()));
        if (errorCase.message() != null) {
            metaInfo.put("errorMessage", errorCase.message());
        }
        String str = "retryPossible";
        if (!errorCase.retry() || positiveAction == null) {
            z = false;
        } else {
            z = true;
        }
        metaInfo.put(str, Boolean.valueOf(z));
        JumioAnalytics.add(MobileEvents.pageView(JumioAnalytics.getSessionId(), Screen.ERROR, metaInfo));
        Builder builder = new Builder(context);
        builder.setTitle("Error");
        builder.setMessage(errorCase.localizedMessage(context));
        builder.setCancelable(false);
        if (errorCase.retry() && positiveAction != null) {
            builder.setPositiveButton(positiveAction.getCaption(), new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    positiveAction.getAction();
                    JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), Screen.ERROR, UserAction.RETRY, String.valueOf(errorCase.code())));
                }
            });
        }
        if (negativeAction != null) {
            builder.setNegativeButton(negativeAction.getCaption(), new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    negativeAction.getAction();
                    JumioAnalytics.add(MobileEvents.userAction(JumioAnalytics.getSessionId(), Screen.ERROR, UserAction.CANCEL, String.valueOf(errorCase.code())));
                }
            });
        }
        AlertDialog alertDialog = builder.show();
        if (positiveAction != null) {
            alertDialog.getButton(-1).setTextColor(Colors.parseColorStateList(context, positiveAction.getColorID(), -6832627));
        }
        if (negativeAction != null) {
            alertDialog.getButton(-2).setTextColor(Colors.parseColorStateList(context, negativeAction.getColorID(), Colors.DIALOG_NEGATIVE_ACTION_COLOR));
        }
        return alertDialog;
    }
}
