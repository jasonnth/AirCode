package com.airbnb.p027n2.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;
import android.support.p000v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.fonts.CustomFontSpan;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.google.common.base.Preconditions;

/* renamed from: com.airbnb.n2.utils.SnackbarWrapper */
public class SnackbarWrapper {
    private String action;
    private String body;
    private Callback callback;
    private Context context;
    private int duration = -2;
    private boolean error;
    private OnClickListener listener;
    private Integer orientation;
    private Snackbar snackbar;
    private int themeActionTextColor;
    private int themeBackgroundColor;
    private Font themeBodyFont;
    private int themeBodyTextColor;
    private String title;
    private View view;

    public SnackbarWrapper view(View view2) {
        this.view = view2;
        this.context = view2.getContext();
        return this;
    }

    public SnackbarWrapper title(String title2, boolean error2) {
        this.title = title2;
        this.error = error2;
        return this;
    }

    public SnackbarWrapper title(int titleRes, boolean error2) {
        if (this.view != null) {
            return title(this.view.getContext().getString(titleRes), error2);
        }
        throw new IllegalStateException("Must set view before setting a title resource");
    }

    public SnackbarWrapper body(String body2) {
        this.body = body2;
        return this;
    }

    public SnackbarWrapper body(int bodyResourceId) {
        this.body = this.view.getContext().getString(bodyResourceId);
        return this;
    }

    public SnackbarWrapper action(int action2, OnClickListener listener2) {
        this.action = this.context.getString(action2);
        this.listener = listener2;
        return this;
    }

    public SnackbarWrapper action(String action2, OnClickListener listener2) {
        this.action = action2;
        this.listener = listener2;
        return this;
    }

    public SnackbarWrapper duration(int duration2) {
        this.duration = duration2;
        return this;
    }

    public SnackbarWrapper orientation(int orientation2) {
        this.orientation = Integer.valueOf(orientation2);
        return this;
    }

    public SnackbarWrapper callBack(Callback callback2) {
        this.callback = callback2;
        return this;
    }

    public Snackbar buildAndShow() {
        return buildAndShow(1);
    }

    public Snackbar buildAndShow(int theme) {
        Preconditions.checkNotNull(this.view, "View is null. Did you forget to set one?");
        setTheme(theme);
        this.snackbar = Snackbar.make(this.view, getSnackbarText(), this.duration);
        modifyViews();
        if (this.action != null) {
            setAndColorAction(this.snackbar, this.action, this.listener);
        }
        setBackgroundColor(this.snackbar, this.themeBackgroundColor);
        if (this.callback != null) {
            this.snackbar.setCallback(this.callback);
        }
        this.snackbar.show();
        return this.snackbar;
    }

    public void dismiss() {
        if (this.snackbar == null) {
            Log.e(SnackbarWrapper.class.getName(), "Snackbar hasn't been built and shown yet.");
        } else {
            this.snackbar.dismiss();
        }
    }

    public boolean isShown() {
        return this.snackbar != null && this.snackbar.isShown();
    }

    private SnackbarWrapper setTheme(int theme) {
        switch (theme) {
            case 1:
                this.themeBackgroundColor = -1;
                this.themeBodyTextColor = ContextCompat.getColor(this.context, R.color.n2_text_color_main);
                this.themeBodyFont = Font.CircularLight;
                this.themeActionTextColor = ContextCompat.getColor(this.context, R.color.n2_text_color_actionable);
                break;
            case 2:
                this.themeBackgroundColor = ContextCompat.getColor(this.context, R.color.n2_babu);
                this.themeBodyTextColor = -1;
                this.themeBodyFont = Font.CircularBold;
                this.themeActionTextColor = ContextCompat.getColor(this.context, R.color.n2_babu_dark);
                break;
            default:
                throw new IllegalArgumentException("Unknown theme " + theme);
        }
        return this;
    }

    private void modifyViews() {
        View view2 = this.snackbar.getView();
        Button actionButton = (Button) view2.findViewById(R.id.snackbar_action);
        actionButton.setTransformationMethod(null);
        ((TextView) view2.findViewById(R.id.snackbar_text)).setMaxLines(10);
        if (this.orientation != null && this.orientation.intValue() == 1) {
            ((LayoutParams) actionButton.getLayoutParams()).gravity = 3;
            actionButton.setPadding(actionButton.getPaddingLeft(), actionButton.getPaddingTop(), view2.getContext().getResources().getDimensionPixelSize(R.dimen.n2_snackbar_action_button_padding_vertical_orientation), actionButton.getPaddingBottom());
        }
    }

    private void setAndColorAction(Snackbar snackbar2, String action2, OnClickListener listener2) {
        ForegroundColorSpan clickableColorSpan = new ForegroundColorSpan(this.themeActionTextColor);
        CustomFontSpan circularBookSpan = new CustomFontSpan(this.context, Font.CircularBook);
        SpannableStringBuilder actionTextBuilder = new SpannableStringBuilder();
        actionTextBuilder.append(action2);
        actionTextBuilder.setSpan(circularBookSpan, 0, actionTextBuilder.length(), 17);
        actionTextBuilder.setSpan(clickableColorSpan, 0, actionTextBuilder.length(), 17);
        snackbar2.setAction((CharSequence) actionTextBuilder, listener2);
    }

    private CharSequence getSnackbarText() {
        ForegroundColorSpan mainColorSpan = new ForegroundColorSpan(this.themeBodyTextColor);
        ForegroundColorSpan errorColorSpan = new ForegroundColorSpan(ContextCompat.getColor(this.context, R.color.n2_arches));
        CustomFontSpan circularBookSpan = new CustomFontSpan(this.context, Font.CircularBook);
        CustomFontSpan circularLightSpan = new CustomFontSpan(this.context, this.themeBodyFont);
        SpannableStringBuilder snackbarTextBuilder = new SpannableStringBuilder();
        int titleStart = snackbarTextBuilder.length();
        if (this.title != null) {
            snackbarTextBuilder.append(this.title);
            if (this.body != null) {
                snackbarTextBuilder.append(" ");
            }
        }
        int titleEnd = snackbarTextBuilder.length();
        int bodyStart = snackbarTextBuilder.length();
        if (this.body != null) {
            snackbarTextBuilder.append(this.body);
        }
        int bodyEnd = snackbarTextBuilder.length();
        snackbarTextBuilder.setSpan(circularBookSpan, titleStart, titleEnd, 17);
        snackbarTextBuilder.setSpan(circularLightSpan, bodyStart, bodyEnd, 17);
        if (this.error) {
            snackbarTextBuilder.setSpan(errorColorSpan, titleStart, titleEnd, 17);
            snackbarTextBuilder.setSpan(mainColorSpan, bodyStart, bodyEnd, 17);
        } else {
            snackbarTextBuilder.setSpan(mainColorSpan, titleStart, bodyEnd, 17);
        }
        return snackbarTextBuilder;
    }

    private static void setBackgroundColor(Snackbar snackbar2, int color) {
        snackbar2.getView().setBackgroundColor(color);
    }
}
