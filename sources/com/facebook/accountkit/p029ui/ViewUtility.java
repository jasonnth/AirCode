package com.facebook.accountkit.p029ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.graphics.ColorUtils;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.airbnb.android.airmapview.AirMapInterface;
import com.facebook.accountkit.C3354R;
import com.facebook.accountkit.p029ui.SkinManager.Skin;
import com.jumio.gui.Drawables;

/* renamed from: com.facebook.accountkit.ui.ViewUtility */
final class ViewUtility {
    private static final double TEXT_COLOR_CONTRAST_THRESHOLD = 1.5d;

    ViewUtility() {
    }

    static void applyThemeAttributes(Context context, UIManager uiManager, View view) {
        if (context != null && view != null) {
            if (view instanceof Button) {
                applyButtonThemeAttributes(context, uiManager, (Button) view);
            } else if (view instanceof EditText) {
                applyInputThemeAttributes(context, uiManager, (EditText) view);
            } else if (view instanceof ProgressBar) {
                applyProgressBarThemeAttributes(context, uiManager, (ProgressBar) view);
            } else if (view instanceof CountryCodeSpinner) {
                applySpinnerThemeAttributes(context, uiManager, (CountryCodeSpinner) view);
            } else if (view instanceof TextView) {
                applyTextViewThemeAttributes(context, uiManager, (TextView) view);
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int count = viewGroup.getChildCount();
                for (int i = 0; i < count; i++) {
                    applyThemeAttributes(context, uiManager, viewGroup.getChildAt(i));
                }
            }
        }
    }

    static void applyThemeBackground(Context context, UIManager uiManager, View view) {
        if (context != null && view != null) {
            if (uiManager instanceof SkinManager) {
                applySkinThemedBackground(context, (SkinManager) uiManager, view);
            } else {
                applyLegacyThemedBackground(context, view);
            }
        }
    }

    private static void applySkinThemedBackground(Context context, SkinManager skinManager, View view) {
        Drawable background;
        if (skinManager.hasBackgroundImage()) {
            background = getDrawable(context.getResources(), skinManager.getBackgroundImageResId());
        } else {
            background = new ColorDrawable(ContextCompat.getColor(context, C3354R.color.com_accountkit_default_skin_background));
        }
        if (skinManager.hasBackgroundImage()) {
            if (view instanceof AspectFrameLayout) {
                ((AspectFrameLayout) view).setAspectWidth(background.getIntrinsicWidth());
                ((AspectFrameLayout) view).setAspectHeight(background.getIntrinsicHeight());
            }
            background.setColorFilter(skinManager.getTintColor(), Mode.SRC_ATOP);
        }
        setBackground(view, background);
    }

    private static Drawable getLegacyThemedBackground(Context context, View view) {
        Drawable drawable;
        Theme theme = context.getTheme();
        TypedValue drawableValue = new TypedValue();
        theme.resolveAttribute(C3354R.attr.com_accountkit_background, drawableValue, true);
        if (drawableValue.resourceId == 0) {
            drawable = new ColorDrawable(getColor(context, C3354R.attr.com_accountkit_background_color, -1));
        } else {
            drawable = getDrawable(context.getResources(), drawableValue.resourceId);
        }
        if (drawableValue.resourceId > 0) {
            if (view instanceof AspectFrameLayout) {
                ((AspectFrameLayout) view).setAspectWidth(drawable.getIntrinsicWidth());
                ((AspectFrameLayout) view).setAspectHeight(drawable.getIntrinsicHeight());
            }
            applyThemeColor(context, drawable, getColor(context, C3354R.attr.com_accountkit_background_color, -1));
        }
        return drawable;
    }

    private static void applyLegacyThemedBackground(Context context, View view) {
        setBackground(view, getLegacyThemedBackground(context, view));
    }

    static void applyThemeColor(Context context, Drawable drawable, int color) {
        if (context != null && drawable != null) {
            drawable.setColorFilter(color, Mode.SRC_ATOP);
        }
    }

    static void applyThemeColor(Context context, ImageView imageView, int color) {
        if (context != null && imageView != null) {
            imageView.setColorFilter(color, Mode.SRC_ATOP);
        }
    }

    static int getDimensionPixelSize(Context context, int size) {
        return (int) TypedValue.applyDimension(1, (float) size, context.getResources().getDisplayMetrics());
    }

