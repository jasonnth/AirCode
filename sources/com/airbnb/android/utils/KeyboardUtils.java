package com.airbnb.android.utils;

import android.app.Activity;
import android.content.Context;
import android.support.p002v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public final class KeyboardUtils {
    public static final int KEYBOARD_DELAY_MILLIS = 200;
    public static final int KEYBOARD_DELAY_MILLIS_FOR_ONE_SECOND = 1000;
    private static final String TAG = "KeyboardUtils";

    public static boolean isKeyboardUp(AppCompatActivity activity, View rootView) {
        return (rootView.getRootView().getHeight() - ActivityUtils.getStatusBarActionBarHeight(activity)) - ActivityUtils.getNavBarHeight(activity) > rootView.getHeight();
    }

    public static void dismissSoftKeyboard(View view) {
        if (view == null) {
            Log.d(TAG, "dismissSoftKeyboard: view is null and has most likely been destroyed by containing fragment or activity.");
        } else {
            dismissSoftKeyboard(view.getContext(), view);
        }
    }

    public static void dismissSoftKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService("input_method");
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void dismissSoftKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService("input_method");
        if (imm != null) {
            imm.hideSoftInputFromWindow(activity.findViewById(16908290).getWindowToken(), 0);
        }
    }

    public static void showSoftKeyboard(View view) {
        if (view == null) {
            Log.d(TAG, "showSoftKeyboard: view is null and has most likely been destroyed by containing fragment or activity.");
        } else {
            showSoftKeyboard(view.getContext(), view);
        }
    }

    public static void showSoftKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService("input_method");
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }

    public static void hideSoftKeyboardForCurrentlyFocusedView(Context context) {
        View currentFocus = ((Activity) context).getCurrentFocus();
        if (currentFocus != null) {
            dismissSoftKeyboard(context, currentFocus);
        }
    }

    public static boolean isEnterPress(KeyEvent event) {
        return event != null && event.getAction() == 0 && event.getKeyCode() == 66;
    }

    public static boolean isDoneAction(int actionId) {
        return actionId == 6;
    }

    public static boolean isSearchAction(int actionId) {
        return actionId == 3;
    }

    public static boolean isEnterOrDone(int actionId, KeyEvent event) {
        return isEnterPress(event) || isDoneAction(actionId);
    }

    public static boolean isEnterOrSearch(int actionId, KeyEvent event) {
        return isEnterPress(event) || isSearchAction(actionId);
    }
}
