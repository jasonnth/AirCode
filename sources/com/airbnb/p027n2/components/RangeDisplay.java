package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.RangeDisplay */
public class RangeDisplay extends LinearLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    AirTextView endSubtitleText;
    @BindView
    AirTextView endTitleText;
    @BindView
    AirTextView startSubtitleText;
    @BindView
    AirTextView startTitleText;

    public RangeDisplay(Context context) {
        super(context);
        init(null);
    }

    public RangeDisplay(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RangeDisplay(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_range_display, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        setOrientation(1);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_RangeDisplay, 0, 0);
        String startTitleText2 = a.getString(R.styleable.n2_RangeDisplay_n2_startTitleText);
        String startSubtitleText2 = a.getString(R.styleable.n2_RangeDisplay_n2_startSubtitleText);
        String endTitleText2 = a.getString(R.styleable.n2_RangeDisplay_n2_endTitleText);
        String endSubtitleText2 = a.getString(R.styleable.n2_RangeDisplay_n2_endSubtitleText);
        boolean invertColors = a.getBoolean(R.styleable.n2_RangeDisplay_n2_invertColors, false);
        setStartTitle((CharSequence) startTitleText2);
        setStartSubtitle((CharSequence) startSubtitleText2);
        setEndTitle((CharSequence) endTitleText2);
        setEndSubtitle((CharSequence) endSubtitleText2);
        invertColors(invertColors);
        a.recycle();
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.startTitleText.setEnabled(enabled);
        this.startSubtitleText.setEnabled(enabled);
        this.endTitleText.setEnabled(enabled);
        this.endSubtitleText.setEnabled(enabled);
    }

    public void setStartTitle(CharSequence text) {
        this.startTitleText.setText(text);
    }

    public void setStartTitle(int stringId) {
        setStartTitle((CharSequence) getResources().getString(stringId));
    }

    public void setStartTitleHint(CharSequence text) {
        this.startTitleText.setHint(text);
    }

    public void setStartTitleHint(int stringId) {
        setStartTitleHint((CharSequence) getResources().getString(stringId));
    }

    public void setStartSubtitle(CharSequence text) {
        this.startSubtitleText.setText(text);
    }

    public void setStartSubtitle(int stringId) {
        setStartSubtitle((CharSequence) getResources().getString(stringId));
    }

    public void setStartSubtitleHint(CharSequence text) {
        this.startSubtitleText.setHint(text);
    }

    public void setStartSubtitleHint(int stringId) {
        setStartSubtitleHint((CharSequence) getResources().getString(stringId));
    }

    public void setEndTitle(CharSequence text) {
        this.endTitleText.setText(text);
    }

    public void setEndTitle(int stringId) {
        setEndTitle((CharSequence) getResources().getString(stringId));
    }

    public void setEndTitleHint(CharSequence text) {
        this.endTitleText.setHint(text);
    }

    public void setEndTitleHint(int stringId) {
        setEndTitleHint((CharSequence) getResources().getString(stringId));
    }

    public void setEndSubtitle(CharSequence text) {
        this.endSubtitleText.setText(text);
    }

    public void setEndSubtitle(int stringId) {
        setEndSubtitle((CharSequence) getResources().getString(stringId));
    }

    public void setEndSubtitleHint(CharSequence text) {
        this.endSubtitleText.setHint(text);
    }

    public void setEndSubtitleHint(int stringId) {
        setEndSubtitleHint((CharSequence) getResources().getString(stringId));
    }

    public void invertColors(boolean invertColors) {
        this.startTitleText.setHasInvertedColors(invertColors);
        this.startSubtitleText.setHasInvertedColors(invertColors);
        this.endTitleText.setHasInvertedColors(invertColors);
        this.endSubtitleText.setHasInvertedColors(invertColors);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(RangeDisplay display) {
        display.setStartTitle((CharSequence) "Start title");
        display.setStartSubtitle((CharSequence) "Start subtitle");
        display.setEndTitle((CharSequence) "End title");
        display.setEndSubtitle((CharSequence) "End subtitle");
    }
}
