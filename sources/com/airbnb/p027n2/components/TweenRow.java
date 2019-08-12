package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirSwitch;
import com.airbnb.p027n2.primitives.AirSwitch.OnCheckedChangeListener;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.RowMeasurementUtils;
import com.airbnb.p027n2.utils.ViewLibUtils;

@Deprecated
/* renamed from: com.airbnb.n2.components.TweenRow */
public class TweenRow extends LinearLayout {
    @BindView
    AirTextView inputText;
    @BindDimen
    int minInputTextWidth;
    @BindView
    View sectionDivider;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirSwitch switchView;
    @BindView
    ViewGroup textContainer;
    @BindView
    AirTextView titleText;

    public TweenRow(Context context) {
        super(context);
        init(null);
    }

    public TweenRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TweenRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_tween_row, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        setOrientation(1);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.n2_TweenRow, 0, 0);
        String titleString = ta.getString(R.styleable.n2_TweenRow_n2_titleText);
        String subtitleString = ta.getString(R.styleable.n2_TweenRow_n2_subtitleText);
        String inputString = ta.getString(R.styleable.n2_TweenRow_n2_inputText);
        boolean showDivider = ta.getBoolean(R.styleable.n2_TweenRow_n2_showDivider, true);
        int maxLines = ta.getInteger(R.styleable.n2_TweenRow_n2_maxLines, 0);
        boolean hasSwitch = ta.getBoolean(R.styleable.n2_TweenRow_n2_hasSwitch, false);
        setTitle((CharSequence) titleString);
        setSubtitleText((CharSequence) subtitleString);
        setInputText((CharSequence) inputString);
        setBottomSectionDividerVisible(showDivider);
        setHasSwitch(hasSwitch);
        if (maxLines > 0) {
            this.subtitleText.setMaxLines(maxLines);
        }
        ta.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (RowMeasurementUtils.resizeRowText(this.textContainer, this.titleText, this.inputText, this.minInputTextWidth)) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitle(int textRes) {
        setTitle((CharSequence) getResources().getString(textRes));
    }

    public void setInputText(CharSequence text) {
        setInputTextVisible(!TextUtils.isEmpty(text));
        this.inputText.setText(text);
    }

    public void setInputText(int textRes) {
        setInputText((CharSequence) getResources().getString(textRes));
    }

    public void setInputTextVisible(boolean enable) {
        ViewLibUtils.setVisibleIf(this.inputText, enable);
    }

    public void setHasSwitch(boolean hasSwitch) {
        ViewLibUtils.setVisibleIf(this.switchView, hasSwitch);
    }

    public void setChecked(boolean checked) {
        this.switchView.setChecked(checked);
    }

    public void setSwitchListener(OnCheckedChangeListener listener) {
        this.switchView.setOnCheckedChangeListener(listener);
    }

    public void setSubtitleText(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.subtitleText, !TextUtils.isEmpty(text));
        this.subtitleText.setText(text);
    }

    public void setSubtitleText(int textRes) {
        setSubtitleText((CharSequence) getResources().getString(textRes));
    }

    public void setBottomSectionDividerVisible(boolean show) {
        ViewLibUtils.setVisibleIf(this.sectionDivider, show);
    }

    public static void mock(TweenRow row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitleText((CharSequence) "Subtitle");
        row.setInputText((CharSequence) "Input");
    }
}
