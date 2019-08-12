package com.jumio.gui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint.Style;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.widget.Button;
import com.jumio.commons.utils.ScreenUtil;
import com.jumio.commons.view.CompatibilityLayer;
import com.jumio.core.environment.Environment;

public class Drawables {
    public static final int DEFAULT_BUTTON_TEXT_COLOR = -419430401;
    public static final int DEFAULT_BUTTON_TEXT_COLOR_DISABLED = -1711276033;
    public static final int DEFAULT_BUTTON_TEXT_COLOR_PRESSED = -419430401;
    public static final int DEFAULT_LIST_ROW_COLOR = 0;
    public static final int DEFAULT_LIST_ROW_COLOR_DISABLED = 0;
    public static final int DEFAULT_LIST_ROW_COLOR_PRESSED = -3355444;
    public static final int GREEN_BUTTON_COLOR = -6832627;
    public static final int GREEN_BUTTON_COLOR_DISABLED = 1301790221;
    public static final int GREEN_BUTTON_COLOR_PRESSED = -9401334;
    public static final int LIGHTGRAY_BUTTON_COLOR = -10000537;
    public static final int LIGHTGRAY_BUTTON_COLOR_DISABLED = 1298622311;
    public static final int LIGHTGRAY_BUTTON_COLOR_PRESSED = -13421773;
    public static final int RED_BUTTON_COLOR = -4322035;
    public static final int RED_BUTTON_COLOR_DISABLED = 1304300813;
    public static final int RED_BUTTON_COLOR_PRESSED = -7665142;

    public static class ButtonColor {
        public ColorStateList background;
        public Drawable image;
        public ColorStateList text;
    }

    public static void styleButton(Context context, Button button, ButtonColor buttonColor) {
        Drawable background;
        if (buttonColor.image != null) {
            background = buttonColor.image;
        } else if (Environment.isLollipop()) {
            background = createRippleDrawable(context, buttonColor.background.getDefaultColor());
        } else {
            background = customButton(context, buttonColor.background);
        }
        CompatibilityLayer.setBackground(button, background);
        button.setTextColor(buttonColor.text);
    }

    public static ButtonColor parseButtonColor(Context context, int textColorId, ColorStateList textColorFallback, int backgroundColorId, ColorStateList backgroundFallback) {
        ButtonColor buttonColor = new ButtonColor();
        TypedArray values = context.getTheme().obtainStyledAttributes(new int[]{textColorId, backgroundColorId});
        buttonColor.text = values.getColorStateList(0);
        if (buttonColor.text == null) {
            int color = values.getColor(0, 0);
            if (color != 0) {
                buttonColor.text = ColorStateList.valueOf(color);
            } else {
                buttonColor.text = textColorFallback;
            }
        }
        try {
            buttonColor.image = values.getDrawable(1);
        } catch (Exception e) {
            buttonColor.image = null;
        }
        if (buttonColor.image == null) {
            buttonColor.background = values.getColorStateList(1);
            if (buttonColor.background == null) {
                int color2 = values.getColor(1, 0);
                if (color2 != 0) {
                    buttonColor.background = ColorStateList.valueOf(color2);
                } else {
                    buttonColor.background = backgroundFallback;
                }
            }
        }
        values.recycle();
        return buttonColor;
    }

    public static ColorStateList getDefaultTextColor() {
        return new ColorStateList(new int[][]{new int[]{16842919}, new int[]{16842908}, new int[]{-16842910}, new int[0]}, new int[]{-419430401, -419430401, DEFAULT_BUTTON_TEXT_COLOR_DISABLED, -419430401});
    }

    public static ColorStateList getGreenButtonColor() {
        return new ColorStateList(new int[][]{new int[]{16842919}, new int[]{16842908}, new int[]{-16842910}, new int[0]}, new int[]{GREEN_BUTTON_COLOR_PRESSED, GREEN_BUTTON_COLOR_PRESSED, GREEN_BUTTON_COLOR_DISABLED, -6832627});
    }

    public static ColorStateList getLightGreyButtonColor() {
        return new ColorStateList(new int[][]{new int[]{16842919}, new int[]{16842908}, new int[]{-16842910}, new int[0]}, new int[]{LIGHTGRAY_BUTTON_COLOR_PRESSED, LIGHTGRAY_BUTTON_COLOR_PRESSED, LIGHTGRAY_BUTTON_COLOR_DISABLED, LIGHTGRAY_BUTTON_COLOR});
    }

