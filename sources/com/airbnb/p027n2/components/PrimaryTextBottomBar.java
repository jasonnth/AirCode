package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p000v4.content.ContextCompat;
import android.support.p002v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.airmapview.AirMapInterface;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.PrimaryTextBottomBar */
public class PrimaryTextBottomBar extends LinearLayout {
    @BindView
    AirButton button;
    @BindView
    AirTextView description;
    @BindView
    View dividerView;

    public PrimaryTextBottomBar(Context context) {
        super(context);
        init(null);
    }

    public PrimaryTextBottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PrimaryTextBottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {
        inflate(getContext(), R.layout.n2_primary_text_bottom_bar, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
        if (attributeSet != null) {
            setUpAttributes(attributeSet);
        }
    }

    private void setUpAttributes(AttributeSet attributes) {
        TypedArray ta = getContext().obtainStyledAttributes(attributes, R.styleable.n2_PrimaryTextBottomBar);
        this.button.setText(ta.getString(R.styleable.n2_PrimaryTextBottomBar_n2_buttonText));
        this.description.setText(ta.getString(R.styleable.n2_PrimaryTextBottomBar_n2_text));
        setStyle(ta.getInt(R.styleable.n2_PrimaryTextBottomBar_n2_bottomBarStyle, 2));
        ta.recycle();
    }

    public void setStyle(int style) {
        switch (style) {
            case 1:
                this.button.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.n2_button_bar_button_background));
                this.button.setTextColor(-1);
                setBackgroundColor(-1);
                this.description.setTextColor(AirMapInterface.CIRCLE_BORDER_COLOR);
                this.dividerView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.n2_divider_color));
                return;
            case 2:
                this.button.setBackgroundColor(0);
                this.button.setTextColor(-1);
                setBackgroundColor(0);
                this.description.setTextColor(-1);
                this.dividerView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.n2_white_10));
                return;
            case 3:
                this.button.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.n2_rausch_button_background));
                this.button.setTextColor(-1);
                this.button.setTextAppearance(getContext(), R.style.n2_SmallText_Inverse);
                setBackgroundColor(-1);
                this.description.setTextColor(AirMapInterface.CIRCLE_BORDER_COLOR);
                this.dividerView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.n2_divider_color));
                return;
            default:
                throw new IllegalStateException("Unknown primary text bottom bar style " + style);
        }
    }

    public void setText(int textRes) {
        this.button.setText(textRes);
    }

    public void setEnabled(boolean enabled) {
        this.button.setEnabled(enabled);
    }

    public void setDescription(CharSequence descriptionText) {
        this.description.setText(descriptionText);
    }

    public void setButtonText(CharSequence buttonText) {
        this.button.setText(buttonText);
    }

    public void setButtonText(int buttonTextRes) {
        this.button.setText(buttonTextRes);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.button.setOnClickListener(listener);
    }

    public void setOptionalText(String optionalText) {
        this.description.setText(optionalText);
    }

    public static void mockBabu(PrimaryTextBottomBar view) {
        view.setStyle(1);
        view.setDescription("Katie requires a minimum stay of 2 nights.");
        view.setButtonText((CharSequence) "Save");
    }

    public static void mockBabuDisabled(PrimaryTextBottomBar view) {
        view.setStyle(1);
        view.setDescription("Katie requires a minimum stay of 2 nights.");
        view.setButtonText((CharSequence) "Save");
        view.setEnabled(false);
    }

    public static void mockTransparent(PrimaryTextBottomBar view) {
        view.setStyle(2);
        view.setDescription("Katie requires a minimum stay of 2 nights.");
        view.setButtonText((CharSequence) "Save");
    }
}
