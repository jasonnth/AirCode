package com.airbnb.android.core.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings.Secure;
import android.support.p002v7.view.menu.ActionMenuItemView;
import android.support.p002v7.widget.ActionMenuView;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.utils.JacksonUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.utils.TextSizeAndBaselineSpan;
import com.google.android.gms.common.GoogleApiAvailability;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;

public class MiscUtils {
    private static final int FAKE_OVERLAY_CLICK_DURATION_MILLIS = 150;
    private static String androidId;

    public static Dialog createErrorDialog(Context context, int messageId) {
        return createErrorDialog(context, (CharSequence) context.getString(messageId));
    }

    public static Dialog createErrorDialog(Context context, CharSequence message) {
        return new Builder(context).setTitle(C0716R.string.error).setMessage(message).setNeutralButton(C0716R.string.okay, MiscUtils$$Lambda$1.lambdaFactory$()).create();
    }

    public static Uri getRawResourceUri(int rawResourceId) {
        return Uri.parse("android.resource://com.airbnb.android/" + rawResourceId);
    }

    public static Strap sanitize(Strap parameters) {
        Strap sanitizedStrap = Strap.make();
        for (Entry<String, String> entry : parameters.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if (!(key == null || value == null)) {
                sanitizedStrap.mo11639kv(key, value);
            }
        }
        return sanitizedStrap;
    }

    public static Spannable makeCurrencyTextSmall(Context context, String priceString) {
        String currencySymbol = CoreApplication.instance(context).component().currencyHelper().getLocalCurrencySymbol();
        int start = priceString.indexOf(currencySymbol);
        if (start == -1) {
            return new SpannableString(priceString);
        }
        int end = start + currencySymbol.length();
        SpannableString workingString = new SpannableString(priceString);
        workingString.setSpan(TextSizeAndBaselineSpan.forPricetag(), start, end, 17);
        return workingString;
    }

    public static void requestFocusAndOpenKeyboard(Context context, View view) {
        view.requestFocus();
        openKeyboard(context, view);
    }

    public static void openKeyboard(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(view, 1);
    }

    public static boolean isSmallScreen(Context context) {
        if (context == null) {
            return false;
        }
        return context.getResources().getBoolean(C0716R.bool.is_small_screen);
    }

    public static boolean isTabletScreen(Context context) {
        if (context == null) {
            return false;
        }
        return context.getResources().getBoolean(C0716R.bool.is_tablet);
    }

    public static boolean isLandscape(Context context) {
        if (context != null && context.getResources().getConfiguration().orientation == 2) {
            return true;
        }
        return false;
    }

    public static boolean isWideTabletScreen(Context context) {
        return context.getResources().getBoolean(C0716R.bool.is_wide_tablet);
    }