    public static ColorStateList getRedButtonColor() {
        return new ColorStateList(new int[][]{new int[]{16842919}, new int[]{16842908}, new int[]{-16842910}, new int[0]}, new int[]{RED_BUTTON_COLOR_PRESSED, RED_BUTTON_COLOR_PRESSED, RED_BUTTON_COLOR_DISABLED, RED_BUTTON_COLOR});
    }

    public static ColorStateList getDefaultListRowColor() {
        return new ColorStateList(new int[][]{new int[]{16842919}, new int[]{-16842910}, new int[0]}, new int[]{DEFAULT_LIST_ROW_COLOR_PRESSED, 0, 0});
    }

    public static StateListDrawable customListRow(Context context, ColorStateList buttonColor) {
        StateListDrawable states = new StateListDrawable();
        int[] state = {16842919};
        Drawable pressed = generateRectDrawable(context, buttonColor.getColorForState(state, DEFAULT_LIST_ROW_COLOR_PRESSED));
        states.addState(state, pressed);
        states.addState(new int[]{16842908}, pressed);
        int[] state2 = {-16842910};
        states.addState(state2, generateRectDrawable(context, buttonColor.getColorForState(state2, 0)));
        states.addState(new int[0], generateRectDrawable(context, buttonColor.getDefaultColor()));
        return states;
    }

    public static StateListDrawable customButton(Context context, ColorStateList buttonColor) {
        StateListDrawable states = new StateListDrawable();
        int[] state = {16842919};
        Drawable pressed = generateButtonDrawable(context, buttonColor.getColorForState(state, GREEN_BUTTON_COLOR_PRESSED));
        states.addState(state, pressed);
        states.addState(new int[]{16842908}, pressed);
        int[] state2 = {-16842910};
        states.addState(state2, generateButtonDrawable(context, buttonColor.getColorForState(state2, GREEN_BUTTON_COLOR_DISABLED)));
        states.addState(new int[0], generateButtonDrawable(context, buttonColor.getDefaultColor()));
        return states;
    }

    public static Drawable generateButtonDrawable(Context context, int color) {
        float dp1 = ScreenUtil.dipToPx(context, 1.0f);
        float dp2 = ScreenUtil.dipToPx(context, 2.0f);
        int dp10 = (int) ScreenUtil.dipToPx(context, 10.0f);
        RoundRectShape roundRect = new RoundRectShape(new float[]{dp2, dp2, dp2, dp2, dp2, dp2, dp2, dp2}, null, null);
        ShapeDrawable drawable = new ShapeDrawable(roundRect);
        drawable.setPadding(dp10, dp10, dp10, dp10);
        drawable.getPaint().setStyle(Style.FILL);
        drawable.getPaint().setColor(color);
        ShapeDrawable drawableShadow = new ShapeDrawable(roundRect);
        drawableShadow.getPaint().setColor(1073741824);
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawableShadow, drawable});
        layerDrawable.setLayerInset(0, 0, (int) dp1, 0, 0);
        layerDrawable.setLayerInset(1, 0, 0, 0, (int) dp1);
        return layerDrawable;
    }

    public static Drawable generateRectDrawable(Context context, int color) {
        int dp10 = (int) ScreenUtil.dipToPx(context, 10.0f);
        ShapeDrawable drawable = new ShapeDrawable(new RectShape());
        drawable.setPadding(dp10, dp10, dp10, dp10);
        drawable.getPaint().setStyle(Style.FILL);
        drawable.getPaint().setColor(color);
        return drawable;
    }

    public static Drawable Button_Help_Background(Context context, int color) {
        ShapeDrawable border = new ShapeDrawable(new RectShape());
        border.getPaint().setColor(color);
        border.getPaint().setStyle(Style.STROKE);
        border.getPaint().setStrokeWidth(ScreenUtil.dipToPx(context, 1.0f));
        return border;
    }

    public static Drawable createRippleDrawable(Context c, int color) {
        Drawable bg = new ColorDrawable(color);
        TypedArray a = c.obtainStyledAttributes(new int[]{16843534});
        Drawable ripple = a.getDrawable(0);
        a.recycle();
        return new LayerDrawable(new Drawable[]{bg, ripple});
    }
}
