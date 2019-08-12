package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.StandardButtonRow */
public class StandardButtonRow extends LinearLayout implements DividerView {
    private ButtonStyle buttonStyle;
    @BindView
    View divider;
    @BindView
    AirButton primaryButton;
    @BindView
    AirButton secondaryButton;
    @BindView
    AirTextView subtitleView;
    @BindView
    AirTextView titleView;

    /* renamed from: com.airbnb.n2.components.StandardButtonRow$ButtonStyle */
    public enum ButtonStyle {
        Primary,
        Secondary
    }

    public StandardButtonRow(Context context) {
        super(context);
        init(null);
    }

    public StandardButtonRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public StandardButtonRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_standard_button_row, this);
        ButterKnife.bind((View) this);
        setButtonStyle(ButtonStyle.Primary);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_StandardButtonRow, 0, 0);
        String titleText = a.getString(R.styleable.n2_StandardButtonRow_n2_titleText);
        String subtitleText = a.getString(R.styleable.n2_StandardButtonRow_n2_subtitleText);
        String buttonText = a.getString(R.styleable.n2_StandardButtonRow_n2_buttonText);
        boolean dividerVisible = a.getBoolean(R.styleable.n2_StandardButtonRow_n2_showDivider, true);
        a.recycle();
        setTitle((CharSequence) titleText);
        setSubtitle((CharSequence) subtitleText);
        setButtonText((CharSequence) buttonText);
        showDivider(dividerVisible);
    }

    public void setTitle(CharSequence text) {
        this.titleView.setText(text);
    }

    public void setTitle(int stringId) {
        setTitle((CharSequence) getResources().getString(stringId));
    }

    public void setSubtitle(CharSequence text) {
        this.subtitleView.setText(text);
    }

    public void setSubtitle(int stringId) {
        setSubtitle((CharSequence) getResources().getString(stringId));
    }

    public void setButtonText(CharSequence text) {
        this.primaryButton.setText(text);
        this.secondaryButton.setText(text);
    }

    public void setButtonText(int stringRes) {
        setButtonText((CharSequence) getResources().getString(stringRes));
    }

    public ButtonStyle getButtonStyle() {
        return this.buttonStyle;
    }

    public void setButtonStyle(ButtonStyle buttonStyle2) {
        this.buttonStyle = buttonStyle2;
        updateButtonState();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.primaryButton.setOnClickListener(listener);
        this.secondaryButton.setOnClickListener(listener);
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        float alpha = enabled ? 1.0f : 0.3f;
        this.titleView.setAlpha(alpha);
        this.subtitleView.setAlpha(alpha);
        updateButtonState();
    }

    private void updateButtonState() {
        boolean z;
        boolean z2 = true;
        AirButton airButton = this.primaryButton;
        if (getButtonStyle() != ButtonStyle.Primary || !isEnabled()) {
            z = false;
        } else {
            z = true;
        }
        ViewLibUtils.setVisibleIf(airButton, z);
        AirButton airButton2 = this.secondaryButton;
        if (getButtonStyle() == ButtonStyle.Primary || !isEnabled()) {
            z2 = false;
        }
        ViewLibUtils.setVisibleIf(airButton2, z2);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(StandardButtonRow view) {
        view.setTitle((CharSequence) "Title");
        view.setSubtitle((CharSequence) "Subtitle");
        view.setButtonText((CharSequence) "Button");
    }
}