    public static boolean hasGooglePlayServices(Context context) {
        if (AppLaunchUtils.isUserInChina()) {
            return false;
        }
        try {
            DebugSettings debugSettings = CoreApplication.instance(context).component().debugSettings();
            if (DebugSettings.SIMULATE_NO_GOOGLE_PLAY_SERVICES.isEnabled() || GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) != 0) {
                return false;
            }
            return true;
        } catch (RuntimeException e) {
            BugsnagWrapper.notify((Throwable) e);
            return false;
        }
    }

    public static void setGoneIf(int resId, View container, boolean goneIfTrue) {
        ViewUtils.setGoneIf(ButterKnife.findById(container, resId), goneIfTrue);
    }

    public static Bitmap getBitmapFromView(MemoryUtils memoryUtils, View view) {
        Bitmap newBitmap = null;
        try {
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
            Bitmap bitmap = view.getDrawingCache();
            if (bitmap != null) {
                newBitmap = bitmap.copy(Config.RGB_565, false);
            }
            view.destroyDrawingCache();
            view.setDrawingCacheEnabled(false);
        } catch (OutOfMemoryError e) {
            memoryUtils.handleCaughtOOM("get_bitmap_from_view");
            AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv("type", "out_of_memory_bitmap_from_view"));
            if (BuildHelper.isDevelopmentBuild()) {
                throw e;
            }
        }
        return newBitmap;
    }

    public static String getTelephonyOperatorName(Context context) {
        if (context.getPackageManager().hasSystemFeature("android.hardware.telephony")) {
            return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
        }
        return null;
    }

    public static void copyToClipboard(Context context, String text) {
        copyToClipboard(context, text, C0716R.string.copied_to_clipboard);
    }

    public static void copyToClipboard(Context context, String text, int toastResId) {
        Toast.makeText(context, toastResId, 0).show();
        ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("airbnb-text", text));
    }

    @TargetApi(19)
    public static int getImmersiveModeFlags() {
        return 5125;
    }

    public static String getAndroidId(Context context) {
        if (androidId == null) {
            androidId = Secure.getString(context.getContentResolver(), JPushReportInterface.ANDROID_ID);
        }
        return androidId;
    }

    public static boolean canDoRecommendedContacts() {
        return !"xiaomi".equalsIgnoreCase(Build.MANUFACTURER) && !"pantech".equalsIgnoreCase(Build.MANUFACTURER);
    }

    public static boolean canHandleIntent(Context context, Intent intent) {
        return intent.resolveActivity(context.getPackageManager()) != null;
    }

    public static void fakeOverlayClick(View overlay) {
        overlay.setPressed(true);
        overlay.postDelayed(MiscUtils$$Lambda$2.lambdaFactory$(overlay), 150);
    }

    public static boolean isUsZipCode(String zipCode) {
        return Pattern.matches("^\\d{5}(-\\d{4})?$", zipCode);
    }

    public static boolean isValidAchName(String name) {
        return Pattern.matches("^[-,.A-Za-z0-9 ]+$", name);
    }

    public static int[] getSwipeRefreshColors() {
        return new int[]{C0716R.color.c_rausch, C0716R.color.c_rausch_dark, C0716R.color.c_rausch_dark, C0716R.color.c_rausch_special};
    }

    public static String sha1Hash(String text) {
        return hashWithAlgorithm("SHA-1", text);
    }

    private static String hashWithAlgorithm(String algorithm, String stringToHash) {
        try {
            return hashBytes(MessageDigest.getInstance(algorithm), stringToHash.getBytes());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private static String hashBytes(MessageDigest hash, byte[] bytes) {
        hash.update(bytes);
        byte[] digest = hash.digest();
        StringBuilder builder = new StringBuilder();
        for (byte b : digest) {
            builder.append(Integer.toHexString((b >> 4) & 15));
            builder.append(Integer.toHexString(b & 15));
        }
        return builder.toString();
    }

    public static boolean getBooleanFromAttribute(Activity activity, int attribute) {
        TypedValue typedValue = new TypedValue();
        activity.getTheme().resolveAttribute(attribute, typedValue, true);
        TypedArray a = activity.obtainStyledAttributes(typedValue.data, new int[]{attribute});
        boolean value = a.getBoolean(0, false);
        a.recycle();
        return value;
    }

    public static String getPrettyStackTrace() {
        LinkedHashSet<String> output = new LinkedHashSet<>();
        List<StackTraceElement> stack = new ArrayList<>();
        stack.addAll(Arrays.asList(Thread.currentThread().getStackTrace()));
        while (!stack.isEmpty()) {
            if (((StackTraceElement) stack.remove(0)).getClassName().contains(BuildHelper.applicationId())) {
                break;
            }
        }
        for (StackTraceElement e : stack) {
            if (e.getClassName().contains(BuildHelper.applicationId())) {
                String[] classNameParts = e.getClassName().split("\\.");
                output.add(classNameParts[classNameParts.length - 1]);
            }
        }
        return output.toString();
    }

    public static boolean shouldDisableTranslations() {
        return true;
    }

    public static void showTextViewIfNeeded(TextView textView, String text) {
        boolean empty = TextUtils.isEmpty(text);
        if (!empty) {
            textView.setText(text);
        }
        ViewUtils.setGoneIf(textView, empty);
    }

    public static CharSequence getHostFromUrl(CharSequence url) {
        if (TextUtils.isEmpty(url)) {
            return "";
        }
        try {
            return new URL(url.toString()).getHost();
        } catch (MalformedURLException e) {
            BugsnagWrapper.notify((Throwable) new IllegalArgumentException("Attempted to get host from a malformed URL"));
            return url;
        }
    }

    public static String getAppUriForAppIndexing(String relativePath) {
        return "android-app://" + BuildHelper.applicationId() + "/airbnb/" + relativePath;
    }

    public static String getAppIndexingTitleForListing(Listing listing) {
        if (TextUtils.isEmpty(listing.getLocation())) {
            return listing.getName();
        }
        return listing.getName() + " " + listing.getLocation();
    }

    public static boolean isUserAMonkey() {
        return ActivityManager.isUserAMonkey();
    }

    public static byte[] hashWithAlgorithm(byte[] bytes, String algorithm) {
        byte[] res = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(bytes);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            BugsnagWrapper.throwOrNotify(new RuntimeException("hashWithAlgorithm algorithm not found : " + algorithm));
            return res;
        }
    }

    public static Map<String, Object> jsonObjectToMap(Context context, JSONObject object) {
        try {
            return (Map) JacksonUtils.readerForType(CoreApplication.instance(context).component().objectMapper(), Map.class).readValue(object.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't happen");
        }
    }

    public static ActionMenuItemView getActionMenuItemView(AirToolbar toolbar) {
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof ActionMenuView) {
                for (int j = 0; j < ((ActionMenuView) view).getChildCount(); j++) {
                    View childView = ((ActionMenuView) view).getChildAt(j);
                    if (childView instanceof ActionMenuItemView) {
                        return (ActionMenuItemView) childView;
                    }
                }
                continue;
            }
        }
        return null;
    }
}
