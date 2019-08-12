package com.airbnb.p027n2.components;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.StandardsBarContent;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.BarRow */
public final class BarRow extends BaseDividerComponent {
    @BindView
    TextView progressLabel;
    @BindView
    StandardsBarContent standardsBarContent;
    @BindView
    TextView subtitle;
    @BindView
    TextView title;

    public BarRow(Context context) {
        super(context);
    }

    public BarRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BarRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_standards_bar;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.enableTransitionType(4);
        setLayoutTransition(layoutTransition);
        setupAttributes(attrs);
    }

    public void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_BarRow, 0, 0);
        setProgressLabelVisible(a.getBoolean(R.styleable.n2_BarRow_n2_showProgressLabel, true));
        setTitle(a.getText(R.styleable.n2_BarRow_n2_titleText));
        a.recycle();
    }

    public void setTitle(CharSequence text) {
        this.title.setText(text);
    }

    public void setSubtitle(CharSequence text) {
        ViewLibUtils.bindOptionalTextView(this.subtitle, text);
    }

    public void setFilledSectionColor(int colorRes) {
        this.standardsBarContent.setFilledSectionColor(colorRes);
    }

    public void setValue(float percentage) {
        this.standardsBarContent.setValue(percentage);
    }

    public void setThreshold(float percentage) {
        this.standardsBarContent.setThreshold(percentage);
    }

    public void setProgressLabel(CharSequence label) {
        this.progressLabel.setText(label);
    }

    public void setProgressLabelVisible(boolean visible) {
        ViewLibUtils.setVisibleIf(this.progressLabel, visible);
    }

    public void setThresholdIndicatorVisible(boolean visible) {
        this.standardsBarContent.setThresholdIndicatorVisible(visible);
    }

    public static void mockAboveStandard(BarRow row) {
        row.setTitle("Title");
        row.setValue(0.88f);
        row.setThreshold(0.7f);
        row.setProgressLabel("88%");
        row.setFilledSectionColor(R.color.n2_lima);
    }

    public static void mockStandard(BarRow row) {
        row.setTitle("Title");
        row.setValue(0.7f);
        row.setThreshold(0.7f);
        row.setProgressLabel("70%");
        row.setFilledSectionColor(R.color.n2_beach_light);
    }

    public static void mockBelowStandard(BarRow row) {
        row.setTitle("Title");
        row.setValue(0.25f);
        row.setThreshold(0.7f);
        row.setProgressLabel("25%");
        row.setFilledSectionColor(R.color.n2_need_a_color_from_design_3);
    }

    public static void mockSubtitleAboveStandard(BarRow row) {
        row.setTitle("Title");
        row.setSubtitle("Up to 3 lines of text. Up to 3 lines of text. Up to 3 lines of text. Up to 3 lines of text. Up to 3 lines of text.");
        row.setValue(0.88f);
        row.setThreshold(0.7f);
        row.setProgressLabel("88%");
        row.setFilledSectionColor(R.color.n2_lima);
    }

    public static void mockSubtitleStandard(BarRow row) {
        row.setTitle("Title");
        row.setSubtitle("Up to 3 lines of text. Up to 3 lines of text. Up to 3 lines of text. Up to 3 lines of text. Up to 3 lines of text.");
        row.setValue(0.7f);
        row.setThreshold(0.7f);
        row.setProgressLabel("70%");
        row.setFilledSectionColor(R.color.n2_beach_light);
    }

    public static void mockSubtitleBelowStandard(BarRow row) {
        row.setTitle("Title");
        row.setSubtitle("Up to 3 lines of text. Up to 3 lines of text. Up to 3 lines of text. Up to 3 lines of text. Up to 3 lines of text.");
        row.setValue(0.25f);
        row.setThreshold(0.7f);
        row.setProgressLabel("25%");
        row.setFilledSectionColor(R.color.n2_need_a_color_from_design_3);
    }
}
