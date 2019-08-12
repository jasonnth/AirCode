package com.airbnb.android.core.utils;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.utils.SnackbarWrapper;

public final class ErrorUtils {
    private ErrorUtils() {
    }

    public static Snackbar showErrorUsingSnackbar(View view, int titleRes, int errorBodyRes) {
        return showErrorUsingSnackbar(view, view.getContext().getString(titleRes), view.getContext().getString(errorBodyRes), 0);
    }

    public static Snackbar showErrorUsingSnackbar(View view, int titleRes, int errorBodyRes, int duration) {
        return showErrorUsingSnackbar(view, view.getContext().getString(titleRes), view.getContext().getString(errorBodyRes), duration);
    }

    public static Snackbar showErrorUsingSnackbar(View view, String errorBody) {
        return showErrorUsingSnackbar(view, view.getContext().getString(C0716R.string.error), errorBody, 0);
    }

    public static Snackbar showErrorUsingSnackbar(View view, String errorBody, int duration) {
        return showErrorUsingSnackbar(view, view.getContext().getString(C0716R.string.error), errorBody, duration);
    }

    public static Snackbar showErrorUsingSnackbar(View view, int errorBody) {
        return showErrorUsingSnackbar(view, view.getContext().getString(C0716R.string.error), view.getContext().getString(errorBody), 0);
    }

    public static Snackbar showErrorUsingSnackbar(View view, String title, String errorBody) {
        return showErrorUsingSnackbar(view, title, errorBody, 0);
    }

    public static Snackbar showErrorUsingSnackbar(View view, String title, String body, String action, OnClickListener listener, int duration) {
        return showErrorUsingSnackbarWithAction(view, title, body, action, listener, duration);
    }

    public static Snackbar showErrorUsingSnackbar(View view, String title, String errorBody, int duration) {
        return new SnackbarWrapper().view(view).title(title, true).body(errorBody).duration(duration).buildAndShow();
    }

    public static Snackbar showErrorUsingSnackbarWithAction(View view, String title, String errorBody, String action, OnClickListener listener, int duration) {
        return new SnackbarWrapper().view(view).title(title, true).body(errorBody).duration(duration).action(action, listener).buildAndShow();
    }
}