    static boolean isTablet(Context context) {
        boolean xlarge;
        boolean large;
        if ((context.getResources().getConfiguration().screenLayout & 15) == 4) {
            xlarge = true;
        } else {
            xlarge = false;
        }
        if ((context.getResources().getConfiguration().screenLayout & 15) == 3) {
            large = true;
        } else {
            large = false;
        }
        if (xlarge || large) {
            return true;
        }
        return false;
    }

    static void showKeyboard(View view) {
        if (view != null && view.getContext() != null && view.requestFocus()) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 1);
        }
    }

    static void hideKeyboard(Activity activity) {
        View rootView = activity.findViewById(16908290);
        if (rootView != null) {
            View focusView = rootView.findFocus();
            if (focusView != null) {
                focusView.clearFocus();
            }
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(rootView.getWindowToken(), 1);
        }
    }

    static boolean useLegacy(UIManager uiManager) {
        return !(uiManager instanceof SkinManager);
    }

    static boolean isSkin(UIManager uiManager, Skin skin) {
        return (uiManager instanceof SkinManager) && ((SkinManager) uiManager).getSkin() == skin;
    }

    private static void applyButtonThemeAttributes(Context context, UIManager uiManager, Button button) {
        int enabledFilledColor;
        int enabledBorderColor;
        int pressedFilledColor;
        int pressedBorderColor;
        int disabledFilledColor;
        int disabledBorderColor;
        if (!useLegacy(uiManager)) {
            int primaryColor = getPrimaryColor(context, uiManager);
            int disabledColor = ((SkinManager) uiManager).getDisabledColor(primaryColor);
            enabledFilledColor = primaryColor;
            enabledBorderColor = primaryColor;
            if (isSkin(uiManager, Skin.TRANSLUCENT)) {
                pressedFilledColor = 0;
            } else {
                pressedFilledColor = primaryColor;
            }
            pressedBorderColor = primaryColor;
            if (isSkin(uiManager, Skin.TRANSLUCENT)) {
                disabledFilledColor = 0;
            } else {
                disabledFilledColor = disabledColor;
            }
            if (isSkin(uiManager, Skin.TRANSLUCENT)) {
                disabledBorderColor = primaryColor;
            } else {
                disabledBorderColor = disabledColor;
            }
        } else {
            enabledFilledColor = getButtonColor(context, uiManager);
            enabledBorderColor = getColor(context, C3354R.attr.com_accountkit_button_border_color, enabledFilledColor);
            pressedFilledColor = getColor(context, C3354R.attr.com_accountkit_button_pressed_background_color, (int) Drawables.DEFAULT_LIST_ROW_COLOR_PRESSED);
            pressedBorderColor = getColor(context, C3354R.attr.com_accountkit_button_pressed_border_color, pressedFilledColor);
            disabledFilledColor = getColor(context, C3354R.attr.com_accountkit_button_disabled_background_color, (int) Drawables.DEFAULT_LIST_ROW_COLOR_PRESSED);
            disabledBorderColor = getColor(context, C3354R.attr.com_accountkit_button_disabled_border_color, disabledFilledColor);
        }
        setBackground(button, getButtonBackgroundDrawable(context, enabledFilledColor, enabledBorderColor, pressedFilledColor, pressedBorderColor, disabledFilledColor, disabledBorderColor));
        ColorStateList buttonTextColor = getButtonTextColorStateList(context, uiManager);
        button.setTextColor(buttonTextColor);
        Drawable[] drawables = button.getCompoundDrawables();
        if (drawables.length >= 4) {
            for (Drawable drawable : drawables) {
                if (drawable != null) {
                    DrawableCompat.setTintList(DrawableCompat.wrap(drawable), buttonTextColor);
                }
            }
        }
    }

    private static Drawable getButtonBackgroundDrawable(Context context, int enabledFilledColor, int enabledBorderColor, int pressedFilledColor, int pressedBorderColor, int disabledFilledColor, int disabledBorderColor) {
        StateListDrawable background = new StateListDrawable();
        if (VERSION.SDK_INT >= 21) {
            background.addState(new int[]{-16842910}, new RippleDrawable(new ColorStateList(new int[][]{new int[0]}, new int[]{enabledFilledColor}), new ColorDrawable(disabledFilledColor), null));
            background.addState(new int[0], new RippleDrawable(new ColorStateList(new int[][]{new int[0]}, new int[]{disabledFilledColor}), new ColorDrawable(enabledFilledColor), null));
        } else {
            background.addState(new int[]{-16842910}, getInputBackgroundDrawable(context, disabledFilledColor, disabledBorderColor));
            background.addState(new int[]{16842919}, getInputBackgroundDrawable(context, pressedFilledColor, pressedBorderColor));
            background.addState(new int[0], getInputBackgroundDrawable(context, enabledFilledColor, enabledBorderColor));
        }
        return background;
    }

    static int getButtonTextColor(Context context, UIManager uiManager) {
        if (!useLegacy(uiManager)) {
            return ((SkinManager) uiManager).getTextColor();
        }
        return getColor(context, C3354R.attr.com_accountkit_button_text_color, (int) AirMapInterface.CIRCLE_BORDER_COLOR);
    }

    private static ColorStateList getButtonTextColorStateList(Context context, UIManager uiManager) {
        int[] buttonTextColors;
        int[][] states = {new int[]{-16842910}, new int[]{16842919}, new int[0]};
        if (!useLegacy(uiManager)) {
            int textColor = ((SkinManager) uiManager).getTextColor();
            buttonTextColors = new int[]{textColor, textColor, textColor};
        } else {
            buttonTextColors = new int[]{getColor(context, C3354R.attr.com_accountkit_button_disabled_text_color, (int) Drawables.DEFAULT_LIST_ROW_COLOR_PRESSED), getColor(context, C3354R.attr.com_accountkit_button_pressed_text_color, -12303292), getColor(context, C3354R.attr.com_accountkit_button_text_color, (int) AirMapInterface.CIRCLE_BORDER_COLOR)};
        }
        return new ColorStateList(states, buttonTextColors);
    }

    private static void applyTextViewThemeAttributes(Context context, UIManager uiManager, TextView textView) {
        int textColor;
        if (useLegacy(uiManager)) {
            textColor = getColor(context, C3354R.attr.com_accountkit_text_color, ContextCompat.getColor(context, 17170433));
        } else {
            textColor = ((SkinManager) uiManager).getTextColor();
        }
        textView.setTextColor(textColor);
        textView.setLinkTextColor(textColor);
    }

    static boolean doesTextColorContrast(Context context, UIManager uiManager) {
        Theme theme;
        int textColor;
        int dominantColor;
        if (uiManager.getThemeId() != -1) {
            theme = context.getResources().newTheme();
            theme.setTo(context.getTheme());
            theme.applyStyle(uiManager.getThemeId(), true);
        } else {
            theme = context.getTheme();
        }
        if (useLegacy(uiManager)) {
            textColor = getColor(theme, C3354R.attr.com_accountkit_text_color, ContextCompat.getColor(context, 17170433));
        } else {
            textColor = ((SkinManager) uiManager).getTextColor();
        }
        if (useLegacy(uiManager)) {
            dominantColor = getColor(theme, C3354R.attr.com_accountkit_background_color, -1);
        } else {
            dominantColor = ((SkinManager) uiManager).getTintColor();
        }
        if (ColorUtils.calculateContrast(textColor | AirMapInterface.CIRCLE_BORDER_COLOR, dominantColor | AirMapInterface.CIRCLE_BORDER_COLOR) >= TEXT_COLOR_CONTRAST_THRESHOLD) {
            return true;
        }
        return false;
    }

    private static void applyInputThemeAttributes(Context context, UIManager uiManager, EditText input) {
        if (!useLegacy(uiManager)) {
            input.setTextColor(((SkinManager) uiManager).getTextColor());
        }
        if (isSkin(uiManager, Skin.CONTEMPORARY)) {
            int primaryColor = getPrimaryColor(context, uiManager);
            Drawable draw = DrawableCompat.wrap(input.getBackground()).mutate();
            DrawableCompat.setTint(draw, primaryColor);
            setBackground(input, draw);
            input.setTextColor(((SkinManager) uiManager).getTextColor());
            return;
        }
        applyInputThemeAttributes(context, uiManager, (View) input);
    }

    private static void applyInputThemeAttributes(Context context, UIManager uiManager, View input) {
        if (useLegacy(uiManager)) {
            int fillColor = getColor(context, C3354R.attr.com_accountkit_input_background_color, (int) Drawables.DEFAULT_LIST_ROW_COLOR_PRESSED);
            setBackground(input, getInputBackgroundDrawable(context, fillColor, getColor(context, C3354R.attr.com_accountkit_input_border_color, fillColor)));
        } else if (isSkin(uiManager, Skin.TRANSLUCENT)) {
            setBackground(input, getInputBackgroundDrawable(context, 0, getPrimaryColor(context, uiManager)));
        } else {
            int fillColor2 = ((SkinManager) uiManager).getDisabledColor(getPrimaryColor(context, uiManager));
            setBackground(input, getInputBackgroundDrawable(context, fillColor2, fillColor2));
        }
    }

    private static void applyProgressBarThemeAttributes(Context context, UIManager uiManager, ProgressBar progressBar) {
        int color;
        Drawable drawable = progressBar.getIndeterminateDrawable();
        if (useLegacy(uiManager)) {
            color = getColor(context, C3354R.attr.com_accountkit_icon_color, (int) AirMapInterface.CIRCLE_BORDER_COLOR);
        } else {
            color = getPrimaryColor(context, uiManager);
        }
        applyThemeColor(context, drawable, color);
    }

    private static void applySpinnerThemeAttributes(Context context, UIManager uiManager, CountryCodeSpinner spinner) {
        ViewGroup viewParent = (ViewGroup) spinner.getParent();
        ImageView countryCodeImage = (ImageView) viewParent.getChildAt(1);
        View underline = viewParent.getChildAt(2);
        Drawable arrowImage = DrawableCompat.wrap(countryCodeImage.getDrawable()).mutate();
        if (isSkin(uiManager, Skin.CONTEMPORARY)) {
            underline.setVisibility(0);
            setBackground(underline, new ColorDrawable(getPrimaryColor(context, uiManager)));
            DrawableCompat.setTint(arrowImage, getPrimaryColor(context, uiManager));
        } else if (isSkin(uiManager, Skin.TRANSLUCENT) || isSkin(uiManager, Skin.CLASSIC)) {
            underline.setVisibility(8);
            DrawableCompat.setTint(arrowImage, ((SkinManager) uiManager).getTextColor());
            applyInputThemeAttributes(context, uiManager, (View) viewParent);
        } else {
            underline.setVisibility(8);
            DrawableCompat.setTint(arrowImage, getColor(context, C3354R.attr.com_accountkit_input_accent_color, (int) AirMapInterface.CIRCLE_BORDER_COLOR));
            applyInputThemeAttributes(context, uiManager, (View) viewParent);
        }
    }

    static int getButtonColor(Context context, UIManager uiManager) {
        if (uiManager instanceof SkinManager) {
            return ((SkinManager) uiManager).getPrimaryColor();
        }
        return getColor(context, C3354R.attr.com_accountkit_button_background_color, (int) Drawables.DEFAULT_LIST_ROW_COLOR_PRESSED);
    }

    static int getPrimaryColor(Context context, UIManager uiManager) {
        if (uiManager instanceof SkinManager) {
            return ((SkinManager) uiManager).getPrimaryColor();
        }
        return getColor(context, C3354R.attr.com_accountkit_primary_color, (int) Drawables.DEFAULT_LIST_ROW_COLOR_PRESSED);
    }

    static int getColor(Context context, int id, int defaultValue) {
        return getColor(context.getTheme(), id, defaultValue);
    }

    static int getColor(Theme theme, int id, int defaultValue) {
        TypedValue colorValue = new TypedValue();
        return theme.resolveAttribute(id, colorValue, true) ? colorValue.data : defaultValue;
    }

    private static Drawable getDrawable(Resources resources, int id) {
        if (VERSION.SDK_INT < 22) {
            return resources.getDrawable(id);
        }
        return resources.getDrawable(id, null);
    }

    private static Drawable getInputBackgroundDrawable(Context context, int fillColor, int borderColor) {
        GradientDrawable drawable = new GradientDrawable();
        Resources resources = context.getResources();
        drawable.setColor(fillColor);
        drawable.setCornerRadius(resources.getDimension(C3354R.dimen.com_accountkit_input_corner_radius));
        drawable.setStroke(resources.getDimensionPixelSize(C3354R.dimen.com_accountkit_input_border), borderColor);
        return drawable;
    }

    static void setBackground(View view, Drawable drawable) {
        if (VERSION.SDK_INT < 16) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }
}
