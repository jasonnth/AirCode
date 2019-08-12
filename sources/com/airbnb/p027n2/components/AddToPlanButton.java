package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.AddToPlanButton */
public class AddToPlanButton extends LinearLayout {
    @BindColor
    int actionableTextColor;
    @BindColor
    int invertedTextColor;
    @BindDrawable
    Drawable selectedBackground;
    @BindView
    AirTextView subtitle;
    @BindView
    AirTextView title;
    @BindDrawable
    Drawable unselectedBackground;

    public AddToPlanButton(Context context) {
        super(context);
        init(context, null);
    }

    public AddToPlanButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AddToPlanButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(1);
        inflate(context, R.layout.n2_add_to_plan_button, this);
        ButterKnife.bind((View) this);
        setupAttributes(context, attrs);
    }

    private void setupAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.n2_AddToPlanButton);
        setTitle((CharSequence) a.getString(R.styleable.n2_AddToPlanButton_n2_titleText));
        setOptionalSubtitleText((CharSequence) a.getString(R.styleable.n2_AddToPlanButton_n2_subtitleText));
        setSelected(a.getBoolean(R.styleable.n2_AddToPlanButton_n2_selected, false));
        a.recycle();
    }

    public void setTitle(CharSequence text) {
        this.title.setText(text);
    }

    public void setTitle(int stringId) {
        setTitle((CharSequence) getResources().getString(stringId));
    }

    public void setOptionalSubtitleText(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.subtitle, !TextUtils.isEmpty(text));
        this.subtitle.setText(text);
    }

    public void setOptionalSubtitleText(int stringId) {
        setOptionalSubtitleText((CharSequence) getResources().getString(stringId));
    }

    public void setSelected(boolean selected) {
        setBackground(selected ? this.selectedBackground : this.unselectedBackground);
        int textColor = selected ? this.invertedTextColor : this.actionableTextColor;
        this.title.setTextColor(textColor);
        this.subtitle.setTextColor(textColor);
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setAlpha(enabled ? 1.0f : 0.3f);
    }

    public static void mock(AddToPlanButton view) {
        view.setTitle((CharSequence) "Title");
    }

    public static void mock2(AddToPlanButton view) {
        view.setTitle((CharSequence) "Title");
        view.setOptionalSubtitleText((CharSequence) "Subtitle");
    }

    public static void mock3(AddToPlanButton view) {
        view.setTitle((CharSequence) "Title");
        view.setOptionalSubtitleText((CharSequence) "Subtitle");
        view.setSelected(true);
    }

    public static void mock4(AddToPlanButton view) {
        view.setTitle((CharSequence) "Title");
        view.setOptionalSubtitleText((CharSequence) "Subtitle");
        view.setEnabled(false);
    }
}
